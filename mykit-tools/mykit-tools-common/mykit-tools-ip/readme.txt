# 作者
Adam Lu(刘亚壮)

# 项目简介
mykit-tools-ip是mykit架构中通用工具集模块中与IP相关的工具插件

# 具体实现
通过Java操作qqwry ip数据库实现：
	1) 程序入口是：io.mykit.tools.ip.qqwry.wrapper.QQWryIPWrapper
	2) 将classpath下的qqwry.dat文件放到某个目录下，比如：d:/qqwry.dat
	3) 然后修改io.mykit.tools.ip.qqwry.wrapper.QQWryIPWrapper类中的常量如下：
		public static final String FILE_PATH = "d:/qqwry.dat";

# 使用方法
	调用：io.mykit.tools.ip.qqwry.wrapper.QQWryIPWrapper中的
	getAddressByIP(String ip, String filePath)或者getAddressByIP(String ip)传入ip和qqwry.dat路径或者ip地址即可。
