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
	
	//��ָ��xml�ļ��Ѵ��ڣ���ֱ�Ӽ���
	//��ָ��xml�ļ������ڣ��򴴽����ڵ�ΪSetting��xml�ļ�
	public XMLEditor(String path){
		loadXml(path);
	}

	//����xml�ļ���Ŀ¼
	public void changeXmlFile(String path){
		loadXml(path);
	}
	//��ȡ����xml�ļ��ĸ�Ԫ��
	public Element getRootElement(){
		return xmlDocument.getRootElement();
	}
	
	//��ȡxml�ļ���document����
	public Document getDocument(){
		return xmlDocument;
	}
	
	//��ȡxml�ļ���file����
	public File getFile(){
		return xmlFile;
	}
	
	//��ָ��·�������ָ��Ԫ�أ������λ��
	public boolean addElelemt(String path, Element e) {
		String[] str = path.split("-");
		int size = str.length;
		lggr.debug(path);
		lggr.debug(String.format("·������Ϊ%d��", size));
		if (size < 2) {
			lggr.error("·�����Ȳ���ȷ���޷����Ԫ�ء�");
			return false;
		}
		
		Element element = getRootElement();	
		
		for (int i = 1; i < size; i++) {
			//�Ȼ�ȡ����Ҫ��ӵ�Ԫ��ǰ���λ��
			Element el= element.getChild(str[i]);
			if(null != el )	{	//Ԫ����ӵ�λ��
				element = el;
			} else {
				lggr.error(String.format("Ԫ�� %s �����ڡ�", str[i]));
				return false ;
			}
		}
		element.addContent(e);
		saveXML();
		return true;
	}
	
	//ɾ��ĳ��·���µ�Ԫ��
	public boolean removeElement(String path){
		lggr.debug(String.format("Ҫɾ����Ԫ��·��Ϊ %s ��", path));
		String[] str = path.split("-");
		int size = str.length;
		if (size < 3) {
			lggr.error("��Ԫ�ز���ɾ����");
			return false;
		}
		Element element = getRootElement();
		for(int i = 1 ; i < size -1 ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("�ڵ� %s �����ڣ���ȷ�ϡ�", str[i]));
				return false;
			}
		}
		if(element.removeChildren(str[size-1]))	{
			saveXML();
			return true;
		}
		return false;
	}
	
	//��������Ԫ�ص�λ��,ͨ��Ԫ�ص�λ��ȷ��
	public void switchElementByText(String path, int index1, int index2){
		
		Element parent = getElement(path);
		Element child1,child2;	//��Ҫ�����������ӽڵ�
		lggr.debug(String.format("���ڽڵ�%s�£�����Ԫ��", parent.getName()));
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
	
	//��ָ��λ�ò���ָ��Ԫ��
	public void insertElement(String path, int index, Element e){
		
		Element parent = getElement(path);
		lggr.info(String.format("����Ԫ�� %s �µĵ� %d ��λ�ò�����Ԫ�� %s��", 
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
	
	//�༭�ƶ�xml������
	public void replaceEmelement(String path, int pos, Element newElement){
		
		Element parent = getElement(path);
		lggr.debug(String.format("��Ҫ�ѵ� %d λ�õ�Ԫ���滻ΪԪ�� %s", 
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
	
	//��սڵ��µ�����Ԫ��
	public void clearElement(String path){
		Element parent = getElement(path);
		lggr.debug("��ɾ��·�� %s �µ�����Ԫ�ء�");
		List<Element> elements = parent.getChildren();
		int size = elements.size();
		for (int i = 0; i < size; i++) {
			elements.remove(0);
		}
		saveXML();
	}
	
	//��ȡָ��·���µ��ƶ����ֵ�Ԫ�أ���Ҫ��֤·������һ��
	public List<Element> getElementsByName(String parentPath,String name){
		String[] str = parentPath.split("-");
		Element element = getRootElement();
		for(int i = 1 ; i < str.length ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("�ڵ� %s �����ڣ���ȷ�ϡ�", str[i]));
				return null;
			}
		}
		List<Element> list = element.getChildren(name);
		return list;
	}
	
	//��ȡָ��Ŀ¼�µĵ�index��Ԫ�ص�Name
	public String getName(String path, int index){
		
		Element parent = getElement(path);
		lggr.debug(String.format("������ȡ·�� %s �µĵ� %d ��Ԫ�ص�����(��0����)��", 
			parent.getName(),index));
		List<Element> elements = parent.getChildren();
		Element element = elements.get(index);
		lggr.debug(String.format("Ԫ�ص�����Ϊ %s", element.getName()));
		return element.getName();
	}
	
	//��ȡָ��·���µ�����Ԫ�أ���Ҫ��֤·������һ��
		public List<Element> getAllElements(String parentPath){
			String[] str = parentPath.split("-");
			Element element = getRootElement();
			for(int i = 1 ; i < str.length ; i++){
				Element el = element.getChild(str[i]);
				if (null != el) {
					element = el;
				} else {
					lggr.error(String.format("�ڵ� %s �����ڣ���ȷ�ϡ�", str[i]));
					return null;
				}
			}
			List<Element> list = element.getChildren();
			return list;
		}
	
	/******************************************************/
	/********************�ڲ�private����*********************/
	/******************************************************/
	//����ָ��·���µ�xml�ļ�
	private void loadXml(String path) {
		// TODO Auto-generated method stub
		filepath = path;
		lggr = Logger.getLogger(XMLEditor.class.getName());
		if(!fileExists()){		//xml�ļ������ڣ��򴴽��ļ�
			lggr.info("xml�ļ������ڣ�����һ�����ڵ�Ϊ\"Setting\"�����ļ���");
			createNewXMLFile();	//��������ʼ��xml�ļ�
		} else {
			lggr.info("xml�ļ��Ѵ��ڣ��������´�����");
		}
		xmlFile = new File(filepath);
		xmlSaxBuilder = new SAXBuilder();
		try {
			xmlDocument = xmlSaxBuilder.build(xmlFile);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			lggr.error("����xml�ļ�ʧ�ܡ�����");
			e.printStackTrace();
		}
		lggr.info("�ɹ�����xml�ļ�");
	}
	
	//�ж�ָ��Ϊ·���µ�ָ���ļ��Ƿ����
	private boolean fileExists(){
		File file = new File(filepath);
		if(file.exists()){
			lggr.debug(String.format("file %s exists.", filepath));
			return true;
		}
		lggr.debug(String.format("file %s does not  exists.", filepath));
		return false;
	}
	
	
	
	//����xml�ļ�
	private void saveXML(){
		Format format = Format.getCompactFormat();
		format.setEncoding("UTF-8");	//�����ַ�����
		format.setIndent("    ");		//���û��к�����
		XMLOutputter outputter = new XMLOutputter(format);
		try {
			outputter.output(xmlDocument, new FileOutputStream(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			lggr.error("xml�ļ�����/�޸�ʧ�ܡ�");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			lggr.error("xml�ļ�����/�޸�ʧ�ܡ�");
			e.printStackTrace();
		}
		lggr.info("����/�޸��ļ���ɡ�");
	}
	//��ȡָ��·���µĵ�һ��Ԫ��
	Element getElement(String path){
		String[] str = path.split("-");
		Element element = getRootElement();
		for(int i = 1 ; i < str.length ; i++){
			Element el = element.getChild(str[i]);
			if (null != el) {
				element = el;
			} else {
				lggr.error(String.format("�ڵ� %s �����ڣ���ȷ�ϡ�", str[i]));
				return null;
			}
		}
		return element;
	}	
	
	//��ָ��Ŀ¼�´���xml�ļ�
	private void createNewXMLFile() {
		// TODO Auto-generated method stub
		Element root = new Element("Setting");
		xmlDocument = new Document(root);	//���ø��ڵ�
		saveXML();
	}
}
