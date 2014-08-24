package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Queen Class
 */

public class Queen {
	
	/***
	 * Method to generate all possible Queen moves
	 * @param i
	 * @return
	 */
	
	public static String possibleQueenMoves(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j++) 
        {
            for (int k=-1; k<=1; k++) 
            {
                if(j!=0 || k!=0)
                {
                	try {
                		while("_".equals(MoveGen.chessBoard[r+temp*j][c+temp*k]))
                		{
                			oldPiece=MoveGen.chessBoard[r+temp*j][c+temp*k];
                			MoveGen.chessBoard[r][c]="_";
                			MoveGen.chessBoard[r+temp*j][c+temp*k]="Q";
                			if (King.kingSafe()) {
                				list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                			}
                			MoveGen.chessBoard[r][c]="Q";
                			MoveGen.chessBoard[r+temp*j][c+temp*k]=oldPiece;
                			temp++;
                		}
                		
                		if (Character.isLowerCase(MoveGen.chessBoard[r+temp*j][c+temp*k].charAt(0))) {
                			oldPiece=MoveGen.chessBoard[r+temp*j][c+temp*k];
                			MoveGen.chessBoard[r][c]="_";
                			MoveGen.chessBoard[r+temp*j][c+temp*k]="Q";
                			if (King.kingSafe()) {
                				list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                			}
                			MoveGen.chessBoard[r][c]="Q";
                			MoveGen.chessBoard[r+temp*j][c+temp*k]=oldPiece;
                		}
                	} 
                	catch (Exception e) 
                	{
                		
                	}                	
                	temp=1;
                }
            }
        }
        return list;
    }

}
