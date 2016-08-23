package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WestOfCmdPanel extends JPanel {
	private static final long serialVersionUID = -7835487897776102097L;

	private JLabel descLabel;
	private JScrollPane listPane;
	private JList<String> functionList;
	private PanelBtn panelBtn;
	private String[] funcArr ;
	
	private static WestOfCmdPanel staticPanel;
	
	public static WestOfCmdPanel createInstance(Dimension bound){
		if (null == staticPanel) {
			staticPanel = new WestOfCmdPanel(bound);
		}
		return staticPanel;
	}
	
	public WestOfCmdPanel(Dimension dimension){
		descLabel = new JLabel("请选择功能：");
		funcArr = getFunctionsFromXml();
		functionList = new JList<String>(funcArr);
		listPane = new JScrollPane(functionList);
		panelBtn = new  PanelBtn();
		
		setPreferredSize(dimension);
		setLayout(new BorderLayout(5,5));
		add(BorderLayout.NORTH,descLabel);
		add(BorderLayout.CENTER,listPane);
		add(BorderLayout.SOUTH, panelBtn);
	}
	

	private String[] getFunctionsFromXml() {
		// TODO Auto-generated method stub
		String[] strArr = {"1","11"};
		return strArr;
	}


	private class PanelBtn extends JPanel{
		private static final long serialVersionUID = 5039416950832057465L;
		
		private JButton addButton;
		private JButton deleteButton;
		
		public PanelBtn(){
			addButton = new JButton("增加");
			deleteButton = new JButton("删除");
			GridLayout layout = new GridLayout(1,2);
			layout.setHgap(10);
			setLayout(layout);
			
			add(addButton);
			add(deleteButton);
		}
	}
}
