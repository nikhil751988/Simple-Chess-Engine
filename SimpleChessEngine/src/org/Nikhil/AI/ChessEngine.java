package org.Nikhil.AI;

import java.io.IOException;
import java.util.Scanner;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * ChessEngine Class
 */

public class ChessEngine {

	/***
	 * Main Method which runs the Engine 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		
		//Initialize the Board Mappings
		MoveGen.initializeBoardMapping();
		
		//Initialize the King Position
		MoveGen.calculateInitialKingsPos();
		
		//Parse the parameter file
		ParameterFileParse.parseParamFile();
		
		while(true)
		{
			int opt;
			System.out.println("Choose one of the options: \n");
			System.out.print("1. Change Depth\n2. Change Branch Factor\n3. Use All Evaluation Functions\n4. Use Basic Evaluation Function\n5. Use Attack Evaluation Function\n6. Use Positional Evaluation Function\n7. Continue with game\n8. Quit\n");
			System.out.print("\nEnter the option: ");
			Scanner scn = new Scanner(System.in);
			opt=Integer.parseInt(scn.next());
			switch(opt)
			{
				case 1: System.out.println("Depth needs to be in the range of 2-5.\nDeeper than this might take the engine more time to respond.\n");
						System.out.print("Enter the depth: ");
						int depth=Integer.parseInt(scn.next());
						ParameterFileParse.setGlobalDepth(depth);
						System.out.println("The new depth is: "+ParameterFileParse.GLOBAL_DEPTH);
						break;
						
				case 2: System.out.println("Branching factor can be in range of 5-10. Make sure that it is in the range to avoid exception.\n");
						System.out.print("Enter the Branching Factor or set it to 1000 for engine to observe all moves: ");
						int branchFac=Integer.parseInt(scn.next());
						ParameterFileParse.setBranchFactor(branchFac);
						System.out.println("The new Branching factor is set to "+ParameterFileParse.BRANCH_FACTOR);
						break;
						
				case 3: if(!ParameterFileParse.ALL_EVAL)
							ParameterFileParse.setAllEvalFlag("true");
						break;
						
				case 4: ParameterFileParse.setAllEvalFlag("false");
						if(ParameterFileParse.POST_EVAL)
							ParameterFileParse.setPostEval("false");
						if(ParameterFileParse.ATTACK_EVAL)
							ParameterFileParse.setAttackEvalFlag("true");
						ParameterFileParse.setBaseEvalFlag("true");
						break;
						
				case 5: ParameterFileParse.setAllEvalFlag("false");
						if(ParameterFileParse.BASE_EVAL)
							ParameterFileParse.setBaseEvalFlag("false");
						if(ParameterFileParse.POST_EVAL)
							ParameterFileParse.setPostEval("false");
						if(!ParameterFileParse.ATTACK_EVAL)
							ParameterFileParse.setAttackEvalFlag("true");
						break;
						
				case 6: ParameterFileParse.setAllEvalFlag("false");
						if(!ParameterFileParse.BASE_EVAL)
							ParameterFileParse.setBaseEvalFlag("true");
						if(ParameterFileParse.ATTACK_EVAL)
							ParameterFileParse.setPostEval("false");
						if(!ParameterFileParse.POST_EVAL)
							ParameterFileParse.setAttackEvalFlag("true");
						break;
				case 7: break;
				case 8: System.exit(1);
			}
			
			System.out.println("\n------------------------------------------------------------------------------------------\n\n");
			Utility.displayBoard();
			System.out.println("\nYou will be playing the White pieces\n");
			System.out.print("Enter the piece movement. For example a7-a5: ");
			String move=scn.next();
			
			//Make the human move
			MoveGen.execute_Human_Moves(move);
			
			//Computer's turn
			AlphaBetaPrune.flipBoard();
			MoveGen.makeMove(AlphaBetaPrune.alphaBeta(ParameterFileParse.GLOBAL_DEPTH, 1000000, -1000000, "", 0));
			AlphaBetaPrune.flipBoard();
			System.out.println("-------------------------------------------------------------------------------------------\n\n");
		}
	}

}
