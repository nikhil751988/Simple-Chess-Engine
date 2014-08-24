package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * AlphaBetaPrune Class
 */

public class AlphaBetaPrune {
	
	
	/***
	 * Method alphaBeta runs the Alpha Beta Search on the moves
	 * @param depth
	 * @param beta
	 * @param alpha
	 * @param move
	 * @param player
	 * @return
	 */
	
	public static String alphaBeta(int depth, int beta, int alpha, String move, int player) 
	{
        
        String list=MoveGen.generatePossibleMoves();
        if (depth==0 || list.length()==0) 
        {
        	return move+(Evaluator.evaluate(list.length(), depth)*(player*2-1));
        }
        //sort the moves
        if(ParameterFileParse.MOVE_ORDER)
        {
        	list=Utility.sortMoves(list);
        }
        
        player=1-player;
        
        for (int i=0;i<list.length();i+=5) 
        {
            MoveGen.makeMove(list.substring(i,i+5));
            flipBoard();
            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
            int value=Integer.valueOf(returnString.substring(5));
            flipBoard();
            MoveGen.undoMove(list.substring(i,i+5));
            
            if (player==0)
            {
                if (value<=beta) 
                {
                	beta=value; 
                	if (depth==ParameterFileParse.GLOBAL_DEPTH)
                	{
                		move=returnString.substring(0,5);
                	}
                }
            } 
            
            else 
            {
                if (value>alpha) 
                {
                	alpha=value; 
                	if (depth==ParameterFileParse.GLOBAL_DEPTH) 
                	{
                		move=returnString.substring(0,5);
                	}
                }
            }
            
            if (alpha>=beta) 
            {
                if (player==0) 
                {
                	return move+beta;
                } 
                else 
                {
                	return move+alpha;
                }
            }
        }
        if (player==0) 
        {
        	return move+beta;
        }
        else 
        {
        	return move+alpha;
        }
    }
	
	/***
	 * Method flipBoard flips the pieces for the analysis 
	 */
	
	public static void flipBoard()
	{
		String temp;
		
        for (int i=0;i<32;i++) 
        {
            int r=i/8, c=i%8;
            if (Character.isUpperCase(MoveGen.chessBoard[r][c].charAt(0))) 
            {
                temp=MoveGen.chessBoard[r][c].toLowerCase();
            } 
            else
            {
                temp=MoveGen.chessBoard[r][c].toUpperCase();
            }
            
            if (Character.isUpperCase(MoveGen.chessBoard[7-r][7-c].charAt(0))) 
            {
                MoveGen.chessBoard[r][c]=MoveGen.chessBoard[7-r][7-c].toLowerCase();
            }
            
            else 
            {
                MoveGen.chessBoard[r][c]=MoveGen.chessBoard[7-r][7-c].toUpperCase();
            }
            MoveGen.chessBoard[7-r][7-c]=temp;
        }
        int kingTemp=MoveGen.kingPositionC;
        MoveGen.kingPositionC=63-MoveGen.kingPositionL;
        MoveGen.kingPositionL=63-kingTemp;
	}	

}
