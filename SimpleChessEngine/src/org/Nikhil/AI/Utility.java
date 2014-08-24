package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Utility Class
 */

public class Utility {
	
	public static char [] files = {'a','b','c','d','e','f','g','h'};
	
	/***
	 * Method to sort the generated moves
	 * @param movesList
	 * @return
	 */

	public static String sortMoves(String movesList)
	{
		String newSortList="";
		String tempList=movesList;
		int [] score = new int[movesList.length()/5];
		for(int i=0;i<movesList.length()/5;i+=5)
		{
			MoveGen.makeMove(movesList.substring(i,i+5));
			score[i/5]=-Evaluator.evaluate(-1, 0);
			MoveGen.undoMove(movesList.substring(i,i+5));
		}
		
		//Getting top 6 best moves in sorted fashion
		for(int i=0;i<Math.min(6, movesList.length()/5);i++)
		{
			int maxValue=-100000;
			int maxLocation=-1;
			for(int j=0;j<score.length;j++)
			{
				if(score[j]>maxValue)
				{
					maxValue=score[j];
					maxLocation=j;
				}
			}
			score[maxLocation]=-100000;
			newSortList+=movesList.substring(maxLocation*5, maxLocation*5+5);
			tempList=tempList.replace(movesList.substring(maxLocation*5, maxLocation*5+5), "");
		}
		return newSortList+tempList;
	}
	
	/***
	 * Method to display the board
	 */
	
	public static void displayBoard()
	{
		System.out.print("\t");
		
		for(int i=0;i<files.length;i++)
		{
			System.out.print(files[i]+"\t");
		}
		
		System.out.println("\n");
		
		for(int index1=0;index1<8;index1++)
		{
			System.out.print((index1+1)+"\t");
			
			for(int index2=0;index2<8;index2++)
			{
				if(Character.isLowerCase(MoveGen.chessBoard[index1][index2].charAt(0)))
				{
					System.out.print("B"+MoveGen.chessBoard[index1][index2].toUpperCase()+"\t");
				}
				else if(!("_".equals(MoveGen.chessBoard[index1][index2])))
				{
					System.out.print("W"+MoveGen.chessBoard[index1][index2]+"\t");
				}
				else
				{
					System.out.print(MoveGen.chessBoard[index1][index2]+"\t");
				}
			}
			System.out.println("\n");
		}
		
		System.out.print("\t");
		
		for(int i=0;i<files.length;i++)
		{
			System.out.print(files[i]+"\t");
		}
		
		System.out.println("\n\n");
	}
	
}
