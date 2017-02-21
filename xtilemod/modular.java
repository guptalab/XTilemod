/**
 * @(#)modular.java
 * @ Mentor : Manish Gupta
 * @ Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @version 1.00 2010/7/14
 */
 
 /*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * finds out modulus of a given expression
 * input and output patten for addition and subtraction 
 * Input1  : Bottommost Row represent First and Second input
 * Input2  : next alternate row represents middle temporary sum and next consicutive inputs
 * Output  : Topmost Row represents the output
 * Input1 Tiles:- for addition and subtraction option..
 *                blue=11      cyan=10      grey=01       pink=00
 *                suppose first nuber is 1100 and second nuber is 1010 then the tile for this is
 *                1) 11     2) 10 or 01     3) 10 or 01         4)00            
 * Output Tiles :- red=1           white=0
 *                 in the topmost row the left most coloum indicate the sign of the integer
 *                 if the leftmost coloum is yellow then the output is negative and if it is brown then the output is positive
 *
 *
 * input and output patten for finding out the modular of the final result of add and subtract expression.
 * Input tiles :-   Brown(11)	yellow(10)	Blue(10`)	grey(01)	pink(00)	Cyan(00`)
 * output Tiles :- red =1         white =0
 *                 there is no sign tile in the output after modulator operation
  */
 
 

import java.util.*;
import java.io.*;
public class modular {
    
