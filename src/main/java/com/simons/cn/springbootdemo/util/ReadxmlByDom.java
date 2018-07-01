package com.simons.cn.springbootdemo.util;

import com.simons.cn.springbootdemo.bean.RequestTextMessage;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanrx on 2018/7/1.
 */
public class ReadxmlByDom {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;
    private static List<RequestTextMessage> requestTextMessage = null;

    static {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List<RequestTextMessage> getBooks(String fileName) throws Exception {
        //将给定 URI 的内容解析为一个 XML 文档,并返回Document对象
        document = db.parse(fileName);
        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList
        NodeList bookList = document.getElementsByTagName("book");
        requestTextMessage = new ArrayList<RequestTextMessage>();
        //遍历books
        for (int i = 0; i < bookList.getLength(); i++) {
            RequestTextMessage book = new RequestTextMessage();
            //获取第i个book结点
            org.w3c.dom.Node node = bookList.item(i);
            //获取第i个book的所有属性
            NamedNodeMap namedNodeMap = node.getAttributes();
            //获取已知名为id的属性值
            String id = namedNodeMap.getNamedItem("id").getTextContent();//System.out.println(id);

            //获取book结点的子节点,包含了Test类型的换行
            NodeList cList = node.getChildNodes();//System.out.println(cList.getLength());9

            //将一个book里面的属性加入数组
            ArrayList<String> contents = new ArrayList<>();
            for (int j = 1; j < cList.getLength(); j += 2) {

                org.w3c.dom.Node cNode = cList.item(j);
                String content = cNode.getFirstChild().getTextContent();
                contents.add(content);
                //System.out.println(contents);
            }

            book.setToUserName(contents.get(0));
            book.setFromUserName(contents.get(1));
            book.setCreateTime(contents.get(2));
            book.setMsgType(contents.get(3));
            book.setContent(contents.get(4));
            book.setMsgId(contents.get(5));
            requestTextMessage.add(book);
        }
        return requestTextMessage;
    }
}