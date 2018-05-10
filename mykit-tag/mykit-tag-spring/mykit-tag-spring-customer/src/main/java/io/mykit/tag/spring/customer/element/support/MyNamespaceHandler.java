package io.mykit.tag.spring.customer.element.support;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import io.mykit.tag.spring.customer.element.parser.SimpleDateFormatBeanDefinitionParser;

/**
 * 注册标签解析器
 * @author liuyazhuang
 *
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
    }
}