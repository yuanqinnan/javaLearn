package com.yuanqn;

import com.yuanqn.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Description: mybatis-first
 * <p>
 * Created by yuanqn on 2019/3/12 23:19
 */
public class CRUDTest {
    //定义 SqlSession
    SqlSession session = null;

    @Before
    public void init() {
        //定义mybatis全局配置文件
        String resource = "config/SqlMapConfig.xml";
        //加载 mybatis 全局配置文件
        InputStream inputStream = CRUDTest.class.getClassLoader()
                .getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //根据 sqlSessionFactory 产生 session
        session = sessionFactory.openSession();
    }

    //根据id查询user表数据
    @Test
    public void testSelectUserById() {
        String statement = "queryUserById";
        User user = session.selectOne(statement, 1);
        System.out.println(user);
        session.close();
    }

    //查询所有user表所有数据
    @Test
    public void testSelectUserAll() {
        String statement = "selectUserAll";
        List<User> listUser = session.selectList(statement);
        for (User user : listUser) {
            System.out.println(user);
        }
        session.close();
    }

    //模糊查询：根据 user 表的username字段(用${}实现)
    @Test
    public void testSelectLikeUserName() {
        String statement = "selectLikeUserName";
        List<User> listUser = session.selectList(statement, "三");
        for (User user : listUser) {
            System.out.println(user);
        }
        session.close();

    }

    //模糊查询：根据 user 表的username字段(用#{}实现)
    @Test
    public void testSelectLikeUserName2() {
        String statement = "selectLikeUserName2";
        List<User> listUser = session.selectList(statement, "%三%");
        for (User user : listUser) {
            System.out.println(user);
        }
        session.close();

    }

    //向 user 表中插入一条数据
    @Test
    public void testInsertUser() {
        String statement = "insertUser";
        User user = new User();
        user.setUsername("袁帅");
        user.setSex("1");
        session.insert(statement, user);
        //提交插入的数据
        session.commit();
        session.close();
    }

    @Test
    public void testInsertUser2() {
        String statement = "saveUser";
        User user = new User();
        user.setUsername("袁大帅");
        user.setSex("1");
        session.insert(statement, user);
        System.out.println(user);
        //提交插入的数据
        session.commit();
        session.close();
    }

    //根据 id 更新 user 表的数据
    @Test
    public void testUpdateUserById() {
        String statement = "updateUserById";
        //如果设置的 id不存在，那么数据库没有数据更改
        User user = new User();
        user.setId(29);
        user.setUsername("袁不帅");
        session.update(statement, user);
        session.commit();
        session.close();
    }


    //根据 id 删除 user 表的数据
    @Test
    public void testDeleteUserById() {
        String statement = "deleteUserById";
        session.delete(statement, 29);
        session.commit();
        session.close();
    }
}
