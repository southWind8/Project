package com.southwind.vlog.task;

import com.southwind.vlog.model.enity.Article;
import com.southwind.vlog.model.enity.ArticleTag;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.xml.bind.util.JAXBSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @ClassName ArticleTask
 * @Description TODO
 * @Author 南风
 * @Date 2020/12/15
 **/
@Component
@Slf4j
public class ArticleTask implements Callable<List<Article>> {

    private static final String BASE_URL = "https://godweiyang.com";
    private Document document = null;
    private List<Article> articleList;

    @Override
    public List<Article> call() throws Exception {
        articleList = new ArrayList<>();
        int index;
        try {
            document = Jsoup.connect(BASE_URL).get();
        } catch (IOException e) {
            log.error("连接失败");
        }

        assert document!=null;
        Element indexCard=document.getElementById("indexCard");
        Elements recommend = indexCard.select(".col");
        recommend.forEach(articleNode->{
            String id= UUID.randomUUID().toString();
            Element categoryNode = articleNode.select(".category").get(0);
            String category=categoryNode.html();

            Element titleNode=articleNode.select(".post-title").get(0);
            String title=titleNode.text();

            Element summaryNode = articleNode.select(".post-description").get(0);
            String summary=summaryNode.text();

            Element urlNode = articleNode.select(".read-more").get(0);
            String url = BASE_URL + urlNode.attr("href");

            String content=getContent(url);
            Article article=Article.builder()
                    .id(id)
                    .userId(1)
                    .title(title)
                    .category(category)
                    .cover("https://picsum.photos/1920/1080?random&rand="+Math.random())
                    .summary(summary)
                    .content(content)
                    .url(url)
                    .createTime(LocalDateTime.now())
                    .build();
            articleList.add(article);
        });

        for (index = 2; index < 19; index++) {
            try {
                document = Jsoup.connect(BASE_URL + "/page/" + index).get();
            } catch (IOException e) {
                log.error("连接失败");
            }
            assert document!=null;
            Elements articles2 = document.getElementsByClass("card");
            parseArticles(articles2);
        }

        return articleList;

    }

    private String getContent(String url){
        Document document=null;
        try {
            document=Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("连接失败");
        }
        assert document!=null;
        Element content=document.getElementById("articleContent");
        return content.html();
    }

    private void parseArticles(Elements articles) {
        for (Element articleNode : articles) {
            String id=UUID.randomUUID().toString();

            Element titleSpan = articleNode.select(".card-title").get(0);
            String title=titleSpan.text();

            Element categoryNode = articleNode.select(".post-category").get(0);
            String category=categoryNode.html();

            Element summaryNode = articleNode.select(".summary").get(0);
            String summary=summaryNode.html();

            String url = BASE_URL + articleNode.child(0).attr("href");

            Element dateNode = articleNode.select(".publish-date").get(0);
            String publishDateString=dateNode.text();

            LocalDate publishDate = LocalDate.parse(publishDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            List<ArticleTag> articleTags=new ArrayList<>();
            Elements tagNodes = articleNode.select(".article-tags").get(0).getElementsByTag("a");
            for (Element tagNode : tagNodes) {
                ArticleTag articleTag=ArticleTag.builder()
                        .articleId(id)
                        .tagName(tagNode.child(0).text())
                        .build();
                articleTags.add(articleTag);
            }

            String content = getContent(url);

            Article article=Article.builder()
                    .id(id)
                    .userId(1)
                    .title(title)
                    .category(category)
                    .cover("https://picsum.photos/1920/1080?random&rand="+Math.random())
                    .summary(summary)
                    .content(content)
                    .url(url)
                    .publishDate(publishDate)
                    .createTime(LocalDateTime.now())
                    .tagList(articleTags)
                    .build();
            articleList.add(article);

        }
    }

    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ExecutorService executor=Executors.newFixedThreadPool(2);
        ArticleTask at=new ArticleTask();
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles=future.get();
        articles.forEach(article-> System.out.println(article.getTitle()+","+article.getCategory()));
    }

}
