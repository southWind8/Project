package com.southwind.vlog.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.southwind.vlog.mapper.UserMapper;
import com.southwind.vlog.model.dto.LoginDto;
import com.southwind.vlog.model.dto.PhoneLoginDto;
import com.southwind.vlog.model.dto.WxLoginDto;
import com.southwind.vlog.model.enity.User;
import com.southwind.vlog.service.RedisService;
import com.southwind.vlog.service.UserService;
import com.southwind.vlog.utils.AliyunResource;
import com.southwind.vlog.utils.FileResource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerError;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private AliyunResource aliyunResource;
    @Resource
    private FileResource fileResource;
    @Override
    public boolean login(LoginDto loginDto) {
        User user = getUser(loginDto.getPhone());
        if (user!=null){
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        User user = null;
        try {
            user= userMapper.findUserByPhone(phone);
        } catch (SQLException throwables) {
            System.err.println("根据手机号查找用户出现异常");
        }
        return user;
    }

    @Override
    public boolean phoneLogin(PhoneLoginDto phoneLoginDto) {
        //无论是否存在该手机号,均先校正验证码,通过再分两种情况处理为登录和注册
        //检查redis中是否存在该手机号的记录
        boolean flag = redisService.existsKey(phoneLoginDto.getPhone());
        if (flag) {
            //取出redis中之前存储的验证码
            String saveCode = redisService.getValue(phoneLoginDto.getPhone(), String.class);
            //和前端传的验证码比对,比对成功
            if (saveCode.equals(phoneLoginDto.getCode())) {
                //查找数据库该手机号用户是否存在
                User user = getUser(phoneLoginDto.getPhone());
                //存在就登录
                if (user != null) {
                    return true;
                }else {
                    //不存在该手机号,就构建新用户记录,补充必备字段写入数据库,一键注册并且登录(密码留空,用户可后期修改)
                    User user1 = User.builder()
                            .phone(phoneLoginDto.getPhone())
                            .nickname("新用户")
                            .avatar("/static/default.jpg")
                            .createTime(LocalDateTime.now())
                            .build();
                    try {
                        userMapper.insert(user1);
                        return true;
                    } catch (SQLException throwable) {
                        System.err.println("新增用户出现异常");
                    }

                }
                return false;
            }
        }
        return  false;
    }

    @Override
    public User updateUser(User user) {
//先查出数据库原用户信息
        User saveUser = getUser(user.getPhone());
//密码字段，如果是修改密码的请求，需要将传来的密码加密
        if(!user.getPassword().equals(saveUser.getPassword())){
            saveUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }else {
            //否则就是修改其他信息，密码直接赋值，以免被覆盖为空
            saveUser.setPassword(user.getPassword());
        }
        saveUser.setNickname(user.getNickname());
        saveUser.setAvatar(user.getAvatar());
        saveUser.setGender(user.getGender());
        saveUser.setBirthday(user.getBirthday());
        saveUser.setAddress(user.getAddress());
        //更新数据
        try {
            userMapper.updateUser(saveUser);
        } catch (SQLException throwable) {
            System.err.println("修改用户信息出现异常");
        }
        //将修改后的用户信息返回
        return saveUser;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        //读取配置文件信息
        String endpoint=fileResource.getEndpoint();
        String accessKeyId=aliyunResource.getAccessKeyId();
        String accessKeySecret=aliyunResource.getAccessKeySecret();
        //创建OSSClient实例
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        String fileName=file.getOriginalFilename();
        //分割文件名，获得文件名后缀
        assert fileName !=null;
        String[] fileNameArr=fileName.split(" \\.");
        String suffix=fileNameArr[fileNameArr.length-1];
        //拼接得到新的上传文件名
        String uplodaFileName=fileResource.getObjectName()+ UUID.randomUUID().toString()+"."+suffix;
        //上传网络需要的字节流
        InputStream inputStream=null;
        try{
            inputStream=file.getInputStream();
        }catch (IOException e){
            System.err.println("上传文件出现异常");
        }
        //执行阿里云上传文件操作
        ossClient.putObject(fileResource.getBucketName(),uplodaFileName,inputStream);
        //关闭OSSClient
        ossClient.shutdown();
        return  uplodaFileName;
    }

    @Override
    public User wxLogin(WxLoginDto wxLoginDto) {
        User user=null;
        try{
            user=userMapper.fineUserByOpenId(wxLoginDto.getWxOpenId());
        }catch (SQLException throwables){
            System.err.println("根据微信openId查找用户出现异常");
        }
        //新用户
        if(user==null){
            user=User.builder()
                    .wxOpenId(wxLoginDto.getWxOpenId())
                    .nickname(wxLoginDto.getNickname())
                    .avatar(wxLoginDto.getAvatar())
                    .gender(wxLoginDto.getGender())
                    .createTime(LocalDateTime.now())
                    .build();
            try{
                userMapper.insert(user);
            }catch (SQLException throwables){
                System.err.println("新增用户出现异常");
            }
        }
        //老用户
        return user;
    }
}
