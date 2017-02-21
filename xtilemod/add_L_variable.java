/**
 * @(#)add_L_variable.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya 
 * @version 1.00 2010/7/17
 */

/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * Addition of two integers using L configuration with variable computational tiles
 * Input1  : Bottommost Row represent the input which is largest
 * Input2  : Rightmost Column represents the smaller input
 * Output  : Topmost Row represents the output
 * Seed Tile:Green
 * Output Tile: Red = 1                White = 0
 * Input  Tile: Brown = 1              yellow = 0
 * colour code of few tiles which are imp.	:	Brown(11)	yellow(10)	grey(01)	pink(00)
 */
 
 
 //package mypack;
import java.util.Scanner;
import java.io.*;



public class add_L_variable {
	
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
	
	int t1,t=0,i1_new;
	
    public add_L_variable()
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
    	int var;
		String temp = "";
		if(string2.length()>string1.length())   //ensuring first input larger then second
		{	
			temp=string2;
			string2=string1;
			string1=temp;
			var=one;
			one=two;
			two=var;
		}
		string1='0'+string1;
		a = new int[string1.length()];  //first number has to be padded with extra zero for carry bit
		b = new int[string2.length()];
			
		for(int i=0; i<string1.length();i++)
			a[string1.length() - 1 - i] = Character.digit((string1.charAt(i)),10 );
	
		for(int i=0; i<string2.length();i++)
			b[string2.length() - 1 - i] = Character.digit((string2.charAt(i)),10 );
			
		//FINDING TOTAL NUMBER OF TILE AND GLUE THAT WILL BE USED
		num_tile_types = 14+(a.length+1)+3*(b.length-1);   //calculating total number of tile types
		i1_new = (a.length)+(b.length)+3;
		num_bd = i1_new+1;   //calculating total number of binding domains
		
