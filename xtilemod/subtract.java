/**
 * @(#)subtract.java
 * @Mentor: Manish Gupta
 * @Author: Avinash, Bhuma 
 * @version 1.00 2010/7/17
 */

/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * Subtraction of two integers
 * Input1  : Bottommost Row represent First input
 * Input2  : Rightmost Column represents Second Input(<=First Input)
 * Output  : Topmost Row represents the output
 * Seed Tile: Green
 * Output Tile: Red = 1                 White = 0
 * Input  Tile: Brown = 1               yellow = 0
 * colour code of few tiles which are imp.	:	Brown(11)	yellow(10)	grey(01)	pink(00)
 */



 //package mypack;
import java.util.Scanner;
import java.io.*;



public class subtract {

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
	int bIStar[] = new int[4];
	int bIPlus[] = new int[4];
	int bISpecial = 0;
	int bseed = 0;
	
    public subtract()
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
		
		//the nuber to be subtracted is made as vetical frame tile and the nuber from which 2nd number is subtracted is made the horizontal number..  and then storing both the binary number in two arrays..  
		if(string1.length()>=string2.length())  // bigger number out of two input is made first number
		{
			a = new int[string1.length()+1];  //first number has to be padded with extra zero for carry bit
			b = new int[string2.length()];
			string1='0'+string1;
		}
		else
		{
			a = new int[string2.length()+1];
			b = new int[string2.length()];
			for(int i=string1.length();i<string2.length();i++)
				string1='0'+string1;
		    string1='0'+string1;
		}
		for(int i=0; i<string1.length();i++)
			a[string1.length() - 1 - i] = Character.digit((string1.charAt(i)),10 );

		for(int i=0; i<string2.length();i++)
			b[string2.length() - 1 - i] = Character.digit((string2.charAt(i)),10 );
	
