package io.mykit.tools.ip.qqwry.wrapper;

import io.mykit.tools.ip.qqwry.IPSeeker;

/**
 * 包装类
 * @author liuyazhuang
 *
 */
public class QQWryIPWrapper {
	
	public static final String FILE_PATH = "d:/qqwry.dat";
	
	/**
	 * 根据IP获取信息
	 * @param ip
	 * @param filePath
	 * @return
	 */
	public static String getAddressByIP(String ip, String filePath){
		IPSeeker seeker = IPSeeker.getInstance(filePath);
		return seeker.getAddress(ip);
	}
	
	/**
	 * 根据IP获取信息
	 * @param ip
	 * @return
	 */
	public static String getAddressByIP(String ip){
		IPSeeker seeker = IPSeeker.getInstance(FILE_PATH);
		return seeker.getAddress(ip);
	}
	public static void main(String[] args) {
		System.out.println(getAddressByIP("10.2.2.2"));
	}
}
