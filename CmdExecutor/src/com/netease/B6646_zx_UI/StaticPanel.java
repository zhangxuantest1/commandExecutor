package com.netease.B6646_zx_UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jdom2.Element;

import com.netease.B6646_zx_Function.XMLEditor;

public class StaticPanel extends JPanel{
	private static final long serialVersionUID = 8545155494512043113L;
	private JComboBox<String> cmboxConnTypeBox;	//����������ѡ����������
	private JComboBox<String> cmboxLoginDescBox;	//�����򣬵�¼����
	private JButton btnConnect;	//���Ӱ�ť
	private JButton btnDeleteDesc;	//ɾ��ѡ�е�¼������¼�İ�ť
	private GridLayout gridLayout1;
	private static String cfgXmlPath = "D:\\temp\\Config.xml";	//��Ҫ�����ļ���·��
	private static Logger lggr = Logger.getLogger(StaticPanel.class.getName());
	private List<String> linkTypeList;	//Ŀǰ�����ĸ�Ԫ�� git/redis/shell/sql
	private Map<String, List<String> > linkDescMap;	//�ĸ�Ԫ�طֱ��Ӧ����������,���ݽṹΪ<String,List<String> >
	private static StaticPanel staticPanel;	
	private String connType;	//�������ͣ���Ϊ��������Ĺ�����Ϣ
	private String connDesc;	//������������Ϊ��������Ĺ�����Ϣ
	private XMLEditor xmlEditor;
		
	public static StaticPanel createInstance(){
		if (null == staticPanel) {
			staticPanel = new StaticPanel();
		}
		return staticPanel;
	}
		
	public StaticPanel(){
		gridLayout1 = new GridLayout(0, 2);
		gridLayout1.setHgap(5);
		gridLayout1.setVgap(5);
		setLayout(gridLayout1);

		addRowOfCmdType();	//��һ�У���������
		addRowOfLoginDesc();	//�ڶ��У���¼����
		addRowOfConnBtn();	//������ �����Ӻ�ɾ����ť
		
		/* ��ȡ�������ͺ���������������
		 * �����ļ�ΪConfig.xml
		 * ����·��Ϊ��Setting-UI
		 * ��ȡ��Ԫ��ΪԪ�ص�����
		 */
		loadAndShowStaticArea(cfgXmlPath);	
		addActionListeners();
	}
	
	


	

	/********************************************
	 *											*	
	 *****************�Զ��巽��********************
	 *											*
	 ********************************************/
		
	/*****************���з���*********************/
	 public void setConnType(String type){
		 connType = type;
	 }
	 
	 public void setConnDesc(String desc){
		 connDesc = desc;
	 }
	 
	 public String getConnType(){
		 return connType;
	 }
	 
	 public String getConnDesc(){
		 return connDesc;
	 }
		
		
	/*****************˽�з���*********************/
	private void loadAndShowStaticArea(String path) {
		linkTypeList = new ArrayList<>();
		linkDescMap = new HashMap<>();
		// TODO Auto-generated method stub
		xmlEditor = new XMLEditor(path);
		List<Element> list = xmlEditor.getAllElements("Setting-UI");
		lggr.info(String.format("��������%d�������������ݡ�",list.size()));
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			linkTypeList.add(e.getName());
			List<Element> l = e.getChildren();
			List<String> strList = new ArrayList<String>();
			for (int j = 0; j < l.size(); j++) {
				strList.add(l.get(j).getAttributeValue("name"));
			}
			linkDescMap.put(e.getName(), strList);
		}
		for (int k = 0; k < linkTypeList.size(); k++) {
			cmboxConnTypeBox.addItem(linkTypeList.get(k));
		}
		List<String> list2 = linkDescMap.get("Git");
		for (int m = 0; m < list2.size(); m++) {
			cmboxLoginDescBox.addItem(list2.get(m));
		}
		connType = cmboxConnTypeBox.getItemAt(0);
		connDesc = cmboxLoginDescBox.getItemAt(0);
		lggr.debug(String.format("��¼����������Ϊ: %s", connType));
		lggr.debug(String.format("��¼����������Ϊ: %s", connDesc));
	}
	
	private void addActionListeners() {
		// TODO Auto-generated method stub
		cmboxConnTypeBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = cmboxConnTypeBox.getSelectedIndex();
				String strSelected = (String)cmboxConnTypeBox.getSelectedItem();
				lggr.debug(String.format("��ǰѡ�е�ѡ��Ϊ %s", strSelected));	
				//����"��������"�е�����
				List<Element> list = xmlEditor.getAllElements("Setting-UI-"+strSelected);
				cmboxLoginDescBox.removeAllItems();
				for (int i = 0; i < list.size(); i++) {
					cmboxLoginDescBox.addItem(list.get(i).getAttributeValue("name"));
				}	
				//�޸���ʾ������(Git/Redis/Shell/Sql)
				
			}
		});
	}

	private void addRowOfConnBtn() {
		// TODO Auto-generated method stub
		btnConnect = new JButton("����");
		btnDeleteDesc = new JButton("ɾ��ѡ��");
		btnConnect.setMaximumSize(new Dimension(10, 10));
		add(btnConnect);
		add(btnDeleteDesc);
	}

	private void addRowOfLoginDesc() {
		// TODO Auto-generated method stub
		cmboxLoginDescBox = new JComboBox<String>();
		add(new JLabel("        ����������"));
		add(cmboxLoginDescBox);
	}

	private void addRowOfCmdType() {
		// TODO Auto-generated method stub
		cmboxConnTypeBox = new JComboBox<String>();
		add(new JLabel("        ��������:"));
		add(cmboxConnTypeBox);		
	}
}