		fileName = "add_L_var_"+one+","+two+".tiles";
		//OPENING THE FILE
		try
		{
			fp = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
				
		//writing into the fill
		fp.println("% " + fileName + " (L configuration)");
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");
		fp.println("%Colouring Pattern\n"+ColourIndication);	
		fp.println("% Addition tile set of "+one+ ","+two);
		fp.println("\n% The top most row shows the sum of "+one+ ", "+two+"\n");
		fp.println("\n%-----Inputs in Binary------\n");
		fp.println("%      "+one+"\t\t"+Integer.toBinaryString(one));
		fp.println("%     +"+two+"\t\t"+Integer.toBinaryString(two));
		fp.println("%---------------------------");
		fp.println("% sum: "+(one+two)+"\t\t"+Integer.toBinaryString((one+two))+"\n");
		
		System.out.println("Requimagenta Addition tile set is loaded in to "+fileName);
		
		fp.println("tile edges matches {{N E S W}*}");
		fp.println("num tile types="+ num_tile_types);
		fp.println("num binding types="+ num_bd);
		fp.println("tile edges={");

		//------TILESET GENERATION STARTS-----
		
		//----SEED TILE
		fp.println("%---------------------Seed Tiles(green)--------------");
		fp.println("{"+"1 0 0 1}\t(green)");
				
		//----HORIZONTAL FRAME TILESET GENERATION OR LARGER INPUT		
		int inputCodeA[] = new int[(a.length)];
		int i,j,k,tempInputA = one;
		for(i=0;i<(a.length);i++)
		{
			inputCodeA[i]=(tempInputA%2)+2;
			tempInputA=tempInputA/2;	
		}
						
		fp.println("%------------------Middle Input Tiles(brown-1 White-0)------");//(Modify it)
		for(j=0;j<2;j++)
		{
			for(t=4,i=0;i<(b.length-1);i++,t++)
			{
				if(j==0)
				   fp.println("{"+(j+2)+" "+(t+1)+" "+(j+2)+" "+(t)+" }\t(pink)");
				else
				   fp.println("{"+(j+2)+" "+(t+1)+" "+(j+2)+" "+(t)+" }\t(magenta)");
			}
		}
		t++;
		fp.println("%---------------------Lower Input Tiles(Brown-1 yellow-0)------");
		if((a.length)==1)
		{
			if(inputCodeA[0]==2)
			   fp.println("{"+inputCodeA[0]+" 1 0 "+i1_new+"}\t(yellow)");
		    else
			   fp.println("{"+inputCodeA[0]+" 1 0 "+i1_new+"}\t(brown)");
		}
		else if((a.length)==2)
		{	
			if(inputCodeA[0]==2)
			   fp.println("{"+inputCodeA[0]+" 1 0 "+t+"}\t(yellow)");
		    else
			   fp.println("{"+inputCodeA[0]+" 1 0 "+t+"}\t(brown)");
			
			if(inputCodeA[1]==2)
			   fp.println("{"+inputCodeA[1]+ " "+t+" 0 "+i1_new+"}\t(yellow)");
		    else
			   fp.println("{"+inputCodeA[1]+" "+t+" 0 "+i1_new+"}\t(brown)");
		}
		else if((a.length)>2)
		{
			if(inputCodeA[0]==2)
			   fp.println("{"+inputCodeA[0]+" 1 0 "+t+"}\t(yellow)");
		    else
			   fp.println("{"+inputCodeA[0]+" 1 0 "+t+"}\t(brown)");
				
			for(i=1;i<(a.length)-1;i++,t++)
			{
				if(inputCodeA[i]==2)
			       fp.println("{"+inputCodeA[i]+" "+t+" 0 "+(t+1)+"}\t(yellow)");
		        else
			       fp.println("{"+inputCodeA[i]+" "+t+" 0 "+(t+1)+"}\t(brown)");	
			}
				
		    if(inputCodeA[i]==2)
			   fp.println("{"+inputCodeA[i]+" "+t+" 0 "+i1_new+"}\t(yellow)");
		    else
			   fp.println("{"+inputCodeA[i]+" "+t+" 0 "+i1_new+"}\t(brown)");
		}
		
		//----VERTICAL FRAME TILESET GENERATION OR SMALLER INPUT
		fp.println("%---------------------Upper Input Tiles(brown-1 yellow-0)-------------");
				
		int inputCodeB[] = new int[b.length];			
		int tempInputB=two;
				
		for(j=0;j<b.length;j++)
		{
			inputCodeB[j]=(tempInputB%2)+2;
			tempInputB=tempInputB/2;	
		}
				
		if((b.length)==1)
		{
			if(inputCodeB[0]==2)
			   fp.println("{"+i1_new+" 0 1 2}\t(yellow)");
			else
	     	   fp.println("{"+i1_new+" 0 1 "+(3+1)+"}\t(brown)");
		}
		else if((b.length)==2)
		{
			t1=t;
			if(inputCodeB[0]==2)
			   fp.println("{"+t1+" 0 1 2}\t(yellow)");
			else
			   fp.println("{"+t1+" 0 1 "+(3+1)+"}\t(brown)");
					
			if(inputCodeB[1]==2)
			   fp.println("{ "+i1_new+" 0 "+t1+" 2}\t(yellow)");
			else
			   fp.println("{ "+i1_new+" 0 "+t1+" "+(3+2)+"}\t(brown)");
		 }
		else if((b.length)>2)
		{
			t1= t-(a.length)+2;
					
			if(inputCodeB[0]==2)
			   fp.println("{"+t1+" 0 1 2}\t(yellow)");
			else
			   fp.println("{"+t1+" 0 1 "+(3+1)+"}\t(brown)");
					
			for(j=1; j<(b.length)-1;j++,t1++)
			{
				if(inputCodeB[j]==2)
				   fp.println("{"+(t1+1)+" 0 "+t1+" 2}\t(yellow)");
				else
				   fp.println("{"+(t1+1)+" 0 "+t1+" "+(3+j+1)+"}\t(brown)");
			}
					
			if(inputCodeB[j]==2)
			   fp.println("{"+i1_new+" 0 "+t1+" 2}\t(yellow)");
			else
			   fp.println("{"+i1_new+" 0 "+t1+" "+(3+(b.length))+"}\t(brown)");	
		}
		
		//----COMPUTATION TILE SET GENERATION
		fp.println("%---------------------Other Input Tiles(Red-1 White-0)-------------)");
		fp.println("{3 "+(3+1)+" 2 2}\t(magenta)");
		fp.println("{2 "+(3+1)+" 3 3}\t(pink)");
		fp.println("{2 2 2 2}\t(pink)");
		fp.println("{2 3 3 3}\t(pink)");
		fp.println("{3 2 3 2}\t(magenta)");
		fp.println("{3 3 2 2}\t(magenta)");
		String finalString="";	
		finalString+="\n{0 0 "+i1_new+" "+(i1_new+1)+"}\t(dark green)";
		finalString+="\n{"+(i1_new+1)+" "+i1_new+" 0 0}\t(dark green)";
		finalString+="\n{"+(i1_new+1)+" 2 "+(i1_new+1)+" 0}\t(dark green)";
		finalString+="\n{"+(i1_new+1)+" 2 "+(i1_new+1)+" 0}\t(dark green)";
		finalString+="\n{0 "+(i1_new+1)+" 2 "+(i1_new+1)+"}\t(white)";
		finalString+="\n{0 "+(i1_new+1)+" 3 "+(i1_new+1)+"}\t(red)";
		finalString+="\n{0 "+(i1_new+1)+" "+(i1_new+1)+" 0}\t(dark green)";
		fp.println(finalString);
		
		fp.println("}");
				
		fp.println("binding strengths=");
		fp.print("{");
		
		//----WRITING BINDING STRENGTH FOR ALL BINDING DOMAINS
		fp.print("2 1 1");
		for(i=4;i<4+(b.length);i++)
			fp.print(" 1");
		for(;i<=(num_bd-1);i++)
			fp.print(" 2");
		fp.print(" 1");
		fp.println("}");
				
		//assigning size of terminal, block(tile) size,
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=2");
		
		fp.close(); //closing the file
	}
}