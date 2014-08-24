package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Knight Class
 */

public class Knight {

	/***
	 * Method to generate all possible Knight moves
	 * @param i
	 * @return
	 */
	
	public static String possibleKnighMoves(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=-1; j<=1; j+=2)
        {
            for (int k=-1; k<=1; k+=2) 
            {
                try
                {
                    if (Character.isLowerCase(MoveGen.chessBoard[r+j][c+k*2].charAt(0)) || "_".equals(MoveGen.chessBoard[r+j][c+k*2])) 
                    {
                        oldPiece=MoveGen.chessBoard[r+j][c+k*2];
                        MoveGen.chessBoard[r][c]="_";
                        MoveGen.chessBoard[r+j][c+k*2]="N";
                        if (King.kingSafe()) 
                        {
                            list=list+r+c+(r+j)+(c+k*2)+oldPiece;
                        }
                        MoveGen.chessBoard[r][c]="N";
                        MoveGen.chessBoard[r+j][c+k*2]=oldPiece;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
                
                try 
                {
                    if (Character.isLowerCase(MoveGen.chessBoard[r+j*2][c+k].charAt(0)) || "_".equals(MoveGen.chessBoard[r+j*2][c+k])) 
                    {
                        oldPiece=MoveGen.chessBoard[r+j*2][c+k];
                        MoveGen.chessBoard[r][c]="_";
                        MoveGen.chessBoard[r+j*2][c+k]="N";
                        if (King.kingSafe()) 
                        {
                            list=list+r+c+(r+j*2)+(c+k)+oldPiece;
                        }
                        MoveGen.chessBoard[r][c]="N";
                        MoveGen.chessBoard[r+j*2][c+k]=oldPiece;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
            }
        }
        return list;
    }

}
