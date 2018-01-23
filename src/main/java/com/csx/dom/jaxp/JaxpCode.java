package com.csx.dom.jaxp;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Description: JAXP（sun公司，官方解析,dom解析）
 * @Author: csx
 * @Date: 2018/01/23
 */
public class JaxpCode {

    private static String path="F:\\idea\\xml\\src\\main\\resources\\note.xml";

    /**
     * 读取节点
     */
    @Test
    public void read() {
        try {
            //1. 获取工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //2. 获得解析器
            DocumentBuilder builder = factory.newDocumentBuilder();
            //3. 解析xml文档，得到代表文档的document
            Document document = builder.parse(new File(path));

            NodeList nodeList = document.getElementsByTagName("to");

            Node node = nodeList.item(0);

            System.out.println(node.getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 更新节点
     * @throws Exception
     */
    @Test
    public void update() throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        Node price = document.getElementsByTagName("to").item(0);
        price.setTextContent("sz");


        //把内存中的document写到xml文档
        TransformerFactory tf = TransformerFactory.newInstance();
        //得到转换器
        Transformer ts = tf.newTransformer();
        ts.transform(new DOMSource(document), new StreamResult(new File(path)));
    }


    /**
     * 向指定节点中增加孩子节点
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        //创建需要增加的节点
        Node price = document.createElement("price");
        price.setTextContent("59元");

        //得到需要增加的节点的父亲
        Node parent = document.getElementsByTagName("note").item(0);

        //把需要增加的节点挂到父结点上
        parent.appendChild(price);


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer ts = tf.newTransformer();
        ts.transform(new DOMSource(document), new StreamResult(new File(path)));

    }

    /**
     * 向指定位置上插入节点
     * @throws Exception
     */
    @Test
    public void add2() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        Node node = document.createElement("name");
        node.setTextContent("名称");

        Node parent = document.getElementsByTagName("note").item(0);
        parent.insertBefore(node, document.getElementsByTagName("price").item(0));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer ts = tf.newTransformer();
        ts.transform(new DOMSource(document), new StreamResult(new File(path)));
    }


    /**
     * 删除xml文档的结点
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        Node node = document.getElementsByTagName("price").item(0);
        node.getParentNode().removeChild(node);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer ts = tf.newTransformer();
        ts.transform(new DOMSource(document), new StreamResult(new File(path)));
    }

    /**
     * 操作xml文档属性
     * @throws Exception
     */
    @Test
    public void updateAttribute() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));


        //操作xml文档的元素时，一般都把元素当作node对象，但是程序员如果发现node不好使时，就应把node强转成相应类型
        Node node = document.getElementsByTagName("name").item(0);
        Element book = null;
        //在作结点转换之前，最好先判断结点类型
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            book = (Element) node;
        }

        book.setAttribute("name", "yyyyyyy");
        book.setAttribute("password", "123");
        book.removeAttribute("password");

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer ts = tf.newTransformer();
        ts.transform(new DOMSource(document), new StreamResult(new File(path)));
    }
}
