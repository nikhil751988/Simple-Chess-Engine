package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Pawn Class
 */

public class Pawn {
	
	/***
	 * Method to generate all possible pawn moves
	 * @param i
	 * @return
	 */
	
	public static String possiblePawnMoves(int i) 
	{
		String list="", oldPiece;
		int r=i/8, c=i%8;
		for (int j=-1; j<=1; j+=2) 
		{
			try 
			{
				//capture
				if (Character.isLowerCase(MoveGen.chessBoard[r-1][c+j].charAt(0)) && i>=16) 
				{
					oldPiece=MoveGen.chessBoard[r-1][c+j];
					MoveGen.chessBoard[r][c]="_";
					MoveGen.chessBoard[r-1][c+j]="P";
					if (King.kingSafe())
					{
						list=list+r+c+(r-1)+(c+j)+oldPiece;
					}
					MoveGen.chessBoard[r][c]="P";
					MoveGen.chessBoard[r-1][c+j]=oldPiece;
				}
			} 
			catch (Exception e) 
			{

			}
			try 
			{
				//promotion && capture
				if (Character.isLowerCase(MoveGen.chessBoard[r-1][c+j].charAt(0)) && i<16) 
				{
					String[] temp={"Q","R","B","K"};
					for (int k=0; k<4; k++) 
					{
						oldPiece=MoveGen.chessBoard[r-1][c+j];
						MoveGen.chessBoard[r][c]="_";
						MoveGen.chessBoard[r-1][c+j]=temp[k];
						if (King.kingSafe())
						{
							//column1,column2,captured-piece,new-piece,P
							list=list+c+(c+j)+oldPiece+temp[k]+"P";
						}
						MoveGen.chessBoard[r][c]="P";
						MoveGen.chessBoard[r-1][c+j]=oldPiece;
					}
				}
			} catch (Exception e) {}
		}
		try
		{
			//move one up
			if ("_".equals(MoveGen.chessBoard[r-1][c]) && i>=16) 
			{
				oldPiece=MoveGen.chessBoard[r-1][c];
				MoveGen.chessBoard[r][c]="_";
				MoveGen.chessBoard[r-1][c]="P";
				if (King.kingSafe()) 
				{
					list=list+r+c+(r-1)+c+oldPiece;
				}
				MoveGen.chessBoard[r][c]="P";
				MoveGen.chessBoard[r-1][c]=oldPiece;
			}
		}
		catch (Exception e) 
		{

		}
		try 
		{
			//promotion && no capture
			if ("_".equals(MoveGen.chessBoard[r-1][c]) && i<16) 
			{
				String[] temp={"Q","R","B","K"};
				for (int k=0; k<4; k++) 
				{
					oldPiece=MoveGen.chessBoard[r-1][c];
					MoveGen.chessBoard[r][c]="_";
					MoveGen.chessBoard[r-1][c]=temp[k];
					if (King.kingSafe())
					{
						//column1,column2,captured-piece,new-piece,P
						list=list+c+c+oldPiece+temp[k]+"P";
					}
					MoveGen.chessBoard[r][c]="P";
					MoveGen.chessBoard[r-1][c]=oldPiece;
				}
			}
		}
		catch (Exception e) 
		{

		}

		try 
		{
			//move two up
			if ("_".equals(MoveGen.chessBoard[r-1][c]) && "_".equals(MoveGen.chessBoard[r-2][c]) && i>=48) 
			{
				oldPiece=MoveGen.chessBoard[r-2][c];
				MoveGen.chessBoard[r][c]="_";
				MoveGen.chessBoard[r-2][c]="P";
				if (King.kingSafe()) 
				{
					list=list+r+c+(r-2)+c+oldPiece;
				}
				MoveGen.chessBoard[r][c]="P";
				MoveGen.chessBoard[r-2][c]=oldPiece;
			}
		} 
		catch (Exception e) 
		{

		}
		return list;
	}

}
