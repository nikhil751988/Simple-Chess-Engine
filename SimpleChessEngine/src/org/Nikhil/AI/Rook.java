package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Rook Class
 */

public class Rook {
	
	/***
	 * Method to generate all possible Rook moves
	 * @param i
	 * @return
	 */
	
	public static String possibleRookMoves(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        int temp=1;
        for (int j=-1; j<=1; j+=2) 
        {
            try 
            {
                while("_".equals(MoveGen.chessBoard[r][c+temp*j]))
                {
                    oldPiece=MoveGen.chessBoard[r][c+temp*j];
                    MoveGen.chessBoard[r][c]="_";
                    MoveGen.chessBoard[r][c+temp*j]="R";
                    if (King.kingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    MoveGen.chessBoard[r][c]="R";
                    MoveGen.chessBoard[r][c+temp*j]=oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(MoveGen.chessBoard[r][c+temp*j].charAt(0)))
                {
                    oldPiece=MoveGen.chessBoard[r][c+temp*j];
                    MoveGen.chessBoard[r][c]="_";
                    MoveGen.chessBoard[r][c+temp*j]="R";
                    if (King.kingSafe()) {
                        list=list+r+c+r+(c+temp*j)+oldPiece;
                    }
                    MoveGen.chessBoard[r][c]="R";
                    MoveGen.chessBoard[r][c+temp*j]=oldPiece;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            
            temp=1;
            
            try 
            {
                while("_".equals(MoveGen.chessBoard[r+temp*j][c]))
                {
                    oldPiece=MoveGen.chessBoard[r+temp*j][c];
                    MoveGen.chessBoard[r][c]="_";
                    MoveGen.chessBoard[r+temp*j][c]="R";
                    if (King.kingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    MoveGen.chessBoard[r][c]="R";
                    MoveGen.chessBoard[r+temp*j][c]=oldPiece;
                    temp++;
                }
                
                if (Character.isLowerCase(MoveGen.chessBoard[r+temp*j][c].charAt(0))) 
                {
                    oldPiece=MoveGen.chessBoard[r+temp*j][c];
                    MoveGen.chessBoard[r][c]="_";
                    MoveGen.chessBoard[r+temp*j][c]="R";
                    if (King.kingSafe()) {
                        list=list+r+c+(r+temp*j)+c+oldPiece;
                    }
                    MoveGen.chessBoard[r][c]="R";
                    MoveGen.chessBoard[r+temp*j][c]=oldPiece;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            
            temp=1;
        }
        return list;
    }

}