    modular()throws Exception
    {
      	int x=61, n, i, j, k, mod;  
	    
    	Vector <Integer>input = new Vector();
 		Vector <Character>oper = new Vector();
 		
 		String str1 = "";
 	    j=0;
 		
 		System.out.println("\nINPUT IS TAKEN IN TWO PARTS FIRST THE EXPRESSION IS TAKEN FOLLOWED BY THE DIVISOR");
 		System.out.println("for example to compute: (1+2-3-4+5+6)mod7");
 		System.out.println("enter the expression as 1+2-3-4+5+6 when prompted to do so");
 		System.out.println("enter the divisor as 7 when prompted");
 		System.out.println("_______________________________________");
 		
 		System.out.println("Enter the expression");
 		
	    BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		str1 = is.readLine();
		
		//Removing red space from the expression1 and then seperating the integer and the operation of the input expression
		StringTokenizer st = new StringTokenizer(str1," ",false);
  		String str="";
  		while (st.hasMoreElements())
  		{
  			str += st.nextElement();
  		}
  		System.out.println("You have entered following expression: ");
  		System.out.println(str);
  		
  		String temp = "";
  		for(i=0; i<str.length(); i++)
  		{
  			if(str.charAt(i) == '+' || str.charAt(i) == '-')
  			{
  				oper.add(str.charAt(i));
  				temp = str.substring(j,i);
  				input.add(Integer.parseInt(temp));
  				j=i+1;
  			}
  		}
  		input.add(Integer.parseInt(str.substring(j,str.length())));

	    Scanner inp = new Scanner(System.in);
  		System.out.print("Enter the divisor: ");
  		mod = inp.nextInt();
	
		n = input.size();
		
		if(n<2)
		{
			System.out.println("wrong input, minimun 2 numbers are needed in exression");
			return;
		}
  		String fileName = "addsub"+str+"modwith"+mod+".tiles";
		
    	int el[]= new int[n-1];
    	String binaryinput[]= new String[n];
    	String binarymod;
   	    int maxlength=1;
	    int totaltiles, totalglues;

    	
   	    PrintWriter fp;
		fp = new PrintWriter(new FileWriter(fileName)); //opening file
		fp.println("% " + fileName);
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya\n\n");	
 		fp.println("% topmost row shows the output");
		String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%Input  Tile:\tfor addition and subtraction option \n%        blue=11\tcyan=10\tgrey=01pink=00\n%for division\n%      Brown(11)	yellow(10)	Blue(10`)	grey(01)	pink(00)	Cyan(00`)";
		fp.println("\n"+ColourIndication+"\n");

    	System.out.println("Required Addition tile set is loaded in to "+fileName);
    	
//converting input numbers in binary string    
   	    for(i=0;i<n;i++)
	    {
    		binaryinput[i] = Integer.toBinaryString(input.elementAt(i));
    		if(binaryinput[i].length() > maxlength)
    		{
    			maxlength=binaryinput[i].length();
   		 	}
    	}
		maxlength=maxlength+n-1;
		binarymod=Integer.toBinaryString(mod);

//making all the input in binary of same length by adding zero in the beginning   
	    for(i=0;i<n;i++)
    	{
    		j=maxlength-binaryinput[i].length();
    		for(;j>0;j--)
    		{
    			binaryinput[i]='0'+binaryinput[i];
    		}
    	}
    	j=maxlength-binarymod.length();
    
    	for(;j>0;j--)
    		binarymod=binarymod+'2';

//outputting the calculation in tileset file
		i=0;
		j=0;
		int temp2=input.elementAt(i);
		fp.println("\n%"+input.elementAt(i)+"\t\t"+binaryinput[i]);
		i++;
		do
		{
			fp.print("%\t\t"+oper.elementAt(j)+"\n");	
			fp.println("%"+input.elementAt(i)+"\t\t"+binaryinput[i]);
			if(oper.elementAt(j) == '+')
				temp2 = temp2+input.elementAt(i);
			else
				temp2 = temp2-input.elementAt(i);
			i++;
			j++;
		}while(i<input.size());
		
		fp.println("%\t\tmod with");
		fp.println("%"+mod+"\t\t"+Integer.toBinaryString(mod));
		
		if(temp2<0)
			temp2 = -temp2;
		temp2 = temp2%mod;
		String t=Integer.toBinaryString(temp2);
		if(t.length()< binaryinput[0].length())
	    {
	    	for(i=t.length(); i<binaryinput[0].length();i++)
	    		t = "0"+t;
	    }
		fp.println("%----->>>Final Result = "+temp2+"\tin binary : "+t);

//calculating total numbers of tiles    
		totaltiles= ((2*n-3)*(maxlength+2)) + 2 + (maxlength*2) + 2 + 161 ;

//calculating total numbers of glues
		if((2*n - 3)<maxlength)
		{
			totalglues=((maxlength+1)*n)+60;
		}
		else
		{
			totalglues = ((maxlength+1)*(n-1))+ 2*n -2  + 60;
		}
    	

//initial printing in .tile file	
		fp.println("\n\ntile edges matches {{N E S W}*}");
		fp.println("num tile types="+ totaltiles);
		fp.println("num binding types="+ totalglues+"\n");
		fp.println("tile edges={");
		
//seed tile
		fp.println("% ----------seed tile------------");	
		fp.println("{ 61 0 0 61 }(green)");
 		
 //Computational tile		
 		fp.println("% ----------basic computational tiles for addition------------");		
    	fp.println("{ 31 48 16 48 }(white)");
		fp.println("{ 32 48 18 48 }(red)");
    	fp.println("{ 31 48 21 49 }(white)");
		fp.println("{ 32 48 19 48 }(red)");
    	fp.println("{ 31 49 18 49 }(white)");
		fp.println("{ 32 49 16 48 }(red)");
    	fp.println("{ 31 49 19 49 }(white)");
		fp.println("{ 32 49 21 49 }(red)");
		
//sign bit tile for addition
		fp.println("% ----------sign tiles for addition------------");		
		fp.println("{ 53 49 52 0 }(brown)");
    	fp.println("{ 53 48 53 0 }(brown)");
		fp.println("{ 52 48 52 0 }(yellow)");
		
		
// basic computational tiles for subtraction..

		fp.println("% ----------basic computational tiles for subtraction------------");				
    	fp.println("{ 31 50 16 50 }(white)");
		fp.println("{ 32 50 18 51 }(red)");
    	fp.println("{ 31 50 21 50 }(white)");
		fp.println("{ 32 51 16 51 }(red)");
    	fp.println("{ 31 51 18 51 }(white)");
		fp.println("{ 32 50 19 50 }(red)");
    	fp.println("{ 31 51 19 50 }(white)");
		fp.println("{ 32 51 21 51 }(red)");

		fp.println("% ----------sign tiles for subtraction------------");		
    	fp.println("{ 52 51 53 0 }(yellow)");
		fp.println("{ 52 50 52 0 }(yellow)");
    	fp.println("{ 53 50 53 0 }(brown)");


// tiles for final output if final output is positive....
		fp.println("% ----------tiles for final output if final output is positive------------");		
		fp.println("{ 56 55 58 0 }(brown)");
		fp.println("{ 31 55 31 55 }(white)");
		fp.println("{ 32 55 32 55 }(red)");


// tiles for final output if final output is negative...
		fp.println("% ----------tiles for final output if final output is negative------------");		
		fp.println("{ 31 32 32 32 }(white)");
		fp.println("{ 32 32 31 31 }(red)");
		fp.println("{ 32 31 32 31 }(red)");
		fp.println("{ 31 31 31 31 }(white)");
		fp.println("{ 56 31 55 0 }(yellow)");


//other computational tiles... for final output..
		fp.println("% ----------other computational tiles... for final output------------");		
		fp.println("{ 31 54 31 54 }(orange)");
		fp.println("{ 32 54 32 54 }(orange)");
		fp.println("{ 58 54 53 0 }(orange)");
		fp.println("{ 57 54 52 0 }(orange)");
		fp.println("{ 55 56 57 0 }(orange)");
		fp.println("{ 32 56 31 56 }(orange)");
		fp.println("{ 31 56 32 56 }(orange)");
		fp.println("{ 60 0 54 55 }(orange)");
		fp.println("{ 59 0 54 56 }(orange)");
		fp.println("{ 60 0 59 32 }(orange)");
 		
 		fp.println("%---------------------tiles for division------------");
 		fp.println("%---------------------Block no 1 -------------------");	
		fp.println("{2 22 16 22}\t(blue)");
		fp.println("{4 23 17 22}\t(blue)");	
		fp.println("{7 24 18 22}\t(blue)");									
		fp.println("{9 25 19 22}\t(blue)");	
		fp.println("{11 26 20 22}\t(blue)");
		fp.println("{14 27 21 22}\t(blue)");
		fp.println("{5 23 17 23}\t(blue)");	
		fp.println("{12 26 20 23}\t(blue)");
		fp.println("{3 22 16 27}\t(blue)");
		fp.println("{6 23 17 27}\t(blue)");
		fp.println("{8 24 18 27}\t(blue)");
		fp.println("{10 25 19 27}\t(blue)");
		fp.println("{13 26 20 27}\t(blue)");
		fp.println("{15 27 21 27}\t(blue)");
		fp.println("{2 25 16 25}\t(blue)");
		fp.println("{4 26 17 25}\t(blue)");
		fp.println("{7 28 18 25}\t(blue)");	
		fp.println("{9 25 19 25}\t(blue)");	
		fp.println("{11 26 20 25}\t(blue)");
		fp.println("{14 28 21 25}\t(blue)");
		fp.println("{5 26 17 26}\t(blue)");	
		fp.println("{12 26 20 26}\t(blue)");
		fp.println("{3 25 16 28}\t(blue)");
		fp.println("{6 26 17 28}\t(blue)");	
		fp.println("{8 28 18 28}\t(blue)");
		fp.println("{10 25 19 28}\t(blue)");
		fp.println("{13 26 20 28}\t(blue)");
		fp.println("{15 28 21 28}\t(blue)");
		fp.println("{2 29 16 29}\t(blue)");
		fp.println("{4 30 17 29}\t(blue)");
		fp.println("{7 24 18 29}\t(blue)");	
		fp.println("{9 29 19 29}\t(blue)");
		fp.println("{11 30 20 29}\t(blue)");
		fp.println("{14 24 21 29}\t(blue)");
		fp.println("{5 30 17 30}\t(blue)");	
		fp.println("{12 30 20 30}\t(blue)");
		fp.println("{3 29 16 24}\t(blue)");	
		fp.println("{6 30 17 24}\t(blue)");	
		fp.println("{8 24 18 24}\t(blue)");	
		fp.println("{10 29 19 24}\t(blue)");
		fp.println("{13 30 20 24}\t(blue)");	
		fp.println("{15 24 21 24}\t(blue)");
				
	    //		======Block 2 Type======
		fp.println("%---------------------Block no 2 -------------------");
		fp.println("{16 36 2 36}\t(cyan)");
		fp.println("{18 36 3 36}\t(cyan)");	
		fp.println("{16 36 4 36}\t(cyan)");	
		fp.println("{17 36 5 36}\t(cyan)");
		fp.println("{18 36 6 36}\t(cyan)");
		fp.println("{16 36 7 36}\t(cyan)");
		fp.println("{18 36 8 36}\t(cyan)");
		fp.println("{19 36 9 36}\t(cyan)");
		fp.println("{21 36 10 36}\t(cyan)");
		fp.println("{19 36 11 36}\t(cyan)");
		fp.println("{20 36 12 36}\t(cyan)");
		fp.println("{21 36 13 36}\t(cyan)");
		fp.println("{19 36 14 36}\t(cyan)");
		fp.println("{21 36 15 36}\t(cyan)");
				
		//		======Block 3 Type======
		fp.println("%---------------------Block no 3 -------------------");
		fp.println("{16 31 2 31}\t(purple)");
		fp.println("{18 31 3 31}\t(purple)");
		fp.println("{16 31 4 31}\t(purple)");
		fp.println("{17 31 5 31}\t(purple)");
		fp.println("{18 31 6 31}\t(purple)");
		fp.println("{19 31 7 32}\t(purple)");
		fp.println("{21 31 8 32}\t(purple)");
		fp.println("{19 31 9 31}\t(purple)");
		fp.println("{21 31 10 31}\t(purple)");
		fp.println("{19 31 11 31}\t(purple)");
		fp.println("{20 31 12 31}\t(purple)");
		fp.println("{21 31 13 31}\t(purple)");
		fp.println("{16 31 14 31}\t(purple)");
		fp.println("{18 31 15 31}\t(purple)");
		fp.println("{19 32 2 32}\t(purple)");
		fp.println("{21 32 3 32}\t(purple)");
		fp.println("{19 32 4 32}\t(purple)");
		fp.println("{20 32 5 32}\t(purple)");
		fp.println("{21 32 6 32}\t(purple)");
		fp.println("{16 32 7 32}\t(purple)");
		fp.println("{18 32 8 32}\t(purple)");
		fp.println("{16 32 9 31}\t(purple)");
		fp.println("{18 32 10 31}\t(purple)");
		fp.println("{16 32 11 31}\t(purple)");
		fp.println("{17 32 12 31}\t(purple)");
		fp.println("{18 32 13 31}\t(purple)");
		fp.println("{19 32 14 32}\t(purple)");
		fp.println("{21 32 15 32}\t(purple)");
				
		 //		======Block 4 Type(Result Tile)======
		fp.println("%---------------------Block no 4 (Result tiles) ----");
		fp.println("%   ----Yellow tiles signifies zero----");
		fp.println("%   ----Green tiles signifies one------");	
	
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
		fp.println("{33 22 1 0} \t(brown)");
				
		//		======Block 6 Type======
		fp.println("%---------------------Block no 6 -------------------");
		fp.println("{37 0 34 22}\t(brown)");
		fp.println("{38 0 34 23}\t(brown)");
		fp.println("{42 0 34 27}\t(brown)");
		fp.println("{40 0 34 25}\t(brown)");
		fp.println("{41 0 34 26}\t(brown)");
		fp.println("{43 0 34 28}\t(brown)");
		fp.println("{44 0 34 29}\t(brown)");
		fp.println("{45 0 34 30}\t(brown)");
		fp.println("{39 0 34 24}\t(brown)");
			    
	    //		======ENDING LEFT(EL) TYPE TILES ====
		fp.println("%---------------------EL Type tiles-----------------");
		fp.println("{1 36 33 0} \t(yellow)   %     EL(0)");
		fp.println("{1 31 33 0} \t(green) %     EL(1)");
		
	    //		======Ending Tiles =====
		fp.println("%---------------------Ending Tiles -----------------");
		fp.println("{47 0 46 29}\t(grey)");
		fp.println("{47 0 46 25}\t(grey)");
		fp.println("{47 0 46 22}\t(grey)");	
		fp.println("{47 0 46 24}\t(grey)");
		fp.println("{47 0 46 28}\t(grey)");
		fp.println("{47 0 46 27}\t(grey)");	
			
		//		======SR Type tiles ====
		fp.println("%---------------------SR Type tiles ----------------");
		fp.println("{46 0 44 36}\t(grey)");
		fp.println("{46 0 39 36}\t(grey)");	
		fp.println("{46 0 37 31}\t(grey)");
		fp.println("{46 0 42 31}\t(grey)");	
		fp.println("{46 0 40 31}\t(grey)");	
		fp.println("{46 0 43 31}\t(grey)");	
		fp.println("{34 0 41 31}\t(grey)");	
		fp.println("{34 0 45 36}\t(grey)");	
		fp.println("{34 0 38 31}\t(grey)");
				
		//		======Right Ending Seed (RES) =====
		fp.println("%---------------------RES tiles --------------------");
		fp.println("{0 35 33 0} \t(pink)");	
		fp.println("{0 0 47 35} \t(pink)");
			
//tiles for first two numbers

		fp.println("% ----------tiles for input------------");		
		for(i=maxlength-1;i >= 0;i--,x++)
		{
			fp.print("{ " + ((Character.digit(binaryinput[0].charAt(i),10)*3)+(Character.digit(binaryinput[1].charAt(i),10)*2)+16)+" "+x +" 0 " + (x+1));
			if(((Character.digit(binaryinput[0].charAt(i),10)*3)+(Character.digit(binaryinput[1].charAt(i),10)*2)+16)==21)
				fp.print(" }(blue)\n");
			else if(((Character.digit(binaryinput[0].charAt(i),10)*3)+(Character.digit(binaryinput[1].charAt(i),10)*2)+16)==19)
				fp.print(" }(cyan)");
			else if(((Character.digit(binaryinput[0].charAt(i),10)*3)+(Character.digit(binaryinput[1].charAt(i),10)*2)+16)==18)
				fp.print(" }(grey)");
			else
				fp.print(" }(pink)\n");
		}
		fp.print("{ 53 "+x +" 0 0 }(brown)\n");
    	

		if((2*n - 3)>maxlength)
			x = 61 + (2*n) - 2 - 1;

//tiles of all the remaining numbers with previous input number as zero as well as one...

		for(j=2, x=x+1;j<n;j++,x++)
		{
//for previous input as zero
			el[j-2]=x;
			for(i=maxlength-1;i>=0;i--, x++)
			{
				fp.print("{ " + ((Character.digit(binaryinput[j].charAt(i),10)*2)+16)+" "+x +" 31 " + (x+1));
				if(((Character.digit(binaryinput[j].charAt(i),10)*2)+16)==18)
					fp.print(" }(grey)\n");
				else
					fp.print(" }(pink)\n");	
			}
			fp.print("{ 52 "+ x +" 52 0 }(yellow)\n");
			x=el[j-2];
//for previous input as one
			for(i=maxlength-1;i>=0;i--, x++)
			{
				fp.print("{ " + ((Character.digit(binaryinput[j].charAt(i),10)*2)+19)+" "+x +" 32 " + (x+1));
				if(((Character.digit(binaryinput[j].charAt(i),10)*2)+19)==21)
					fp.print(" }(blue)\n");
				else
					fp.print(" }(cyan)\n");	
			}
			fp.print("{ 53 "+x +" 53 0 }(brown)\n");
		}
		
// for divide		
		el[j-2]=x;
		for(i=maxlength-1;i>=0;i--,x++)
		{
			int l=Character.digit(binarymod.charAt(i),10);
			if(l==2) 
				l=1;
			else
				l=l*2;
			fp.print("{ " + (l+16)+" "+x +" 31 " + (x+1));
			if(l==2)
				fp.print(" }(grey)\n");
			else if(l==1)
				fp.print(" }(cyan)\n");
			else
				fp.print(" }(pink)\n");	
		}

		x=el[j-2];
		for(i=maxlength-1;i>=0;i--,x++)
		{
			int l=Character.digit(binarymod.charAt(i),10);
			if(l==2) 
				l=1;
			else
				l=l*2;
			fp.print("{ " + (l+19)+" "+x +" 32 " + (x+1));	
			if(l==2)
				fp.print(" }(brown)\n");
			else if(l==1)
				fp.print(" }(blue)\n");
			else
				fp.print(" }(yellow)\n");	

		}
		fp.print("{ 1 " + x + " 56 0 }(red)\n");
		
//calculating remaining frame tiles SR and ER..
		fp.println("% ----------right side frame tiles------------");		
		for(i=0,k=61;i<n-1;i++,k=k+2)
		{
			fp.print("{ "+(k+1)+ " 0 "+k+ ((oper.elementAt(i)=='+')?" 48":" 50") +" }(green)\n" );
		}
		for(i=0,k=62;i<n-2;i++,k=k+2)
		{
			fp.print("{ "+ (k+1) +" 0 "+ k+" "+el[i]+ " }(green)\n");
		}
		fp.print("{ 54 0 "+ k + " 54 }(green)\n");
		fp.print("{ 34 0 60 "+el[i] + " }(green)\n");
		fp.println("}\n\n");    	
    	
    	
    	//calculating binding strength
		fp.println("binding strengths=");
		fp.print("{2 ");

		for(i=2;i<37;i++)
		{
			fp.print("1 ");
		}
		fp.print("2 2 2 2 2 2 2 2 2 1 2 ");
		for(i=48;i<57;i++)
		{
			fp.print("1 ");
		}
		fp.print("2 2 2 2 ");

		i=61;
		if((2*n - 3)<maxlength)
		{
			for(;i<(maxlength+62);i++)
			{
				fp.print("2 ");
			}
		}
		else
		{
			for(;i<(2*n -2 + 61);i++)
			{
				fp.print("2 ");
			}
		}
		for(;i<(totalglues+1);i++)
		{
			fp.print("1 ");
		}
		fp.println("}");
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=2");

		fp.close(); //closing file
  }
}
