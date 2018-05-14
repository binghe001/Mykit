package io.mykit.db.sync.provider.sync.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.mykit.db.sync.provider.entity.JobInfo;
import io.mykit.db.sync.provider.sync.DBSync;
import io.mykit.db.sync.provider.utils.Tool;

/**
 * 实现MySQL同步数据库
 * @author liuyazhuang
 *
 */
public class MySQLSync extends AbstractDBSync implements DBSync {

	@Override
    public String assembleSQL(String srcSql, Connection conn, JobInfo jobInfo) throws SQLException {
        String uniqueName = Tool.generateString(6) + "_" + jobInfo.getName();
        String[] fields = jobInfo.getDestTableFields().split(",");
        fields = this.trimArrayItem(fields);
        String[] updateFields = jobInfo.getDestTableUpdate().split(",");
        updateFields = this.trimArrayItem(updateFields);
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

	@Override
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
