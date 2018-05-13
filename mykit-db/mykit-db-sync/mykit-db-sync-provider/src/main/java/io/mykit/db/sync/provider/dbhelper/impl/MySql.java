package io.mykit.db.sync.provider.dbhelper.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.mykit.db.sync.provider.Tool;
import io.mykit.db.sync.provider.dbhelper.DbHelper;
import io.mykit.db.sync.provider.entity.JobInfo;

public class MySql implements DbHelper {

    public String assembleSQL(String srcSql, Connection conn, JobInfo jobInfo) throws SQLException {
        String uniqueName = Tool.generateString(6) + "_" + jobInfo.getName();
        String[] fields = jobInfo.getDestTableFields().split(",");
        String[] updateFields = jobInfo.getDestTableUpdate().split(",");
        String destTable = jobInfo.getDestTable();
        String destTableKey = jobInfo.getDestTableKey();
        PreparedStatement pst = conn.prepareStatement(srcSql);
        ResultSet rs = pst.executeQuery();
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ").append(jobInfo.getDestTable()).append(" (").append(jobInfo.getDestTableFields()).append(") values ");
        long count = 0;
        while (rs.next()) {
            sql.append("(");
            for (int index = 0; index < fields.length; index++) {
                sql.append("'").append(rs.getString(fields[index])).append(index == (fields.length - 1) ? "'" : "',");
            }
            sql.append("),");
            count++;
        }
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (count > 0) {
            sql = sql.deleteCharAt(sql.length() - 1);
            if ((!jobInfo.getDestTableUpdate().equals("")) && (!jobInfo.getDestTableKey().equals(""))) {
                sql.append(" on duplicate key update ");
                for (int index = 0; index < updateFields.length; index++) {
                    sql.append(updateFields[index]).append("= values(").append(updateFields[index]).append(index == (updateFields.length - 1) ? ")" : "),");
                }
                return new StringBuffer("alter table ").append(destTable).append(" add constraint ").append(uniqueName).append(" unique (").append(destTableKey).append(");").append(sql.toString())
                                .append(";alter table ").append(destTable).append(" drop index ").append(uniqueName).toString();
            }
            return sql.toString();
        }
        return null;
    }

    public void executeSQL(String sql, Connection conn) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("");
        String[] sqlList = sql.split(";");
        for (int index = 0; index < sqlList.length; index++) {
            pst.addBatch(sqlList[index]);
        }
        pst.executeBatch();
        conn.commit();
        pst.close();
    }
}
