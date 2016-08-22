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
	private Logger lggr = Logger.getLogger(SouthRecordPanel.class.getName());
	
	public SouthRecordPanel(Dimension bound){
		lggr.info("��ʼ����South���ֵ�Panel");
		
		int width = bound.width-10;
		int height = bound.height-10;
		recordArea = new JTextArea("����ִ�������");
		recordArea.setEditable(false);
		recordArea.setBackground(Color.LIGHT_GRAY);
		recordJsp = new JScrollPane(recordArea);
		
		
		setPreferredSize(new Dimension(width,(int)height/3));
		setLayout(new BorderLayout());
		add(recordJsp);
		
		lggr.info("���South���ֵ�Panel");
	}
	
}
