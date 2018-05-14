package io.mykit.db.sync.provider.sync.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import io.mykit.db.sync.provider.entity.JobInfo;
import io.mykit.db.sync.provider.sync.DBSync;

/**
 * SQLServer同步的数据库的实现
 * @author liuyazhuang
 *
 */
public class SQLServerSync extends AbstractDBSync implements DBSync {
    private Logger logger = Logger.getLogger(SQLServerSync.class);

    @Override
    public String assembleSQL(String srcSql, Connection conn, JobInfo jobInfo) throws SQLException {
        String fieldStr = jobInfo.getDestTableFields();
        String[] fields = jobInfo.getDestTableFields().split(",");
        fields = this.trimArrayItem(fields);
        String[] updateFields = jobInfo.getDestTableUpdate().split(",");
        updateFields = this.trimArrayItem(updateFields);
        String destTableKey = jobInfo.getDestTableKey();
        String destTable = jobInfo.getDestTable();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(srcSql);
        StringBuffer sql = new StringBuffer();
        long count = 0;
        while (rs.next()) {
            sql.append("if not exists (select ").append(destTableKey).append(" from ").append(destTable).append(" where ").append(destTableKey).append("='").append(rs.getString(destTableKey))
                            .append("')").append("insert into ").append(destTable).append("(").append(fieldStr).append(") values(");
            for (int index = 0; index < fields.length; index++) {
                sql.append("'").append(rs.getString(fields[index])).append(index == (fields.length - 1) ? "'" : "',");
            }
            sql.append(") else update ").append(destTable).append(" set ");
            for (int index = 0; index < updateFields.length; index++) {
                sql.append(updateFields[index]).append("='").append(rs.getString(updateFields[index])).append(index == (updateFields.length - 1) ? "'" : "',");
            }
            sql.append(" where ").append(destTableKey).append("='").append(rs.getString(destTableKey)).append("';");
            count++;
            // this.logger.info("第" + count + "耗时: " + (new Date().getTime() - oneStart) + "ms");
        }
        this.logger.info("总共查询到 " + count + " 条记录");
        if (rs != null) {
            rs.close();
        }
        if (stat != null) {
            stat.close();
        }
        return count > 0 ? sql.toString() : null;
    }
    
    @Override
    public void executeSQL(String sql, Connection conn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.executeUpdate();
        conn.commit();
        pst.close();
    }
}
