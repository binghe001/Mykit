package io.mykit.tag.spring.customer.beans.parser;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import io.mykit.tag.spring.customer.beans.entity.User;

/**
 * 自定义User解析元素
 * @author liuyazhuang
 *
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}
	
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		String name = element.getAttribute("name");
		if(StringUtils.hasText(name)){
			bean.addPropertyValue("name", name);
		}
		String sex = element.getAttribute("sex");
		if(StringUtils.hasText(sex)){
			bean.addPropertyValue("sex", "sex");
		}
	}
}
