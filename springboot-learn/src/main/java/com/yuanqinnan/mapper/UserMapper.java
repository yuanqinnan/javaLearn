package com.yuanqinnan.mapper;

import com.yuanqinnan.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuanqn
 * @date 2019/4/2122:58
 */
@Mapper
public interface UserMapper {

    User queryUserById(Integer id);

}
