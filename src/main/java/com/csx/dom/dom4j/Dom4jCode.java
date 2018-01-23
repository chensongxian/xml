package com.csx.dom.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: TODO
 * @Author: csx
 * @Date: 2018/01/23
 */
public class Dom4jCode {
    private static String path="F:\\idea\\xml\\src\\main\\resources\\note.xml";

    /**
     *  private static String path="F:\\idea\\xml\\src\\main\\resources\\note.xml";
     * @throws Exception
     */
    @Test
    public void read() throws Exception {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element root = document.getRootElement();
        Element bookname = root.element("note").element("to");
        System.out.println(bookname.getText());
    }

    /**
     * 读取属性
     * @throws Exception
     */
    @Test
    public void readAttr() throws Exception {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element root = document.getRootElement();
        String value = root.element("note").element("to").attributeValue("test");
        System.out.println(value);
    }

    /**
     * 添加节点
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element price = DocumentHelper.createElement("售价");
        price.setText("19元");

        document.getRootElement().element("note").add(price);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
        writer.write(document);
        writer.close();
    }

    /**
     * 修改节点
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element price = (Element) document.getRootElement().element("note").elements("售价").get(0);
        price.setText("209元");

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
        writer.write(document);
        writer.close();

    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element price = (Element) document.getRootElement().element("note").elements("售价").get(0);
        price.getParent().remove(price);

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
        writer.write(document);
        writer.close();
    }


    /**
     * 向指定位置增加售价结点
     * @throws Exception
     */
    @Test
    public void add2() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element price = DocumentHelper.createElement("售价");
        price.setText("19元");

        List list = document.getRootElement().element("note").elements();
        list.add(1, price);


        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(path), format);
        writer.write(document);
        writer.close();

    }

    @Test
    public void findWithXpath() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element e = (Element) document.selectNodes("//售价").get(0);
        System.out.println(e.getText());
    }

    @Test
    public void findUser() throws Exception {
        String username = "cc";
        String password = "1234";

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        Element e = (Element) document.selectSingleNode("//user[@username='" + username + "' and @password='" + password + "']");
        if (e != null) {
            System.out.println("让用户登陆成功！！");
        } else {
            System.out.println("用户名和密码不正确！！");
        }

    }
}
