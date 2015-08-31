package com.tradeshift.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DAOUnitBase {

    private static final Logger logger = LoggerFactory.getLogger(DAOUnitBase.class);

    protected static EmbeddedDatabase database;

    @BeforeClass
    public static void beforeClass()
    {
        logger.info("instantiating Jdbc stuff");
        database = new EmbeddedDatabaseBuilder().addScript("schema.sql").build();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        database.shutdown();
    }

}
