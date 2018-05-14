package io.mykit.db.sync;

import io.mykit.db.sync.build.DBSyncBuilder;


/**
 * 程序入口
 * @author liuyazhuang
 *
 */
public class Main {

	public static void main(String[] args) {
		DBSyncBuilder.builder().init().start();
	}
}
