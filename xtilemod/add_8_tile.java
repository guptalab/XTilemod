/**
 * @(#)add_8_tile.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya 
 * @version 1.00 2010/7/17
 */

/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * Addition of two integers using 8 tile method
 * Input1 : Bottommost Row represents the First input
 * Input2 : Topmost Row Represents the Second input
 * Output : The middle Row Represents the Output
 * Seed Tile :Green
 * Output Tile: Red = 1            White = 0
 * Input  Tile:Brown = 1           yellow = 0
 */

//package mypack;
import java.util.Scanner;
import java.io.*;


public class add_8_tile {
 
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
	int bISpecial = 0;
	int bISpecial2 = 0;
	int bseed = 0;
	
	public add_8_tile()
	{
//taking first input and converting it into binary..
		while(!input_a)
		{
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
		
		//making input of the same size by adding zeros in the beginning of the binary number. and then storing both the binary number in two arrays..
		if(string1.length() > string2.length())  //keeping the first number bigger one 
		{
			a = new int[string1.length()+1];  //first number has to be padded with extra zero for carry bit
			b = new int[string1.length()+1];
			string1='0'+string1;
			String temp = "";
			int dif = string1.length() - string2.length(); //one added as to add extra zero for carry bit
			for(int i=0; i<dif; i++)
				temp = '0'+temp;
			string2=temp+string2;
						
			for(int i=0; i<string1.length();i++)
			{
				a[string1.length() - 1 - i] = Character.digit((string1.charAt(i)),10 );
				b[string2.length() - 1 - i] = Character.digit((string2.charAt(i)),10 );
			}
		}
		else
		{
			a = new int[string2.length()+1];  //first number has to be padded with extra zero for carry bit
			b = new int[string2.length()+1];
			string2='0'+string2;
			String temp = "";
			int dif = string2.length() - string1.length(); //one added as to add extra zero for carry bit
			for(int i=0; i<dif; i++)
				temp = '0'+temp;
			string1=temp+string1;
			
			for(int i=0; i<string1.length();i++)
			{
				a[string1.length() - 1 - i] = Character.digit((string1.charAt(i)),10 );
				b[string2.length() - 1 - i] = Character.digit((string2.charAt(i)),10 );
			}	
		}
	    
	    //FINDING TOTAL NUMBER OF TILE AND GLUE THAT WILL BE USED
		num_tile_types = 3+8+a.length+b.length;   //calculating total number of tile types
		num_bd = 4+a.length+b.length;   //calculating total number of binding domains
		
		fileName = "add_8_tile_"+one+","+two+".tiles";
		//opening the file
		try
		{
			fp = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
		
		//writing into the file
		fp.println("% " + fileName + " (8 tile adder)");
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");
		fp.println("%Colouring Pattern\n"+ColourIndication);	
		fp.println("% Addition tile set of "+one+ ","+two);
		fp.println("\n% The middle row shows the sum of "+one+ ", "+two+" to get output of "+(one+two)+"\n");
		fp.println("\n%-----Inputs in Binary------\n");
		fp.println("%      "+one+"\t\t"+Integer.toBinaryString(one));
		fp.println("%     +"+two+"\t\t"+Integer.toBinaryString(two));
		fp.println("%---------------------------");
		fp.println("% sum: "+(one+two)+"\t\t"+Integer.toBinaryString((one+two))+"\n");
		
		System.out.println("Required Addition tile set is loaded in to "+fileName);
	    
	    fp.println("tile edges matches {{N E S W}*}");
		fp.println("num tile types="+ num_tile_types);
		fp.println("num binding types="+ num_bd);
		fp.println("tile edges={");
		
			
		bindingIndex = 0;//binding domain index
				
		//assigning binding domain index for 0, 1
		for(int i=0; i < 2; i++)
		{
			bINormal[i] = ++bindingIndex;
		}
		
		//------TILESET GENERATION STARTS-----
		bISpecial = ++bindingIndex;
		bISpecial2 = ++bindingIndex;
		bseed = ++bindingIndex;
					
		//----SEED TILE
		fp.println("%---------------------Seed Tiles(green)--------------");
		fp.print("{"+bISpecial+" "+0+" "+0+" "+bseed + "}"+"[.1]");
		fp.println("(green)");
					
		//----HORIZONTAL FRAME TILESET GENERATION OR FIRST INPUT
		fp.println("%---------------------Horizontal Frame Tile(brown-1,yellow-0)--------------");
		int i; 
		for(i=0; i<a.length-1; i++)
		{
			fp.print( "{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " + (++bindingIndex) + "}" + "[.1]" );
			if(a[i]==0)
				fp.println("(yellow)");
			else
				fp.println("(brown)");	
		}
		fp.print( "{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " +0+ "}" + "[.1]" );
		if(a[i]==0)
			fp.println("(yellow)");
		else
			fp.println("(brown)");
		
		//----VERTICAL FRAME TILESET GENERATION
		fp.println("%---------------------VerticaL Frame Tile--------------");
		fp.print("{"+bISpecial2+" "+0+" "+bISpecial + " "+bINormal[0]+ "}"+"[.1]");
		fp.println("(orange)\t% starter tile");
		fp.print("{"+0+" "+0+" "+bISpecial2 +" "+(++bindingIndex)+"}"+"[.1]");
		fp.println("(orange)\t% top corner tile");
					
		//----TOPMOST ROW TILE SET GENERATION OR SECOND INPUT
		fp.println("%---------------------Topmost Row(brown-1,yellow-0)--------------");
		for(i=0; i<b.length-1; i++)
		{
			fp.print( "{" +0+ " " + bindingIndex + " "+ (b[i]+bINormal[0]) + " " + (++bindingIndex) + "}" + "[.1]" );
			if(b[i]==0)
				fp.println("(yellow)");
			else
				fp.println("(brown)");
		}
		fp.print( "{" +0+ " " + bindingIndex + " "+ (b[i]+bINormal[0]) + " " +0+ "}" + "[.1]" );
		if(b[i]==0)
			fp.println("(yellow)");
		else
			fp.println("(brown)");
		
		//----8 COMPUTATION TILE SET GENERATION
		fp.println("\n% --------computational tiles(Red-1,White-0) ---------");
		fp.println("%   White Tiles means zero in final output");
		fp.println("%   Red Tiles means one in final output");
		fp.println("{ "+1+" "+1+" "+1+" " +1+" }(white)");
		fp.println("{ "+2+" "+2+" "+1+" " +2+" }(white)");
		fp.println("{ "+1+" "+2+" "+2+" " +2+" }(white)");
		fp.println("{ "+2+" "+1+" "+2+" " +2+" }(white)");
		fp.println("{ "+2+" "+1+" "+1+" " +1+" }(red)");
		fp.println("{ "+1+" "+2+" "+1+" " +1+" }(red)");
		fp.println("{ "+1+" "+1+" "+2+" " +1+" }(red)");
		fp.println("{ "+2+" "+2+" "+2+" " +2+" }(red)");
		
		fp.println("}");
				
		fp.println("binding strengths=");
		fp.print("{");
		
		//----WRITING BINDING STRENGTH FOR ALL BINDING DOMAINS
		fp.print("1 1 ");
		for(i=2; i< num_bd; i++)
			fp.print("3 ");
		fp.println("}");
				
		//assigning size of terminal, block(tile) size,
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=3");
		
		fp.close(); //closing the file
	}
}