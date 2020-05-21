package blokus;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Mainframe extends Canvas implements Runnable{
	private static final long serialVersionUID = -8417618763703147898L;
	
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	
	public static final int WIDTH = 1350, HEIGHT = WIDTH/4*3;
	
	private final boolean t = true;
	private final boolean f = false;
	private final boolean pieceTypes[][][] = 	{{{t,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //1
							  					 {{t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //2
							  					 {{t,t,f,f,f}, {f,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //3
							  					 {{t,t,t,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //4
							  					 {{t,t,f,f,f}, {t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //5
							  					 {{f,t,f,f,f}, {t,t,t,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //6
							  					 {{t,t,t,t,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //7
							  					 {{f,f,t,f,f}, {t,t,t,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //8
							  					 {{f,t,t,f,f}, {t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //9
							  					 {{t,f,f,f,f}, {t,t,t,t,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //10
							  					 {{f,f,t,f,f}, {f,f,t,f,f}, {f,t,t,t,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //11
							  					 {{t,f,f,f,f}, {t,f,f,f,f}, {t,t,t,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //12
							  					 {{f,t,t,t,f}, {t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //13
							  					 {{f,f,t,f,f}, {t,t,t,f,f}, {t,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //14
							  					 {{t,f,f,f,f}, {t,f,f,f,f}, {t,f,f,f,f}, {t,f,f,f,f}, {t,f,f,f,f}}, //15
							  					 {{t,f,f,f,f}, {t,t,f,f,f}, {t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //16
							  					 {{f,t,t,f,f}, {t,t,f,f,f}, {t,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //17
							  					 {{t,t,f,f,f}, {t,f,f,f,f}, {t,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //18
							  					 {{f,t,t,f,f}, {t,t,f,f,f}, {f,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //19
							  					 {{f,t,f,f,f}, {t,t,t,f,f}, {f,t,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //20
							  					 {{f,t,f,f,f}, {t,t,t,t,f}, {f,f,f,f,f}, {f,f,f,f,f}, {f,f,f,f,f}}, //21
							  					 };
	
	public Mainframe() {
		handler = new Handler();
		new Window(WIDTH, HEIGHT, "Blokus", this);
		
		handler.addObject(new Grid((WIDTH - 500)/2, (HEIGHT - 500)/2, ID.grid));
		
		placePieces(handler, Color.GREEN, 20);
		placePieces(handler, Color.RED, 980);
		
		MouseInput mi = new MouseInput(handler);
		this.addMouseListener(mi);
		this.addMouseMotionListener(mi);
	}
	
	@Override
	public void run() {
		 long lastTime = System.nanoTime();
	        double amountOfTicks = 60.0;
	        double ns = 100000000 / amountOfTicks;
	        double delta = 0;
	        long timer = System.currentTimeMillis();
	        int frames = 0;
	        while(running) {
	        	long now = System.nanoTime();
	            delta += (now - lastTime) / ns;
	            lastTime = now;
	            while(delta >=1) {
	            	tick();
	            	delta--;
	            }
	            
	            if(running)
	            	render();
	            frames++;
	                            
	            if(System.currentTimeMillis() - timer > 1000) {
	            	timer += 1000;
	            	System.out.println("FPS: "+ frames);
	            	frames = 0;
	            }
	        }
	        stop();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Mainframe();
	}
	
	private void placePieces(Handler handler, Color color, int startingX) {
		int i = 0;
		for(int y = 0; y < 7; y++) {
			for(int x = 0; x < 3; x++) {
				handler.addObject(new Piece(120 * x + startingX, 140 * y, ID.piece, color, pieceTypes[i]));
				i++;
				if(i == 21)
					break;
			}
		}
	}

}