		//FINDING TOTAL NUMBER OF TILE AND GLUE THAT WILL BE USED
		num_tile_types = 1+a.length+1+b.length+1+4+2+1+16; //calculating total number of tile types
		num_bd = 1+a.length+b.length+1+6; //calculating total number of binding domains
		
		
		fileName = "subtract"+one+","+two+".tiles";
		//opening the file
		try
		{
			fp = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
		
		//writing into the fill
		fp.println("% " + fileName);
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Bhooma and Avinash");
		fp.println("%Colouring Pattern\n"+ColourIndication);	
		fp.println("% Subtraction tile set of "+one+ ","+two);
		fp.println("\n% The top most row shows the difference between "+one +" and "+two+"\n");
		fp.println("\n%-----Inputs in Binary------\n");
		fp.println("%             "+one+"\t\t"+Integer.toBinaryString(one));
		fp.println("%            -"+two+"\t\t"+Integer.toBinaryString(two));
		fp.println("%---------------------------");
		fp.println("% difference: "+(one-two)+"\t\t"+Integer.toBinaryString((one-two))+"\n");
		
		System.out.println("Required Subtraction tile set is loaded in to "+fileName);
		
		fp.println("tile edges matches {{N E S W}*}");
		fp.println("num tile types="+ num_tile_types);
		fp.println("num binding types="+ num_bd);
		fp.println("tile edges={");
		
			
		bindingIndex = 0;//binding domain index
				
		//assigning binding domain index for 0, 1
		for(int i=0; i < 2; i++)
			bINormal[i] = ++bindingIndex;
			
		//assining binding domain index for 0*, 1*
		for(int i=0; i < 2; i++)
			bIStar[i] = ++bindingIndex;
	
		//assining binding domain index for 0+, 1+
		for(int i=0; i < 2; i++)
			bIPlus[i] = ++bindingIndex;
		
		//------TILESET GENERATION STARTS-----
		
		
		bISpecial = ++bindingIndex;
		bseed = ++bindingIndex; //assigning bd for corner tile
		
		//----SEED TILE
		fp.println("%---------------------Seed Tiles(green)--------------");
		fp.print( "{" + bseed + " " + "0" + " " + "0" + " " + bseed + "}" + "[.1]" );
		fp.println("(green)");
			
		//----HORIZONTAL FRAME TILESET GENERATION OR THE FIRST INPUT
		fp.println("%---------------------Horizontal Frame Tile(brown-1,yellow-0)--------------");
		for(int i=0; i< a.length; i++)
		{
			fp.print( "{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " + (++bindingIndex) + "}" + "[.1]" );
			if(a[i]==0)
			{
				fp.println("(yellow)");
			}
			else
			{
				fp.println("(brown)");
			}
		}
		fp.print( "{" + bISpecial + " " + (bindingIndex ) + " " + "0" + " " + "0" + "}" + "[.1]" );
		fp.println("(green)");
		
		//----VERTICAL FRAME TILESET GENERATION OR THE SECOND INPUT
		fp.println("%---------------------VerticaL Frame Tile(brown-1,yellow-0)--------------");
		for(int i=0; i<b.length; i++)
		{
			if(i==0)
				fp.print( "{" + (++bindingIndex) + " " + "0" + " " + bseed + " " + (b[i]+bIStar[0]) + "}" + "[.1]" );
			else
				fp.print( "{" + (++bindingIndex) + " " + "0" + " " + (bindingIndex - 1) + " " + (b[i]+bIPlus[0]) + "}" + "[.1]" );
	
			if(b[i]==0)
				fp.println("(yellow)");				
			else
				fp.println("(brown)");
		}
				
		fp.print( "{" + "0" + " " + "0" + " " + (bindingIndex) + " " + bISpecial + "}" + "[.1]" );
		fp.println("(orange)");
		
		//----BOUNDARY TILES
		int binary[][] = new int[4][2];
		for(int i=0; i<4; i++)
		{
			binary[i][0] = i%2;
			binary[i][1] = i/2;
		}
		fp.println("%---------------------Horizontal Boundary Tile(Red-1,White-0)--------------");
		for(int i=0; i<2; i++)
		{
			fp.print( "{" + "0" + " " + bISpecial + " " + (binary[i][0] + bINormal[0]) + " " + bISpecial + "}" );
			if(binary[i][0]==0)
				fp.println("(white)");//Modified and color toggling
			else
				fp.println("(red)");
		}
		for(int i=0; i<2; i++)
		{
			fp.print( "{" + "0" + " " + bISpecial + " " + (binary[i][0] + bIStar[0]) + " " + bISpecial + "}" );
			if(binary[i][0]==0)
				fp.println("(white)");
			else
				fp.println("(red)");
		}
		
		fp.println("%---------------------Vertical Boundary Tile--------------");		
		fp.print( "{" + bISpecial + " " + bINormal[0] + " " + bISpecial + " " + "0" + "}"  );
		fp.println("(dark green)");
		fp.print( "{" + bISpecial + " " + bINormal[1] + " " + bISpecial + " " + "0" + "}"  );
		fp.println("(dark green)");
		fp.print( "{" + "0" + " " + bISpecial + " " + bISpecial + " " + "0" + "}" );
		fp.println("(dark green)");
		
		//----COMPUTATION TILE SET GENERATION
		for(int i=0; i<4; i++)
		{
			fp.print( "{" + ((binary[i][0]^binary[i][1]) + bINormal[0]) + " " + (binary[i][0] + bINormal[0]) + " " + (binary[i][1] + bINormal[0]) + " " + ( ((~binary[i][1])&binary[i][0]) + bINormal[0]) + "}" );
			fp.println("(orange)\t\t% normal tile");
			fp.print( "{" + ((binary[i][0]^binary[i][1]) + bIStar[0]) + " " + (binary[i][0] + bIStar[0]) + " " + (binary[i][1] + bINormal[0]) + " " + ( ((~binary[i][1])&binary[i][0]) + bINormal[0]) + "}" );
			fp.println("(blue)\t\t% sum tile");
			fp.print( "{" + (binary[i][1] + bINormal[0]) + " " + (binary[i][0] + bIPlus[0]) + " " + (binary[i][1] + bINormal[0]) + " " + (binary[i][0] + bIPlus[0]) + "}" );
			fp.println("(purple)\t\t% sifting tile");
			fp.print( "{" + (binary[i][1] + bINormal[0]) + " " + (binary[i][0] + bIPlus[0]) + " " + (binary[i][1] + bIStar[0]) + " " + (binary[i][0] + bIStar[0]) + "}" );
			fp.println("(magenta)\t\t% conversion tile");
		}
		
		fp.println("}");
			
		fp.println("binding strengths=");
		fp.print("{");
		
		//----WRITING BINDING STRENGTH FOR ALL BINDING DOMAINS
		for(int i=1; i<= num_bd; i++)
		{
			if(i<=(6+1))
				fp.print("1 ");
			else
				fp.print("2 ");
		}
		fp.println("}");
		//assigning size of terminal, block(tile) size,
		fp.println("size=24");
		fp.println("block=12");
		fp.println("seed=100,100,1");
		fp.println("update_rate=10000");
		fp.println("Gse=12.3");
		fp.println("Gmc=24");
		fp.println("T=2");	
			
		fp.close(); //closing the file
    }
    
    
}