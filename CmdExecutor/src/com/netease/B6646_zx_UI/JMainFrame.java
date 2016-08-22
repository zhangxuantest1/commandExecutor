package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import org.apache.log4j.*;

public class JMainFrame extends JFrame {
	private static final long serialVersionUID = 318860219327704860L;
	
	private static Logger lggr;	//log4j对象
	public JMainFrame(){	
		lggr = Logger.getLogger(JMainFrame.class.getName());
		
		lggr.debug("初始化主窗口配置");	
		setBounds(10, 10, 900, 600);
		setTitle("命令执行");
		setLayout(new BorderLayout(5,5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setVisible(true);
		//获取主窗体的大小
		Dimension boundMainFrame = new Dimension(getWidth(), getHeight());
		lggr.info(String.format("主窗体的大小为(宽度,高度):(%d,%d)",
				getWidth(),getHeight()));
		
		//设置各个区域的panel
		setWestArea(boundMainFrame);
		setSouthArea(boundMainFrame);
		setCenterArea(boundMainFrame);
	}
	
	private void setCenterArea(Dimension bound) {
		// TODO Auto-generated method stub
		lggr.info("开始初始化MainFrame的Center部分");
		CenterCmdLinePanel centerPanel = new CenterCmdLinePanel(bound);
		getContentPane().add("Center", centerPanel);
		lggr.info("初始化MainFrame的Middle部分完成");
	}

	private void setWestArea(Dimension bound) {
		// TODO Auto-generated method stub
		lggr.info("开始初始化MainFrame的West部分");
		WestSettingPanel westPanel = new WestSettingPanel(bound);
		getContentPane().add("West", westPanel);
		lggr.info("初始化MainFrame的West部分完成");
	}

	private void setSouthArea(Dimension bound) {
		// TODO Auto-generated method stub
		//设置FrameSouth部分，South部分为记录展示框
		lggr.info("开始初始化MainFrame的South部分");
		SouthRecordPanel southPanel = new SouthRecordPanel(bound);
		getContentPane().add("South", southPanel);
		lggr.info("初始化MainFrame的South部分完成");
	}
}
