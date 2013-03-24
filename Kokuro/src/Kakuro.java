import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class Kakuro {
	
	public static int puzzleSize = 6;
	static Combis combis = new Combis();
	
	public static void main(String[] args) {
		
		
		if(args.length == 1){
			puzzleSize = Integer.parseInt(args[0]);
		} 
		
		String[][] kField = CreateNewFieldToSolve(puzzleSize);
		printField(kField);
		DecomposeField(kField);
//		System.out.println("The size of the square Kakuro is: " + kaku.blackwhiteBoxFinal[0].length);
//		for(int ii = 0 ; ii < kaku.blackwhiteBoxFinal[0].length; ii++){
//			for( int jj = 0; jj < kaku.blackwhiteBoxFinal[0].length; jj++){
//				System.out.print(kaku.blackwhiteBoxFinal[ii][jj]);
//				for(int kk = 0 ; kk < 6 - kaku.blackwhiteBoxFinal[ii][jj].length(); kk++){
//					System.out.print(" ");
//				}
//				
//			}
//			System.out.println("");
//		}
		
//		ArrayList<Integer> values = combis.getFirstValues(3, 9);
//		
//		for( int d : values){
//			System.out.print(d + " ");
//		}
//		System.out.println("");
		
		
	}
	
	public Kakuro (int PuzzleSize){
		GenerateNewPuzzle(PuzzleSize);
		
	}
	
//	public static String[][] firstStep(String[][] kField){
//		for(int i = 0 ; i < kField[0].length ; i++){
//			for(int j = 0 ; j < kField[0].length ; j++){
//				if(kField[i][j] == "?"){
//					new GroepEntry(i,j, )
//				}
//			}
//		}
//		
//		return kField;
//	}
	
	public static void DecomposeField(String[][] kField){
		for(int ii = 0 ; ii < kField[0].length; ii++){
			for( int jj = 0; jj < kField[0].length; jj++){
				if(kField[ii][jj].indexOf("\\") == 0){
					int[] spotsumH = getHSpots(kField, ii, jj);
					ArrayList<Integer> coms = combis.getFirstValues(spotsumH[0], spotsumH[1]);
					GroepEntry a = new GroepEntry(ii, jj, 0,0,spotsumH[0],spotsumH[1], coms );
					System.out.print("H:");
					System.out.print(a.numbers);
					System.out.println("");
					}else{
				
				if(kField[ii][jj].indexOf("\\") == kField[ii][jj].length() - 1){
					int[] spotsumV = getVSpots(kField, ii, jj);
					ArrayList<Integer> coms = combis.getFirstValues(spotsumV[0], spotsumV[1]);
					GroepEntry a = new GroepEntry(ii, jj,spotsumV[0],spotsumV[1], 0, 0, coms );
					System.out.print("V: ");
					System.out.print(a.numbers);
					System.out.println("");
				}else{
//			System.out.print("length: " + (kField[ii][jj].split("\\\\")).length + " ");
				if((kField[ii][jj].split("\\\\")).length == 2){
					int[] spotsumVH = getVHSpots(kField, ii,jj);
				}
				}
				}
			}
		}
	}
	
	public static int[] getVHSpots(String[][] kField, int ii, int jj){
		String[] temp = kField[ii][jj].split("\\\\");
		//System.out.print(temp[0]);
		//System.out.print(temp[1]);
		int hsum = Integer.parseInt(temp[1]);
		int vsum = Integer.parseInt(temp[0]);
//		System.out.print("vsum " + vsum + " ");
//		System.out.print("hsum " + hsum + " ");
		int hspots = 0;
		int hlcount = 1;
		while(jj+hlcount < kField[0].length && kField[ii][jj+hlcount].equals("?")){
			hspots++;
			hlcount++;
		}
		int vspots = 0;
		int vlcount = 1;
		while(ii+vlcount < kField[0].length && kField[ii+vlcount][jj].equals("?")){
			vspots++;
			vlcount++;
		}
//		System.out.print("vspots " +  vspots + " ");
//		System.out.print("hspots " +  hspots + " ");
		int[] values = {hspots, hsum, vspots, vsum};
		return values;
	}
	
	public static int[] getHSpots(String[][] kField, int ii, int jj){
		String temp = kField[ii][jj];
		int hsum = Integer.parseInt(temp.replace("\\", ""));
		//System.out.print(hsum + " ");
		int hspots = 0;
		int hlcount = 1;
		while(jj+hlcount < kField[0].length && kField[ii][jj+hlcount].equals("?")){
			hspots++;
			hlcount++;
		}
		int[] values = {hspots, hsum};
		return values;
	}
	
	public static int[] getVSpots(String[][] kField, int ii, int jj){
		String temp = kField[ii][jj];
		int vsum = Integer.parseInt(temp.replace("\\", ""));
		//System.out.print(vsum + " ");
		int vspots = 0;
		int vlcount = 1;
		while(ii+vlcount < kField[0].length && kField[ii+vlcount][jj].equals("?")){
			vspots++;
			vlcount++;
		}
		int[]values = {vspots, vsum};
		return values;
	}

	
	
	public static String[][] CreateNewFieldToSolve(int pSize){
		Kakuro kaku = new Kakuro(pSize);
		String[][] kField = new String[pSize][pSize];
		System.out.println("The size of the square Kakuro is: " + kaku.blackwhiteBoxFinal[0].length);
		for(int ii = 0 ; ii < kaku.blackwhiteBoxFinal[0].length; ii++){
		for( int jj = 0; jj < kaku.blackwhiteBoxFinal[0].length; jj++){
				if(isInteger(kaku.blackwhiteBoxFinal[ii][jj])){
					kField[ii][jj] = "?";
				} else { 
					kField[ii][jj] = kaku.blackwhiteBoxFinal[ii][jj];
				}
			}
		}
		return kField;
	}
	
	public static void printField(String[][] kField){
		for(int ii = 0 ; ii < kField[0].length; ii++){
			for( int jj = 0; jj < kField[0].length; jj++){
				String pr = kField[ii][jj];
				System.out.print(pr);
				for(int kk = 0 ; kk < 6 - kField[ii][jj].length(); kk++){
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
	

	
	
	//The class isInteger is from:
	//http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	//http://stackoverflow.com/users/330057/corsika
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public void GenerateNewPuzzle(int pSize)
	{

		textField = new JTextField[pSize][pSize];
		textFieldTemp = new JTextField[pSize][pSize];
		textFieldSolution = new JTextField[pSize][pSize];
		labelField = new JLabel[pSize][pSize];
		blackwhiteBox = new String[pSize][pSize];
		blackwhiteBoxH = new String[pSize][pSize];
		blackwhiteBoxV = new String[pSize][pSize];
		blackwhiteBoxFinal = new String[pSize][pSize];
		blackwhiteBoxYesNo = new String[pSize][pSize];

		int i = 0;
		int j = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				blackwhiteBox[i][j] = "0";
				blackwhiteBoxYesNo[i][j] = "Y";
				blackwhiteBoxFinal[i][j] = "";
				textField[i][j] = new JTextField("");

				textFieldTemp[i][j] = new JTextField("");				
				textFieldSolution[i][j] = new JTextField("");
				labelField[i][j] = new JLabel("X",JLabel.CENTER);
			}
		}

		i = 0;		
		for (i=0;i<pSize;i++)
		{
			blackwhiteBox[i][0] = "X";
			blackwhiteBoxYesNo[i][0] = "N";			
		}		

		j = 0;		
		for (j=0;j<pSize;j++)
		{
			blackwhiteBox[0][j] = "X";
			blackwhiteBoxYesNo[0][j] = "N";			
		}		

		String randomNumberStr = "";
		int randBlackBox = (pSize - 1)/2;
		i = 0;
		j = 0;
		String alphaval = "";
		for (i=1;i<pSize;i++)
		{
			for(j=0;j<randBlackBox;j++)
			{
				int randTemp = randGen.nextInt(pSize);
				blackwhiteBox[i][randTemp] = "X";
				blackwhiteBoxYesNo[i][randTemp] = "N";				
			}
		}

		int count = 0;
		int notX = 0;
		for (i=1;i<pSize;i++)
		{
			count = 0;
			for(j=0;j<pSize;j++)
			{
				if (blackwhiteBox[i][j].equals("X"))
				{
					count = 0;
				}
				if(blackwhiteBox[i][j].equals("0"))
				{
					count ++;
					//if (count >= 9)
					if (count >= 8)
					{
						blackwhiteBox[i][j] = "X";
						blackwhiteBoxYesNo[i][j] = "N";	
					}
				}
			}
		}

		for (i=1;i<pSize;i++)
		{
			count = 0;
			for(j=0;j<pSize;j++)
			{
				if (blackwhiteBox[j][i].equals("X"))
				{
					count = 0;
				}
				if(blackwhiteBox[j][i].equals("0"))
				{
					count ++;

					if (count >= 8)
					{
						blackwhiteBox[j][i] = "X";
						blackwhiteBoxYesNo[j][i] = "N";	
					}
				}
			}
		}


		int si=0, sj=0, sk=0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				if (blackwhiteBox[i][j] == "0")
				{
					int randTemp = randGen.nextInt(10);	

					int upval[] = new int[pSize];
					int downval[] = new int[pSize];
					int rightval[] = new int[pSize];
					int leftval[] = new int[pSize];	
						
					for(int cnt=0;cnt<pSize;cnt++)
					{
						upval[cnt] = 0;
						downval[cnt] = 0;
						leftval[cnt] = 0;
						rightval[cnt] = 0;
					}															
					
					si = i;
					sj = j;
					sk = 0;
					si --;
					while((blackwhiteBox[si][sj] != "X")&&(blackwhiteBox[si][sj] != "0"))
					{
						upval[sk] = Integer.parseInt(blackwhiteBox[si][sj]);
						sk ++;
						si --;
						if (si < 0)	
						{
							break;
						}
					}

					si = i;
					sj = j;
					sk = 0;
					si ++;
					if (si < pSize)
					{
						while((blackwhiteBox[si][sj] != "X")&&(blackwhiteBox[si][sj] != "0"))
						{
							downval[sk] = Integer.parseInt(blackwhiteBox[si][sj]);
							sk ++;
							si ++;
							if (si > pSize)	
							{
								break;
							}
						}
					}

		
					si = i;
					sj = j;
					sk = 0;
					sj --;
					while((blackwhiteBox[si][sj] != "X")&&(blackwhiteBox[si][sj] != "0"))
					{
						leftval[sk] = Integer.parseInt(blackwhiteBox[si][sj]);
						sk ++;
						sj --;
						if (sj < 0)	
						{
							break;
						}
					}
					

	
					si = i;
					sj = j;
					sk = 0;
					sj ++;
					if (sj < pSize)
					{
						while((blackwhiteBox[si][sj] != "X")&&(blackwhiteBox[si][sj] != "0"))
						{
							rightval[sk] = Integer.parseInt(blackwhiteBox[si][sj]);
							sk ++;
							sj ++;
							if (sj > pSize)	
							{
								break;
							}
						}
					}


					if((CheckUnique(randTemp,upval,pSize,i,j)) || (CheckUnique(randTemp,downval,pSize,i,j))
						|| (CheckUnique(randTemp,rightval,pSize,i,j)) || (CheckUnique(randTemp,leftval,pSize,i,j)))
						{
							randTemp = 0;
						}
					
					
					while(randTemp == 0)
					{
						randTemp = randGen.nextInt(10);		
						if((CheckUnique(randTemp,upval,pSize,i,j)) || (CheckUnique(randTemp,downval,pSize,i,j))
							|| (CheckUnique(randTemp,rightval,pSize,i,j)) || (CheckUnique(randTemp,leftval,pSize,i,j)))
							{
								randTemp = 0;
							}
					}	
					if(overlimit == -1)		
						blackwhiteBox[i][j]= ""+randTemp;
				}
			}
			
		}
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				blackwhiteBoxH[i][j] = blackwhiteBox[i][j];
				blackwhiteBoxV[i][j] = blackwhiteBox[i][j];
			}
		}
		int ni =0, nj = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				int cjSum = 0;
				ni = i;
				nj = j;
				while(blackwhiteBox[ni][nj] != "X")
				{
					cjSum = cjSum + Integer.parseInt(blackwhiteBox[ni][nj]);
					nj += 1;
					if(nj >= pSize)
					{
						break;
					}
				}
				if (cjSum > 0)
				{
					blackwhiteBoxH[i][j-1] = "z"+cjSum;
					j = nj;
				}
			}
		}

		ni =0; nj = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				int ciSum = 0;
				ni = i;
				nj = j;
				while(blackwhiteBox[ni][nj] != "X")
				{
					ciSum = ciSum + Integer.parseInt(blackwhiteBox[ni][nj]);
					ni += 1;
					if(ni >= pSize)
					{
						break;
					}
				}
				if (ciSum > 0)
				{
					if (blackwhiteBoxV[i-1][j] == "X")
					{
						blackwhiteBoxV[i-1][j] = "z"+ciSum;
					}
				}
			}
		}

		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				if((blackwhiteBoxH[i][j].substring(0,1).equals("z")) && (blackwhiteBoxV[i][j].substring(0,1).equals("z")))
				{
					blackwhiteBoxFinal[i][j] = blackwhiteBoxV[i][j].substring(1,blackwhiteBoxV[i][j].length())+
						"\\"+blackwhiteBoxH[i][j].substring(1,blackwhiteBoxH[i][j].length());
				}else if ((blackwhiteBoxH[i][j].substring(0,1).equals("z")) && !(blackwhiteBoxV[i][j].substring(0,1).equals("z")))
				{
					blackwhiteBoxFinal[i][j] = "\\"+blackwhiteBoxH[i][j].substring(1,blackwhiteBoxH[i][j].length());
					
				}else if (!(blackwhiteBoxH[i][j].substring(0,1).equals("z")) && (blackwhiteBoxV[i][j].substring(0,1).equals("z")))
				{
					blackwhiteBoxFinal[i][j] = blackwhiteBoxV[i][j].substring(1,blackwhiteBoxV[i][j].length())+"\\";
					
				}else
				{
					blackwhiteBoxFinal[i][j] = blackwhiteBox[i][j];
				}
			}
		}
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				textFieldSolution[i][j].setText(blackwhiteBoxFinal[i][j]);
			}
		}
	}
	

	 
	public boolean CheckUnique(int rand, int array[], int size, int a, int b)
	{
		overlimit = -1;

		for (int i=0; i<size;i++)
		{
			
			if(array[i] != 0)
			{
				if (array[i] > 9)
				{
					blackwhiteBox[a][b] = "X";
					blackwhiteBoxYesNo[a][b] = "N";	
					overlimit = 1;
					return false;
				}

				if (array[i] == rand)
				{
					return true;
					
				}
			}
		}
		return false;
	}
	
	public void CheckSolution(int pSize)
	{
		boolean matched = true;
		int i =0, j = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				if(blackwhiteBoxYesNo[i][j].equals("Y"))
				{
					if (!(textField[i][j].getText().equals(textFieldSolution[i][j].getText())))
					{
						matched = false;
					}
				}
			}
		}
		if (!matched)
		{
			System.out.println("NotSolved");
		}else
		{
			System.out.println("Good, Solved!");
		}
		
	}

	private JTextField textField[][] = null;
	private JTextField textFieldTemp[][] = null;
	private JTextField textFieldSolution[][] = null;
	private JLabel labelField[][] = null;
	private	String blackwhiteBox[][] = null;
	private	String blackwhiteBoxH[][] = null;
	private String blackwhiteBoxV[][] = null;
	private	String blackwhiteBoxFinal[][] = null;
	private	String blackwhiteBoxYesNo[][] = null;
	private int overlimit = -1;
	private Random randGen = new Random();

}
