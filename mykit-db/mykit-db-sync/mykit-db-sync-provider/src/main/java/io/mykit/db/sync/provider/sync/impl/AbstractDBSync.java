package io.mykit.db.sync.provider.sync.impl;

import io.mykit.db.sync.provider.sync.DBSync;

/**
 * 执行数据库同步的抽象类
 * @author liuyazhuang
 *
 */
public abstract class AbstractDBSync implements DBSync {
	/**
	 * 去除String数组每个元素中的空格
	 * @param arr
	 * @return
	 */
	protected String[] trimArrayItem(String[] src){
		if(src == null || src.length == 0) return src;
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++){
			dest[i] = src[i].trim();
		}
		return dest;
	}
}
