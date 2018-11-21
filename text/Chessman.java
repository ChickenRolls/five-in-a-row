package text;

public class Chessman {
	private int color;//1-white，0-black
	private boolean placed = false;
	int matchCount = 1;
	
	public Chessman(int color,boolean placed){
		this.color=color;
		this.placed=placed;
	}
	
	public boolean getPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
