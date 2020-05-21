package blokus;

public class GridSquare extends Square{
	private boolean occupied;
	private boolean highlighted;

	public GridSquare(int x1, int y1, int x2, int y2, int r, int c) {
		super(x1, y1, x2, y2, r, c);
		occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
}
