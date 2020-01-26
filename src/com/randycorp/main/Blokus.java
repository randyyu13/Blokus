package com.randycorp.main;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.image.BufferStrategy;

public class Blokus extends Canvas implements Runnable{
private static final long serialVersionUID = 8320999467410371392L;
	
	public static final int WIDTH = 1920, HEIGHT = WIDTH/16*9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	  int screenX = 0;
	  int screenY = 0;
	  int myX = 0;
	  int myY = 0;
	  
	  boolean f=false;
	  boolean t=true;
	  
	  boolean bool[]= {f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f};
	  ID id[]={ID.gm1,ID.gd1,ID.gt1,ID.gt2,ID.gtr1,ID.gtr2,ID.gtt3,ID.gtt4,ID.gtt5,ID.gp1,ID.gp2,ID.gp3,ID.gp4,ID.gp5,ID.gp6,ID.gp7,ID.gp8,ID.gp9,ID.gp10,ID.gp11,ID.gp12,ID.rm1,ID.rd1,ID.rt1,ID.rt2,ID.rtr1,ID.rtr2,ID.rtt3,ID.rtt4,ID.rtt5,ID.rp1,ID.rp2,ID.rp3,ID.rp4,ID.rp5,ID.rp6,ID.rp7,ID.rp8,ID.rp9,ID.rp10,ID.rp11,ID.rp12};
	  
