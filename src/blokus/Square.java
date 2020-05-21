package blokus;

public class Square {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int matrixPosR;
	private int matrixPosC;
	
	public Square(int x1, int y1, int x2, int y2, int r, int c) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		matrixPosR = r;
		matrixPosC = c;
	}
	
	public boolean containsPoint(int x, int y) {//does the square contain the point x,y?
		return x1 <= x && y1 <= y && x2 >= x && y2 >= y;
	}

	public int getX1() {
		return x1;
	}

	public int getMatrixPosR() {
		return matrixPosR;
	}

	public void setMatrixPosR(int matrixPosR) {
		this.matrixPosR = matrixPosR;
	}

	public int getMatrixPosC() {
		return matrixPosC;
	}

	public void setMatrixPosC(int matrixPosC) {
		this.matrixPosC = matrixPosC;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	
}
