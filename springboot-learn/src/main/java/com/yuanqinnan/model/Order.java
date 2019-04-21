package com.yuanqinnan.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/24 16:05
 */
@Data
public class Order {

    // 订单id
    private int id;
    // 用户id
    private Integer userId;
    // 订单号
    private String number;
    // 订单创建时间
    private Date createtime;
    // 备注
    private String note;
    //客户
    private User user;
    //中间表
    private List<OrderProduct> products;

}
