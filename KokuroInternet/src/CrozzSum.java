/**
 * CrozzSum.java
 * 
 * Author: Meianandh
 * Email: meianandh@users.sourceforge.net
 * Date: October 29, 2009
 * Compiled using: j2sdk1.4.2_19
 * Works best with JDK 1.6
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 **/

 
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CrozzSum
{
	public static void main (String[] args)
	{
		/**
		 * To get System Look and Feel
		 */
		try {
			UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception exp) {
			System.out.println("Unable to Load the System Look and Feel...");
		}
		
		CrozzSum cs = new CrozzSum();
	}
	
	/**
	 * This will return String with HTML Text with Subscript & Superscript 
	 */
	
	public String getSubSuperText(int a, int b)
	{
		return("<html><body><sub>"+a+"</sub>&nbsp<big>\\</big>&nbsp<sup>"+b+"</sup></body></html>");
	}
	
	/**
	 * This function will parse the String and will create the HTML Text with 
	 * Subscript & Superscript
	 * Input: "24\5"
	 * Output: <html><body>
	 *			<sub>24</sub>&nbsp
	 *			<big>\\</big>&nbsp
	 *			<sup>6</sup>
	 *			</body></html>
	 */

	public String getSubSuperTextString(String data)
	{
		int indexofS = data.indexOf("\\");
		String firstStr = data.substring(0,indexofS);
		String secondStr = data.substring(indexofS+1,data.length());
		return("<html><body><sub>"+firstStr+"</sub>&nbsp<big>\\</big>&nbsp<sup>"+secondStr+"</sup></body></html>");
	}
	
	/**
	 * Puzzle Generator Logic.
	 * Input: Size of the Puzzle (8,12,16 or 20)
	 */
	
	public void GenerateNewPuzzle(int pSize)
	{
		solutionmitem.setEnabled(true);	
		chksolutionmitem.setEnabled(true);	
		textFont = new Font("Verdana",Font.BOLD,14);
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
				textField[i][j].setFont(textFont);
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
		//System.out.println("row chk");
		// check this...
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
		//System.out.println("column chk");
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
					//if (count >= 9)
					if (count >= 8)
					{
						blackwhiteBox[j][i] = "X";
						blackwhiteBoxYesNo[j][i] = "N";	
					}
				}
			}
		}

		//System.out.println("get random number chk");
		int si=0, sj=0, sk=0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				if (blackwhiteBox[i][j] == "0")
				{
					int randTemp = randGen.nextInt(10);	
					//int upval = 0, downval = 0, rightval = 0, leftval = 0;
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
					//if (blackwhiteBox[i-1][j] != "X")
					//{
					//	upval = Integer.parseInt(blackwhiteBox[i-1][j]);
					//}
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

					//if (i+1 < pSize)
					//{
					//	if (blackwhiteBox[i+1][j] != "X")
					//	{
					//		downval = Integer.parseInt(blackwhiteBox[i+1][j]);
					//	}
					//}
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
					

					//if (blackwhiteBox[i][j-1] != "X")
					//{
					//	leftval = Integer.parseInt(blackwhiteBox[i][j-1]);
					//}
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

					//if (j+1 < pSize)
					//{
					//	if (blackwhiteBox[i][j+1] != "X")
					//	{
					//		rightval = Integer.parseInt(blackwhiteBox[i][j+1]);
					//	}
					//}
					
					//if((randTemp == upval) || (randTemp == downval)
					//	|| (randTemp == rightval) || (randTemp == leftval))
					//	{
					//		randTemp = 0;
					//	}
					//System.out.println("Check Unique 01");
					if((CheckUnique(randTemp,upval,pSize,i,j)) || (CheckUnique(randTemp,downval,pSize,i,j))
						|| (CheckUnique(randTemp,rightval,pSize,i,j)) || (CheckUnique(randTemp,leftval,pSize,i,j)))
						{
							randTemp = 0;
						}
					
					
					while(randTemp == 0)
					{
						//System.out.println("Check Unique Loop 01");
						randTemp = randGen.nextInt(10);		
						if((CheckUnique(randTemp,upval,pSize,i,j)) || (CheckUnique(randTemp,downval,pSize,i,j))
							|| (CheckUnique(randTemp,rightval,pSize,i,j)) || (CheckUnique(randTemp,leftval,pSize,i,j)))
						//if((randTemp == upval) || (randTemp == downval)
						//	|| (randTemp == rightval) || (randTemp == leftval))
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

		con2.removeAll();
		glayout = null;
		glayout = new GridLayout(pSize,pSize,2,2);
		Border border = LineBorder.createGrayLineBorder();
		con2.setLayout(glayout);
		int li = 1, lj = 1;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				int indexofS = -1;
				indexofS = blackwhiteBoxFinal[i][j].indexOf("\\");
				if(blackwhiteBoxFinal[i][j].equals("X"))
				{
					labelField[i][j].setText("");
					labelField[i][j].setBorder(border);
					con2.add(labelField[i][j]);
				}else if (indexofS >= 0)
				{
					labelField[i][j].setText(getSubSuperTextString(blackwhiteBoxFinal[i][j]));
					labelField[i][j].setBorder(border);
					con2.add(labelField[i][j]);
				}else if(indexofS == -1)
				{
					con2.add(textField[i][j]);					
				}
			}
		}
		if (pSize == 8)
		{
			mainFrame.setSize(400+pSize+randGen.nextInt(10),400+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}else if (pSize == 12)
		{
			mainFrame.setSize(600+pSize+randGen.nextInt(10),500+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}else if (pSize == 16)
		{
			mainFrame.setSize(700+pSize+randGen.nextInt(10),600+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}else if (pSize == 20)
		{
			mainFrame.setSize(800+pSize+randGen.nextInt(10),700+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}else if (pSize == 5)
		{
			mainFrame.setSize(250+pSize+randGen.nextInt(10),250+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}else if (pSize == 6)
		{
			mainFrame.setSize(275+pSize+randGen.nextInt(10),275+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();
		}else if (pSize == 7)
		{
			mainFrame.setSize(350+pSize+randGen.nextInt(10),350+pSize+randGen.nextInt(10));
			//mainFrame.pack();
			mainFrame.validate();

		}
		System.out.println("The size of the square Kakuro is: " + blackwhiteBoxFinal[0].length);
		for(int ii = 0 ; ii < blackwhiteBoxFinal[0].length; ii++){
			for( int jj = 0; jj < blackwhiteBoxFinal[0].length; jj++){
				System.out.print(blackwhiteBoxFinal[ii][jj] + " ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * CheckSolution - Verify whether Puzzle is Unique or NOT
	 */
	 
	public boolean CheckUnique(int rand, int array[], int size, int a, int b)
	{
		overlimit = -1;
		//System.out.println("rand"+rand);
		//for (int i=0; i<size;i++)
		//{
		//	if (array[i]!=0)
		//		System.out.print("S-"+array[i]);
		//}
		//System.out.println("----");
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
				//System.out.print("S-"+array[i]);
				if (array[i] == rand)
				{
					//System.out.println("\n");
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
			JOptionPane.showMessageDialog(mainFrame,"Puzzle Not Solved...","CrozzSum (Kakuro)",2);
		}else
		{
			JOptionPane.showMessageDialog(mainFrame,"Hurray !!!\nPuzzle Solved... :-)","CrozzSum (Kakuro)",1);			
		}
		
	}
	
	/**
	 * ShowSolution - Displays the solution of the puzzle 
	 */

	public void ShowSolution(int pSize)
	{
		backtopuzzmitem.setEnabled(true);
		newmenu.setEnabled(false);
		chksolutionmitem.setEnabled(false);
		solutionmitem.setEnabled(false);
		boolean matched = true;
		int i =0, j = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				
				if(blackwhiteBoxYesNo[i][j].equals("Y"))
				{
					textFieldTemp[i][j].setText(textField[i][j].getText());
					textField[i][j].setText(textFieldSolution[i][j].getText());
				}
			}
		}
	}

	/** 
	 * BackToPuzzle - When you are in ShowSolution Mode - 
	 * We will have only one option to move further that is BackToPuzzle mode...
	 */
	
	public void BackToPuzzle(int pSize)
	{
		backtopuzzmitem.setEnabled(false);
		newmenu.setEnabled(true);
		chksolutionmitem.setEnabled(true);
		solutionmitem.setEnabled(true);
		boolean matched = true;
		int i =0, j = 0;
		for (i=0;i<pSize;i++)
		{
			for(j=0;j<pSize;j++)
			{
				
				if(blackwhiteBoxYesNo[i][j].equals("Y"))
				{
					textField[i][j].setText(textFieldTemp[i][j].getText());
				}
			}
		}

	}
	/*
	 * To read the GPL v 3.0 License File.
	 *
	 */
	public String readGPLFile(String filename) throws IOException
	{
	  InputStream inputs = getClass().getResourceAsStream(filename);
	  InputStreamReader inputsr = new InputStreamReader(inputs);
	  BufferedReader br = new BufferedReader(inputsr);
	  StringBuffer strbuf = new StringBuffer();
	  String rline;
	  while ((rline = br.readLine()) != null) 
	  {
	    strbuf.append(rline);
	    strbuf.append("\n");
	  }
	  br.close();
	  inputsr.close();
	  inputs.close();
	  return strbuf.toString();
	}
	
	/**
	 * Display GPL 3.0 License
	 *
	 */

	public void DisplayGPLLicense()
	{
		gplDialog = new JDialog(mainFrame,"GPL 3.0 GNU - GENERAL PUBLIC LICENSE",true);
		Container gplDialogC = gplDialog.getContentPane();
		JTextArea gplTextArea = new JTextArea("",10,10);
		JScrollPane scrollp = new JScrollPane(gplTextArea);
		gplTextArea.setEditable(false);		
		try
		{
   		  gplTextArea.setText(readGPLFile("gpl_license.txt"));
		}catch (Exception ex)
		{
			System.out.println("Exception "+ex);	
		}
		gplTextArea.setCaretPosition(0);
		gplDialogC.add(scrollp);
		gplDialog.pack();
		gplDialog.setSize(600,300);
		gplDialog.setVisible(true);
	}

	public void GetCombination(JTextArea text,int num, int box)
	{
		try{
		  InputStream inputs = getClass().getResourceAsStream("HintSheet.txt");
		  InputStreamReader inputsr = new InputStreamReader(inputs);
		  BufferedReader br = new BufferedReader(inputsr);
		  StringBuffer strbuf = new StringBuffer();
		  String rline;
		  while ((rline = br.readLine()) != null) 
		  {
			  if(box == Integer.parseInt(rline.substring(0,1)))
			  {
				  if (num == Integer.parseInt(rline.substring(2,4)))
				  {
				  	  
					  int len = rline.length();
					  //System.out.println("Length :"+len);
					  strbuf.append(rline.substring(5,len));
					  //System.out.println("Text : "+rline.substring(5,len));
					  //System.out.println(""+rline);
					  strbuf.append("\n");
				  }
			  }
		  }
		  br.close();
		  inputsr.close();
		  inputs.close();
		  //System.out.println("Mei : "+strbuf.toString());
		  text.setText(strbuf.toString());
		}catch (Exception ex)
		{
			JOptionPane.showMessageDialog(hintDialog,"Reading File Exception: "+ex,"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public String[] GetNumberCombination(int box)
	{
		String[] retStr = null;
		try{
		  InputStream inputs = getClass().getResourceAsStream("HintSheet_Base.txt");
		  InputStreamReader inputsr = new InputStreamReader(inputs);
		  BufferedReader br = new BufferedReader(inputsr);
		  StringBuffer strbuf = new StringBuffer();
		  String rline;
		  String [] retStr_a = new String[45];
		  int icnt = 0;
		  while ((rline = br.readLine()) != null) 
		  {
			  if(box == Integer.parseInt(rline.substring(0,1)))
			  {
			  	  retStr_a[icnt] = rline.substring(2,4);
			  	  icnt = icnt + 1;
			  }
		  }
		  br.close();
		  inputsr.close();
		  inputs.close();
		  //icnt = icnt - 1;
		  retStr = new String[icnt];
		  for (int i=0;i<icnt;i++)
		  {
		  	  retStr[i] = retStr_a[i];
		  }
		  
		}catch (Exception ex)
		{
			//JOptionPane.showMessageDialog(hintDialog,"Reading File Exception: "+ex,"Error",JOptionPane.ERROR_MESSAGE);
			System.out.println("Num Combination Exception : "+ex);
		}
		return(retStr);
	}

	/**
	 * Hint Dialog...
	 *
	 */

	public void StartHintDialog()
	{
		hintDialog = new JDialog(mainFrame,"CrozzSum Hint...");
		Container hintDialogC = hintDialog.getContentPane();
		BorderLayout blayout = new BorderLayout();
		hintDialogC.setLayout(blayout);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		JLabel hintNumL = new JLabel("Number #");
		hintNum = new JTextField("",4);
		String[] numString = { "03" };
		hintNumCombo = new JComboBox(numString);
		
		JLabel hintBoxL = new JLabel("Boxes #");
		hintBox = new JTextField("",4);
		
		String[] boxString = { "2", "3", "4", "5", "6", "7", "8", "9" };
		hintBoxCombo = new JComboBox(boxString);

		hintBoxCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//JOptionPane.showMessageDialog(hintDialog,"Combo Action ...");
				String selectedItem = (String)hintBoxCombo.getSelectedItem();
				String tNumStr[] = GetNumberCombination((Integer.parseInt(selectedItem)));
				hintNumCombo.removeAllItems();
				try{
					for(int i=0;i<45;i++)
					{
						hintNumCombo.addItem(tNumStr[i]);
						
					}
				}catch (ArrayIndexOutOfBoundsException ex)
				{}

			}
		});
		
		hintNumCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String temp = (String)hintNumCombo.getSelectedItem();
					int num = Integer.parseInt(temp);
					temp = (String)hintBoxCombo.getSelectedItem();
					int box = Integer.parseInt(temp);
					if (num > 0 && box > 0)
					{
						//System.out.println("AAA : "+num+"--"+box);
						GetCombination(hintTextArea,num,box);
					}
				}catch (NumberFormatException nex)
				{
					//JOptionPane.showMessageDialog(hintDialog,"Invalid Number Format...","Error",JOptionPane.ERROR_MESSAGE);
				}catch (Exception ex)
				{
					JOptionPane.showMessageDialog(hintDialog,"Exception "+ex,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		hintTextArea = new JTextArea("",5,5);
		scrollp = new JScrollPane(hintTextArea);		
		hintTextArea.setCaretPosition(0);
		hintTextArea.setEditable(false);
		

		northPanel.add(hintBoxL);
		northPanel.add(hintBoxCombo);
		northPanel.add(hintNumL);
		northPanel.add(hintNumCombo);

		
		hintDialogC.add(northPanel,BorderLayout.NORTH);
		hintDialogC.add(scrollp,BorderLayout.CENTER);
		hintDialog.pack();
		hintDialog.setSize(200,250);
		hintDialog.setVisible(true);
		{
		String selectedItem = (String)hintBoxCombo.getSelectedItem();
		String tNumStr[] = GetNumberCombination((Integer.parseInt(selectedItem)));
		hintNumCombo.removeAllItems();
		try{
			for(int i=0;i<45;i++)
			{
				hintNumCombo.addItem(tNumStr[i]);
				
			}
		}catch (ArrayIndexOutOfBoundsException ex)
		{}
		}
		
	}

	/**
	 * Constructor
	 */
	public CrozzSum ()
	{
		mainFrame = new JFrame("CrozzSum - Kakuro (0.03)");
		con1 = mainFrame.getContentPane();
		con1.setLayout(new BorderLayout());
		String labelText = getSubSuperTextString("15\\4");
		statuslabel = new JLabel("",JLabel.CENTER);
		statuslabel.setText(labelText);
		
		mb = new JMenuBar();
		gamemenu = new JMenu("Game");
		gamemenu.setMnemonic(KeyEvent.VK_G);

		helpmenu = new JMenu("Help");
		helpmenu.setMnemonic(KeyEvent.VK_H);
		
		newmenu = new JMenu("New Puzzle");
		newmenu.setMnemonic(KeyEvent.VK_N);
		
		new55 = new JMenuItem(" 5 x  5 - Puzzle");
		new66 = new JMenuItem(" 6 x  6 - Puzzle");
		new77 = new JMenuItem(" 7 x  7 - Puzzle");
		
		new88 = new JMenuItem(" 8 x  8 - Puzzle");
		new1212 = new JMenuItem("12 x 12 - Puzzle");
		new1616 = new JMenuItem("16 x 16 - Puzzle");
		new2020 = new JMenuItem("20 x 20 - Puzzle");						
		newmenu.add(new55);
		newmenu.add(new66);
		newmenu.add(new77);
		newmenu.add(new88);
		newmenu.add(new1212);
		newmenu.add(new1616);
		//newmenu.add(new2020);
		new55.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 5;
				GenerateNewPuzzle(puzzleSize);
			}
		});
		new66.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 6;
				GenerateNewPuzzle(puzzleSize);
			}
		});
		new77.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 7;
				GenerateNewPuzzle(puzzleSize);
			}
		});

	
		new88.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 8;
				GenerateNewPuzzle(puzzleSize);
			}
		});
		new1212.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 12;
				GenerateNewPuzzle(puzzleSize);
			}
		});

		new1616.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 16;
				GenerateNewPuzzle(puzzleSize);
			}
		});

		new2020.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				puzzleSize = 20;
				GenerateNewPuzzle(puzzleSize);
			}
		});
		backtopuzzmitem = new JMenuItem("Back to Puzzle...");
		backtopuzzmitem.setEnabled(false);
		backtopuzzmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				BackToPuzzle(puzzleSize);
			}
			
		});

		chksolutionmitem = new JMenuItem("Check Solution...");
		chksolutionmitem.setEnabled(false);
		chksolutionmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				CheckSolution(puzzleSize);
			}
			
		});

		hintmitem = new JMenuItem("Hint",KeyEvent.VK_H);
		//hintmitem.setEnabled(false);
		hintmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(mainFrame,"Hint Dialog");
				StartHintDialog();
			}
			
		});

		solutionmitem = new JMenuItem("Solution...");
		solutionmitem.setEnabled(false);
		solutionmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				ShowSolution(puzzleSize);
			}
			
		});

		exitmitem = new JMenuItem("Exit",KeyEvent.VK_X);
		exitmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		aboutmitem = new JMenuItem("About");
		aboutmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,"Crozz Sum (Kakuro)... \nA simple \"Kakuro\" puzzle generator..."+
					"\nhttp://crozzsum.sourceforge.net/\nEmail: meianandh@users.sourceforge.net"+
					"\nLicense: GPL 3.0 - GNU General Public License (or later version)"+
					"\n\nGame Rules: http://en.wikipedia.org/wiki/Kakuro","CrozzSum (0.03) - Kakuro...",1);
			}
			
		});

		gplmitem = new JMenuItem("GPL 3.0 (GNU General Public License)");
		gplmitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				DisplayGPLLicense();
			}
			
		});
		
		
		gamemenu.add(newmenu);
		gamemenu.addSeparator();
		gamemenu.add(chksolutionmitem);
		gamemenu.add(solutionmitem);
		gamemenu.add(backtopuzzmitem);
		gamemenu.addSeparator();
		gamemenu.add(hintmitem);
		gamemenu.addSeparator();
		gamemenu.add(exitmitem);
		
		helpmenu.add(aboutmitem);
		helpmenu.addSeparator();
		helpmenu.add(gplmitem);
		
		mb.add(gamemenu);
		mb.add(helpmenu);
		
		mainDialog = new JDialog();
		con2 = mainDialog.getContentPane();
		JLabel tempLabel = new JLabel("<html><body><font face=verdana><b>CrozzSum (Kakuro) V 0.03...</b></font></body></html>",JLabel.CENTER);
		con2.add(tempLabel);
		con1.add(mb,BorderLayout.NORTH);
		con1.add(con2,BorderLayout.CENTER);
		con1.add(statuslabel,BorderLayout.SOUTH);
		mainFrame.setSize(400,400);
		//mainFrame.pack();
		mainFrame.validate();
		mainFrame.show();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	private int blackBox[][] = null;
	private int randomBlackBox[] = null;
	private GridLayout glayout = null;	
	private Font textFont = null;
	private JFrame mainFrame = null;
	private Container con1 = null, con2 = null;
	private JMenuBar mb = null;
	private JMenu gamemenu = null;
	private JMenu helpmenu = null;
	private JMenu newmenu = null;
	private JMenuItem new55 = null, new66 = null, new77 = null, new88 = null; 
	private JMenuItem new1212 = null, new1616 = null, new2020 = null;
	private JMenuItem newmitem = null;
	private JMenuItem exitmitem = null;
	private JMenuItem aboutmitem = null;
	private JMenuItem solutionmitem = null;
	private JMenuItem chksolutionmitem = null;
	private JMenuItem backtopuzzmitem = null;
	private JMenuItem gplmitem = null;
	private JMenuItem hintmitem = null;
	private JDialog mainDialog = null;
	private int puzzleSize = 0;
	private JLabel statuslabel = null;
	private JTextField textField[][] = null;
	private JTextField textFieldTemp[][] = null;
	private JTextField textFieldSolution[][] = null;
	private JLabel labelField[][] = null;
	private	String blackwhiteBox[][] = null;
	private	String blackwhiteBoxH[][] = null;
	private String blackwhiteBoxV[][] = null;
	private	String blackwhiteBoxFinal[][] = null;
	private	String blackwhiteBoxYesNo[][] = null;
	private JDialog hintDialog = null;
	private JDialog gplDialog = null;
	private int overlimit = -1;
	private JTextArea hintTextArea = null;
	private	JScrollPane scrollp = null;		
	private	JTextField hintNum = null;
	private	JTextField hintBox = null;
	private JComboBox hintBoxCombo = null;
	private JComboBox hintNumCombo = null;
	private JButton getCombination = null;
	private Random randGen = new Random();

	
}
