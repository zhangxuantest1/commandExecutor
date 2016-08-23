package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.apache.log4j.Logger;

/* 该类位于JMainFrame的borderlayout布局的center部分
 * 可以自动调整大小，不需要手工设置大小
 * 该类中的其他组件大小的配置，可以根据该类的大小进行配置。
 */
public class CenterCmdLinePanel extends JPanel {

	private static final long serialVersionUID = 1350362489443848810L;
	private static Logger lggr ;
	private static Dimension bound ;	//当前Panel的大小	
	
	//东西南中四个位置的组件
	private WestOfCmdPanel westPane;
	private CenterOfCmdPanel centerPane;
	private EastOfCmdPanel eastPanel;
	//private SouthOfCmdPanel southPanel;
	
	private static CenterCmdLinePanel staticPanel;
	
	public static CenterCmdLinePanel createInstance(Dimension bound){
		lggr = Logger.getLogger(CenterCmdLinePanel.class.getName());
		if(null == staticPanel){
			lggr.info("CenterCmdLinePanel尚未创建，在单例中创建。");
			staticPanel = new CenterCmdLinePanel(bound);
		}
		
		return staticPanel;
	}
	public CenterCmdLinePanel(Dimension boundMainFrame){
		lggr.info("进入CenterCmdLinePanel的构造函数，创建中间部分开始。");
		/* 设置为borderlayout，间隔为5像素
		 * 此layout有west、east、south、center部分，但无north部分
		 * 四个部分分别为四个Panel功能描述如下：
		 * West：进行功能的选择和增删
		 * Center：一个列表，每一行代表一个命令
		 * East：一系列按钮，用来修改Center中的内容
		 */
		setLayout(new BorderLayout(5,5));
		
		//初始化并配置组件
		//中间部分的宽度为总宽度的3/4,高度为总高度的2/3
		bound = new Dimension(boundMainFrame.width/4*3, boundMainFrame.height/3*2);
		lggr.debug(String.format("命令框整体的bound为(%f,%f)",bound.getWidth(),bound.getHeight()));
		Dimension dimWest = new Dimension((int)bound.getHeight()/3, (int)bound.getHeight()/5*4);
		//westPane = new WestOfCmdPanel(dimWest);
		westPane = WestOfCmdPanel.createInstance(dimWest);
		lggr.debug(String.format("命令框West部分的bound为(%f,%f)",dimWest.getWidth(),dimWest.getHeight()));
		Dimension dimEast = new Dimension((int)bound.getHeight()/4, (int)bound.getHeight()/5*4);
		//eastPanel = new EastOfCmdPanel(dimEast);
		eastPanel = EastOfCmdPanel.createInstance(dimEast);
		lggr.debug(String.format("命令框East部分的bound为(%f,%f)",dimEast.getWidth(),dimEast.getHeight()));
		//Dimension dimSouth = new Dimension((int)bound.getHeight(), (int)bound.getHeight()/5);
		//southPanel = new SouthOfCmdPanel(dimSouth);
		//lggr.debug(String.format("命令框South部分的bound为(%f,%f)",dimSouth.getWidth(),dimSouth.getHeight()));
		//centerPane = new CenterOfCmdPanel();
		centerPane = CenterOfCmdPanel.createInstance();
		
		add(BorderLayout.WEST,westPane);
		add(BorderLayout.EAST, eastPanel);
	//	add(BorderLayout.SOUTH, southPanel);
		add(BorderLayout.CENTER, centerPane);
		
		setBackground(Color.WHITE);
		lggr.info("离开CenterCmdLinePanel的构造函数，创建中间部分结束。");
	}

}
