package com.southwind.vlog.mapper;

import com.southwind.vlog.model.enity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author admin
 * @Date 2020/12/5
 */

public interface UserMapper {

    /**
     * 查找对象
     * @param phone 手机号
     * @return User 用户对象
     * @throws SQLException 异常
     */
    @Select({"<script>",
            "SELECT * FROM t_user",
            "where 1=1",
            "<when test='phone!=null'>",
            "and phone =#{phone}",
            "</when>",
            "</script>"})
    User findUserByPhone(@Param("phone") String phone) throws SQLException;

    /**
     * 上传用户信息
     * @param user 用户对象
     * @throws SQLException 异常
     */
    @Update("UPDATE t_user SET password=#{password},nickname=#{nickname},avatar=#{avatar},gender=#{gender},birthday=#{birthday},address=#{address}" +
            " where phone=#{phone}")
    void updateUser(User user) throws SQLException;

    /**
     * 根据微信OpenId查询用户信息
     * @param wxOpenId
     * @return
     * @throws SQLException
     */
    @Select({"<script>",
            "select *from t_user",
            "where 1=1",
            "<when test='wxOpenId!=null'>",
            "AND wx_openid=#{wxOpenId}",
            "</when>",
            "</script>"})
    User fineUserByOpenId(@Param("wxOpenId")String wxOpenId)throws SQLException;

    /**
     * 新增用户，并返回自增主键
     * @param user
     * @throws SQLException
     */
    @Insert("Insert into t_user (wx_openid,phone,nickname,avatar,create_time) Values(#{wxOpenId},#{phone},#{nickname},#{avatar},#{createTime}) ")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(User user) throws SQLException;

}
