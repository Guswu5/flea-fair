package com.fleafair.Handler;

import com.fleafair.Common.Enum.ItemStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(ItemStatus.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class ItemStatusHandler extends BaseTypeHandler<ItemStatus> {
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ItemStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public ItemStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return ItemStatus.fromCode(code);
    }

    @Override
    public ItemStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        }
        return ItemStatus.fromCode(code);
    }

    @Override
    public ItemStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        }
        return ItemStatus.fromCode(code);
    }
}