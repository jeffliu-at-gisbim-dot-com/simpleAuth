/*
 * IBatisFactory.java
 *
 * Created on 2008�~5��22��, �W�� 10:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.gisbim.simpleAuthWeb;

import java.io.Reader;
import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * Using ibatis 3.0
 * @author angle
 *
 */
public class IBatisFactory {
    private SqlSessionFactory sqlMapper;
    private static IBatisFactory instance = new IBatisFactory();
    String resource = "com/gisbim/simpleAuthWeb/uiController/ibatisConfig.xml";
    /** Creates a new instance of IBatisFactory */
    private IBatisFactory() {
        try {
        	Reader reader = Resources.getResourceAsReader(resource);
        	sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Fail fast.
            throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
        }

    }

    public static IBatisFactory getInstance() {
        return instance;
    }

    public SqlSession getSqlSession() {
        return this.sqlMapper.openSession();
    }





}
