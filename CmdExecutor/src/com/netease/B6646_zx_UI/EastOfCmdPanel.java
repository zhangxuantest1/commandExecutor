package com.netease.B6646_zx_UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EastOfCmdPanel extends JPanel {
	private static final long serialVersionUID = -1340271094150445799L;
	
	private JButton addButton;
	private JButton insertButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton clearButton;
	private JButton moveUpButton;
	private JButton moveDownButton;
	private JButton execAllButton;
	private JButton execSelectedButton;
	private static EastOfCmdPanel staticPanel;
	
	public static EastOfCmdPanel createInstance(Dimension bound){
		if (null == staticPanel) {
			staticPanel  = new EastOfCmdPanel(bound);
		}
		return staticPanel;
	}
	
	public EastOfCmdPanel(Dimension dimension){
		setPreferredSize(dimension);
		setLayout(new FlowLayout());
		
		addButton = new JButton(" ��  �� ");
		insertButton = new JButton(" ��  �� ");
		editButton = new JButton(" ��  �� ");
		deleteButton = new JButton(" ɾ  �� ");
		clearButton = new JButton(" ��  �� ");
		moveUpButton = new JButton("���ơ�");
		moveDownButton = new JButton("���ơ�");
		execAllButton = new JButton("ִ������");
		execSelectedButton = new JButton("ִ��ѡ��");
		
		add(new JLabel("���ܰ�ť"));
		add(addButton);
		add(insertButton);
		add(editButton);
		add(deleteButton);
		add(clearButton);
		add(moveUpButton);
		add(moveDownButton);
		add(new JLabel("            "));
		add(execAllButton);
		add(execSelectedButton);
	}
}
