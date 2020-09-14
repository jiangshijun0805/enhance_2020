package com.xiaojiang.jdbc.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 转换数据库行到对象的接口
 */
public interface RowMapper<T> {
    public T getEntity(ResultSet resultSet) throws SQLException;
}
