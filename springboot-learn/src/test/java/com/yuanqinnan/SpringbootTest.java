package com.yuanqinnan;

import com.yuanqinnan.entities.Department;
import com.yuanqinnan.entities.Order;
import com.yuanqinnan.mapper.DepartmentMapper;
import com.yuanqinnan.mapper.UserMapper;
import com.yuanqinnan.model.User;
import com.yuanqinnan.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author yuanqn
 * @date 2019/4/1921:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void jdbcTest(){
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from user ");
        System.out.println(mapList.get(0));
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void mybatisTest(){
        Department deptById = departmentMapper.getDeptById(1);
        System.out.println(deptById);
        User userById = userMapper.queryUserById(1);
        System.out.println(userById);
    }

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void jpaTest(){
        List<Order> all = orderRepository.findAll();
        System.out.println(all);

    }
}
