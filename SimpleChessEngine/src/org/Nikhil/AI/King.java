package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * King Class
 */

public class King {
	
	/***
	 * Generate all possible king moves
	 * @param i
	 * @return
	 */
	
	public static String possibleKingMoves(int i) {
        String list="", oldPiece;
        int r=i/8, c=i%8;
        for (int j=0; j<9; j++)
        {
            if (j!=4) 
            {
                try 
                {
                    if (Character.isLowerCase(MoveGen.chessBoard[r-1+j/3][c-1+j%3].charAt(0)) || "_".equals(MoveGen.chessBoard[r-1+j/3][c-1+j%3])) 
                    {
                        oldPiece=MoveGen.chessBoard[r-1+j/3][c-1+j%3];
                        MoveGen.chessBoard[r][c]="_";
                        MoveGen.chessBoard[r-1+j/3][c-1+j%3]="K";
                        int kingTemp=MoveGen.kingPositionC;
                        MoveGen.kingPositionC=i+(j/3)*8+j%3-9;
                        if (kingSafe()) 
                        {
                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
                        }
                        MoveGen.chessBoard[r][c]="K";
                        MoveGen.chessBoard[r-1+j/3][c-1+j%3]=oldPiece;
                        MoveGen.kingPositionC=kingTemp;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
            }
        }
     
        return list;
    }
	
	/***
	 * Method used to check King's safety 
	 * @return
	 */
	
    public static boolean kingSafe() {
    	
    	//for bishop & queen
        int temp=1;
        for (int i=-1; i<=1; i+=2)
        {
            for (int j=-1; j<=1; j+=2) 
            {
                try 
                {
                    while("_".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8+temp*j])) 
                    {
                    	temp++;
                    }
                    
                    if ("b".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8+temp*j]) ||
                            "q".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8+temp*j])) 
                    {
                        return false;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
                temp=1;
            }
        }
        //for rook & queen
        for (int i=-1; i<=1; i+=2) 
        {
            try 
            {
                while("_".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8][MoveGen.kingPositionC%8+temp*i])) 
                {
                	temp++;
                }
                if ("r".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8][MoveGen.kingPositionC%8+temp*i]) ||
                        "q".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8][MoveGen.kingPositionC%8+temp*i]))
                {
                    return false;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            temp=1;
            
            try
            {
                while("_".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8])) 
                {
                	temp++;
                }
                if ("r".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8]) ||
                        "q".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+temp*i][MoveGen.kingPositionC%8])) 
                {
                    return false;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            temp=1;
        }
        
        //for knight
        for (int i=-1; i<=1; i+=2) 
        {
            for (int j=-1; j<=1; j+=2) 
            {
                try 
                {
                    if ("n".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+i][MoveGen.kingPositionC%8+j*2])) 
                    {
                        return false;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
                try 
                {
                    if ("n".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+i*2][MoveGen.kingPositionC%8+j])) 
                    {
                        return false;
                    }
                } 
                catch (Exception e) 
                {
                	
                }
            }
        }
        
        //for pawn
        if (MoveGen.kingPositionC>=16) 
        {
            try 
            {
                if ("p".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8-1][MoveGen.kingPositionC%8-1])) 
                {
                    return false;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            
            try 
            {
                if ("p".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8-1][MoveGen.kingPositionC%8+1])) 
                {
                    return false;
                }
            } 
            catch (Exception e) 
            {
            	
            }
            
            //king
            for (int i=-1; i<=1; i++)
            {
                for (int j=-1; j<=1; j++) 
                {
                    if (i!=0 || j!=0) 
                    {
                        try 
                        {
                            if ("k".equals(MoveGen.chessBoard[MoveGen.kingPositionC/8+i][MoveGen.kingPositionC%8+j])) 
                            {
                                return false;
                            }
                        } 
                        catch (Exception e) 
                        {
                        	
                        }
                    }
                }
            }
        }
        return true;
     }

}
