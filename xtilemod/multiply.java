/**
 * @(#)multiply.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya 
 * @version 1.00 2010/7/17
 */

/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * Multiplication of two integers using 28 computational tiles
 * Input1  : Bottommost Row represent First input
 * Input2  : Rightmost Column represents Second Input(<=First Input)
 * Output  : Topmost Row represents the output
 * Seed Tile: Green
 * Output Tile: Red = 1                 White = 0
 * Input  Tile: Brown = 1               yellow = 0
 * colour code of few tiles which are imp.	:	Brown(11)	yellow(10)	grey(01)	pink(00)
 */
 
 
 //package mypack;
import java.io.*;
import java.util.*;



public class multiply {
	
	String fileName = "";
	String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%Input  Tile:\tBrown = 1 \tyellow = 0\n";
	int num_tile_types = 0;      //calculating total number of tile types
	int num_bd = 0;              //calculating total num
	
	boolean input_a = false;
    boolean input_b = false;
    
    int one = 0;    //stores first integer input
	int two = 0;    //stores second integer input
	int a[],b[];
    	
    String string1 = null;   //first number got from user 
	String string2 = null;   //second number gor from user
	
	PrintWriter fp;
	
	//binding domains 
	int bindingIndex=0;
	int bINormal[] = new int[2];
	int bINormal1[] = new int[4];
	int bIMult[] = new int[2];
	int bISpecial = 0;
	int bISpecial2 = 0;
	int bseed = 0;

