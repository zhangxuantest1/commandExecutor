package com.netease.B6646_zx_UI;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GitSettingPanel extends JPanel {
	private static final long serialVersionUID = -3096240928416273101L;
	
	private JTextField txtLocalPath;	//本地路径
	private JTextField txtRemoteURL;	//远端URL
	private JButton addButton;
	private JButton editButton;

	public GitSettingPanel(){
		GridLayout layout = new GridLayout(3,2);
		layout.setVgap(5);
		layout.setHgap(5);
		txtLocalPath = new JTextField();
		txtRemoteURL = new JTextField();
		addButton = new JButton("添加配置");
		editButton = new JButton("修改配置");
		
		setLayout(layout);
		add(new JLabel("        本地目录:"));
		add(txtLocalPath);
		add(new JLabel("        远端URL:"));
		add(txtRemoteURL);
		add(addButton);
		add(editButton);
	}
}
