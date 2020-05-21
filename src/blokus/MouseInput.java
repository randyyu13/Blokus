package blokus;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class MouseInput implements MouseListener, MouseMotionListener{ //TODO: possibly re-generate the handler copy? Helps when I want to push a piece to the front of the handler
	
	private LinkedList<GameObject> handlerCopy;
	private int clickXPos = 0;
	private int clickYPos = 0;
	private int gamePieceXPos = 0;
	private int gamePieceYPos = 0;
	private Square selectedSquare;
	private Handler handler;
	
	public MouseInput() {
		
	}
	
	public MouseInput(Handler handler) {
		this.handler = handler;
		handlerCopy = handler.getHandlerCopy(); //re-generate the handler copy later maybe
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GameObject gameObj = recordClickProperties(e);
		allowPieceToBeMoved(gameObj);
		refactorHandler(gameObj);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		determinePieceCoords(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		movePiece(e);
		highlight(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private GameObject recordClickProperties(MouseEvent e) {
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.piece && ((Piece) gameObj).containsPoint(e.getX(), e.getY()) ) {
				clickXPos = e.getX();
				clickYPos = e.getY();
				gamePieceXPos = gameObj.getX();
				gamePieceYPos = gameObj.getY();
				
				selectedSquare = ((Piece) gameObj).findSquare(clickXPos, clickYPos);
				
				return gameObj;
			}
		}
		return null;
	}
	
	private void refactorHandler(GameObject gameObj) {
		handler.moveToFront(gameObj);
		handlerCopy = handler.getHandlerCopy();
	}
	
	private void allowPieceToBeMoved(GameObject obj) {
		if(obj != null && !((Piece) obj).placedInGrid())
			((Piece) obj).setCanMove(true);
	}
	
	private void determinePieceCoords(MouseEvent e) {
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.grid) {
				for(GridSquare gs : ((Grid) gameObj).getGridSquaresCopy()) {
					gs.setHighlighted(false);
				}
			}
			if(gameObj.id == ID.piece && ((Piece) gameObj).canMove()) {
				Piece pieceObj = ((Piece) gameObj);
				if(!pieceObj.placedInGrid()) {
					pieceObj.initializeSquares(); 
					tryPlacingInGrid(pieceObj, e.getX(), e.getY());
				}
				if(!pieceObj.placedInGrid()) {
					pieceObj.setX(pieceObj.getOriginalX());
					pieceObj.setY(pieceObj.getOriginalY());
				}
				pieceObj.initializeSquares();
				pieceObj.setDragging(false);
				pieceObj.setCanMove(false);
			}
		}
	}
	
	private void movePiece(MouseEvent e) { //move your piece relative to the original offset between mouse and shape
		int differenceInX = gamePieceXPos - clickXPos;
		int differenceInY = gamePieceYPos- clickYPos;
		
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.piece && ((Piece) gameObj).containsPoint(e.getX(), e.getY()) && ((Piece) gameObj).canMove()) { //is it a piece? does the piece contain where I'm clicking? can you move it currently? (you're only allowed to move 1 piece at a time)
				gameObj.setX(e.getX() + differenceInX);
				gameObj.setY(e.getY() + differenceInY);
				((Piece) gameObj).setDragging(true);
			}
		}
	}
	
	private void highlight(MouseEvent e) {
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.grid) {
				highlightHelper(e, ((Grid) gameObj));
			}
		}
	}
	
	private void highlightHelper(MouseEvent e, Grid grid) { //this code is very messy lol
		//System.out.println("hello");
		ArrayList<GridSquare> gridSquares = grid.getGridSquaresCopy();
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.piece && ((Piece) gameObj).canMove()) {
				LinkedList<GridSquare> highlightedSquares = new LinkedList<GridSquare>();
				LinkedList<Integer> highlightedSquaresIndices = new LinkedList<Integer>();
				for(Square pieceSquare : ((Piece) gameObj).getSquaresCopy()) {
					GridSquare currentSquare = grid.findSquare(e.getX(), e.getY());
					if(currentSquare != null) {
						int relativeC = currentSquare.getMatrixPosC() + pieceSquare.getMatrixPosC() - selectedSquare.getMatrixPosC();
						int relativeR = currentSquare.getMatrixPosR() + pieceSquare.getMatrixPosR() - selectedSquare.getMatrixPosR();
						int highlightedSquare = gridCoordsToIndex(relativeR, relativeC);
						if(relativeC >= 0 && relativeC < 20 && relativeR >= 0 && relativeR < 20) {
							highlightedSquares.add(gridSquares.get(highlightedSquare));
							highlightedSquaresIndices.add(highlightedSquare);
						}
					} else {
						for(GridSquare gs : gridSquares) {
							gs.setHighlighted(false);
						}
					}
				}
				for(GridSquare gs : highlightedSquares) {
					gs.setHighlighted(true);
				}
				for(int i = 0; i < gridSquares.size(); i++) {
					if(!highlightedSquaresIndices.contains(i)) {
						gridSquares.get(i).setHighlighted(false);
					}
				}
			}
		}
	}
	
	private void tryPlacingInGrid(Piece piece, int xReleased, int yReleased) {
		ArrayList<Square> pieceSquares = piece.getSquaresCopy();
		LinkedList<GridSquare> occupiedSquares = new LinkedList<GridSquare>();
		for(Square pieceSquare : pieceSquares) {
			int x = xReleased + 25 * (pieceSquare.getMatrixPosC() - selectedSquare.getMatrixPosC()); //coords of all the separate squares in the piece
			int y = yReleased + 25 * (pieceSquare.getMatrixPosR() - selectedSquare.getMatrixPosR()); //^
			if(!canFitPieceSquare(x, y, occupiedSquares)) {
				piece.canPlaceInGrid(false);
				return;
			}
		}
		for(GridSquare temp : occupiedSquares) {
			temp.setOccupied(true);
		}
		piece.canPlaceInGrid(true);
		//now place the piece in the grid.
		//System.out.println(pieceSquares.get(0));
		placePiece(occupiedSquares.get(0), pieceSquares.get(0), piece);
	}
	
	private boolean canFitPieceSquare(int x, int y, LinkedList<GridSquare> occupiedSquares) {
		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.grid) {
				Grid grid = ((Grid) gameObj);
				GridSquare gs = grid.findSquare(x, y);
				if(isOpenGridSquare(gs, grid.getGridSquaresCopy(), occupiedSquares)) {
					return true;
				}
				return false;
			}
		}
		return false; //arbitary
	}
	
	private boolean isOpenGridSquare(GridSquare gs, ArrayList<GridSquare> gridSquares, LinkedList<GridSquare> occupiedSquares) {
		if(gs != null && !gs.isOccupied()) {
			LinkedList<Integer> indices = new LinkedList<Integer>();
			if(gs.getMatrixPosC() - 1 > 0) 
				indices.add(gridCoordsToIndex(gs.getMatrixPosR(), gs.getMatrixPosC() - 1)); //left
			if(gs.getMatrixPosC() < 19)
				indices.add(gridCoordsToIndex(gs.getMatrixPosR(), gs.getMatrixPosC() + 1)); //right
			if(gs.getMatrixPosR() > 0)
				indices.add(gridCoordsToIndex(gs.getMatrixPosR() - 1, gs.getMatrixPosC())); //top
			if(gs.getMatrixPosR() < 19)
				indices.add(gridCoordsToIndex(gs.getMatrixPosR() + 1, gs.getMatrixPosC())); //bottom
			for(int i : indices) {
				if(gridSquares.get(i).isOccupied()) {
					return false;
				}
			}
			occupiedSquares.add(gs);
			return true;
		}
		return false;
	}
	
	private int gridCoordsToIndex(int r, int c) {
		return 20 * r + c;
	}
	
	private void placePiece(GridSquare occupiedSquare, Square topLeftSquare, Piece piece) {

		for(GameObject gameObj : handlerCopy) {
			if(gameObj.id == ID.grid) {
				Grid grid = ((Grid) gameObj);
				int newX = occupiedSquare.getX1() + 25 * (0 - topLeftSquare.getMatrixPosC());
				int newY = occupiedSquare.getY1() + 25 * (0 - topLeftSquare.getMatrixPosR());
				piece.setX(newX);
				piece.setY(newY);
			}
		}
	}

}
