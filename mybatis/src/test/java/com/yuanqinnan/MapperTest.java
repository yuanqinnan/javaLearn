package com.yuanqinnan;

import com.yuanqinnan.mapper.OrderMapper;
import com.yuanqinnan.mapper.UserMapper;
import com.yuanqinnan.model.Order;
import com.yuanqinnan.model.User;
import com.yuanqinnan.pojo.OrderUserVO;
import com.yuanqinnan.pojo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: javalearn
 * <p>
 * Created by yuanqn on 2019/3/17 10:26
 */
public class MapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        // 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
        // 创建SqlsessionFactory
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryUserById() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        User user = userMapper.queryUserById(1);
        System.out.println(user);

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testQueryUserByUsername() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        List<User> list = userMapper.selectLikeUserName("张");
        for (User user : list) {
            System.out.println(user);
        }

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testSaveUser() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 创建保存对象
        User user = new User();
        user.setUsername("刘备");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("蜀国");
        // 执行查询方法
        userMapper.saveUser(user);
        System.out.println(user);


        // 和spring整合后由spring管理
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testQueryUserByUsername2() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("张");
        queryVo.setUser(user);
        List<User> list = userMapper.queryByQo(queryVo);
        for (User user2 : list) {
            System.out.println(user2);
        }

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testQueryUserCount() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询方法
        int count = userMapper.queryUserCount();
        System.out.println(count);

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    @Test
    public void testQueryAll() {
        // 获取sqlSession
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 获取OrderMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        // 执行查询
        List<Order> list = orderMapper.queryOrderAll();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void testQueryAll2() {
        // 获取sqlSession
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 获取OrderMapper
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        // 执行查询
        List<Order> list = orderMapper.queryOrderAll2();
        for (Order order : list) {
            System.out.println(order);
        }
    }

    @Test
    public void testQueryUserByWhere() {
        // mybatis和spring整合，整合之后，交给spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 创建Mapper接口的动态代理对象，整合之后，交给spring管理
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 使用userMapper执行根据条件查询用户
        User user = new User();
        //user.setSex("1");
        user.setUsername("张");
        List<User> list = userMapper.queryUserByWhere(user);
        for (User u : list) {
            System.out.println(u);
        }
        // mybatis和spring整合，整合之后，交给spring管理
        sqlSession.close();
    }

    @Test
    public void dynamicSetTest() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        //user.setSex("1");
        user.setUsername("袁大大");
        user.setId(26);
        userMapper.dynamicSetTest(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectUserByChoose(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        //user.setSex("1");
        user.setUsername("张%");
        List<User> list = userMapper.selectUserByChoose(user);
        for (User u : list) {
            System.out.println(u);
        }
        // mybatis和spring整合，整合之后，交给spring管理
        sqlSession.close();
    }

    @Test
    public void queryUserByIds(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo user = new QueryVo();
        List<Integer>ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(24);
        user.setIds(ids);
        List<User> list = userMapper.queryUserByIds(user);
        for (User u : list) {
            System.out.println(u);
        }
        sqlSession.close();
    }

    @Test
    public void queryOrderUser(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderUserVO> list = orderMapper.queryOrderUser();
        for (OrderUserVO u : list) {
            System.out.println(u);
        }
        sqlSession.close();
    }

    @Test
    public void queryOrderUserResultMap(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> list = orderMapper.queryOrderUserResultMap();
        for (Order u : list) {
            System.out.println(u);
        }
        sqlSession.close();
    }
    @Test
    public void queryUserOrder(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.queryUserOrder();
        for (User u : list) {
            System.out.println(u);
        }
        sqlSession.close();
    }
    @Test
    public void listOrder(){
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> list = orderMapper.listOrder();
        for (Order u : list) {
            System.out.println(u);
        }
        sqlSession.close();
    }


}
