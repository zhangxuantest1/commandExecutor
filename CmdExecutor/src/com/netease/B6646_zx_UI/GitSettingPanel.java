package com.netease.B6646_zx_UI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.netease.B6646_zx_Function.XMLEditor;
import org.apache.log4j.*;

public class GitSettingPanel extends JPanel {
	private static final long serialVersionUID = -3096240928416273101L;
	
	private JTextField txtLocalPath;	//����·��
	private JTextField txtRemoteURL;	//Զ��URL
	private JButton addButton;
	private JButton editButton;
	private static GitSettingPanel staticPanel;
	private static String cfgXmlPath = "D:\\temp\\Config.xml";	//��Ҫ�����ļ���·��
	private static Logger lggr = Logger.getLogger(GitSettingPanel.class.getName());
	private XMLEditor xmlEditor;
	
	public static GitSettingPanel createInstance(){
		if (null == staticPanel) {
			staticPanel = new GitSettingPanel();
		}
		return staticPanel;
	}

	public GitSettingPanel(){
		xmlEditor = new XMLEditor(cfgXmlPath);
		
		
		GridLayout layout = new GridLayout(3,2);
		layout.setVgap(5);
		layout.setHgap(5);
		txtLocalPath = new JTextField();
		txtRemoteURL = new JTextField();
		addButton = new JButton("�������");
		editButton = new JButton("�޸�����");
		
		setLayout(layout);
		add(new JLabel("        ����Ŀ¼:"));
		add(txtLocalPath);
		add(new JLabel("        Զ��URL:"));
		add(txtRemoteURL);
		add(addButton);
		add(editButton);
	}
	
	//��xml�ļ��������չʾ��������
	public void showValue(String xmlPath){
		//String loaclPath = xmlEditor.
	}
}
