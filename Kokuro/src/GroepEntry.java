import java.util.ArrayList;


public class GroepEntry {
	public int vsum = 0;
	public int vspots = 0;
	public int hsum = 0;
	public int hspots = 0;
	public ArrayList<Integer> numbers;
	public int x;
	public int y;

		public GroepEntry(int x, int y, int vspots , int vsum, int hspots, int hsum, ArrayList<Integer> numbers){
			this.x = x;
			this.y = y;
			this.vsum = vsum;
			this.vspots = vspots;
			this.hsum = hsum;
			this.hspots = hspots;
			this.numbers = numbers;
	}	
}
