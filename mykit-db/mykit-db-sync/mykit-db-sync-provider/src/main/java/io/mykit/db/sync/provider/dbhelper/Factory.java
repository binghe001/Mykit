package io.mykit.db.sync.provider.dbhelper;

import io.mykit.db.sync.provider.dbhelper.impl.MySql;
import io.mykit.db.sync.provider.dbhelper.impl.SqlServer;

public class Factory {
	public static DbHelper create(String type) {
		if (type.toLowerCase().equals("sqlserver")) {
			return new SqlServer();
		} else if (type.toLowerCase().equals("mysql")) {
			return new MySql();
		}
		return null;
	}
}
