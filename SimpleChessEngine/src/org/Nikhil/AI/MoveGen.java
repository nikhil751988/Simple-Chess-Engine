package org.Nikhil.AI;

import java.util.HashMap;

/***
 * 
 * @author NIKHIL BHRADWAJ RAMESHA
 * MoveGen Class
 */

public class MoveGen {
	
	//HashMap to store the file to number mappings
	public static HashMap<Character, Integer> boardMappings=new HashMap<Character, Integer>();
	
	//Chess Board grid
	public static String [][] chessBoard = {{"r","n","b","q","k","b","n","r"},
		   									{"p","p","p","p","p","p","p","p"},
		   									{"_","_","_","_","_","_","_","_"},
		   									{"_","_","_","_","_","_","_","_"},
		   									{"_","_","_","_","_","_","_","_"},
		   									{"_","_","_","_","_","_","_","_"},
		   									{"P","P","P","P","P","P","P","P"},
		   									{"R","N","B","Q","K","B","N","R"}};
	
	static int kingPositionC, kingPositionL;
	
	/***
	 * Method to execute Human Moves
	 * @param moveType
	 */
	
	public static void execute_Human_Moves(String moveType)
	{
		String [] positions=moveType.split("-");
		int getSrcCol=boardMappings.get(positions[0].charAt(0));
		int getSrcRow=Character.getNumericValue(positions[0].charAt(1));
		int getDestCol=boardMappings.get(positions[1].charAt(0));
		int getDestRow=Character.getNumericValue(positions[1].charAt(1));
		chessBoard[getDestRow-1][getDestCol]=chessBoard[getSrcRow-1][getSrcCol];
		chessBoard[getSrcRow-1][getSrcCol]="_";
		System.out.println("New Board State: \n");
		Utility.displayBoard();
	}
	
	/***
	 * Method to initialize board mapping
	 */
	
	public static void initializeBoardMapping()
	{
		for(int i=0;i<Utility.files.length;i++)
		{
			boardMappings.put(Utility.files[i], i);
		}
	}
	
	/***
	 * Method to calculate Initial King Positions
	 */
	
	public static void calculateInitialKingsPos()
	{
		while(!"K".equals(chessBoard[kingPositionC/8][kingPositionC%8]))
		{
			kingPositionC++;
		}
		
		while(!"k".equals(chessBoard[kingPositionL/8][kingPositionL%8]))
		{
			kingPositionL++;
		}
	}
	
	/***
	 * Method to make the move
	 * @param receivedMove
	 */
	
	public static void makeMove(String receivedMove)
	{
		if (receivedMove.charAt(4)!='P') 
		{
            chessBoard[Character.getNumericValue(receivedMove.charAt(2))][Character.getNumericValue(receivedMove.charAt(3))]=chessBoard[Character.getNumericValue(receivedMove.charAt(0))][Character.getNumericValue(receivedMove.charAt(1))];
            chessBoard[Character.getNumericValue(receivedMove.charAt(0))][Character.getNumericValue(receivedMove.charAt(1))]="_";
            
            if ("K".equals(chessBoard[Character.getNumericValue(receivedMove.charAt(2))][Character.getNumericValue(receivedMove.charAt(3))])) {
                kingPositionC=8*Character.getNumericValue(receivedMove.charAt(2))+Character.getNumericValue(receivedMove.charAt(3));
            }
        } 
		else 
		{
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(receivedMove.charAt(0))]="_";
            chessBoard[0][Character.getNumericValue(receivedMove.charAt(1))]=String.valueOf(receivedMove.charAt(3));
        }
	}
	
	/***
	 * Method to undo the move
	 * @param move
	 */
	
	public static void undoMove(String move)
	{
        if (move.charAt(4)!='P')
        {
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
            
            if ("K".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
                kingPositionC=8*Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1));
            }
        } 
        else 
        {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))]="P";
            chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(2));
        }
    }
	
	/***
	 * Method to generate all possible moves
	 * @return
	 */
	
	public static String generatePossibleMoves() 
	{
        String list="";
        for (int i=0; i<64; i++) 
        {
            switch (chessBoard[i/8][i%8]) 
            {
                case "P": list+=Pawn.possiblePawnMoves(i);
                    break;
                case "R": list+=Rook.possibleRookMoves(i);
                    break;
                case "N": list+=Knight.possibleKnighMoves(i);
                    break;
                case "B": list+=Bishop.possibleBishopMoves(i);
                    break;
                case "Q": list+=Queen.possibleQueenMoves(i);
                    break;
                case "K": list+=King.possibleKingMoves(i);
                    break;
            }
        }
        
        if(ParameterFileParse.BRANCH_FACTOR==1000 || list.length()/5<=ParameterFileParse.BRANCH_FACTOR)
        {
        	return list;
        }
        else
        {
        	//Only few moves are observed in the analysis
        	return list.substring(0, ParameterFileParse.BRANCH_FACTOR*5);
        }
        
	}

}
