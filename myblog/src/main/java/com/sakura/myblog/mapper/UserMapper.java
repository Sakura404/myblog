package com.sakura.myblog.mapper;

import com.sakura.myblog.model.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Sakura
 */
@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert into mb_users (user_account,user_password,user_name,user_created,user_modified,user_status,user_email) values (#{account},#{password},#{name},#{created},#{modified},#{status},#{email} )")
    public int addUser(User user);

    @Select("Select user_id, user_account,user_password,user_name,user_created,user_modified,user_status,user_email from mb_users  where user_account = #{account}")
    @Results(id = "UserMap", value = {
            @Result(column = "user_id", property = "id"),
            @Result(column = "user_account", property = "account"),
            @Result(column = "user_password", property = "password"),
            @Result(column = "user_name", property = "name"),
            @Result(column = "user_created", property = "created"),
            @Result(column = "user_modified", property = "modified"),
            @Result(column = "user_status", property = "status"),
            @Result(column = "user_email", property = "email")
    })
    public User findUserByAccount(String account);

    @ResultMap(value = "UserMap")
    @Select("Select user_id,user_account,user_name,user_created,user_modified,user_status,user_email from mb_users where user_email = #{email}")
    public User findUserByEmail(String email);

    @ResultMap(value = "UserMap")
    @Select("Select user_id,user_account,user_name,user_created,user_modified,user_status,user_email from mb_users where user_id = #{id}")
    public User findUserById(int id);
}

