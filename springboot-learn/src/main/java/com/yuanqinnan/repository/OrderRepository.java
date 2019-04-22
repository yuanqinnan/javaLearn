package com.yuanqinnan.repository;

import com.yuanqinnan.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yuanqn
 * @date 2019/4/2223:25
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
