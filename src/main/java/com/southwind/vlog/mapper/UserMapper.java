package com.southwind.vlog.mapper;

import com.southwind.vlog.model.enity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author admin
 * @Date 2020/12/5
 */

public interface UserMapper {
    /**
     *
     * @param user 入参user 对象
     * @throws SQLException 异常
     */
    @Insert("INSERT INTO t_user (phone,password,nickname,avatar,gender,birthday,address,create_time)"+
            "values (#{phone},#{password},#{nickname},#{avatar},#{gender},#{birthday},#{address},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(User user) throws SQLException;

    /**
     *
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
    User findUserByPhone(@Param("phone")String phone)throws SQLException;


}
