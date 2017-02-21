/**
 * @(#)divide.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya 
 * @version 1.00 2010/7/17
 */
 
 /*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * dividing an integer from a divisor and finding the quotient and the remainder
 * Input1  : Bottommost Row represent First input and Second Input
 *           for eg:- if integer is 100110 and divisor is 1010 then its input set will be...
 *           1) 11       2)00        3)01      4)10      5)10'       6) 00'
 * Output1 : Leftmost column represents the 	quotient
 * Output2 : Topmost Row represents the remainder
 * Seed Tile :Green
 * Output Tilefor both renainder and quotient :Red = 1                 White = 0
 * Input  Tile:	Brown(11)	yellow(10)	Blue(10`)	grey(01)	pink(00)	Cyan(00`)
 */



 //package mypack
import java.util.Scanner;
import java.io.*;



public class divide {

	String fileName = "";
	String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%Input Tiles	:	Brown(11)	yellow(10)	Blue(10`)	grey(01)	pink(00)	Cyan(00`)";
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
	
    public divide()
    {
    	while(!input_a)
		{
//taking first integer which is to be dividedas the input and converting it into binary..
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
//taking divisor as input and converting it into binary..
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
		
		//storing inputs in array..
		a = new int[string1.length()];
		b = new int[string2.length()];
		
		for(int i=0; i<string1.length();i++)
			a[i] = Character.digit((string1.charAt(i)),10 );
			
		for(int i=0; i<string2.length();i++)
			b[i] = Character.digit((string2.charAt(i)),10 );
	
	    //FINDING TOTAL NUMBER OF TILE AND GLUE THAT WILL BE USED
		num_tile_types = a.length+123; //calculating total number of tile types
		num_bd = a.length+48; //calculating total number of binding domains
		
		fileName = "divide"+one+","+two+".tiles";
		
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
		fp.println("% Division tile set of "+one+ ","+two);
		fp.println("\n% The leftmost column shows the Qoutient when "+one+" is divided by "+two);
		fp.println("% The top most row shows the remainder left\n");
		fp.println("\n%-----Inputs in Binary------\n");
		fp.println("%            "+one+"\t\t"+Integer.toBinaryString(one));
		fp.println("%           /"+two+"\t\t"+Integer.toBinaryString(two));
		fp.println("%---------------------------");
		fp.println("% quotient:  "+(one/two)+"\t\t"+Integer.toBinaryString((one/two)));
		fp.println("% remainder: "+(one%two)+"\t\t"+Integer.toBinaryString((one%two))+"\n");
		
		System.out.println("Required Division tile set is loaded in to "+fileName);
		
		fp.println("tile edges matches {{N E S W}*}");
		fp.println("num tile types="+ num_tile_types);
		fp.println("num binding types="+ num_bd);
		fp.println("tile edges={");
		
		
		//------TILESET GENERATION STARTS-----
		int inputCode[] = new int[a.length];
		int var;
		int tempInputBits=a.length;
		int tempInput2Bits=b.length;//Length of Divisor
		tempInput2Bits--;
			
		int tempInputDivisor=two;
		int tempInputDividend=one;
		int y;
		int count=0;
		
		for(y=(b.length); y<(a.length); y++)
		{
			tempInputDivisor=tempInputDivisor*2;
			count++;
		}
				
		String inputarray[] = new String[a.length];
			
		for(y=0; y<(a.length); y++,count--)
		{
			int r;
			int l;
			r=tempInputDividend%2;
			l=tempInputDivisor%2;
			tempInputDividend=tempInputDividend/2;
			tempInputDivisor=tempInputDivisor/2;
			if(count>0)
				inputarray[y]=Integer.toString(r)+"0`";		
			else
				inputarray[y]=Integer.toString(r)+Integer.toString(l);
		}
		
		String[] coloring;
		coloring=new String[a.length];

		int a1;
		int b1;
					
		//----SEED TILE
		fp.println("%---------------------Seed Tiles(green)--------------");
		fp.println("{34 0 0 38}\t\t(green)");
		fp.println("{1 37 0 0}\t\t(green)");
					
		//assining binding domain index for 00, 01, 10, 11,00',10,		
		for(a1=((a.length)-1),b1=0;a1>=0;a1--,b1++)
		{
			if(inputarray[a1].equalsIgnoreCase( "00"))
			{
				coloring[b1]="(pink)";
				inputCode[b1]=16;
			}
			else if(inputarray[a1].equalsIgnoreCase( "00`"))
			{
				coloring[b1]="(cyan)";
				inputCode[b1]=17;
			}
			else if(inputarray[a1].equalsIgnoreCase( "01"))
			{
				coloring[b1]="(grey)";
				inputCode[b1]=18;
			}
			else if(inputarray[a1].equalsIgnoreCase( "10"))
			{
				coloring[b1]="(yellow)";
				inputCode[b1]=19;
			}
			else if(inputarray[a1].equalsIgnoreCase( "10`"))
			{
				coloring[b1]="(blue)";
				inputCode[b1]=20;
			}
			else if(inputarray[a1].equalsIgnoreCase( "11"))
			{
				coloring[b1]="(brown)";
				inputCode[b1]=21;
			}
		}		
					
		//----HORIZONTAL FRAME TILESET GENERATION OR INPUT TILES
		fp.println("%---------------------Horizontal Frame Tile  Brown(11)	yellow(10)	Blue(10`)	grey(01)	pink(00)	Cyan(00`)--------------");
		int temp;
		if((a.length)>2)
		{
			fp.print("{"+inputCode[0]+" "+(49+1)+" 0 37}\t");	
			fp.println(coloring[0]);
			int temp1;
			for(temp=49+2,temp1=1;temp1<=(a.length)-2;temp++,temp1++)
			{
				fp.print("{"+inputCode[temp1]+" "+temp+" 0 "+(temp-1)+"}\t");	
				fp.println(coloring[temp1]);
			}
			fp.print("{"+inputCode[(a.length)-1]+" 38 0 "+(49+(a.length)-1)+"}");	
			fp.println(coloring[(a.length)-1]);
		 }
		 else if((a.length)==2)
		 {
			fp.print("{"+inputCode[0]+" "+(49+1)+" 0 37}\t");
			fp.println(coloring[0]);
			fp.print("{"+inputCode[1]+" 38 0 "+(49+1)+"}\t");	
			fp.println(coloring[1]);
		 }
		 else if((a.length)==1)
		 {
		 	fp.print("{"+inputCode[0]+" 38 0 37}\t");
			fp.println(coloring[0]);
		 }
		 
		//---- COMPUTATION TILE SET GENERATION
		fp.println("%---------------------Block no 1 -------------------");	
		fp.println("{2 22 16 22}\t(purple)");
		fp.println("{4 23 17 22}\t(purple)");	
		fp.println("{7 24 18 22}\t(purple)");									
		fp.println("{9 25 19 22}\t(purple)");	
		fp.println("{11 26 20 22}\t(purple)");
		fp.println("{14 27 21 22}\t(purple)");
		fp.println("{5 23 17 23}\t(purple)");	
		fp.println("{12 26 20 23}\t(purple)");
		fp.println("{3 22 16 27}\t(purple)");
		fp.println("{6 23 17 27}\t(purple)");
		fp.println("{8 24 18 27}\t(purple)");
		fp.println("{10 25 19 27}\t(purple)");
		fp.println("{13 26 20 27}\t(purple)");
		fp.println("{15 27 21 27}\t(purple)");
		fp.println("{2 25 16 25}\t(purple)");
		fp.println("{4 26 17 25}\t(purple)");
		fp.println("{7 28 18 25}\t(purple)");	
		fp.println("{9 25 19 25}\t(purple)");	
		fp.println("{11 26 20 25}\t(purple)");
		fp.println("{14 28 21 25}\t(purple)");
		fp.println("{5 26 17 26}\t(purple)");	
		fp.println("{12 26 20 26}\t(purple)");
		fp.println("{3 25 16 28}\t(purple)");
		fp.println("{6 26 17 28}\t(purple)");	
		fp.println("{8 28 18 28}\t(purple)");
		fp.println("{10 25 19 28}\t(purple)");
		fp.println("{13 26 20 28}\t(purple)");
		fp.println("{15 28 21 28}\t(purple)");
		fp.println("{2 29 16 29}\t(purple)");
		fp.println("{4 30 17 29}\t(purple)");
		fp.println("{7 24 18 29}\t(purple)");	
		fp.println("{9 29 19 29}\t(purple)");
		fp.println("{11 30 20 29}\t(purple)");
		fp.println("{14 24 21 29}\t(purple)");
		fp.println("{5 30 17 30}\t(purple)");	
		fp.println("{12 30 20 30}\t(purple)");
		fp.println("{3 29 16 24}\t(purple)");	
		fp.println("{6 30 17 24}\t(purple)");	
		fp.println("{8 24 18 24}\t(purple)");	
		fp.println("{10 29 19 24}\t(purple)");
		fp.println("{13 30 20 24}\t(purple)");	
		fp.println("{15 24 21 24}\t(purple)");
				
	    //		======Block 2 Type======
		fp.println("%---------------------Block no 2 -------------------");
		fp.println("{16 36 2 36}\t(pink)");
		fp.println("{18 36 3 36}\t(pink)");	
		fp.println("{16 36 4 36}\t(pink)");	
		fp.println("{17 36 5 36}\t(pink)");
		fp.println("{18 36 6 36}\t(pink)");
		fp.println("{16 36 7 36}\t(pink)");
		fp.println("{18 36 8 36}\t(pink)");
		fp.println("{19 36 9 36}\t(pink)");
		fp.println("{21 36 10 36}\t(pink)");
		fp.println("{19 36 11 36}\t(pink)");
		fp.println("{20 36 12 36}\t(pink)");
		fp.println("{21 36 13 36}\t(pink)");
		fp.println("{19 36 14 36}\t(pink)");
		fp.println("{21 36 15 36}\t(pink)");
				
		//		======Block 3 Type======
		fp.println("%---------------------Block no 3 -------------------");
		fp.println("{16 31 2 31}\t(magenta)");
		fp.println("{18 31 3 31}\t(magenta)");
		fp.println("{16 31 4 31}\t(magenta)");
		fp.println("{17 31 5 31}\t(magenta)");
		fp.println("{18 31 6 31}\t(magenta)");
		fp.println("{19 31 7 32}\t(magenta)");
		fp.println("{21 31 8 32}\t(magenta)");
		fp.println("{19 31 9 31}\t(magenta)");
		fp.println("{21 31 10 31}\t(magenta)");
		fp.println("{19 31 11 31}\t(magenta)");
		fp.println("{20 31 12 31}\t(magenta)");
		fp.println("{21 31 13 31}\t(magenta)");
		fp.println("{16 31 14 31}\t(magenta)");
		fp.println("{18 31 15 31}\t(magenta)");
		fp.println("{19 32 2 32}\t(magenta)");
		fp.println("{21 32 3 32}\t(magenta)");
		fp.println("{19 32 4 32}\t(magenta)");
		fp.println("{20 32 5 32}\t(magenta)");
		fp.println("{21 32 6 32}\t(magenta)");
		fp.println("{16 32 7 32}\t(magenta)");
		fp.println("{18 32 8 32}\t(magenta)");
		fp.println("{16 32 9 31}\t(magenta)");
		fp.println("{18 32 10 31}\t(magenta)");
		fp.println("{16 32 11 31}\t(magenta)");
		fp.println("{17 32 12 31}\t(magenta)");
		fp.println("{18 32 13 31}\t(magenta)");
		fp.println("{19 32 14 32}\t(magenta)");
		fp.println("{21 32 15 32}\t(magenta)");
				
	    //		======Block 4 Type(Result Tile)======
		fp.println("%---------------------Block no 4 (Result tiles) ----");
		fp.println("%   ----White tiles signifies zero----");
		fp.println("%   ----Red tiles signifies one------");	
				
		fp.println("{0 35 2 35} \t(white)");
		fp.println("{0 35 3 35} \t(white)");
		fp.println("{0 35 7 35} \t(white)");
		fp.println("{0 35 8 35} \t(white)");
		fp.println("{0 35 9 35} \t(red)");
		fp.println("{0 35 10 35}\t(red)");
		fp.println("{0 35 14 35}\t(red)");
		fp.println("{0 35 15 35}\t(red)");
				
	    //		======Block 5 Type======
		fp.println("%---------------------Block no 5 -------------------");
		fp.println("{33 22 1 0} \t(dark green)");
		
		//		======Block 6 Type======
		fp.println("%---------------------Block no 6 -------------------");
		fp.println("{39 0 34 22}\t(orange)");
		fp.println("{40 0 34 23}\t(orange)");
		fp.println("{44 0 34 27}\t(orange)");
		fp.println("{42 0 34 25}\t(orange)");
		fp.println("{43 0 34 26}\t(orange)");
		fp.println("{45 0 34 28}\t(orange)");
		fp.println("{46 0 34 29}\t(orange)");
		fp.println("{47 0 34 30}\t(orange)");
		fp.println("{41 0 34 24}\t(orange)");
		    
	    //		======ENDING LEFT(EL) TYPE TILES ====
		fp.println("%---------------------EL Type tiles-----------------");
		fp.println("{1 36 33 0} \t(white)   %     EL(0)");
		fp.println("{1 31 33 0} \t(red) %     EL(1)");
		
	    //		======Ending Tiles =====
		fp.println("%---------------------Ending Tiles -----------------");
		fp.println("{49 0 48 29}\t(orange)");
		fp.println("{49 0 48 25}\t(orange)");
		fp.println("{49 0 48 22}\t(orange)");	
		fp.println("{49 0 48 24}\t(orange)");
		fp.println("{49 0 48 28}\t(orange)");
		fp.println("{49 0 48 27}\t(orange)");	
					
		//		======SR Type tiles ====
		fp.println("%---------------------SR Type tiles ----------------");
		fp.println("{48 0 46 36}\t(dark green)");
		fp.println("{48 0 41 36}\t(dark green)");	
		fp.println("{48 0 39 31}\t(dark green)");
		fp.println("{48 0 44 31}\t(dark green)");	
		fp.println("{48 0 42 31}\t(dark green)");	
		fp.println("{48 0 45 31}\t(dark green)");	
		fp.println("{34 0 43 31}\t(dark green)");	
		fp.println("{34 0 47 36}\t(dark green)");	
		fp.println("{34 0 40 31}\t(dark green)");
			
		//		======Right Ending Seed (RES) =====
		fp.println("%---------------------RES tiles --------------------");
		fp.println("{0 35 33 0} \t(green)");	
		fp.println("{0 0 49 35} \t(green)");
		
		fp.println("}");
		fp.println("binding strengths=");
		fp.print("{");
		
		//----WRITING BINDING STRENGTH FOR ALL BINDING DOMAINS
		int counter=0;
		counter++;
		fp.print("2 ");
		
		for(temp=2; temp<=36; temp++)
		{
			counter++;
			fp.print("1 ");
		}
		for(temp=37; temp<=47; temp++)
		{
			counter++;
			fp.print("2 ");
		}
		counter+=2;
		fp.print("1 2");
		for(temp=0;temp<((a.length)-1);temp++)
		{
			counter++;
			fp.print(" 2");
		}
		fp.println("}");
				
		//assigning size of terminal, block(tile) size,
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=2");
		
		fp.close(); //closing the file
    }
}