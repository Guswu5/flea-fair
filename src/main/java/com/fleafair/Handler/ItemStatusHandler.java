package com.fleafair.Handler;

import com.fleafair.Common.Enum.ItemStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理ItemStatus枚举与数据库的转换
 */
public class ItemStatusHandler extends BaseTypeHandler<ItemStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    ItemStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode()); // 存code到数据库
    }

    @Override
    public ItemStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull() ? null : ItemStatus.fromCode(code);
    }

    @Override
    public ItemStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : ItemStatus.fromCode(code);
    }

    @Override
    public ItemStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : ItemStatus.fromCode(code);
    }
}