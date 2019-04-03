package com.yuanqinnan.model;

import java.util.Date;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/24 16:05
 */
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


    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNumber() {
        return number;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", createtime=" + createtime +
                ", note='" + note + '\'' +
                '}';
    }
}
