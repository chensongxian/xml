package com.csx.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: 读取contact.xml文件，完整输出文档内容
 * @Author: csx
 * @Date: 2018/01/23
 */
public class Demo2 {

	public static void main(String[] args)throws Exception {
		//1.创建SAXParser
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		//2.读取xml
		MyDefaultHandler2 handler = new MyDefaultHandler2();
		parser.parse(new File("F:\\idea\\xml\\src\\main\\resources\\contact.xml"), handler);
		String content = handler.getContent();
		System.out.println(content);
	}

}
