package io.mykit.db.sync.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import io.mykit.db.sync.provider.entity.DbInfo;
import io.mykit.db.sync.provider.entity.JobInfo;
import io.mykit.db.sync.provider.factory.DBSyncFactory;
import io.mykit.db.sync.provider.sync.DBSync;

/**
 * 同步数据库任务的具体实现
 * @author liuyazhuang
 *
 */
public class JobTask implements Job {
	private Logger logger = Logger.getLogger(JobTask.class);

	/**
	 * 执行同步数据库任务
	 *
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.logger.info("开始任务调度: " + new Date());
		Connection inConn = null;
		Connection outConn = null;
		JobDataMap data = context.getJobDetail().getJobDataMap();
		DbInfo srcDb = (DbInfo) data.get("srcDb");
		DbInfo destDb = (DbInfo) data.get("destDb");
		JobInfo jobInfo = (JobInfo) data.get("jobInfo");
		String logTitle = (String) data.get("logTitle");
		try {
			inConn = createConnection(srcDb);
			outConn = createConnection(destDb);
			if (inConn == null) {
				this.logger.info("请检查源数据连接!");
				return;
			} else if (outConn == null) {
				this.logger.info("请检查目标数据连接!");
				return;
			}

			DBSync dbHelper = DBSyncFactory.create(destDb.getDbtype());
			long start = new Date().getTime();
			String sql = dbHelper.assembleSQL(jobInfo.getSrcSql(), inConn, jobInfo);
			this.logger.info("组装SQL耗时: " + (new Date().getTime() - start) + "ms");
			if (sql != null) {
				this.logger.debug(sql);
				long eStart = new Date().getTime();
				dbHelper.executeSQL(sql, outConn);
				this.logger.info("执行SQL语句耗时: " + (new Date().getTime() - eStart) + "ms");
			}
		} catch (SQLException e) {
			this.logger.error(logTitle + e.getMessage());
			this.logger.error(logTitle + " SQL执行出错，请检查是否存在语法错误");
		} finally {
			this.logger.error("关闭源数据库连接");
			destoryConnection(inConn);
			this.logger.error("关闭目标数据库连接");
			destoryConnection(outConn);
		}
	}

	/**
	 * 创建数据库连接
	 * @param db
	 * @return
	 */
	private Connection createConnection(DbInfo db) {
		try {
			Class.forName(db.getDriver());
			Connection conn = DriverManager.getConnection(db.getUrl(), db.getUsername(), db.getPassword());
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 关闭并销毁数据库连接
	 * @param conn
	 */
	private void destoryConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
				this.logger.error("数据库连接关闭");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
