/**
 * @(#)nadder_L_type.java
 * @ Mentor : Manish Gupta
 * @ Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @version 1.00 2010/7/6
 */

/*
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * ADDS 'n' integers using L Configuration method
 * Input1  : Bottommost Row represent the largest input among all input
 * Input2  : Right most coloum represent rest of all input consicutively..
 * Output  : Topmost Row represents the output
 * Seed Tile: Green
 * Output Tile: Red = 1                           White = 0
 * Input  Tile: brown=1                           yellow=0
 *              the grey tiles is use for boundary
 */ 


 //package mypack; 	
import java.util.*;
import java.io.*;



public class nadder_L_type {
    
    nadder_L_type() throws Exception{

    int n, i, k, j;

    Scanner inp = new Scanner(System.in);
	System.out.print("Enter total number of integers you want to enter:-  ");	//taking value of n.   i.e. total nuber of integer
	n = inp.nextInt();
    if(n<2)
    {
    	System.out.println("cannot add a single number");
    	return;
    }
    
    int input[]= new int[n];
    String binaryinput[]= new String[n];
    int lowerlength, largestinput=0, largestinputindex=0;
    int x=5, y, z;
    int maxvarlength=1, vertile=0, hortile=0;
    int totaltiles, totalglues;


//taking inputs from user...
	for(i=0;i<n ; i++)
	{
		System.out.print("number " +(i+1)+ ":-  ");
		input[i]=inp.nextInt();
		if(largestinput<input[i]) // for finding out the largest input
		{
			largestinput=input[i];
			largestinputindex=i;
		}
		if(input[i]<0)
		{
			System.out.println("cannot add negative number so pl. enter only positive number.");	// negative input not allowed
				i--;
		}
	}
	
// making the largest input at index 0...	
	input[largestinputindex]=input[0];
	input[0]=largestinput;


//creating file    	
    PrintWriter fp;
    String fileName = "nadder_L_type";
    for(i=0;i<n;i++)
    {
    	fileName=fileName+input[i]+",";
    }
    fileName=fileName+".tiles";

	fp = new PrintWriter(new FileWriter(fileName)); //opening file
	fp.println("% " + fileName);
	fp.println("% Mentor : Manish Gupta");
	fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");	
	fp.print("% Addition tile set of ");
	for(i=0;i<n;i++)
    {
    	fp.print(input[i]+", ");
    }
	fp.println("\n% topmost row shows the output");	
	String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tRed = 1\tWhite = 0\n%Input  Tile:\tBrown=1\tyellow=0\n% grey colour in the input represent frame tile.\n";
	fp.println("\n"+ColourIndication+"\n");
	

//converting input numbers in binary string    
   	binaryinput[0]= Integer.toBinaryString(input[0]);
	hortile=binaryinput[0].length() + n - 1;
    for(i=1;i<n;i++)
    {
    	binaryinput[i]= Integer.toBinaryString(input[i]);
    	vertile= vertile + binaryinput[i].length();
    	if(binaryinput[i].length() > maxvarlength)
    	{
    		maxvarlength=binaryinput[i].length();
    	}
    }
    
//printing the numbers and final sum in the file
	    int temp = input[0];
	    for(i=1;i<n;i++)
	    {
	    	temp = temp+input[i];  // calculating final product
	    	fp.println("% "+input[i]+" : "+binaryinput[i]);
	    }
	    String t=Integer.toBinaryString(temp);
	    if(t.length()< maxvarlength)
	    {
	    	for(i=t.length(); i<maxvarlength;i++)
	    		t = "0"+t;
	    }
	    fp.println("%----->>>Final Sum = "+temp+"\tin binary : "+t); 
    
// making the horizontal tile to be of sutable size by adding 0 in the beginning    
   	for(j=(n-1);j>0;j--)
   	{
   		binaryinput[0]='0'+binaryinput[0];
   	}    

//calculating total number of tiles..   
	totaltiles= 11+hortile+vertile +(maxvarlength*2);

//calculating total number of glues..
	if(hortile>vertile)
	{
		totalglues=4+maxvarlength+hortile;
	}    
	else
	{
		totalglues=4+maxvarlength+vertile;
	}
   
//initial printing in .tile file	
	fp.println("\n\ntile edges matches {{N E S W}*}");
	fp.println("num tile types="+ totaltiles);
	fp.println("num binding types="+ totalglues+"\n");
	fp.println("tile edges={");
	
//seed tile..
	fp.println("% ----------seed tile------------");	
	fp.println("{ "+ (maxvarlength+4) + " 0 0 " +(maxvarlength+4) + " }(green)");

// basic computational tiles..

	fp.println("% ----------basic computational tiles------------");		
    fp.println("{ 1 4 2 2 }(white)");
	fp.println("{ 2 4 1 1 }(red)");
    fp.println("{ 1 1 1 1 }(white)");
	fp.println("{ 2 1 2 1 }(red)");
    fp.println("{ 1 2 2 2 }(white)");
	fp.println("{ 2 2 1 1 }(red)");


//other computational tiles
	fp.println("% ----------other computational tiles------------");		
	for(i=0;i<(maxvarlength-1);i++, x++)
	{
		fp.print("{ 1 "+ x + " 1 "+ (x-1)+ " }(white)\n" );
	}
	x=5;
		for(i=0;i<(maxvarlength-1);i++, x++)
	{
		fp.print("{ 2 "+ x + " 2 "+ (x-1)+ " }(red)\n" );
	}


//left and top frame tile
	fp.println("% ----------left and topmost frame tiles------------");		
	fp.println("{3 1 3 0 }(grey)");
	fp.println("{ 0 3 3 0 }(grey)");
	fp.println("{ 0 3 2 3 }(red)");
	fp.println("{ 0 3 1 3}(white)");




	z=y=x;	
//vertical frame
	fp.println("% ----------vertical frame tiles------------");		
	for(j=1;j<n;j++)
	{
		for(i=(binaryinput[j].length()-1), k=0 ;i>= 0;i--, y++, k++)
		{
			fp.print("{ " +(y+1)+" 0 " +y+" " + ((Character.digit(binaryinput[j].charAt(i),10)==0)?1:(k+4)));
		if((Character.digit(binaryinput[j].charAt(i),10))==1)
			fp.print(" }(brown)\n");
		else
			fp.print(" }(yellow)\n");
		}
	}
	fp.print("{ 0 0 "+ y +" 3 }(grey)\n" );


// horizontal frame	
	fp.println("% ----------horizontal frame tiles------------");		
	for(i=(binaryinput[0].length()-1);i>=0;i--, z++)
	{
		fp.print("{ " + (Character.digit(binaryinput[0].charAt(i),10)+1)+" "+z +" 0 " + (z+1));
		if((Character.digit(binaryinput[0].charAt(i),10))==1)
			fp.print(" }(brown)\n");
		else
			fp.print(" }(yellow)\n");
	}
	fp.print("{ 3 "+z +" 0 0 }(grey)\n");


	fp.println("}\n\n");
	
//binding strength
	fp.println("binding strengths=");
	fp.print("{");

	for(i=1;i<x;i++)
	{
		fp.print("1 ");
	}
		if(y>z)
	{
		for(;i<=y;i++)
		{
			fp.print("2 ");
		}
	}
	else
	{
		for(;i<=z;i++)
		{
			fp.print("2 ");
		}
	}
	
	//assigning size of terminal, block(tile) size
	fp.println("}");
	fp.println("size=24");
	fp.println("block=12");
	fp.println("T=2");

	fp.close();	//closing the file
    }
}
