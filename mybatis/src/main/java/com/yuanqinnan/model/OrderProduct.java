package com.yuanqinnan.model;

import lombok.Data;

@Data
public class OrderProduct {

    private Integer id;
    //订单主键
    private Integer oid;
    //产品主键
    private Integer pid;
    //订单
    private Order order;
    //产品
    private Product product;
}