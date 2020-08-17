package com.foton.test;

import com.foton.dao.IUserDao;
import com.foton.io.Resources;
import com.foton.pojo.User;
import com.foton.sqlSession.SqlSession;
import com.foton.sqlSession.SqlSessionFactory;
import com.foton.sqlSession.SqlSessionFactoryBuilder;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setU_id(1);
        user.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.selectAll();
        for (User user1 : all) {
            System.out.println(user1);
        }


    }
    
    @Test
    public void test_add() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setU_id(4);
        user.setUsername("赵六");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

       
        System.out.println(userDao.insertItem(user));
    }
    
    @Test
    public void test_delete() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setU_id(4);
        user.setUsername("赵六");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

       
        System.out.println(userDao.deleteItem(user));
    }
    
    @Test
    public void test_update() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setU_id(3);
        user.setUsername("xxxxxx");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

       
        System.out.println(userDao.updateItem(user));
    }



}
