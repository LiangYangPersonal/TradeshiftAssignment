package com.tradeshift.commons.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 8/30/15
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskBaseDAO<K> {
    public int insert(K k);
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
}
