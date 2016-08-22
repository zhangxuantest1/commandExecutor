package com.netease.B6646_zx_Function;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
		filepath = path;
		lggr = Logger.getLogger(XMLEditor.class.getName());
		if(!fileExists()){		//xml�ļ������ڣ��򴴽��ļ�
			lggr.info("xml�ļ������ڣ����´���");
			createNewXMLFile();	//��������ʼ��xml�ļ�
		} else {
			lggr.info("xml�ļ��Ѵ��ڣ��������´���");
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
	 
	//��ָ��Ŀ¼�´���xml�ļ�
	private void createNewXMLFile() {
		// TODO Auto-generated method stub
		Element root = new Element("Setting");
		xmlDocument = new Document(root);	//���ø��ڵ�
		saveXML();
	}

	//�ж�ָ��Ϊ·���µ�ָ���ļ��Ƿ����
	public boolean fileExists(){
		File file = new File(filepath);
		if(file.exists()){
			lggr.debug(String.format("file %s exists.", filepath));
			return true;
		}
		lggr.debug(String.format("file %s does not  exists.", filepath));
		return false;
	}
	
	//��ȡ����xml�ļ��ĸ�Ԫ��
	public Element getRootElement(){
		return xmlDocument.getRootElement();
	}
	
	//��ָ��λ������ƶ�Ԫ��
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
	
	public void saveXML(){
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
}
