package com.yuanqinnan.pojo;

import com.yuanqinnan.model.User;
import lombok.Data;

import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/22 22:00
 */
@Data
public class QueryVo {

    private User user;

    private List<Integer> ids;
}
