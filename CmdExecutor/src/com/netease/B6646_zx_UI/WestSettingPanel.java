package com.netease.B6646_zx_UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

public class WestSettingPanel extends JScrollPane {
	/* ������Ϊһ��JScrollPane������
	 * ��С��	����MainFrame���1/4
	 * 		�ߡ���MainFrame�ߵ�2/3
	 * 
	 * ����ΪScrollPaneLayout,�����Զ�����
	 * �����е�����Ԫ�ر����ڹ��췽����super�����
	 * �˴����һ��JPanel������ WestSettingPanelInPane
	 * ������Ķ��巽������������*/
	private static final long serialVersionUID = 8780480850195055713L;
	
	private Dimension dimWest;	//��panel�Ĵ�С
	private Logger lggr = Logger.getLogger(WestSettingPanel.class.getName());
	private static WestSettingPanelInPane panelInPane = new WestSettingPanelInPane();
	
	public WestSettingPanel(Dimension mainFrameBound){
		super(panelInPane);	//�ڴ˴�������WestPane�е����пؼ�
		lggr.debug("ִ��super��ɣ����� WestSettingPanel ���췽��");
		dimWest = new Dimension(mainFrameBound.width/4, mainFrameBound.height*2/3);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(dimWest);
	}
}


//�����������Ԫ�ص���
class WestSettingPanelInPane extends JPanel{
	/* ������Ϊһ��JPanel������
	 * ��С��	����MainFrame���1/4
	 * 		�ߡ���MainFrame�ߵ�2/3
	 * 
	 * ����ΪGridLayout,��0��(����ȷ������)��2��
	 * ��һ��Ϊ����(��̬)����
	 * �����������͡���¼����������¼/ɾ����ť
	 * 
	 * �ڶ���Ϊ��̬����
	 * ����cmdtypeȷ�� 
	 */
	private static final long serialVersionUID = -7497047752880969380L;
	private StaticPanel staticPanel;	//���Ϸ���panel���̶������Ϸ�λ��
	private GitSettingPanel gitPanel;	//Git������ص�panel
	private RedisSettingPanel redisPanel;	//Redis������ص�panel
	private SQLSettingPanel sqlPanel;	//	SQL������ص�panel
	private ShellSettingPanel shellpPanel;	//shell������ص�panel
	
	private GridLayout gridLayout;	//���񲼾�
	private int cmdType;	//�������� 0-Git 1-redis 2-sql 3-shell
	private Logger lggr;
	
	public WestSettingPanelInPane(){
		lggr  = Logger.getLogger(WestSettingPanelInPane.class.getName());
		gridLayout = new GridLayout(3,1);	//n��2��
		gridLayout.setVgap(30);
		gridLayout.setHgap(10);
		setLayout(gridLayout);	
		cmdType = 0;	//Ĭ������Ϊ 0-Git
		
		redisPanel = new RedisSettingPanel();
		gitPanel = new GitSettingPanel();
		sqlPanel = new SQLSettingPanel();
		shellpPanel = new ShellSettingPanel();
		
		//���������ÿ��grid������
		addRowOfBasicSetting();
		addRowOfDynamicSetting(cmdType);
	}

	private void addRowOfDynamicSetting(int cmdType) {
		// TODO Auto-generated method stub
		lggr.info("ΪWestPanel�Ķ�̬���ò���");
		switch (cmdType) {
		case 0:		//Ϊ0��չʾgit
			lggr.info("ѡ���Ϊgit��¼����չ�ֳ�git�ĵ�¼����");
			add(gitPanel);
			break;
		case 1:		//Ϊ1��չʾredis
			lggr.info("ѡ���Ϊredis��¼����չ�ֳ�redis�ĵ�¼����");
			add(redisPanel);
			break;
		case 2:		//Ϊ2��չʾsql
			lggr.info("ѡ���Ϊ���ݿ��¼����չ�ֳ�SQL�ĵ�¼����");
			add(sqlPanel);
			break;
		default:	//Ϊ3��չʾshell
			lggr.info("ѡ���Ϊshell��¼����չ�ֳ�shell�ĵ�¼����");
			add(shellpPanel);
			break;
		}
		lggr.info("ΪWestPanel�Ķ�̬���ò������");
	}

	private void addRowOfBasicSetting() {
		// TODO Auto-generated method stub
		lggr.info("ΪWestPanel�Ļ������ò���");
		staticPanel = new StaticPanel();
		add(staticPanel);
		lggr.info("ΪWestPanel�Ļ������ò������");
	}
}
