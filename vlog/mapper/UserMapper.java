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
     * @param user 入参user 对象
     * @throws SQLException 异常
     */
    @Insert("INSERT INTO t_user (phone,nickname,avatar,create_time)values (#{phone},#{nickname},#{avatar},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user) throws SQLException;

    /**
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
     * @param user 用户对象
     * @throws SQLException 异常
     */
    @Update("UPDATE t_user SET password=#{password},nickname=#{nickname},avatar=#{avatar},gender=#{gender},birthday=#{birthday},address=#{address}" +
            "where phone=#{phone}")
    void updateUser(User user) throws SQLException;
}
