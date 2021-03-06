package com.netease.B6646_zx_UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

public class WestSettingPanel extends JScrollPane {
	/* 此容器为一个JScrollPane的子类
	 * 大小：	宽——MainFrame宽的1/4
	 * 		高——MainFrame高的2/3
	 * 
	 * 布局为ScrollPaneLayout,可以自动换行
	 * 容器中的所有元素必须在构造方法的super中添加
	 * 此处添加一个JPanel的子类 WestSettingPanelInPane
	 * 该子类的定义方法见下面描述*/
	private static final long serialVersionUID = 8780480850195055713L;
	
	private Dimension dimWest;	//此panel的大小
	private static Logger lggr; 
	//private static WestSettingPanelInPane panelInPane = new WestSettingPanelInPane();
	private static WestSettingPanelInPane panelInPane = WestSettingPanelInPane.createInstance();
	private static WestSettingPanel staticPanel;	//单例
	
	public static WestSettingPanel createInstance(Dimension dimension){
		if(null == staticPanel){
			lggr = Logger.getLogger(WestSettingPanel.class.getName());
			lggr.info("配置Panel尚未创建，在单例模式中创建");
			staticPanel = new WestSettingPanel(dimension);
		}
		return staticPanel;
	}
	public WestSettingPanel(Dimension mainFrameBound){
		super(panelInPane);	//在此处添加这个WestPane中的所有控件
		lggr.debug("执行super完成，进入 WestSettingPanel 构造方法");
		dimWest = new Dimension(mainFrameBound.width/4, mainFrameBound.height*2/3);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(dimWest);
	}
	
	public WestSettingPanelInPane getPanelInPane(){
		return panelInPane;
	}
}


//用来存放所有元素的类
class WestSettingPanelInPane extends JPanel{
	/* 此容器为一个JPanel的子类
	 * 大小：	宽——MainFrame宽的1/4
	 * 		高——MainFrame高的2/3
	 * 
	 * 布局为GridLayout,有0行(即不确定行数)和2列
	 * 第一行为基础(静态)配置
	 * 包括命令类型、登录描述符、登录/删除按钮
	 * 
	 * 第二行为动态配置
	 * 根据cmdtype确定 
	 */
	private static final long serialVersionUID = -7497047752880969380L;
	private StaticPanel staticPanel;	//最上方的panel，固定在最上方位置
	private GitSettingPanel gitPanel;	//Git配置相关的panel
	private RedisSettingPanel redisPanel;	//Redis配置相关的panel
	private SQLSettingPanel sqlPanel;	//	SQL命令相关的panel
	private ShellSettingPanel shellpPanel;	//shell命令相关的panel
	
	private GridLayout gridLayout;	//网格布局
	private int cmdType;	//命令类型 0-Git 1-redis 2-sql 3-shell
	private Logger lggr;
	private static WestSettingPanelInPane staticPane;
	
	public static WestSettingPanelInPane createInstance(){
		if (null == staticPane) {
			staticPane = new WestSettingPanelInPane();
		}
		return staticPane;
	}
	
	public WestSettingPanelInPane(){
		lggr  = Logger.getLogger(WestSettingPanelInPane.class.getName());
		gridLayout = new GridLayout(3,1);	//1行3列
		gridLayout.setVgap(30);
		gridLayout.setHgap(10);
		setLayout(gridLayout);	
		cmdType = 0;	//默认设置为 0-Git
		
	/*	redisPanel = new RedisSettingPanel();
		gitPanel = new GitSettingPanel();
		sqlPanel = new SQLSettingPanel();
		shellpPanel = new ShellSettingPanel();*/
		redisPanel = RedisSettingPanel.createInstance();
		gitPanel = GitSettingPanel.createInstance();
		sqlPanel = SQLSettingPanel.createInstance();
		shellpPanel = ShellSettingPanel.createInstance();
		
		//在这里添加每个grid的内容
		addRowOfBasicSetting();
		addRowOfDynamicSetting(cmdType);
	}
	
	public JPanel getCurrentPanel(){
		lggr.debug(String.format("即将返回的cmdType为 %d", cmdType));
		switch (cmdType) {
		case 0:
			return gitPanel;
		case 1:
			return redisPanel;
		case 2:
			return sqlPanel;
		default:
			return shellpPanel;
		}
	}

	public void addRowOfDynamicSetting(int cmdType) {
		// TODO Auto-generated method stub
		lggr.info("为WestPanel的动态配置布局");
		switch (cmdType) {
		case 0:		//为0，展示git
			lggr.info("选择的为git登录，将展现出git的登录界面");
			add(gitPanel);
			break;
		case 1:		//为1，展示redis
			lggr.info("选择的为redis登录，将展现出redis的登录界面");
			add(redisPanel);
			break;
		case 2:		//为2，展示sql
			lggr.info("选择的为数据库登录，将展现出SQL的登录界面");
			add(sqlPanel);
			break;
		default:	//为3，展示shell
			lggr.info("选择的为shell登录，将展现出shell的登录界面");
			add(shellpPanel);
			break;
		}
		lggr.info("为WestPanel的动态配置布局完成");
		
		//必须有这一句，实现对窗口的重绘。否则只有在鼠标滑过时，才会刷新展示的控件
		repaint();	
	}

	public void setcmdType(int type){
		cmdType = type;
	}
	private void addRowOfBasicSetting() {
		// TODO Auto-generated method stub
		lggr.info("为WestPanel的基础配置布局");
		staticPanel = new StaticPanel();
		add(staticPanel);
		lggr.info("为WestPanel的基础配置布局完成");
	}
}
