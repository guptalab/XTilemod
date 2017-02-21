/**
 * @(#)addsubtract.java
 * @ Mentor : Manish Gupta
 * @ Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @version 1.00 2010/7/7
 */
 
/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * solves an expression containing integer inputs and '+','-' operators
 * Input1  : Bottommost Row represent First and Second input
 * Input2  : next alternate row represents middle temporary sum and next consicutive inputs
 * Output  : Topmost Row represents the output
 * Input1 Tiles:- blue=11         cyan=10        grey=01      pink==00
 *                suppose first nuber is 1100 and second nuber is 1010 then the tile for this is
 *                1) 11     2) 10     3) 01         4)00            
 * Output Tiles :- red=1           white=0
 *                 in the topmost row the left most coloum indicate the sign of the integer
 *                 if the leftmost coloum is yellow then the output is negative and if it is brown then the output is positive
 *
 */
 
 
 
import java.util.*;
import java.io.*;



public class addsubtract {
    
    addsubtract() throws Exception
    {    	
     	int x=19, n, i, j, k;  
     		    
    	Vector <Integer>input = new Vector();
 		Vector <Character>oper = new Vector();
 		
 		String str1 = "";
 	    j=0;
 	  
 		System.out.println("Enter the expression(example: \"1+2-3-4+5+6\"):-");
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
  		
  		String fileName = "addsub"+str+".tiles";
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
	
		n = input.size();
    	int el[]= new int[n-2];
    	String binaryinput[]= new String[n];
   	    int maxlength=1;
	    int totaltiles, totalglues;
	    
	    
   	    PrintWriter fp;
		fp = new PrintWriter(new FileWriter(fileName)); //opening file
		fp.println("% " + fileName);
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya\n\n");	
 		fp.println("% topmost row shows the output");
		String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%if the leftmost coloum in the topmost row is yellow then the output is negative and if it is brown then the output is positive top\n%Input  Tile:\tblue=11\t cyan=10\t grey=01\t pink=00";
		fp.println("\n"+ColourIndication+"\n");
    	System.out.println("Required Addition tile set is loaded in to "+fileName);
    	
//converting input numbers in binary string    
   	    for(i=0;i<n;i++)
	    {
    		binaryinput[i]= Integer.toBinaryString(input.elementAt(i));
    		if(binaryinput[i].length() > maxlength)
    		{
    			maxlength=binaryinput[i].length();
   		 	}
    	}
		maxlength=maxlength+n-1;


//making all the input in binary of same length by adding zero in the beginning   
	    for(i=0;i<n;i++)
    	{
    		j=maxlength-binaryinput[i].length();
    		for(;j>0;j--)
    		{
    			binaryinput[i]='0'+binaryinput[i];
    		}
    	}
    	
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
		
		if(temp2 >= 0)
		{
			String t=Integer.toBinaryString(temp2);
			if(t.length()< binaryinput[0].length())
	    	{
	    		for(i=t.length(); i<binaryinput[0].length();i++)
	    			t = "0"+t;
	    	}
	    	fp.println("%----->>>Final Result = "+temp2+"\tin binary : "+t);
		}
		else
		{
			temp2 = -temp2;
			String t=Integer.toBinaryString(temp2);
			if(t.length()< binaryinput[0].length())
	    	{
	    		for(i=t.length(); i<binaryinput[0].length();i++)
	    			t = "0"+t;
	    	}
	    	fp.println("%----->>>Final Result = -"+temp2+"\tin binary : -"+t);
	    	fp.println("% the '-' denotes that output is negative");
		}	
		

//calculating total numbers of tiles    
		totaltiles= ((2*n-3)*(maxlength+2)) + 40 + 2;

//calculating total numbers of glues
		if((2*n - 3)<maxlength)
		{
			totalglues=((maxlength+1)*(n-1))+18;
		}
		else
		{
			totalglues = ((maxlength+1)*(n-2))+ 2*n -2  + 18;
		}
    	

//initial printing in .tile file	
		fp.println("\n\ntile edges matches {{N E S W}*}");
		fp.println("num tile types="+ totaltiles);
		fp.println("num binding types="+ totalglues+"\n");
		fp.println("tile edges={");
		
//seed tile..
		fp.println("% ----------seed tile------------");	
		fp.println("{ 19 0 0 19 }(green)");

// basic computational tiles for addition..

		fp.println("% ----------basic computational tiles for addition------------");		
    	fp.println("{ 1 7 3 7 }(white)");
		fp.println("{ 2 7 4 7 }(red)");
    	fp.println("{ 1 7 6 8 }(white)");
		fp.println("{ 2 7 5 7 }(red)");
    	fp.println("{ 1 8 4 8 }(white)");
		fp.println("{ 2 8 3 7 }(red)");
    	fp.println("{ 1 8 5 8 }(white)");
		fp.println("{ 2 8 6 8 }(red)");
		
//sign bit tile for addition
		fp.println("% ----------sign tiles for addition------------");		
		fp.println("{ 12 8 11 0 }(brown)");
    	fp.println("{ 12 7 12 0 }(brown)");
		fp.println("{ 11 7 11 0 }(yellow)");
		
		
// basic computational tiles for subtraction..

		fp.println("% ----------basic computational tiles for subtraction------------");				
    	fp.println("{ 1 9 3 9 }(white)");
		fp.println("{ 2 9 4 10 }(red)");
    	fp.println("{ 1 9 6 9 }(white)");
		fp.println("{ 2 10 3 10 }(red)");
    	fp.println("{ 1 10 4 10 }(white)");
		fp.println("{ 2 9 5 9 }(red)");
    	fp.println("{ 1 10 5 9 }(white)");
		fp.println("{ 2 10 6 10 }(red)");

		fp.println("% ----------sign tiles for subtraction------------");		
    	fp.println("{ 11 10 12 0 }(yellow)");
		fp.println("{ 11 9 11 0 }(yellow)");
    	fp.println("{ 12 9 12 0 }(brown)");


// tiles for final output if final output is positive....
		fp.println("% ----------tiles for final output if final output is positive------------");		
		fp.println("{ 0 14 17 0 }(brown)");
		fp.println("{ 0 14 1 14 }(white)");
		fp.println("{ 0 14 2 14 }(red)");


// tiles for final output if final output is negative...
		fp.println("% ----------tiles for final output if final output is negative------------");		
		fp.println("{ 0 2 2 2 }(white)");
		fp.println("{ 0 2 1 1 }(red)");
		fp.println("{ 0 1 2 1 }(red)");
		fp.println("{ 0 1 1 1 }(white)");
		fp.println("{ 0 1 14 0 }(yellow)");


//other computational tiles... for final output..
		fp.println("% ----------other computational tiles... for final output------------");		
		fp.println("{ 1 13 1 13 }(orange)");
		fp.println("{ 2 13 2 13 }(orange)");
		fp.println("{ 17 13 12 0 }(orange)");
		fp.println("{ 16 13 11 0 }(orange)");
		fp.println("{ 14 15 16 0 }(orange)");
		fp.println("{ 2 15 1 15 }(orange)");
		fp.println("{ 1 15 2 15 }(orange)");
		fp.println("{ 0 0 13 14 }(orange)");
		fp.println("{ 18 0 13 15 }(orange)");
		fp.println("{ 0 0 18 2 }(orange)");
    	
    	
    	
//tiles for first two numbers

		fp.println("% ----------tiles for input------------");		
		for(i=maxlength-1;i >= 0;i--,x++)
		{
			fp.print("{ " + ((Character.digit(binaryinput[0].charAt(i),10)*2)+Character.digit(binaryinput[1].charAt(i),10)+3)+" "+x +" 0 " + (x+1));
			if(((Character.digit(binaryinput[0].charAt(i),10)*2)+Character.digit(binaryinput[1].charAt(i),10)+3)==6)
				fp.print(" }(blue)\n");
			else if(((Character.digit(binaryinput[0].charAt(i),10)*2)+Character.digit(binaryinput[1].charAt(i),10)+3)==5)
				fp.print(" }(cyan)\n");				
			else if(((Character.digit(binaryinput[0].charAt(i),10)*2)+Character.digit(binaryinput[1].charAt(i),10)+3)==4)
				fp.print(" }(grey)\n");				
			else
				fp.print(" }(pink)\n");
		}
		fp.print("{ 12 "+x +" 0 0 }(brown)\n");
    	

		if((2*n - 3)>maxlength)
			x = 19 + (2*n) - 2 - 1;

//tiles of all the remaining numbers with previous input number as zero as well as one...

		for(j=2, x=x+1;j<n;j++,x++)
		{
//for previous input as zero
			el[j-2]=x;
			for(i=maxlength-1;i>=0;i--, x++)
			{
				fp.print("{ " + (Character.digit(binaryinput[j].charAt(i),10)+3)+" "+x +" 1 " + (x+1));
				if((Character.digit(binaryinput[j].charAt(i),10)+3)==4)
					fp.print(" }(grey)\n");
				else
					fp.print(" }(pink)\n");	
			}
			fp.print("{ 11 "+ x +" 11 0 }(yellow)\n");
			x=el[j-2];
//for previous input as one
			for(i=maxlength-1;i>=0;i--, x++)
			{
				fp.print("{ " + (Character.digit(binaryinput[j].charAt(i),10)+5)+" "+x +" 2 " + (x+1));
				if((Character.digit(binaryinput[j].charAt(i),10)+5)==6)
					fp.print(" }(blue)\n");
				else
					fp.print(" }(cyan)\n");	
			}
			fp.print("{ 12 "+x +" 12 0 }(brown)\n");
		}
		
//calculating remaining frame tiles SR and ER..
		fp.println("% ----------right side frame tiles------------");		
		for(i=0,k=19;i<n-1;i++,k=k+2)
		{
			fp.print("{ "+(k+1)+ " 0 "+k+ ((oper.elementAt(i)=='+')?" 7":" 9") +" }(magenta)\n" );
		}
		for(i=0,k=20;i<n-2;i++,k=k+2)
		{
			fp.print("{ "+ (k+1) +" 0 "+ k+" "+el[i]+ " }(magenta)\n");
		}
		fp.print("{ 13 0 "+ k + " 13 }(magenta)\n");//changed
		fp.println("}\n\n");





		fp.println("binding strengths=");
		fp.print("{");

		for(i=1;i<16;i++)
		{
			fp.print("1 ");
		}
		fp.print("2 2 2 ");
		i=19;
		if((2*n - 3)<maxlength)
		{
			for(;i<(maxlength+20);i++)
			{
				fp.print("2 ");
			}
		}
		else
		{
			for(;i<(2*n +17);i++)
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

		fp.close();    	
    }
}
