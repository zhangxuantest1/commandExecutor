package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CenterOfCmdPanel extends JPanel {
	private static final long serialVersionUID = -8689239250012959641L;
	
	private JList<String> cmdList;
	private JScrollPane listPane;
	private JLabel label;
	private String[] arr ;
	private static CenterOfCmdPanel staticPanel;
	
	public static CenterOfCmdPanel createInstance(){
		if(null == staticPanel){
			staticPanel = new CenterOfCmdPanel();
		}
		return staticPanel;
	}
	
	public CenterOfCmdPanel(){
		setLayout(new BorderLayout(5,5));
		label = new JLabel("√¸¡Ó¡–±Ì£∫");
		arr = getCmdFromXml();
		cmdList = new JList<String>(arr);
		listPane = new JScrollPane(cmdList);
		add(BorderLayout.NORTH,label);
		add(listPane);
	}
	private String[] getCmdFromXml() {
		// TODO Auto-generated method stub
		String[] strArr= {"1","2"};
		return strArr;
	}
}
