package util;

public class PanelPosition {

	private int x = 0;
	private int y = 0;
	
	public PanelPosition(int x, int y) {
		setPanelPosition(x, y);
	}
	
	public PanelPosition getPanelPosition() {
		return this;
	}
	
	public void setPanelPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
		else {
			x = 0;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
		else {
			y = 0;
		}
	}
	
	
	public void moveX(int x) {
		this.x += x;
		if (this.x < 0){
			this.x = 0;
		}
	}
	
	public void moveY(int y) {
		this.y += y;
		if (this.y < 0) {
			this.y = 0;
		}
	}
	
}
