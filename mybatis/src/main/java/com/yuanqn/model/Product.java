package com.yuanqn.model;


import lombok.Data;

import java.util.List;


@Data
public class Product {
    //产品id
    private Integer id;
    //产品名称
    private String name;
    //中间表
    List<OrderProduct> orders;
}