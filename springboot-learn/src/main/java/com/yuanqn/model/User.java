package com.yuanqn.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: mybatis-first
 * <p>
 * Created by yuanqn on 2019/3/12 22:42
 */
@Data
public class User implements Serializable {

    //id
    private Integer id;
    //用户姓名
    private String username;
    //性别
    private String sex;
    //生日
    private Date birthday;
    //地址
    private String address;
    //订单列表
    private List<Order> orderList;
}
