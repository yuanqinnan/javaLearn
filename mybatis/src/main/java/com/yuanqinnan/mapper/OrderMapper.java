package com.yuanqinnan.mapper;

import com.yuanqinnan.model.Order;

import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/24 16:12
 */
public interface OrderMapper {
    List<Order> queryOrderAll();

    List<Order> queryOrderAll2();
}
