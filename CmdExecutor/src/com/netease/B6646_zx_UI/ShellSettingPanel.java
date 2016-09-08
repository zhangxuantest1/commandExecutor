package com.netease.B6646_zx_UI;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ShellSettingPanel extends JPanel {
	private static final long serialVersionUID = -8558677704962485116L;
	private static ShellSettingPanel staticPanel;
	
	public static ShellSettingPanel createInstance(){
		if (null == staticPanel) {
			staticPanel = new ShellSettingPanel();
		}
		return staticPanel;
	}

	public ShellSettingPanel(){
		add(new JButton("Shell"));
		add(new JButton("Shell"));
	}
	
	//将xml文件里的配置展示到界面上
	public void showValue(String xmlPath){
		
	}
}
