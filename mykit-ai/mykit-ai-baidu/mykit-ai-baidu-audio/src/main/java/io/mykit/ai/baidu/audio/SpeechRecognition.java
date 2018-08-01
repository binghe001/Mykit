package io.mykit.ai.baidu.audio;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;

import io.mykit.ai.baidu.audio.factory.AipSpeechFactory;

/**
 * 语音识别
 * @author liuyazhuang
 *
 */
public class SpeechRecognition {
	
	/**
	 * 固定采样率
	 */
	private static final int RATE = 16000;
	
	public static final String EXT_PCM = "pcm";
	public static final String EXT_WAV = "wav";
	public static final String EXT_OPUS = "opus";
	public static final String EXT_SPEEX = "speex";
	public static final String EXT_AMR = "amr";
	
	/**
	 * 语音识别
	 * @param path
	 * @param format
	 * @param rate
	 * @param options
	 * @return
	 */
	public static JSONObject asr(String path, String format, int rate, HashMap <String, Object> options){
		AipSpeech aipSpeech = AipSpeechFactory.getInstance();
		JSONObject jsonObject = aipSpeech.asr(path, format, rate, options);
		return jsonObject;
	}
	/**
	 * 语音识别
	 * @param path
	 * @param format
	 * @param options
	 * @return
	 */
	public static JSONObject asr(String path, String format, HashMap <String, Object> options){
		AipSpeech aipSpeech = AipSpeechFactory.getInstance();
		JSONObject jsonObject = aipSpeech.asr(path, format, RATE, options);
		return jsonObject;
	}
	
	/**
	 * 语音识别
	 * @param data
	 * @param format
	 * @param rate
	 * @param options
	 * @return
	 */
	public static JSONObject asr(byte[] data, String format, int rate, HashMap<String, Object> options){
		AipSpeech aipSpeech = AipSpeechFactory.getInstance();
		JSONObject jsonObject = aipSpeech.asr(data, format, rate, options);
		return jsonObject;
	}
	/**
	 * 语音识别
	 * @param data
	 * @param format
	 * @param rate
	 * @param options
	 * @return
	 */
	public static JSONObject asr(byte[] data, String format,  HashMap<String, Object> options){
		AipSpeech aipSpeech = AipSpeechFactory.getInstance();
		JSONObject jsonObject = aipSpeech.asr(data, format, RATE, options);
		return jsonObject;
	}
}
