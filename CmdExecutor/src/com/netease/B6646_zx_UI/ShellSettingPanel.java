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
	
	//��xml�ļ��������չʾ��������
	public void showValue(String xmlPath){
		
	}
}
