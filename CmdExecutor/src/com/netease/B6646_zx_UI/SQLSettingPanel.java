package com.netease.B6646_zx_UI;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SQLSettingPanel extends JPanel {
	private static final long serialVersionUID = -8877162901792590153L;
	private static SQLSettingPanel staticPanel;
	
	public static SQLSettingPanel createInstance(){
		if (null == staticPanel) {
			staticPanel = new SQLSettingPanel();
		}
		return staticPanel;
	}

	public SQLSettingPanel(){
		add(new JButton("SQL"));
		add(new JButton("SQL"));

	}
	
	//将xml文件里的配置展示到界面上
	public void showValue(String xmlPath){
		
	}
}
