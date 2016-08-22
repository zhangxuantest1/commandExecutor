package com.netease.B6646_zx_UI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StaticPanel extends JPanel{
		private static final long serialVersionUID = 8545155494512043113L;
		
		private JComboBox<String> cmboxConnTypeBox;	//����������ѡ����������
		private JComboBox<String> cmboxLoginDescBox;	//�����򣬵�¼����
		private JButton btnConnect;	//���Ӱ�ť
		private JButton btnDeleteDesc;	//ɾ��ѡ�е�¼������¼�İ�ť
		private GridLayout gridLayout1;
		
		public StaticPanel(){
			gridLayout1 = new GridLayout(0, 2);
			gridLayout1.setHgap(5);
			gridLayout1.setVgap(5);
			setLayout(gridLayout1);

			addRowOfCmdType();	//��һ�У���������
			addRowOfLoginDesc();	//�ڶ��У���¼����
			addRowOfConnBtn();	//������ �����Ӻ�ɾ����ť
		}
		
		private void addRowOfConnBtn() {
			// TODO Auto-generated method stub
			btnConnect = new JButton("����");
			btnDeleteDesc = new JButton("ɾ��ѡ��");
			btnConnect.setMaximumSize(new Dimension(10, 10));
			add(btnConnect);
			add(btnDeleteDesc);
		}

		private void addRowOfLoginDesc() {
			// TODO Auto-generated method stub
			cmboxLoginDescBox = new JComboBox<String>();
			add(new JLabel("        ����������"));
			add(cmboxLoginDescBox);
		}

		private void addRowOfCmdType() {
			// TODO Auto-generated method stub
			cmboxConnTypeBox = new JComboBox<String>();
			add(new JLabel("        ��������:"));
			add(cmboxConnTypeBox);		
		}
	}