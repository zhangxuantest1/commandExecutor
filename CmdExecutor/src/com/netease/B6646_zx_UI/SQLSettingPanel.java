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
	
	//��xml�ļ��������չʾ��������
	public void showValue(String xmlPath){
		
	}
}
