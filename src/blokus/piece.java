package blokus;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Piece extends GameObject{
	private Color color;
	private boolean[][] pieceBlueprint;
	private ArrayList<Square> squares;
	private boolean dragging; //this boolean's purpose is to bypass the "cursor on the hitbox" requirement when dragging because you may move your cursor faster than the system can register
	private boolean canMove; //this boolean exists to prevent being able to move 2 pieces if you hover over another one by accident
	private final int setX;
	private final int setY;
	private boolean placedInGrid = false;
	
	public Piece(int x, int y, ID id, Color c, boolean[][] pieceBP) {
		super(x, y, id);
		color = c;
		pieceBlueprint = pieceBP;
		initializeSquares();
		dragging = false;
		canMove = false;
		setX = x;
		setY= y;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		drawPiece(g);
		//drawHitbox(g); //debug purposes
	}
	
	private void drawPiece(Graphics g) {
		for(int r = 0; r < pieceBlueprint.length; r++) {
			for(int c = 0; c < pieceBlueprint[r].length; c++) {
				if(pieceBlueprint[r][c]) {
					g.setColor(color);
					g.fillRect(25 * c + x, 25 * r + y, 25, 25);
					
					g.setColor(Color.BLACK);
					g.drawRect(25 * c + x, 25 * r + y, 25, 25);
				}
			}
		}
	}
	
	public int getOriginalX() {
		return setX;
	}
	
	public int getOriginalY() {
		return setY;
	}
	
	public boolean placedInGrid() {
		return placedInGrid;
	}
	
	public void canPlaceInGrid(boolean newBool) {
		placedInGrid = newBool;
	}
	
	private void drawHitbox(Graphics g) { //for debugging
		g.setColor(Color.orange);
		for(int i = 0; i < squares.size(); i++) {
				g.drawRect(squares.get(i).getX1(), squares.get(i).getY1(), 25, 25);
		}
	}
	
	
	public void initializeSquares() {
		squares = new ArrayList<Square>();
		
		for(int r = 0; r < pieceBlueprint.length; r++) {
			for(int c = 0; c < pieceBlueprint[r].length; c++) {
				if(pieceBlueprint[r][c]) {
					int currX = 25 * c + x;
					int currY = 25 * r + y;
					Square temp = new Square(currX, currY, currX + 25, currY + 25, r, c);
					squares.add(temp);
				}
			}
		}
	}
	
	public boolean containsPoint(int x, int y) { //does the game piece contain my mouse click? (x and y are the mouse clicks)
		if(dragging) {
			return true;
		}
		
		int arrSize = squares.size(); 
		for(int i = 0; i < arrSize; i++) {
			if(squares.get(i).containsPoint(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public void setDragging(boolean newBool) { //changes dragged b/w on and off, for errorless piece drag
		dragging = newBool;
	}
	
	public boolean canMove() { //can you move this piece? (only 1 piece allowed to move at a time)
		return canMove;
	}
	
	public void setCanMove(boolean newBool) { //setting the canMove value for this piece
		canMove = newBool;
	}
	
	public ArrayList<Square> getSquaresCopy() {
		return squares;
	}

	public Square findSquare(int x, int y) {
		for(Square s : squares) {
			if(s.containsPoint(x, y)) {
				return s;
			}
		}
		return null;
	}
	
}
