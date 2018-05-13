package io.mykit.db.sync.provider.dbhelper;

import java.sql.Connection;
import java.sql.SQLException;

import io.mykit.db.sync.provider.entity.JobInfo;


public interface DbHelper {
	public String assembleSQL(String paramString, Connection paramConnection, JobInfo paramJobInfo) throws SQLException;
	public void executeSQL(String sql, Connection conn) throws SQLException;

}
