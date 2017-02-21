/**
 * @(#)nadder_8_tile.java
 * @ Mentor : Manish Gupta
 * @ Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @ version 1.00 2010/7/4
 */

/**
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * ADDS 'n' integers using 8 Computational Tile method
 * Input1  : Bottommost Row represent First and Second input
 * Input2  : next alternate row represents middle temporary sum and next consicutive inputs
 * Output  : Topmost Row represents the output
 * Input Tiles:- brown=11                yellow=01 or 10         pink=00
 *                suppose first nuber is 1100 and second nuber is 1010 then the tile for this is
 *                1) 11     2) 10 or 01     3) 10 or 01         4)00            
 *                here 01 and 10 both are same and doesnot make any difference because sun of 0+1 and 1+0 is both 1..
 * Output Tiles :- red=1           white=0
 * grey colour tile is use for frame tiles..
 */ 



 //package mypack;
import java.util.*;
import java.io.*; 



public class nadder_8_tile{
    
    nadder_8_tile()throws Exception{

    int n, i, k, j;

    Scanner inp = new Scanner(System.in);
	System.out.print("enter total number of integers you want to enter:-  "); //taking value of n.   i.e. total nuber of integer
	n = inp.nextInt();
    if(n<2)
    {
    	System.out.println("cannot add a single number");
    	return;
    }
    
    int input[]= new int[n];
    String binaryinput[]= new String[n];
    int maxlength=1;
    int x=7;
    int totaltiles, totalglues;
   	int el[]=new int[n-2];

//taking all inputs from user...
	for(i=0;i<n ; i++)
	{
		System.out.print("number " +(i+1)+ ":-  ");
		input[i]=inp.nextInt();
		if(input[i]<0)
		{
			System.out.println("cannot add negative number so pl. enter only positive number."); // negative input not allowed
				i--;
		}
	}

//creating file    	
    PrintWriter fp;
    String fileName = "nadder_8_tile";
    for(i=0;i<n;i++)
    {
    	fileName=fileName+input[i]+",";
    }
    fileName=fileName+".tiles";

//writing to file..
	fp = new PrintWriter(new FileWriter(fileName)); //opening file
	fp.println("% " + fileName);
	fp.println("% Mentor : Manish Gupta");
	fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");	
	fp.print("% Addition tile set of ");
	for(i=0;i<n;i++)
    {
    	fp.print(input[i]+", ");
    }
    fp.println("");
    fp.println("% topmost row shows the output");
    
	String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%Input  Tile:\tbrown=11\tyellow=01 or 10\tpink=00";
	fp.println("\n"+ColourIndication+"\n");
	System.out.println("Required Addition tile set is loaded in to "+fileName);
	
	//converting input numbers in binary string    
    for(i=0;i<n;i++)
    {
    	binaryinput[i]= Integer.toBinaryString(input[i]);
    	if(binaryinput[i].length() > maxlength)
    	{
    		maxlength=binaryinput[i].length();
    	}
    }
    //writing to file
    int tempsum=0;
	int buff;
	int inew;
    for(inew=0;inew<n;inew++)
    {
    	binaryinput[inew]= Integer.toBinaryString(input[inew]);
    	int temporary;
    	tempsum=tempsum+input[inew];
    	temporary=input[inew];
		fp.print("%"+input[inew]+"\t\t ");
        int[] s;
        s=new int[16];
        for(buff=0;buff<16;buff++)
        {
			s[buff]=(temporary%2);
			temporary=temporary/2;
    	}
    	
    	for(buff=15;buff>=0;buff--)
    		fp.print(s[buff]);
    		
    	if(inew<(n-1))
    		fp.print("\n%\t\t+\n");
    		
    	if(binaryinput[inew].length() > maxlength)
    		maxlength=binaryinput[inew].length();
    }
    	fp.print("\n%\t\t=\n"+"%"+tempsum+"\t\t ");
        	int[] s;
        	s=new int[16];
        	for(buff=0;buff<16;buff++){
				s[buff]=(tempsum%2);
				tempsum=tempsum/2;
    	}
    	for(buff=15;buff>=0;buff--){
    		fp.print(s[buff]);
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

//calculating total numbers of tiles    
	totaltiles= 8 + 3*n + maxlength*(2*n - 3);

//calculating total numbers of glues
	if((2*n - 3)<maxlength)
	{
		totalglues=maxlength*(n-1)+5 + n;
	}
	else
	{
		totalglues = maxlength*(n-2)+ 3*n + 2;
	}
	
//initial printing in .tile file	
	fp.println("\n\ntile edges matches {{N E S W}*}");
	fp.println("num tile types="+ totaltiles);
	fp.println("num binding types="+ totalglues+"\n");
	fp.println("tile edges={");
	
//seed tile..
	fp.println("% ----------seed tile------------");	
	fp.println("{ 7 0 0 7 }(green)");

// basic computational tiles..

	fp.println("% ----------basic computational tiles------------");		
    fp.println("{ 1 1 3 1 }(white)");
	fp.println("{ 2 1 4 1 }(red)");
    fp.println("{ 1 1 5 2 }(white)");
	fp.println("{ 2 2 3 1 }(red)");
    fp.println("{ 1 2 4 2 }(white)");
	fp.println("{ 2 2 5 2 }(red)");


//left and top frame tile
	fp.println("% ----------left and topmost frame tiles------------");		
	fp.println("{6 1 6 0 }(yellow)");
	fp.println("{ 0 6 6 0 }(grey)");
	fp.println("{ 0 6 2 6 }(red)");
	fp.println("{ 0 6 1 6}(white)");
	

//tiles for addition of first two numbers or horizontal frame tiles

	fp.println("% ----------tiles for addition of numbers------------");		
	for(i=maxlength-1;i >= 0;i--,x++)
	{
		fp.print("{ " + (Character.digit(binaryinput[0].charAt(i),10)+Character.digit(binaryinput[1].charAt(i),10)+3)+" "+x +" 0 " + (x+1));
		if((Character.digit(binaryinput[0].charAt(i),10)+Character.digit(binaryinput[1].charAt(i),10))==2)
			fp.print(" }(brown)\n");
		else if((Character.digit(binaryinput[0].charAt(i),10)+Character.digit(binaryinput[1].charAt(i),10))==1)
			fp.print(" }(yellow)\n");
		else
			fp.print(" } (pink)\n");
	}
	fp.print("{ 6 "+x +" 0 0 }(grey)\n");

//tiles for addition of all the remaining numbers with previous input number as zero as well as one...

	for(j=2, x=x+1;j<n;j++,x++)
	{
//for previous input as zero
		el[j-2]=x;
		for(i=maxlength-1;i>=0;i--, x++)
		{
			fp.print("{ " + (Character.digit(binaryinput[j].charAt(i),10)+3)+" "+x +" 1 " + (x+1) );
			if((Character.digit(binaryinput[j].charAt(i),10))==1)
				fp.print(" }(yellow)\n");
			else
				fp.print(" } (pink)\n");
		}
		x=el[j-2];
//for previous input as one
		for(i=maxlength-1;i>=0;i--, x++)
		{
			fp.print("{ " + (Character.digit(binaryinput[j].charAt(i),10)+4)+" "+x +" 2 " + (x+1));
			if((Character.digit(binaryinput[j].charAt(i),10))==1)
				fp.print(" }(brown)\n");
			else
				fp.print(" }(yellow)\n");
		}
		fp.print("{ 6 "+x +" 6 0 }(grey)\n");
	}
	
//calculating remaining frame tiles SR and ER..

	fp.println("% ----------right side frame tiles------------");		
	for(i=0,k=7;i<n-1;i++,k=k+2)
	{
		fp.print("{ "+(k+1)+ " 0 "+k+" 1 }(blue)\n" );
	}
	for(i=0,k=8;i<n-2;i++,k=k+2)
	{
		fp.print("{ "+ (k+1) +" 0 "+ k+" "+el[i]+ " }(cyan)\n");
	}
	fp.print("{ 0 0 "+ k + " 6 }(grey)\n");
	fp.println("}\n\n");
	

//calculating binding strength
	fp.println("binding strengths=");
	fp.print("{");

	for(i=1;i<7;i++)
	{
		fp.print("1 ");
	}

	if((2*n - 3)<maxlength)
	{
		for(;i<(maxlength+8);i++)
		{
			fp.print("2 ");
		}
	}
	else
	{
		for(;i<(2*n +5);i++)
		{
			fp.print("2 ");
		}
	}
	for(;i<(totalglues+1);i++)
	{
		fp.print("1 ");
	}
	fp.println("}");

	//assigning size of terminal, block(tile) size
	fp.println("size=24");
	fp.println("block=12");
	fp.println("T=2");

	fp.close(); //closing the file
    }
}
