package com.netease.B6646_zx_UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SouthOfCmdPanel extends JPanel{
	private static final long serialVersionUID = 6004325947706297394L;
	
	
	public SouthOfCmdPanel(Dimension dimension){
		setPreferredSize(dimension);
		
		add(new JButton("southOfCmdPanel"));
		setBackground(Color.green);
	}

}
