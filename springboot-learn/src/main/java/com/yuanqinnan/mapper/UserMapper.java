package com.yuanqinnan.mapper;

import com.yuanqinnan.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yuanqn
 * @date 2019/4/2122:58
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    public User getUserById(Integer id);
}
