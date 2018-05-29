package io.mykit.filter.spring.repeat.utils;

/**
 * 时间操作
 * @author liuyazhuang
 *
 */
public class TimeUtils {
	
	/**
	 * 时间单位枚举
	 * @author liuyazhuang
	 *
	 */
	public enum TimeUnit{
		YEAR, MONTH, WEEK, DAY, HOURS, MINUTES, SECONDS, MILLISECONDS
	}
	
	/**
	 * 将对应的Value值转化为毫秒
	 * @param value
	 * @param timeUnit
	 * @return
	 */
	public static long getTime(long value, TimeUnit timeUnit){
		if(value == 0) return value;
		switch (timeUnit) {
		case YEAR:
			return value * 365 * 24 * 60 * 60 * 1000;
		case MONTH:
			return value * 30 * 24 * 60 * 60 * 1000;
		case WEEK:
			return value * 7 * 24 * 60 * 60 * 1000;
		case DAY:
			return value * 24 * 60 * 60 * 1000;
		case HOURS:
			return value * 60 * 60 * 1000;
		case MINUTES:
			return value * 60 * 1000;
		case SECONDS:
			return value * 1000;
		case MILLISECONDS:
			return value;
		default:
			return value;
		}
	}
	
	/**
	 * 获取从现在开始的时间
	 * @param value
	 * @param timeUnit
	 * @return
	 */
	public static long getTimeFromCurrent(long value, TimeUnit timeUnit){
		value = getTime(value, timeUnit);
		return (value += System.currentTimeMillis());
	}
	
}
