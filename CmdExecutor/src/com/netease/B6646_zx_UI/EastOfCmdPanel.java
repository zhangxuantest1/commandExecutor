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
		
		addButton = new JButton(" 增  加 ");
		insertButton = new JButton(" 插  入 ");
		editButton = new JButton(" 编  辑 ");
		deleteButton = new JButton(" 删  除 ");
		clearButton = new JButton(" 清  空 ");
		moveUpButton = new JButton("上移↑");
		moveDownButton = new JButton("下移↓");
		execAllButton = new JButton("执行所有");
		execSelectedButton = new JButton("执行选中");
		
		add(new JLabel("功能按钮"));
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
