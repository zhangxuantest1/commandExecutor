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
	private JComboBox<String> cmboxConnTypeBox;	//下拉框，用来选择连接类型
	private JComboBox<String> cmboxLoginDescBox;	//下拉框，登录描述
	private JButton btnConnect;	//连接按钮
	private JButton btnDeleteDesc;	//删除选中登录描述记录的按钮
	private GridLayout gridLayout1;
	private static String cfgXmlPath = "D:\\temp\\Config.xml";	//需要加载文件的路径
	private static Logger lggr = Logger.getLogger(StaticPanel.class.getName());
	private List<String> linkTypeList;	//目前包含四个元素 git/redis/shell/sql
	private Map<String, List<String> > linkDescMap;	//四个元素分别对应的若干描述,数据结构为<String,List<String> >
	private static StaticPanel staticPanel;	
	private String connType;	//连接类型，作为与其他类的共享信息
	private String connDesc;	//连接描述，作为与其他类的共享信息
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

		addRowOfCmdType();	//第一行，连接类型
		addRowOfLoginDesc();	//第二行，登录描述
		addRowOfConnBtn();	//第三行 ，连接和删除按钮
		
		/* 读取连接类型和链接描述的内容
		 * 数据文件为Config.xml
		 * 加载路径为：Setting-UI
		 * 获取的元素为元素的名字
		 */
		loadAndShowStaticArea(cfgXmlPath);	
		addActionListeners();
	}
	
	


	

	/********************************************
	 *											*	
	 *****************自定义方法********************
	 *											*
	 ********************************************/
		
	/*****************公有方法*********************/
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
		
		
	/*****************私有方法*********************/
	private void loadAndShowStaticArea(String path) {
		linkTypeList = new ArrayList<>();
		linkDescMap = new HashMap<>();
		// TODO Auto-generated method stub
		xmlEditor = new XMLEditor(path);
		List<Element> list = xmlEditor.getAllElements("Setting-UI");
		lggr.info(String.format("共加载了%d条连接类型数据。",list.size()));
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
		lggr.debug(String.format("记录的连接类型为: %s", connType));
		lggr.debug(String.format("记录的连接描述为: %s", connDesc));
	}
	
	private void addActionListeners() {
		// TODO Auto-generated method stub
		cmboxConnTypeBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = cmboxConnTypeBox.getSelectedIndex();
				String strSelected = (String)cmboxConnTypeBox.getSelectedItem();
				lggr.debug(String.format("当前选中的选项为 %s", strSelected));	
				//更改"连接描述"中的内容
				List<Element> list = xmlEditor.getAllElements("Setting-UI-"+strSelected);
				cmboxLoginDescBox.removeAllItems();
				for (int i = 0; i < list.size(); i++) {
					cmboxLoginDescBox.addItem(list.get(i).getAttributeValue("name"));
				}	
				//修改显示的类型(Git/Redis/Shell/Sql)
				
			}
		});
	}

	private void addRowOfConnBtn() {
		// TODO Auto-generated method stub
		btnConnect = new JButton("连接");
		btnDeleteDesc = new JButton("删除选中");
		btnConnect.setMaximumSize(new Dimension(10, 10));
		add(btnConnect);
		add(btnDeleteDesc);
	}

	private void addRowOfLoginDesc() {
		// TODO Auto-generated method stub
		cmboxLoginDescBox = new JComboBox<String>();
		add(new JLabel("        连接描述："));
		add(cmboxLoginDescBox);
	}

	private void addRowOfCmdType() {
		// TODO Auto-generated method stub
		cmboxConnTypeBox = new JComboBox<String>();
		add(new JLabel("        连接类型:"));
		add(cmboxConnTypeBox);		
	}
}