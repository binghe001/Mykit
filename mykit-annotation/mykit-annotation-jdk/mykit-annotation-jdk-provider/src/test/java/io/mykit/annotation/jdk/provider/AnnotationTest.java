package io.mykit.annotation.jdk.provider;

import org.junit.Test;

import io.mykit.annotation.jdk.db.provider.entity.User;
import io.mykit.annotation.jdk.db.provider.parser.AnnotationParser;
import io.mykit.annotation.jdk.provider.parser.AnnotationProcessor;

/**
 * 测试自定义注解
 * @author liuyazhuang
 *
 */
public class AnnotationTest {
	
	@Test
	public void testAnnotation(){
		AnnotationProcessor.parseAnnotation(io.mykit.annotation.jdk.provider.entity.User.class);
	}
	
	@Test
	public void testDBAnnotation(){
		User testDto = new User("123", "34");  
		User testDto1 = new User("123", "test1");  
		User testDto2 = new User("456", "1,2,3,4");  
        String sql = AnnotationParser.assembleSqlFromObj(testDto);  
        String sql1 = AnnotationParser.assembleSqlFromObj(testDto1);  
        String sql2 = AnnotationParser.assembleSqlFromObj(testDto2);  
        System.out.println(sql);  
        System.out.println(sql1);  
        System.out.println(sql2);  
	}
}
