package com.netease.B6646_zx_Function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class XMLEditor {
	private String filepath;
	private File xmlFile;
	private Document xmlDocument;
	private SAXBuilder xmlSaxBuilder;
	private Logger lggr;
	
	//若指定xml文件已存在，则直接加载
	//若指定xml文件不存在，则创建根节点为Setting的xml文件
	public XMLEditor(String path){
		loadXml(path);
	}

	//更换xml文件的目录
	public void changeXmlFile(String path){
		loadXml(path);
	}
	//获取管理xml文件的根元素
	public Element getRootElement(){
		return xmlDocument.getRootElement();
	}
	
	//获取xml文件的document对象
	public Document getDocument(){
		return xmlDocument;
	}
	
	//获取xml文件的file对象
	public File getFile(){
		return xmlFile;
	}
	
	//在指定路径下添加指定元素，在最后位置
	public boolean addElelemt(String path, Element e) {
		String[] str = path.split("-");
		int size = str.length;
		lggr.debug(path);
		lggr.debug(String.format("路径长度为%d。", size));
		if (size < 2) {
			lggr.error("路径长度不正确，无法添加元素。");
			return false;
		}
		
		Element element = getRootElement();	
		
		for (int i = 1; i < size; i++) {
			//先获取到需要添加的元素前面的位置
			Element el= element.getChild(str[i]);
			if(null != el )	{	//元素添加的位置
				element = el;
			} else {
				lggr.error(String.format("元素 %s 不存在。", str[i]));
				return false ;
			}
		}
		element.addContent(e);
		saveXML();
		return true;
	}
	
	//删除某个路径下的元素
	public boolean removeElement(String path){
		lggr.debug(String.format("要删除的元素路径为 %s 。", path));
		String[] str = path.split("-");
		int size = str.length;
		if (size < 3) {
			lggr.error("此元素不能删除。");
			return false;
		}
		Element element = getRootElement();
		for(int i = 1 ; i < size -1 ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("节点 %s 不存在，请确认。", str[i]));
				return false;
			}
		}
		if(element.removeChildren(str[size-1]))	{
			saveXML();
			return true;
		}
		return false;
	}
	
	//交换两个元素的位置,通过元素的位置确定
	public void switchElementByText(String path, int index1, int index2){
		
		Element parent = getElement(path);
		Element child1,child2;	//需要交换的两个子节点
		lggr.debug(String.format("将在节点%s下，交换元素", parent.getName()));
		List<Element> elements = parent.getChildren();
		List<Element> elementsNew = new ArrayList<Element>();
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elementsNew.add(elements.get(0));
			elements.remove(0);
		}

		child1 = elementsNew.get(index1);
		child2 = elementsNew.get(index2);
		elementsNew.set(index1, child2);
		elementsNew.set(index2, child1);
		parent.addContent(elementsNew);

		saveXML();
		
	}
	
	//在指定位置插入指定元素
	public void insertElement(String path, int index, Element e){
		
		Element parent = getElement(path);
		lggr.info(String.format("将在元素 %s 下的第 %d 个位置插入新元素 %s。", 
				parent.getName(), index, e.getName()));
		List<Element> elements = parent.getChildren();
		List<Element> elementsNew = new ArrayList<Element>();
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elementsNew.add(elements.get(0));
			elements.remove(0);
		}
		elementsNew.add(index, e);
		parent.addContent(elementsNew);
		saveXML();

	}
	
	//编辑制定xml的内容
	public void replaceEmelement(String path, int pos, Element newElement){
		
		Element parent = getElement(path);
		lggr.debug(String.format("将要把第 %d 位置的元素替换为元素 %s", 
				pos, newElement.getName()));
		List<Element> elements = parent.getChildren();
		List<Element> elementsNew = new ArrayList<Element>();
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elementsNew.add(elements.get(0));
			elements.remove(0);
		}
		elementsNew.set(pos, newElement);
		parent.addContent(elementsNew);
		saveXML();

	}
	
	//清空节点下的所有元素
	public void clearElement(String path){
		Element parent = getElement(path);
		lggr.debug("将删除路径 %s 下的所有元素。");
		List<Element> elements = parent.getChildren();
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elements.remove(0);
		}
		saveXML();
	}
	
	//获取指定路径下的制定名字的元素，需要保证路径仅有一条
	public List<Element> getElementsByName(String parentPath,String name){
		String[] str = parentPath.split("-");
		Element element = getRootElement();
		for(int i = 1 ; i < str.length ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("节点 %s 不存在，请确认。", str[i]));
				return null;
			}
		}
		List<Element> list = element.getChildren(name);
		return list;
	}
	
	//获取指定目录下的第index个元素的Name
	public String getName(String path, int index){
		
		Element parent = getElement(path);
		lggr.debug(String.format("即将获取路径 %s 下的第 %d 个元素的名字(从0算起)。", 
			parent.getName(),index));
		List<Element> elements = parent.getChildren();
		Element element = elements.get(index);
		lggr.debug(String.format("元素的名字为 %s", element.getName()));
		return element.getName();
	}
	
	//获取指定路径下的所有元素，需要保证路径仅有一条
		public List<Element> getAllElements(String parentPath){
			String[] str = parentPath.split("-");
			Element element = getRootElement();
			for(int i = 1 ; i < str.length ; i++){
				Element el = element.getChild(str[i]);
				if (null != el) {
					element = el;
				} else {
					lggr.error(String.format("节点 %s 不存在，请确认。", str[i]));
					return null;
				}
			}
			List<Element> list = element.getChildren();
			return list;
		}
	
	/******************************************************/
	/********************内部private方法*********************/
	/******************************************************/
	//加载指定路径下的xml文件
	private void loadXml(String path) {
		// TODO Auto-generated method stub
		filepath = path;
		lggr = Logger.getLogger(XMLEditor.class.getName());
		if(!fileExists()){		//xml文件不存在，则创建文件
			lggr.info("xml文件不存在，创建一个根节点为\"Setting\"的新文件。");
			createNewXMLFile();	//创建并初始化xml文件
		} else {
			lggr.info("xml文件已存在，无需重新创建。");
		}
		xmlFile = new File(filepath);
		xmlSaxBuilder = new SAXBuilder();
		try {
			xmlDocument = xmlSaxBuilder.build(xmlFile);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			lggr.error("加载xml文件失败。。。");
			e.printStackTrace();
		}
		lggr.info("成功加载xml文件");
	}
	
	//判断指定为路径下的指定文件是否存在
	private boolean fileExists(){
		File file = new File(filepath);
		if(file.exists()){
			lggr.debug(String.format("file %s exists.", filepath));
			return true;
		}
		lggr.debug(String.format("file %s does not  exists.", filepath));
		return false;
	}
	
	
	
	//储存xml文件
	private void saveXML(){
		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8");	//设置字符编码
		format.setIndent("    ");		//设置换行和缩进
		XMLOutputter outputter = new XMLOutputter(format);
		try {
			outputter.output(xmlDocument, new FileOutputStream(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			lggr.error("xml文件创建/修改失败。");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			lggr.error("xml文件创建/修改失败。");
			e.printStackTrace();
		}
		lggr.info("创建/修改文件完成。");
	}
	//获取指定路径下的第一个元素
	Element getElement(String path){
		String[] str = path.split("-");
		Element element = getRootElement();
		for(int i = 1 ; i < str.length ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("节点 %s 不存在，请确认。", str[i]));
				return null;
			}
		}
		return element;
	}	
	
	//在指定目录下创建xml文件
	private void createNewXMLFile() {
		// TODO Auto-generated method stub
		Element root = new Element("Setting");
		xmlDocument = new Document(root);	//设置根节点
		saveXML();
	}
}
