package com.yuanqinnan.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuanqn
 * @date 2019/4/2223:11
 */

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "order") //@Table来指定和哪个数据表对应;order；
@Data
public class Order {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name = "user_Id")
    private Integer userId;
    //这是和数据表对应的一个列
    @Column(name = "number", length = 32)
    private String number;
    // 订单创建时间,省略默认列名就是属性名
    private Date createtime;
    // 备注
    private String note;
}
