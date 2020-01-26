package com.randycorp.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BlokusGrid extends GameObject{
	
	

	public BlokusGrid(int x, int y, ID id, int mapX, int mapY) {
		
		super(x, y, id, new boolean[20][20], mapX, mapY);
		
	}

	public void tick() {
		// TODO Auto-generated method stub

	}
	

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		for(int i=0;i<21;i++)
		{
			g.fillRect(x+32*i, y, 1, 32*20);
			g.fillRect(x, y+32*i, 32*20, 1);
			//g.drawLine(24*20+i*48, 120, 24*20+i*48, 36+48*20);
		}
		
	}
	

}
