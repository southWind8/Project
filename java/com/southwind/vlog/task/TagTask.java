package com.southwind.vlog.task;

import com.southwind.vlog.model.enity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName TagTask
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/13
 **/
@Component
@Slf4j
public class TagTask implements Callable<List<Tag>> {


    @Override
    public List<Tag> call() throws Exception {
            Document document=null;
            List<Tag> tagList=new ArrayList<>(100);
            try{
                document= Jsoup.connect("https://godweiyang.com/tags/").get();
            }catch (IOException e){
                log.error("连接失败");
            }
            assert  document!=null;
            //取得了包含所有标签的div，注意因为是根据class查找，所以返回的是个集合
            Elements tagChips=document.getElementsByClass("tag-chips");
            //我们需要get(0),然后再取得所有的孩子节点，也就是所有的a标记
            Elements tags=tagChips.get(0).children();
            //遍历所有的标记
            tags.forEach(tagNode ->{
                //通过取节点属性的方法，得到博客 4这样的结果
                String title=tagNode.attr("title");
                //分割一下之前得到的内容
                title=title.split(":")[0];
                //得到a的第一个孩子节点，也就是span元素，从中获取颜色
                Element span=tagNode.child(0);
                //得到了"background-color"#F9EBEA这样的结果
                String styleContent=span.attr("style");
                //再取得子串得到颜色的值:#F9EBEA
                String color=styleContent.substring(styleContent.indexOf("#"),styleContent.length()-1);
                //构建Tag对象
                Tag tag=Tag.builder().tagName(title).tagColor(color).build();
                //加入集合结果
                tagList.add(tag);
            });
            return tagList;

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor=Executors.newFixedThreadPool(2);
        TagTask tt=new TagTask();
        Future<List<Tag>> future=executor.submit(tt);
        List<Tag> tags=future.get();
        tags.forEach(tag->System.out.println(tag.getTagName()+","+tag.getTagColor()));
    }
}
