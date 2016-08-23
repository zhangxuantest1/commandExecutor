package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.log4j.Logger;

public class SouthRecordPanel extends JPanel {

	private static final long serialVersionUID = 5126139975681672809L;
	private JTextArea recordArea;
	private JScrollPane recordJsp;
	private static Logger lggr;
	private static SouthRecordPanel staticPanel;
	
	public static SouthRecordPanel createInstance(Dimension bound){
		if (null == staticPanel) {
			lggr = Logger.getLogger(SouthRecordPanel.class.getName());
			lggr.info("SouthRecordPanel尚未创建，在单例中创建。"); 
			staticPanel = new SouthRecordPanel(bound);
		}
		return staticPanel;
	}
	
	public SouthRecordPanel(Dimension bound){
		lggr.info("开始创建South部分的Panel");
		
		int width = bound.width-10;
		int height = bound.height-10;
		recordArea = new JTextArea("命令执行情况：");
		recordArea.setEditable(false);
		recordArea.setBackground(Color.LIGHT_GRAY);
		recordJsp = new JScrollPane(recordArea);
		
		setPreferredSize(new Dimension(width,(int)height/3));
		setLayout(new BorderLayout());
		add(recordJsp);
		
		lggr.info("完成South部分的Panel");
	}
	
}
