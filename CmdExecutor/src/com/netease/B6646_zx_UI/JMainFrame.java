package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import org.apache.log4j.*;

public class JMainFrame extends JFrame {
	private static final long serialVersionUID = 318860219327704860L;
	
	private static Logger lggr;	//log4j����
	public JMainFrame(){	
		lggr = Logger.getLogger(JMainFrame.class.getName());
		
		lggr.debug("��ʼ������������");	
		setBounds(10, 10, 900, 600);
		setTitle("����ִ��");
		setLayout(new BorderLayout(5,5));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setVisible(true);
		//��ȡ������Ĵ�С
		Dimension boundMainFrame = new Dimension(getWidth(), getHeight());
		lggr.info(String.format("������Ĵ�СΪ(���,�߶�):(%d,%d)",
				getWidth(),getHeight()));
		
		//���ø��������panel
		setWestArea(boundMainFrame);
		setSouthArea(boundMainFrame);
		setCenterArea(boundMainFrame);
	}
	
	private void setCenterArea(Dimension bound) {
		// TODO Auto-generated method stub
		lggr.info("��ʼ��ʼ��MainFrame��Center����");
		CenterCmdLinePanel centerPanel = new CenterCmdLinePanel(bound);
		getContentPane().add("Center", centerPanel);
		lggr.info("��ʼ��MainFrame��Middle�������");
	}

	private void setWestArea(Dimension bound) {
		// TODO Auto-generated method stub
		lggr.info("��ʼ��ʼ��MainFrame��West����");
		WestSettingPanel westPanel = new WestSettingPanel(bound);
		getContentPane().add("West", westPanel);
		lggr.info("��ʼ��MainFrame��West�������");
	}

	private void setSouthArea(Dimension bound) {
		// TODO Auto-generated method stub
		//����FrameSouth���֣�South����Ϊ��¼չʾ��
		lggr.info("��ʼ��ʼ��MainFrame��South����");
		SouthRecordPanel southPanel = new SouthRecordPanel(bound);
		getContentPane().add("South", southPanel);
		lggr.info("��ʼ��MainFrame��South�������");
	}
}
