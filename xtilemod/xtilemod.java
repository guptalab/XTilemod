/**
 * @(#)Xtile_arithmetic.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @version 1.00 2010/7/18
 */

/*
 *Main class Xtile_arithmetic
*/

import java.util.Scanner;
import java.io.*;

public class xtilemod
{
	public static void main(String[] args) throws Exception
    {
    	boolean input_s = true;
    	boolean input = true;
    	String str = null;   //stores menu1 input
    	String string1 = null;   //stores menu2 input
    	int sel=0;	
    	int select1=0;
    	BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
    	
    	System.out.println("Welcome to the xArithmetic world!!");
    	
    	do{
    		try
    		{
    			System.out.println("\nEnter your choice here: ");
		    	System.out.println("1. '2' integer operaations\n2. 'N' integer operations\n3. exit");
	    		System.out.print("Y1our choice: ");
    			
		      	str = is.readLine();
		      	sel = Integer.parseInt(str);    //converting string input to integer
		      	input = true;
    		}catch (NumberFormatException ex) {
		    	input_s = false;
		      	System.err.println("Not a valid number: " + str);
		    } catch (IOException e) {
		    	input_s = false;
		      	System.err.println("Unexpected IO ERROR: " + e);
		    }
		    if((sel <=0) || (sel >3))
			{
				input_s = false;
				System.out.println("Please enter intergers between 1 and 7");
			}
    	}while(!input);
		
		if(sel == 3)
		{
			System.out.println("\nSystem terminated\nThank You for using Xtile_Arithmetic");
			System.exit(1);
		}
		
		//reading select1 input if previous choice is 1 
		else if (sel == 1)
		{
			do{
	    		try 
	    		{
	    			System.out.println("\n\n------------------------------------------------\n");
	    			System.out.println("This computes operations on only two inputs.");
	    			System.out.println("\nEnter your choice here: ");
		    		System.out.println("1. addition(8 tile adder)\n2. addition(L configuration adder)\n3. addition(L configuration constant-tile adder)\n4. subtraction\n5. multiplication\n6. division\n7. exit");
	    			System.out.print("Your choice: ");
	    			
		      		string1 = is.readLine();
		      		select1 = Integer.parseInt(string1);    //converting string input to integer
		      		input_s = true;
		      	
		    	} catch (NumberFormatException ex) {
		    		input_s = false;
		      		System.err.println("Not a valid number: " + string1);
		    	} catch (IOException e) {
		    		input_s = false;
		      		System.err.println("Unexpected IO ERROR: " + e);
		    	}
		    
		    	if((select1 <=0) || (select1 >7))
				{
					input_s = false;
					System.out.println("Please enter intergers between 1 and 7");
				}
			}while(!input_s);
		
			if(select1 == 7)
			{
				System.out.println("\nSystem terminated\nThank You for using Xtile_Arithmetic");
				System.exit(1);
			}
			else 
			{
				switch(select1)
				{
					case 1 : {
								add_8_tile obj = new add_8_tile(); 
						    	break;
						 	 }
					case 2 : {
								add_L_variable obj = new add_L_variable(); 
						    	break;
						 	 }
					case 3 : {
								add_L_constant obj = new add_L_constant (); 
						    	break;
						 	 }
					case 4 : {
								subtract obj = new subtract(); 
						    	break;
						 	 }
					case 5 : {
								multiply obj = new multiply(); 
						    	break;
						 	 }
					default : divide obj = new divide(); 
				}
			}	
		}
		
		//reading select1 input if previous choice is 2
		else
		{
			do{
	    		try 
	    		{
	    			System.out.println("\n\n------------------------------------------------\n");
	    			System.out.println("This computes operations on 'n' integer inputs.");
	    			System.out.println("\nEnter your choice here: ");
		    		System.out.println("1. nadder(8 tile)\n2. nadder(L configuration adder)\n3. multiplication\n4. addition, subtraction expression solver\n5. modulus\n6. exit");
	    			System.out.print("Your choice: ");
	    		
		      		string1 = is.readLine();
		      		select1 = Integer.parseInt(string1);    //converting string input to integer
		      		input_s = true;
		      	
		    	} catch (NumberFormatException ex) {
		    		input_s = false;
		      		System.err.println("Not a valid number: " + string1);
		    	} catch (IOException e) {
		    		input_s = false;
		      		System.err.println("Unexpected IO ERROR: " + e);
		    	}
		    
		    	if((select1 <=0) || (select1 >6))
				{
					input_s = false;
					System.out.println("Please enter intergers between 1 and 7");
				}
			}while(!input_s);
			
			if(select1 == 6)
			{
				System.out.println("\nSystem terminated\nThank You for using Xtile_Arithmetic");
				System.exit(1);
			}
			else 
			{
				switch(select1)
				{
					case 1 : {
								nadder_8_tile obj = new nadder_8_tile(); 
						    	break;
						 	 }
					case 2 : {
								nadder_L_type obj = new nadder_L_type(); 
						    	break;
						 	 }
					case 3 : {
								n_multiply obj = new n_multiply(); 
						    	break;
						 	 }
					case 4 : {
								 addsubtract obj = new addsubtract(); 
						    	break;
						 	 }
					default : modular obj = new modular(); 
				}
			}	
		}
   }
}
