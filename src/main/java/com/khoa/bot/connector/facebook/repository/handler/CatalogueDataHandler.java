package com.khoa.bot.connector.facebook.repository.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khoa.bot.connector.facebook.entity.CatalogueData;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogueDataHandler extends BaseTypeHandler<CatalogueData> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CatalogueData parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, OBJECT_MAPPER.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CatalogueData getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return OBJECT_MAPPER.readValue(rs.getString(columnName), CatalogueData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new CatalogueData();
    }

    @Override
    public CatalogueData getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return OBJECT_MAPPER.readValue(rs.getString(columnIndex), CatalogueData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new CatalogueData();
    }

    @Override
    public CatalogueData getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return OBJECT_MAPPER.readValue(cs.getString(columnIndex), CatalogueData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new CatalogueData();
    }
}
