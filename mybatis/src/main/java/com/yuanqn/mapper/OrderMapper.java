package com.yuanqn.mapper;

import com.yuanqn.model.Order;
import com.yuanqn.pojo.OrderUserVO;

import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/24 16:12
 */
public interface OrderMapper {
    List<Order> queryOrderAll();

    List<Order> queryOrderAll2();

    List<OrderUserVO> queryOrderUser();

    List<Order> queryOrderUserResultMap();

    List<Order> getOrderByPorductId(int productId);

    List<Order> listOrder();
}
