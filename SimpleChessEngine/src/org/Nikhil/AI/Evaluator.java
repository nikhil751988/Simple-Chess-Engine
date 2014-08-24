package org.Nikhil.AI;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * Evaluator Class
 */

public class Evaluator {
	
	//Scores attributed to http://chessprogramming.wikispaces.com/Simplified+evaluation+function
	
	static int pawnBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
	
    static int rookBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
    
    static int knightBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    
    static int bishopBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    
    static int queenBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    
    static int kingMidBoard[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
    
    static int kingEndBoard[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
    
    /***
     * This method calls upon Heuristics to evaluate a move
     * @param listLen
     * @param depth
     * @return
     */
	
	public static int evaluate(int listLen, int depth)
	{
		int counter=0;
		int material=0;
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.BASE_EVAL)
		{
			material=evalMaterial();
			counter+=material;
		}
		
		if(ParameterFileParse.ALL_EVAL || (ParameterFileParse.POST_EVAL && ParameterFileParse.BASE_EVAL))
		{
			counter+=evalPositional(material);
		}
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.ATTACK_EVAL)
		{
			counter+=evalAttacks();
		}
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.BASE_EVAL)
		{
			counter+=evalMobility(listLen, depth, material);
		}
		
		AlphaBetaPrune.flipBoard();
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.BASE_EVAL)
		{
			material=evalMaterial();
			counter-=material;
		}
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.POST_EVAL)
		{
			counter-=evalPositional(material);
		}
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.ATTACK_EVAL)
		{
			counter-=evalAttacks();
		}
		
		if(ParameterFileParse.ALL_EVAL || ParameterFileParse.BASE_EVAL)
		{
			counter-=evalMobility(listLen, depth, material);
		}
		
		AlphaBetaPrune.flipBoard();
		
		return -(counter+depth*50);
		
	}
	
	/***
	 * Method used for calculating materialistic value
	 * @return
	 */
	
	public static int evalMaterial()
	{
		int counter=0, bishopCounter=0;
        for (int i=0;i<64;i++) 
        {
            switch (MoveGen.chessBoard[i/8][i%8]) 
            {
                case "P": counter+=100;
                    break;
                case "R": counter+=500;
                    break;
                case "K": counter+=300;
                    break;
                case "B": bishopCounter+=1;
                    break;
                case "Q": counter+=900;
                    break;
            }
        }
        
        if (bishopCounter>=2) 
        {
            counter+=300*bishopCounter;
        }
        
        else if (bishopCounter==1) 
        {
        	counter+=250;
        }
     
		return counter;
	}
	
	/***
	 * Method used to evaluate positional value of a piece
	 * @param material
	 * @return
	 */
	
	public static int evalPositional(int material)
	{
		int counter=0;
		
        for (int i=0;i<64;i++) 
        {
            switch (MoveGen.chessBoard[i/8][i%8]) 
            {
                case "P": counter+=pawnBoard[i/8][i%8];
                    break;
                    
                case "R": counter+=rookBoard[i/8][i%8];
                    break;
                    
                case "N": counter+=knightBoard[i/8][i%8];
                    break;
                    
                case "B": counter+=bishopBoard[i/8][i%8];
                    break;
                    
                case "Q": counter+=queenBoard[i/8][i%8];
                    break;
                    
                case "K": if (material>=1800) 
                		  {
                			  counter+=kingMidBoard[i/8][i%8]; counter+=King.possibleKingMoves(MoveGen.kingPositionC).length()*10;
                		  } 
                		  else
                		  {
                			  counter+=kingEndBoard[i/8][i%8]; counter+=King.possibleKingMoves(MoveGen.kingPositionC).length()*30;
                		  }
                    break;
            }
        }
        return counter;
	}
	
	/***
	 * Method used to evaluate Attacks
	 * @return
	 */
	
	public static int evalAttacks()
	{
		int counter=0;
		
        int tempPositionC=MoveGen.kingPositionC;
        
        for (int i=0;i<64;i++) 
        {
            switch (MoveGen.chessBoard[i/8][i%8]) 
            {
                case "P": MoveGen.kingPositionC=i; 
                		  if (!King.kingSafe()) 
                		  {
                			  counter-=64;
                		  }
                		  break;
                		  
                case "R": MoveGen.kingPositionC=i; 
                		  if (!King.kingSafe()) 
                		  {
                			  counter-=500;
                		  }
                		  break;
                		  
                case "N": MoveGen.kingPositionC=i; 
                	      if (!King.kingSafe()) 
                	      {
                	    	  counter-=300;
                	      }
                	      break;
                	      
                case "B": MoveGen.kingPositionC=i; 
                		  if (!King.kingSafe()) 
                		  {
                			  counter-=300;
                		  }
                		  break;
                		  
                case "Q": MoveGen.kingPositionC=i; 
                		  if (!King.kingSafe()) 
                		  {
                			  counter-=900;
                		  }
                		  break;
            }
        }
        
        MoveGen.kingPositionC=tempPositionC;
        
        if (!King.kingSafe()) 
        {
        	counter-=200;
        }
        
        return counter/2;
	}
	
	/***
	 * Method used to evaluate mobility of a piece
	 * @param listLen
	 * @param depth
	 * @param material
	 * @return
	 */
	
	public static int evalMobility(int listLen, int depth, int material)
	{
		int counter=0;
		
		//Five points for each move 
        counter+=listLen;
        
        if (listLen==0) 
        {
        	//if checkmate
        	if (!King.kingSafe()) 
            {
                counter+=-200000*depth;
            } 
        	//if stalemate
        	else 
        	{
                counter+=-150000*depth;
            }
        }
        return counter;
	}

}
