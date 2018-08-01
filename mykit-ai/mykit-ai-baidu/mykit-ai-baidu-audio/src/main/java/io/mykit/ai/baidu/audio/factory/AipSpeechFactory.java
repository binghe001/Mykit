package io.mykit.ai.baidu.audio.factory;

import com.baidu.aip.speech.AipSpeech;

/**
 * 百度AI客户端工厂类
 * @author liuyazhuang
 *
 */
public class AipSpeechFactory {
	
	//设置APPID/AK/SK
    private static final String APP_ID = "11358064";
    private static final String API_KEY = "A2726VoXot1ZnhnVvCMBqUZP";
    private static final String SECRET_KEY = "Eftf1AUHMn6LVuO3UdRDgGbULcDVTGpG";
    
	private AipSpeechFactory(){}
	
	private volatile static AipSpeech aipSpeech;
	
	public static AipSpeech getInstance(){
		if(aipSpeech == null){
			synchronized (AipSpeechFactory.class) {
				if(aipSpeech == null){
					// 初始化一个AipSpeech
					aipSpeech= new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

			        // 可选：设置网络连接参数
					aipSpeech.setConnectionTimeoutInMillis(2000);
					aipSpeech.setSocketTimeoutInMillis(60000);

			        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
					//aipSpeech.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
					//aipSpeech.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

			        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
			        // 也可以直接通过jvm启动参数设置此环境变量
			        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
					
				}
			}
		}
		return aipSpeech;
	}
}
