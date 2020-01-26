package com.randycorp.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

public class piece extends GameObject{
	Color color;
	public piece(int x, int y, ID id, Color color, boolean mat[][], int mapX, int mapY) {
		super(x, y, id, mat, mapX, mapY);
		// TODO Auto-generated constructor stub
		this.color=color;
		

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void render(Graphics g) {
		
		for(int r=0;r<mat.length;r++)
		{
			for(int c=0;c<mat[r].length;c++)
			{
				if(mat[r][c])
				{
					g.setColor(color);
					g.fillRect(32*(c)+x, 32*(r)+y, 32, 32);
					
					g.setColor(Color.black);
					g.drawRect(32*(c)+x, 32*(r)+y, 32, 32);
				}
			}
		}
		
		/*for(int r=0;r<map.length;r++)
		{
			for(int c=0;c<map[r].length;c++)
			{
				if(mat[r][c])
				{
					g.drawRect((int)map[r][c].getX(), (int)map[r][c].getY(), 32, 32);
					
				}
			}
		}*/
		
		
	}

}
