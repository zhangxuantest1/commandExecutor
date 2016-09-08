package com.netease.B6646_zx_UI;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RedisSettingPanel extends JPanel {
	private static final long serialVersionUID = 7264528016607705530L;
	private static RedisSettingPanel staticPanel;
	
	public static RedisSettingPanel createInstance(){
		if (null == staticPanel) {
			staticPanel = new RedisSettingPanel();
		}
		return staticPanel;
	}

	public RedisSettingPanel(){
		add(new JButton("Redis"));
		add(new JButton("Redis"));
	}
	
	//将xml文件里的配置展示到界面上
	public void showValue(String xmlPath){
		
	}
}
