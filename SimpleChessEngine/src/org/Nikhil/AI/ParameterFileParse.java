package org.Nikhil.AI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/***
 * 
 * @author NIKHIL BHARADWAJ RAMESHA
 * ParameterFileParse class
 */

public class ParameterFileParse {

	public static int GLOBAL_DEPTH=1;
	
	public static int BRANCH_FACTOR=1000;
	
	public static boolean MOVE_ORDER;
	
	public static boolean ALL_EVAL;
	
	public static boolean BASE_EVAL;
	
	public static boolean ATTACK_EVAL;
	
	public static boolean POST_EVAL;
	
	/***
	 * Method to parse input Parameter file
	 * @throws IOException
	 */
	
	public static void parseParamFile()throws IOException
	{
		
		System.out.print("Enter the Parameter File location: ");
		Scanner scn = new Scanner(System.in);
		String presentDirectory = scn.next();
		String fileName=presentDirectory+"\\Parameter.txt";
		
		BufferedReader bfReader = new BufferedReader(new FileReader(fileName));
		
		String readLine="";
		
		while((readLine=bfReader.readLine())!=null)
		{
			if(!(readLine.startsWith("[")) && !(readLine.startsWith("#")))
			{
				String [] readParams = readLine.split("=");
				
				switch(readParams[0])
				{
					case "DEPTH"         : setGlobalDepth(Integer.parseInt(readParams[1]));
					 			           break;
					case "BRANCH_FACTOR" : setBranchFactor(Integer.parseInt(readParams[1]));	
										   break;					
					case "MOVE_ORDER"    : setMoveOrderFlag(readParams[1]);
									       break;
					case "USE_ALL_EVAL"  : setAllEvalFlag(readParams[1]);
										   break;
					case "USE_BASIC_EVAL": setBaseEvalFlag(readParams[1]);
										   break;
					case "USE_ATTACK"    : setAttackEvalFlag(readParams[1]);
					                       break;
					case "USE_POSITIONAL": setPostEval(readParams[1]);
										   break;
				}
			}
		}
		
		bfReader.close();
	}
	
	/***
	 * Method to set global depth
	 * Global Depth not allowed over 5
	 * @param val
	 */
	
	public static void setGlobalDepth(int val)
	{
		if(val<=5)
		{
			GLOBAL_DEPTH=val;
		}
		else
		{
			GLOBAL_DEPTH=5;
		}
	}
	
	/***
	 * Method to set branching factor
	 * @param val
	 */
	
	public static void setBranchFactor(int val)
	{
		BRANCH_FACTOR=val;
	}
	
	/***
	 * Method to set Move Order Flag
	 * @param val
	 */
	
	public static void setMoveOrderFlag(String val)
	{
		if(val.equalsIgnoreCase("true"))
		{
			MOVE_ORDER=true;
		}
		else
		{
			MOVE_ORDER=false;
		}
	}
	
	/***
	 * Method to set All evaluation flag
	 * @param val
	 */
	
	public static void setAllEvalFlag(String val)
	{
		if(val.equalsIgnoreCase("true"))
		{
			ALL_EVAL=true;
		}
		else
		{
			ALL_EVAL=false;
		}
	}
	
	/***
	 * Method to set Basic evaluation flag
	 * @param val
	 */
	
	public static void setBaseEvalFlag(String val)
	{
		if(val.equalsIgnoreCase("true"))
		{
			BASE_EVAL=true;
		}
		else
		{
			BASE_EVAL=false;
		}
	}
	
	/***
	 * Method to set Attack Evaluation Flag
	 * @param val
	 */
	
	public static void setAttackEvalFlag(String val)
	{
		if(val.equalsIgnoreCase("true"))
		{
			ATTACK_EVAL=true;
		}
		else
		{
			ATTACK_EVAL=false;
		}
	}
	
	/***
	 * Method to set Positional Evaluation flag
	 * @param val
	 */
	
	public static void setPostEval(String val)
	{
		if(val.equalsIgnoreCase("true"))
		{
			POST_EVAL=true;
		}
		else
		{
			POST_EVAL=false;
		}
	}

}
