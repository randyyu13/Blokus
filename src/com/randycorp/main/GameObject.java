package com.randycorp.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;

public abstract class GameObject {

	protected int x,y;
	protected ID id;
	
	
	
	boolean mat[][];
	Rectangle map[][];
	
	
	

	public GameObject(int x, int y, ID id, boolean[][] mat, int mapX, int mapY)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
		this.mat=mat;
		map=new Rectangle[mapX][mapY];
		
		for(int r=0;r<map.length;r++)
		{
			for(int c=0;c<map[r].length;c++)
			{
				if(mat[r][c])
				{
					map[r][c]= new Rectangle(32*(c)+x, 32*(r)+y, 32, 32);
				}
				else
					map[r][c]= new Rectangle();
			}
		}

	}
	
	public void rehitbox()
	{
		for(int r=0;r<map.length;r++)
		{
			for(int c=0;c<map[r].length;c++)
			{
				if(mat[r][c])
				{
					map[r][c]= new Rectangle(32*(c)+x, 32*(r)+y, 32, 32);
				}
				else
					map[r][c]= new Rectangle();
			}
		}
	}
	
	public boolean mapContains(int eX, int eY)
	{
		for(int r=0;r<map.length;r++)
		{
			for(int c=0;c<map[r].length;c++)
			{
				if(new Area(map[r][c]).contains(eX, eY))
				{
					
					return true;
					
				}
			}
		}
		return false;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	
	
	
}