	  boolean pieceMat[][][]= {
			  //1
			  {{t,f,f,f,f},
		  	   {f,f,f,f,f},
		  	   {f,f,f,f,f},
		  	   {f,f,f,f,f},
		  	   {f,f,f,f,f}},
			  //2
			  {{t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //3
			  {{t,t,f,f,f},
			   {f,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //4
			  {{t,t,t,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //5
			  {{t,t,f,f,f},
			   {t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //6
			  {{f,t,f,f,f},
			   {t,t,t,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //7
			  {{t,t,t,t,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //8
			  {{f,f,t,f,f},
			   {t,t,t,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //9
			  {{f,t,t,f,f},
			   {t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
		  		//10
		  	  {{t,f,f,f,f},
		  	   {t,t,t,t,f},
		  	   {f,f,f,f,f},
		  	   {f,f,f,f,f},
		  	   {f,f,f,f,f}},
		  	  //11	  
			  {{f,f,t,f,f},
			   {f,f,t,f,f},
			   {f,t,t,t,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //12
			  {{t,f,f,f,f},
			   {t,f,f,f,f},
			   {t,t,t,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //13
			  {{f,t,t,t,f},
			   {t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //14
			  {{f,f,t,f,f},
			   {t,t,t,f,f},
			   {t,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //15
			  {{t,f,f,f,f},
			   {t,f,f,f,f},
			   {t,f,f,f,f},
			   {t,f,f,f,f},
			   {t,f,f,f,f}},
			  //16
			  {{t,f,f,f,f},
			   {t,t,f,f,f},
			   {t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //17
			  {{f,t,t,f,f},
			   {t,t,f,f,f},
			   {t,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //18
			  {{t,t,f,f,f},
			   {t,f,f,f,f},
			   {t,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //19
			  {{f,t,t,f,f},
			   {t,t,f,f,f},
			   {f,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //20
			  {{f,t,f,f,f},
			   {t,t,t,f,f},
			   {f,t,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
			  //21
			  {{f,t,f,f,f},
			   {t,t,t,t,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f},
			   {f,f,f,f,f}},
		  
		  };

	public Blokus()
	{
		handler = new Handler();
		
		addMouseListener(new MouseListener() {

			@Override
		    public void mouseClicked(MouseEvent e) { }

		    @Override
		    public void mousePressed(MouseEvent e) {
		    	for(int i=0;i<handler.object.size();i++)
				{
					GameObject gameObj=handler.object.get(i);
					
					for(int o=0;o<bool.length;o++)
					{
					uponPress(gameObj,e.getX(),e.getY(),o,id[o]);
					
					}
					
					//System.out.println(gameObj.mapContains(e.getX(), e.getY()));
					if(gameObj.getId()==ID.BlokusGrid&&gameObj.mapContains(e.getX(), e.getY()))
	    			{
	    				System.out.println("haha");
	    			}
					
				}
		    }

		    @Override
		    public void mouseReleased(MouseEvent e) { 
		    	for(int i=0;i<handler.object.size();i++)
	    		{
	    			GameObject gameObj=handler.object.get(i);
	    			
	    			for(int o=0;o<bool.length;o++)
					{
					uponRelease(gameObj,e.getX(),e.getY(),o,id[o]);
					
					}
	    			
	    			
	    			
	    		}
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) { }

		    @Override
		    public void mouseExited(MouseEvent e) { }
		    });
		
		
		    addMouseMotionListener(new MouseMotionListener() {

		    	@Override
		    	public void mouseDragged(MouseEvent e) {
		    		// TODO Auto-generated method stub
		    		for(int i=0;i<handler.object.size();i++)
		    		{
		    			GameObject gameObj=handler.object.get(i);
		    			
		    			for(int o=0;o<bool.length;o++)
						{
						uponDrag(gameObj,e.getX(),e.getY(),o,id[o]);
						}
		    			
		    			
		    		}
		    		
		    	}

		    	@Override
		    	public void mouseMoved(MouseEvent arg0) {
		    		// TODO Auto-generated method stub
		    		
		    	}

		    });
		
		
		
		handler.object.add(new BlokusGrid(32*20, 32*5, ID.BlokusGrid, 20, 20));
		
		int yModifier=0;
		for(int i=1;i<=21;i++)
		{
			if((i-1)%4==0&&i!=1)
			{
				yModifier++;
			}
			handler.object.add(new piece(32*5*((i-1)%4)+16, (int) (32*5.5*yModifier)+50, id[i-1], Color.GREEN, pieceMat[i-1],5,5));
		}
		
		yModifier=0;
		for(int i=1;i<=21;i++)
		{
			if((i-1)%4==0&&i!=1)
			{
				yModifier++;
			}
			handler.object.add(new piece(32*5*((i-1)%4)+1332, (int) (32*5.5*yModifier)+50, id[i-1+21], Color.RED, pieceMat[i-1],5,5));
		}

		

		
		new Window(WIDTH,HEIGHT,"Blokus",this);
		
		
	}
	
	public void uponPress(GameObject gameObj, int x, int y, int b, ID id)
	{
		if(gameObj.getId()==id&&(gameObj.mapContains(x,y)))
		{
			screenX = x;
			screenY = y;

			myX = gameObj.getX();
  			myY = gameObj.getY();
  			
  			
  			System.out.println("Square Detected");
  			bool[b]=true;
		}
	}
	
	public void uponRelease(GameObject gameObj, int x, int y, int b, ID id)
	{
		if(gameObj.getId()==id&&(gameObj.mapContains(x,y)))
		{
			
			gameObj.rehitbox();
     
     for(int r=0;r<gameObj.map.length;r++)
		{
			for(int c=0;c<gameObj.map[r].length;c++)
			{
				if(gameObj.mat[r][c])
				{
					gameObj.map[r][c]= new Rectangle((int)gameObj.map[r][c].getX(),(int)gameObj.map[r][c].getY(),32,32);
				}
			}
		}
     bool[b]=false;
     
     
		}
	}
	public void uponDrag(GameObject gameObj, int x, int y, int b, ID id)
	{
		if(gameObj.getId()==id&&(gameObj.mapContains(x,y))&&bool[b])
		{
			
	 int deltaX = x - screenX;
     int deltaY = y - screenY;

     gameObj.setX(myX + deltaX);
     gameObj.setY(myY + deltaY);
     
     for(int r=0;r<gameObj.map.length;r++)
		{
			for(int c=0;c<gameObj.map[r].length;c++)
			{
				if(gameObj.mat[r][c])
				{
					gameObj.map[r][c]= new Rectangle(0,0,1920,1080);
				}
			}
		}
		}
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running=true;
	}
	public synchronized void stop()
	{
		try{
			thread.join();
			running=false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run(){
		 long lastTime = System.nanoTime();
	        double amountOfTicks = 60.0;
	        double ns = 100000000 / amountOfTicks;
	        double delta = 0;
	        long timer = System.currentTimeMillis();
	        int frames = 0;
	        while(running)
	        {
	                    long now = System.nanoTime();
	                    delta += (now - lastTime) / ns;
	                    lastTime = now;
	                    while(delta >=1)
	                            {
	                                tick();
	                                delta--;
	                            }
	                            if(running)
	                                render();
	                            frames++;
	                            
	                            if(System.currentTimeMillis() - timer > 1000)
	                            {
	                                timer += 1000;
	                                System.out.println("FPS: "+ frames);
	                                frames = 0;
	                            }
	        }
	                stop();
	}
	private void tick(){
		handler.tick();
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
		this.createBufferStrategy(3);
		return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		new Blokus();
	}
}