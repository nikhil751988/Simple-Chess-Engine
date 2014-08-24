package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Bishop Class
 */

public class Bishop {
	
	/***
	 * This method is used to generate all possible Bishop moves
	 * @param i
	 * @return
	 */
	
	public static String possibleBishopMoves(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) 
        {
            for (int k=-1; k<=1; k+=2)
            {
                try 
                {
                    while("_".equals(MoveGen.chessBoard[r+temp*j][c+temp*k]))
                    {
                        oldPiece=MoveGen.chessBoard[r+temp*j][c+temp*k];
                        MoveGen.chessBoard[r][c]="_";
                        MoveGen.chessBoard[r+temp*j][c+temp*k]="B";
                        
                        if (King.kingSafe()) 
                        {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        
                        MoveGen.chessBoard[r][c]="B";
                        MoveGen.chessBoard[r+temp*j][c+temp*k]=oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(MoveGen.chessBoard[r+temp*j][c+temp*k].charAt(0))) 
                    {
                        oldPiece=MoveGen.chessBoard[r+temp*j][c+temp*k];
                        MoveGen.chessBoard[r][c]="_";
                        MoveGen.chessBoard[r+temp*j][c+temp*k]="B";
                        if(King.kingSafe()) 
                        {
                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
                        }
                        MoveGen.chessBoard[r][c]="B";
                        MoveGen.chessBoard[r+temp*j][c+temp*k]=oldPiece;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
                temp=1;
            }
        }
        return list;
    }
}
