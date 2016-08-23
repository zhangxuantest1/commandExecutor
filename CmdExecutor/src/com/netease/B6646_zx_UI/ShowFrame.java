package com.netease.B6646_zx_UI;

import javax.swing.SwingUtilities;

import com.netease.B6646_zx_UI.JMainFrame;
import org.apache.log4j.*;

public class ShowFrame {
	public static void main(String[] args){
		final Logger lggr = Logger.getLogger(ShowFrame.class.getName());
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lggr.info("--------------------------------------------------");
				lggr.info("创建并显示主界面");
				//new JMainFrame();
				JMainFrame.createInstance();
				lggr.info("主界面创建成功");
				lggr.info("--------------------------------------------------");
			}
		});
	}
}
