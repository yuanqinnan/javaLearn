package com.yuanqinnan.mapper;

import com.yuanqinnan.model.User;
import com.yuanqinnan.pojo.QueryVo;

import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/17 10:19
 */
public interface UserMapper {

    //查询用户
    User queryUserById(int id);
    //查询用户列表
    List<User> selectUserAll();

    //模糊查询
    List<User> selectLikeUserName(String username);

    //新增
    void saveUser(User user);

    List<User> queryByQo(QueryVo queryVo);

    int queryUserCount();

}
