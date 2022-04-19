package com.hfg.utils;

import cn.hutool.core.util.XmlUtil;
import lombok.SneakyThrows;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/4/13 09:22
 * @Description:
 */

public class XmlResolverUtil {

    private static final String CHARSET = "GBK";

    @SneakyThrows
    public static void main(String[] args) {
//        System.out.println(xmlResolverMethod(xmlString));
//        iteratorDocument();

//        Document document = DocumentHelper.parseText(xmlString);
//        Element rootElement = document.getRootElement();
////      treeWalk(rootElement);
//        recursiveRemoveElement(rootElement);
//        recursiveRemoveAttribute(rootElement);
//        System.out.println(xmlRemoveEmptyLines(document));

        Document document = DocumentHelper.parseText(xmlString);
        System.out.println(xmlRemoveEmptyLines(document));

        String value = document.valueOf("/order/user_info/@age");
        System.out.println(value);

    }

    /**
     * 参考官网
     * @param element
     */
    public static void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            System.out.println(node.getName());
            if (node instanceof Element) {
                treeWalk((Element) node);
            }
            else {
                // do something…
            }
        }
    }

    /**
     * 递归遍历删除某个元素的属性
     * @param rootElement
     */
    private static void recursiveRemoveAttribute(Element rootElement,String elementName,String attributeName) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            if (elementName.equals(element.getName())) {
                List<Attribute> attributeList = element.attributes();
                for (Attribute attribute : attributeList) {
                    if (attribute.getName().equals(attributeName))
                        element.remove(attribute);
                }
            }
            List<Element> subElementList = element.elements();
            //说明该元素下还有子节点
            if (subElementList.size()!=0) {
                recursiveRemoveAttribute(element,elementName,attributeName);
            }
        }
    }
    /**
     * 递归遍历Document文档的所有节点,根据条件删除某些element
     */
    private static void recursiveRemoveElement(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            if (element.getName().equals("address")) {
                rootElement.remove(element);
                continue;
            }
            List<Element> subElementList = element.elements();
            //说明该元素下还有子节点
            if (subElementList.size()!=0) {
                recursiveRemoveElement(element);
            }
        }
    }

    /**
     * 根据节点名字删除xml字符串某一个节点
     * @throws DocumentException
     */
    private static void iteratorDocument() throws DocumentException {
        Document document = DocumentHelper.parseText(xmlString);
        Element rootElement = document.getRootElement();
        //用各种返回标准 Java 迭代器的方法来导航文档
        for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
            //遍历文档的对象
            Element element = it.next();
            System.out.println(element.getName());
            if (element.getName().equals("user_info")) {
                rootElement.remove(element);
            }
        }
        //去掉空行的xml字符串
        String newXmlString = xmlRemoveEmptyLines(document);
        System.out.println(newXmlString);
    }

    /**
     * 对xml格式的字符串进行解析
     * @param xmlString xml字符串
     * @return
     */
    @SneakyThrows
    public static String xmlResolverMethod(String xmlString) {
        //通过SAXReader读取  解析文件
        //第二种  解析字符串
        //DocumentHelper.parseText("String");

        SAXReader saxReader = new SAXReader();
        try (InputStream xmlStream = new ByteArrayInputStream(xmlString.getBytes(CHARSET))) {
            Document document = saxReader.read(xmlStream);
            //获取根节点
            Element rootElement = document.getRootElement();
            Element user_info = rootElement.element("user_info");
            System.out.println("user_info对象名字："+user_info.element("name").getText());
            Element user_info_name = user_info.element("name");
            user_info.remove(user_info_name);
            String cleanComment = XmlUtil.cleanComment(document.asXML());
            System.out.println(cleanComment);
            xmlRemoveEmptyLines(document);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlString;
    }
    @Test
    public void test() {

    }
    /**
     * 去掉document文件的空行
     * @param document 文档对象
     * @return
     */
    @SneakyThrows
    public static String xmlRemoveEmptyLines(Document document) {
        OutputFormat format =OutputFormat.createPrettyPrint();
        format.setIndent(true);
        format.setNewlines(true);
        format.setNewLineAfterDeclaration(true);
        StringWriter writer = new StringWriter();
        XMLWriter xmlWriter=new XMLWriter(writer,format);
        xmlWriter.write(document);
        xmlWriter.close();
        return writer.toString();
    }


    private static final String xmlString ="<?xml version=\"1.0\" encoding=\"gbk\"?>\n" +
            "<order>\n" +
            "    <user_info type=\"2\" age=\"24\">\n" +
            "    <name type=\"string\">思无邪</name>\n" +
            "    <address type=\"string\">桃花岛水帘洞123号</address>\n" +
            "    <phone type=\"string\">15960238696</phone>\n" +
            "    </user_info>\n" +
            "    <goods_list>\n" +
            "        <goods_item>\n" +
            "            <goods_name type=\"string\">Mate30</goods_name>\n" +
            "            <goods_number type=\"int\">1</goods_number>\n" +
            "            <goods_price type=\"double\">8888</goods_price>\n" +
            "        </goods_item>\n" +
            "        <goods_item>\n" +
            "            <goods_name type=\"string\">格力中央空调</goods_name>\n" +
            "            <goods_number type=\"int\">1</goods_number>\n" +
            "            <goods_price type=\"double\">58000</goods_price>\n" +
            "        </goods_item>\n" +
            "        <goods_item>\n" +
            "            <goods_name type=\"string\">红蜻蜓皮鞋</goods_name>\n" +
            "            <goods_number type=\"int\">3</goods_number>\n" +
            "            <goods_price type=\"double\">999</goods_price>\n" +
            "        </goods_item>\n" +
            "    </goods_list>\n" +
            "</order>";
}