    public multiply() 
    {
    	while(!input_a)
		{
//taking first input and converting it into binary..
			try 
			{
				System.out.print("\nPlease enter first decimal value: ");
			    BufferedReader is1 = new BufferedReader(new InputStreamReader(System.in));
			   	string1 = is1.readLine();
		      	one = Integer.parseInt(string1);       //converting string input to integer
		      	input_a = true;
				      	
			} catch (NumberFormatException ex) {
			 	input_a = false;
				System.err.println("Not a valid number: " + string1);
			} catch (IOException e) {
				input_a = false;
				System.err.println("Unexpected IO ERROR: " + e);
			}
			string1=Integer.toBinaryString(one);	//converting integer into binary
		}
				
		//reading second input
		while(!input_b)
		{
//taking second input and converting it into binary..
			try 
		 	{
	    		System.out.print("\nPlease enter second decimal value: ");
	    		BufferedReader is2 = new BufferedReader(new InputStreamReader(System.in));
		      	string2 = is2.readLine();
		      	two = Integer.parseInt(string2);
		      	input_b = true;
		      		      	
			} catch (NumberFormatException ex) {
		    	input_b = false;
		      	System.err.println("Not a valid number: " + string2);
		    } catch (IOException e) {
		    	input_b = false;
		      	System.err.println("Unexpected IO ERROR: " + e);
		    }
		    string2=Integer.toBinaryString(two);	//converting integer into binary
		}
		
		//making the bigger integer as input for horizontal frame and smaller integer for right side frame and then storing both the binary number in two arrays..
		int var=0;
		String temp="";
		if(two > one)  // keep first number larger one alwaya
		{
			temp=string2;
			string2=string1;
			string1=temp;
			var=one;
			one=two;
			two=var;
		}
		
		int dif = string1.length()*2;
		a = new int[dif];  //first number has to be padded with extra zero as product could be 2n bit long
		b = new int[string2.length()];

		for(int i=string1.length(); i<dif; i++)
			string1 = '0'+string1;

		for(int i=0; i<dif;i++)
			a[i] = Character.digit((string1.charAt(i)),10 );
			
		for(int i=0; i<string2.length(); i++)
			b[i] = Character.digit((string2.charAt(i)),10 );
    
    	//FINDING TOTAL NUMBER OF TILE AND GLUE THAT WILL BE USED
		num_tile_types = a.length+b.length+28+1; //calculating total number of tile types
		num_bd = a.length+b.length+8; //calculating total number of binding domains
		
		fileName = "mult"+one+","+two+".tiles";
		//opening the file
		try
		{
			fp = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
		
		//writing into the file	
		fp.println("% " + fileName);
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");
		fp.println("%Colouring Pattern\n"+ColourIndication);	
		fp.println("% Multiplication tile set of "+one+ ","+two);
		fp.println("\n% The top most row shows the product of "+one+" and "+two+"\n");
		fp.println("\n%-----Inputs in Binary------\n");
		fp.println("%          "+one+"\t\t"+Integer.toBinaryString(one));
		fp.println("%         x"+two+"\t\t"+Integer.toBinaryString(two));
		fp.println("%---------------------------");
		fp.println("% product: "+(one*two)+"\t\t"+Integer.toBinaryString((one*two))+"\n");
		
		System.out.println("Required Multiplication tile set is loaded in to "+fileName);
		
		fp.println("tile edges matches {{N E S W}*}");
		fp.println("num tile types="+ num_tile_types);
		fp.println("num binding types="+ num_bd);
		fp.println("tile edges={");
		
			
		bindingIndex = 0;//binding domain index
				
		//assigning binding domain index for 0, 1
		for(int i=0; i < 2; i++)
			bINormal[i] = ++bindingIndex;
			
		//assining binding domain index for 00, 01, 10, 11
		for(int i=0; i < 4; i++)
			bINormal1[i] = ++bindingIndex;

		//assining binding domain index for 20,21
		for(int i=0; i < 2; i++)
			bIMult[i] = ++bindingIndex;
	
		
		//------TILESET GENERATION STARTS-----				
		bseed = ++bindingIndex;
		
		//----SEED TILE
		fp.println("%--------------Seed Tile(Green)-------------");
		fp.println("{" + (bseed) + " " + "0" + " " + "0" + " " + (++bindingIndex) + "}" + "[.1](green)" );
				
		//----HORIZONTAL FRAME TILESET GENERATION OR BIGGER NUMBER
		fp.println("%--------------Horizontal Frame Tile (1-Brown, 0-Yellow)-------------");

		int i;
		for(i=(a.length-1); i>0; i--)
		{
			fp.print( "{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " + (++bindingIndex) + "}" + "[.1]" );
			if(a[i]==0)
				fp.println("(brown)");
			else
				fp.println("(yellow)");
		}
		if(a[i] == 0)
			fp.println("{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " +"0"+ "}" + "[.1](brown)");
		else
			fp.println("{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " +"0"+ "}" + "[.1](yellow)");	
				
		//----VERTICAL FRAME TILESET GENERATION OR SMALLER NUMBER
		fp.println("%--------------Vertical Frame Tile(1-Brown,0-Yellow)-------------");
		if(b[b.length-1] == 0)
		{
			fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bseed) +" " + 1 + "}" + "[.1]" );
			fp.println("(yellow)");
		}
		else
		{
			fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bseed)+" " + 2 + "}" + "[.1]" );
			fp.println("(brown)");
		}
			
		for(i=(b.length-2); i>0; i--)
		{
			bISpecial=bindingIndex;
			if(b[i]==0)
			{
				fp.print( "{" + (++bindingIndex) + " "+ "0" + " " + bISpecial +" "+bINormal1[0]+ "}" + "[.1]" );
				fp.println("(yellow)");
			}
			else
			{
				fp.print( "{" + (++bindingIndex) + " "+ "0" + " " + bISpecial +" "+ bINormal1[2] + "}" + "[.1]" );
				fp.println("(brown)");
			}
		}
		if(b[0] == 0)
			fp.println("{0 0 "+ bindingIndex + " "+bINormal1[0]+ "}" + "[.1](yellow)");
		else
			fp.println("{0 0 " +bindingIndex + " " +bINormal1[2]+ "}[.1](brown)");
			
		//---- COMPUTATION TILE SET GENERATION
		fp.println("%---------------------Block no 1 -------------------");	
		fp.println("{3 1 1 1}(red)");
		fp.println("{3 2 1 2}(red)");
		fp.println("{5 1 2 1}(red)");
		fp.println("{6 2 2 2}(red)");
				
		fp.println("%---------------------Block no 2 (red-1 orange-0) -------------------");
		fp.println("{3 3 3 3}(white)");
		fp.println("{4 3 4 3}(white)");
		fp.println("{5 4 3 3}(white)");
		fp.println("{6 4 4 3}(red)");
		fp.println("{3 3 5 4}(white)");
		fp.println("{4 3 6 4}(red)");
		fp.println("{5 4 5 4}(white)");
		fp.println("{6 4 6 4}(red)");
				
		fp.println("%---------------------Block no 3 (red-1 white-0) -------------------");
		fp.println("{3 5 5 6}(white)");
		fp.println("{3 5 3 5}(white)");
		fp.println("{6 6 3 5}(red)");
		fp.println("{6 6 5 6}(red)");
		fp.println("{4 5 4 5}(red)");
		fp.println("{4 5 6 6}(red)");
		fp.println("{5 6 4 7}(white)");
		fp.println("{5 6 6 8}(white)");
				
		fp.println("%---------------------Block no 4 (orange-1 orange-0) -------------------");
		fp.println("{4 7 3 5}(red)");
		fp.println("{4 7 5 6}(red)");
		fp.println("{5 8 3 7}(white)");
		fp.println("{5 8 5 8}(white)");
		fp.println("{3 7 4 7}(white)");
		fp.println("{3 7 6 8}(white)");
		fp.println("{6 8 4 7}(red)");
		fp.println("{6 8 6 8}(red)");
		
		fp.println("}");
				
		fp.println("binding strengths=");
		fp.print("{");
		
		//----WRITING BINDING STRENGTH FOR ALL BINDING DOMAINS
		for(i=0; i<8; i++)
			fp.print("2 ");
		for(;i<num_bd;i++)
			fp.print("3 ");
		fp.println("}");
				
		//assigning size of terminal, block(tile) size,
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=3");
		
		fp.close(); //closing the file	
    }
}