package com.csx.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: sax读取xml
 * @Author: csx
 * @Date: 2018/01/23
 */
public class Demo1 {

	
	public static void main(String[] args) throws Exception{
		//1.创建SAXParser对象
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

		//2.调用parse方法
		/**
		 * 参数一： xml文档
		 * 参数二： DefaultHandler的子类
		 */
		parser.parse(new File("F:\\idea\\xml\\src\\main\\resources\\contact.xml"), new MyDefaultHandler());
	}

}
