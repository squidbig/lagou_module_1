package com.foton.dao;

import java.util.List;

import com.foton.pojo.User;

public interface IUserDao {

    //查询所有用户
    public List<User> selectAll() throws Exception;

    //根据条件进行用户查询
    public User selectByCondition(User user) throws Exception;
    
    //添加单个用户
    public boolean insertItem(User user) throws Exception;
    
    //添加多个用户
    //public boolean addItems(List<User> users) throws Exception;
    
    //更新用户
    public boolean updateItem(User user) throws Exception;
    
    //删除用户
    public boolean deleteItem(User user) throws Exception;


}
