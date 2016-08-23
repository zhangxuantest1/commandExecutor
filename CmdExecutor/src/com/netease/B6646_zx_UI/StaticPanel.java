package com.netease.B6646_zx_UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.jdom2.Document;
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
		private Map<String, List<String> > linkDescMap;	//四个元素分别对应的若干描述
		
		private static StaticPanel staticPanel;	
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
		}

		private void loadAndShowStaticArea(String path) {
			// TODO Auto-generated method stub
			XMLEditor xmlEditor = new XMLEditor(path);
			List<Element> list = xmlEditor.getAllElements("Setting-UI");
			lggr.debug(String.format("共加载了%d条连接类型数据。",list.size()));
			
		}

		/********************************************
		 *											*	
		 *****************自定义方法********************
		 *											*
		 ********************************************/

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