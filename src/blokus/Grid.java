package blokus;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Grid extends GameObject{
	final boolean f = false;
	final boolean t = true;
	
	private ArrayList<Piece> pieces;
	private ArrayList<GridSquare> gridSquares;
	//size of grid is 500x500px and is 20 by 20
	
	public Grid(int x, int y, ID id) {
		super(x, y, id);
		this.pieces = new ArrayList<Piece>();
		initializeSquares(x, y);
	}
	
	public Grid(int x, int y, ID id, ArrayList<Piece> pieces) {
		super(x, y, id);
		
		this.pieces = new ArrayList<Piece>();
		for(int i = 0; i < pieces.size(); i++) {
			this.pieces.add(pieces.get(i));
		}
		
		initializeSquares(x, y);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		drawGrid(g);
		highlight(g);
		//drawHitbox(g); //debug purposes
	}
	
	public GridSquare findSquare(int x, int y) {
		for(GridSquare gs : gridSquares) {
			if(gs.containsPoint(x, y)) {
				return gs;
			}
		}
		return null;
	}
	
	public ArrayList<GridSquare> getGridSquaresCopy() {
		ArrayList<GridSquare> copy = new ArrayList<GridSquare>();
		for(GridSquare gs : gridSquares) {
			copy.add(gs);
		}
		return copy;
	}
	
	private void drawGrid(Graphics g) {
		g.setColor(Color.BLACK);
		
		g.drawRect(x, y, 500, 500); //overall contour of the grid
		int VPos = x + 25; //where you start drawing the first vertical line
		int HPos = y + 25; //where you start drawing the second vertical line
		
		int endVLine = y + 500;
		int endHLine = x + 500;
		
		for(int i = 0; i < 19; i++) {
			g.drawLine(VPos, y, VPos, endVLine); //draw vertical line
			g.drawLine(x, HPos, endHLine, HPos); //draw horizontal line
			
			VPos += 25;
			HPos += 25;
		}
	}
	
	private void highlight(Graphics g) {
		g.setColor(Color.gray);
		for(GridSquare gs : gridSquares) {
			if(gs.isHighlighted()) {
				g.fillRect(gs.getX1() + 1, gs.getY1() + 1, 24, 24);
			}
		}
	}
	
	private void drawHitbox(Graphics g) { //for debugging
		g.setColor(Color.orange);
		for(int i = 0; i < gridSquares.size(); i++) {
				g.drawRect(gridSquares.get(i).getX1(), gridSquares.get(i).getY1(), 25, 25);
		}
	}
	
	private void initializeSquares(int x, int y) {
		
		gridSquares = new ArrayList<GridSquare>();
		
		for(int r = 0; r < 20; r++) {
			for(int c = 0; c < 20; c++) {
				int currX = 25 * c + x;
				int currY = 25 * r + y;
				GridSquare temp = new GridSquare(currX, currY, currX + 25, currY + 25, r, c);
				gridSquares.add(temp); //add new grid square to the list of squares
			}
		}
	}

}
