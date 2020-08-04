package com.foton.sqlSession;

import com.foton.pojo.Configuration;
import com.foton.pojo.MappedStatement;

import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration,MappedStatement mappedStatement,Object... params) throws Exception;

}
