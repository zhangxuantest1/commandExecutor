package com.netease.B6646_zx_UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.apache.log4j.Logger;

/* ����λ��JMainFrame��borderlayout���ֵ�center����
 * �����Զ�������С������Ҫ�ֹ����ô�С
 * �����е����������С�����ã����Ը��ݸ���Ĵ�С�������á�
 */
public class CenterCmdLinePanel extends JPanel {

	private static final long serialVersionUID = 1350362489443848810L;
	private static Logger lggr ;
	private static Dimension bound ;	//��ǰPanel�Ĵ�С	
	
	//���������ĸ�λ�õ����
	private WestOfCmdPanel westPane;
	private CenterOfCmdPanel centerPane;
	private EastOfCmdPanel eastPanel;
	//private SouthOfCmdPanel southPanel;
	
	private static CenterCmdLinePanel staticPanel;
	
	public static CenterCmdLinePanel createInstance(Dimension bound){
		lggr = Logger.getLogger(CenterCmdLinePanel.class.getName());
		if(null == staticPanel){
			lggr.info("CenterCmdLinePanel��δ�������ڵ����д�����");
			staticPanel = new CenterCmdLinePanel(bound);
		}
		
		return staticPanel;
	}
	public CenterCmdLinePanel(Dimension boundMainFrame){
		lggr.info("����CenterCmdLinePanel�Ĺ��캯���������м䲿�ֿ�ʼ��");
		/* ����Ϊborderlayout�����Ϊ5����
		 * ��layout��west��east��south��center���֣�����north����
		 * �ĸ����ֱַ�Ϊ�ĸ�Panel�����������£�
		 * West�����й��ܵ�ѡ�����ɾ
		 * Center��һ���б�ÿһ�д���һ������
		 * East��һϵ�а�ť�������޸�Center�е�����
		 */
		setLayout(new BorderLayout(5,5));
		
		//��ʼ�����������
		//�м䲿�ֵĿ��Ϊ�ܿ�ȵ�3/4,�߶�Ϊ�ܸ߶ȵ�2/3
		bound = new Dimension(boundMainFrame.width/4*3, boundMainFrame.height/3*2);
		lggr.debug(String.format("����������boundΪ(%f,%f)",bound.getWidth(),bound.getHeight()));
		Dimension dimWest = new Dimension((int)bound.getHeight()/3, (int)bound.getHeight()/5*4);
		//westPane = new WestOfCmdPanel(dimWest);
		westPane = WestOfCmdPanel.createInstance(dimWest);
		lggr.debug(String.format("�����West���ֵ�boundΪ(%f,%f)",dimWest.getWidth(),dimWest.getHeight()));
		Dimension dimEast = new Dimension((int)bound.getHeight()/4, (int)bound.getHeight()/5*4);
		//eastPanel = new EastOfCmdPanel(dimEast);
		eastPanel = EastOfCmdPanel.createInstance(dimEast);
		lggr.debug(String.format("�����East���ֵ�boundΪ(%f,%f)",dimEast.getWidth(),dimEast.getHeight()));
		//Dimension dimSouth = new Dimension((int)bound.getHeight(), (int)bound.getHeight()/5);
		//southPanel = new SouthOfCmdPanel(dimSouth);
		//lggr.debug(String.format("�����South���ֵ�boundΪ(%f,%f)",dimSouth.getWidth(),dimSouth.getHeight()));
		//centerPane = new CenterOfCmdPanel();
		centerPane = CenterOfCmdPanel.createInstance();
		
		add(BorderLayout.WEST,westPane);
		add(BorderLayout.EAST, eastPanel);
	//	add(BorderLayout.SOUTH, southPanel);
		add(BorderLayout.CENTER, centerPane);
		
		setBackground(Color.WHITE);
		lggr.info("�뿪CenterCmdLinePanel�Ĺ��캯���������м䲿�ֽ�����");
	}

}
