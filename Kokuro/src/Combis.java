import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Combis {

	int[][] posArray =  new int[511][11];
	
	public Combis() {
		
		String dict = "HintSheet.txt";
		
		int i = 0;

		  try {
	            BufferedReader in = new BufferedReader(new FileReader(dict));
	            String str;
	            while ((str = in.readLine()) != null) {
	            	
	            	String[] result = str.split(",");
	            	posArray[i][0] = Integer.parseInt(result[0]);
	            	posArray[i][1] = Integer.parseInt(result[1]);
	            	String[] resultnummers = result[2].split("\\+");
	        		int j = 2;
	            	for(String a : resultnummers){
	            		posArray[i][j] = Integer.parseInt(a);
	            		j++;
	            	}
	            	i++; 	 
	            }

	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	}
	
	public ArrayList<Integer> getFirstValues(int spots, int sum){
		ArrayList<Integer> values = new ArrayList<Integer>();
		int row = 0;
			while(row < posArray.length && posArray[row][0] <= spots){
				if(posArray[row][0] == spots && posArray[row][1] == sum){
					for(int ii = 2 ; ii < 11; ii++){
						if(posArray[row][ii] != 0 ){
							values.add(posArray[row][ii]);
						}
					}
				}
				row++;
			}
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.addAll(values);
			values.clear();
			values.addAll(hs);
		return values;
	}
}

