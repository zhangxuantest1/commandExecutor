package com.netease.B6646_zx_UI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StaticPanel extends JPanel{
		private static final long serialVersionUID = 8545155494512043113L;
		
		private JComboBox<String> cmboxConnTypeBox;	//下拉框，用来选择连接类型
		private JComboBox<String> cmboxLoginDescBox;	//下拉框，登录描述
		private JButton btnConnect;	//连接按钮
		private JButton btnDeleteDesc;	//删除选中登录描述记录的按钮
		private GridLayout gridLayout1;
		
		public StaticPanel(){
			gridLayout1 = new GridLayout(0, 2);
			gridLayout1.setHgap(5);
			gridLayout1.setVgap(5);
			setLayout(gridLayout1);

			addRowOfCmdType();	//第一行，连接类型
			addRowOfLoginDesc();	//第二行，登录描述
			addRowOfConnBtn();	//第三行 ，连接和删除按钮
		}
		
		private void addRowOfConnBtn() {
			// TODO Auto-generated method stub
			btnConnect = new JButton("连接");
			btnDeleteDesc = new JButton("删除选中");
			btnConnect.setMaximumSize(new Dimension(10, 10));
			add(btnConnect);
			add(btnDeleteDesc);
		}

		private void addRowOfLoginDesc() {
			// TODO Auto-generated method stub
			cmboxLoginDescBox = new JComboBox<String>();
			add(new JLabel("        连接描述："));
			add(cmboxLoginDescBox);
		}

		private void addRowOfCmdType() {
			// TODO Auto-generated method stub
			cmboxConnTypeBox = new JComboBox<String>();
			add(new JLabel("        连接类型:"));
			add(cmboxConnTypeBox);		
		}
	}