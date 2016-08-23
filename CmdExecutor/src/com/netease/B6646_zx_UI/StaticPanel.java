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
		private JComboBox<String> cmboxConnTypeBox;	//����������ѡ����������
		private JComboBox<String> cmboxLoginDescBox;	//�����򣬵�¼����
		private JButton btnConnect;	//���Ӱ�ť
		private JButton btnDeleteDesc;	//ɾ��ѡ�е�¼������¼�İ�ť
		private GridLayout gridLayout1;
		private static String cfgXmlPath = "D:\\temp\\Config.xml";	//��Ҫ�����ļ���·��
		private static Logger lggr = Logger.getLogger(StaticPanel.class.getName());
		private List<String> linkTypeList;	//Ŀǰ�����ĸ�Ԫ�� git/redis/shell/sql
		private Map<String, List<String> > linkDescMap;	//�ĸ�Ԫ�طֱ��Ӧ����������
		
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

			addRowOfCmdType();	//��һ�У���������
			addRowOfLoginDesc();	//�ڶ��У���¼����
			addRowOfConnBtn();	//������ �����Ӻ�ɾ����ť
			
			/* ��ȡ�������ͺ���������������
			 * �����ļ�ΪConfig.xml
			 * ����·��Ϊ��Setting-UI
			 * ��ȡ��Ԫ��ΪԪ�ص�����
			 */
			loadAndShowStaticArea(cfgXmlPath);	
		}

		private void loadAndShowStaticArea(String path) {
			// TODO Auto-generated method stub
			XMLEditor xmlEditor = new XMLEditor(path);
			List<Element> list = xmlEditor.getAllElements("Setting-UI");
			lggr.debug(String.format("��������%d�������������ݡ�",list.size()));
			
		}

		/********************************************
		 *											*	
		 *****************�Զ��巽��********************
		 *											*
		 ********************************************/

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