/**
 * @(#)n_multiply.java
 * @Mentor: Manish Gupta
 * @Author: Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya
 * @version 1.00 2010/7/3
 */
 
 /**
 * Use at your own risk. The software is provided as it is. No guarantee is taken for any errors or omissions. 
 * carries out multiplication of 'n' integers
 * Input1  : Bottommost Row represent the largest input among all input
 * Input2  : Right most coloum represent rest of all input consicutively..
 * Output  : Topmost Row represents the output
 * Seed Tile:Green
 * Output Tile:Cyan = 1            Magenta = 0
 * Input  Tile:Red=1               White=0
 */
 
 //package mypack;   
 import java.util.Scanner;
 import java.io.*;
 
 
 public class n_multiply{
 	n_multiply()throws Exception
 	{
 		int n,temp;
 		Scanner inp = new Scanner(System.in);
		System.out.print("Enter total integers you want to input:-  ");//taking value of n.   i.e. total nuber of integer
		n = inp.nextInt();
	    if(n<2)
	    {
	    	System.out.println("cannot multiply a single number");
	    	return;
	    }
	    
	    int input[]= new int[n];
	    String binaryinput[]= new String[n];
	    int maxlength=1;
	    int x=6;
	    int totaltiles=0, totalglues=0;
	   	int el[]=new int[n-2];
	   	
	   	//taking inputs from user...
		for(int i=0;i<n ; i++)
		{
			System.out.print("number " +(i+1)+ ":-  ");
			input[i]=inp.nextInt();
			if(input[i]<0)
			{
				System.out.println("Please do not enter negative number");// negative input not allowed
				i--;
			}
		}
		
		//searching biggest integer and placing it at first position
		temp=input[0];
		int j=0;
		for(int i=0; i<n; i++)
		{
			if(temp < input[i])
			{
				temp = input[i];
				j=i;
			}
		}
		input[j] = input[0];
		input[0] = temp;
		
		//creating file
		PrintWriter fp;
	    String fileName = "n_mult";
	    for(int i=0;i<n;i++)
	    {
	    	fileName=fileName+input[i]+",";
	    }
	    fileName=fileName+".tiles";
	
		fp = new PrintWriter(new FileWriter(fileName)); //opening file
		
		fp.println("% " + fileName);
		fp.println("% Mentor : Manish Gupta");
		fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");	
		fp.print("% Addition tile set of ");
		
		for(int i=0;i<n;i++)
	    {
	    	fp.print(input[i]+", ");
	    }
	    fp.println();
	    fp.println("% topmost row shows the product of all inputs");
		String ColourIndication="%Seed Tile\t:\tGreen\n%Output Tile:\tCyan = 1\tMagenta = 0\n%Input  Tile:\tRed=1 \t White=0";
		fp.println("\n"+ColourIndication+"\n");	
		fp.println("\n% Biggest input is made horizontal frame\n% Other inputs form vertical frame");
			    
		//converting input numbers in binary string	    
	    //padding first integer with extra zeros as product of 'n'integers of size 'm' could be n*m long
	    binaryinput[0]=Integer.toBinaryString(input[0]);
	    maxlength=binaryinput[0].length(); 
	    
	    for(int i=maxlength; i<(maxlength*n) ;i++)
	    {
	    	binaryinput[0] = "0"+binaryinput[0];
	    }
	    maxlength=binaryinput[0].length();
	    totaltiles += maxlength;
	   	totalglues = totaltiles;
	   	
	    fp.println("\n%-----Inputs in Binary------\n% "+input[0]+" : "+binaryinput[0]);
	    
	    temp = input[0];
	    for(int i=1;i<n;i++)
	    {
	    	temp = temp*input[i];  // calculating final product
	    	binaryinput[i]= Integer.toBinaryString(input[i]);
	    	totaltiles += binaryinput[i].length();
	    	totalglues = totaltiles;
	    	fp.println("% "+input[i]+" : "+binaryinput[i]);
	    }
	    String t=Integer.toBinaryString(temp);
	    if(t.length()< maxlength)
	    {
	    	for(int i=t.length(); i<maxlength;i++)
	    		t = "0"+t;
	    }
	    fp.println("%----->>>Final Product = "+temp+"\tin binary : "+t);
	    
	    
	    
	    totaltiles = totaltiles+28+(n)+4; //calculating total number of tile types
		totalglues = totalglues+8+(n)+1; //calculating total number of binding domains
	    
	    fp.println("\ntile edges matches {{N E S W}*}");
		fp.println("num tile types="+ totaltiles);
		fp.println("num binding types="+ totalglues);
		fp.println("tile edges={");
		
		
		int bindingIndex = 0;//binding domain index
			
		//assigning binding domain index for 0, 1
		int bINormal[] = new int[2];
		for(int i=0; i < 2; i++)
		{
			bINormal[i] = ++bindingIndex;
		}
		
		int bISpecial = 0;
		int bISpecial2 = 0;
		int bINormal1[] = new int[4];
		int bIMult[] = new int[2];
		int bseed;
		
		//assining binding domain index for 00, 01, 10, 11
		for(int i=0; i < 4; i++)
		{
			bINormal1[i] = ++bindingIndex;
		}

		//assining binding domain index for 20,21
		for(int i=0; i < 2; i++)
		{
			bIMult[i] = ++bindingIndex;
		}
		
		
		//Generating Seed Configuration
		bseed = ++bindingIndex;
		bISpecial2 = ++bindingIndex;
		//writing seed tile
		fp.println("\n%--------------Seed Tile-------------");
		fp.println("{" + (bseed) + " " + "0" + " " + "0" + " " + (++bindingIndex) + "}" + "[.1](Green)" );
				
		//writing the horizontal frame tiles
		fp.println("%--------------Horizontal Frame Tile (Red-1 White-0)-------------");
		int a[] = new int[maxlength];
		for(int i=0; i<maxlength;i++)
		{
			a[i] = Character.digit((binaryinput[0].charAt(i)),10 );
		}

		int i;
		for(i=binaryinput[0].length()-1; i>0; i--)
		{
			fp.print( "{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " + (++bindingIndex) + "}" + "[.1]" );
			if(a[i]==0)
			{
				fp.println("(white)");
			}
			else
			{
				fp.println("(red)");
			}
		}
		if(a[i] == 0)
			fp.println("{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " +"0"+ "}" + "[.1](white)");
		else
			fp.println("{" + (a[i]+bINormal[0]) + " " + bindingIndex + " " + "0" + " " +"0"+ "}" + "[.1](red)");
		
		
		//Generating vertical frame
		fp.println("%--------------Vertical Frame Tile (Red-1 White-0)-------------");
		for(i=1; i<n ;i++)
		{
			j=binaryinput[i].length()-1;
			if(i == 1)
			{
				if(Character.digit((binaryinput[i].charAt(j)),10 ) == 0)
				{
					fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bseed) +" " + 1 + "}" + "[.1]" );
					fp.println("(white)");
				}
				else
				{
					fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bseed)+" " + 2 + "}" + "[.1]" );
					fp.println("(red)");
				}
			}
			else
			{
				bISpecial = bindingIndex;
				if(Character.digit((binaryinput[i].charAt(j)),10 ) == 0)
				{
					fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bISpecial) +" " + 1 + "}" + "[.1]" );
					fp.println("(white)");
				}
				else
				{
					fp.print("{"+ (++bindingIndex)+ " "+ "0" + " " + (bISpecial)+" " + 2 + "}" + "[.1]" );
					fp.println("(red)");
				}	
			}
			j--;
			for(; j>=0; j--)
			{
				bISpecial=bindingIndex;
				if(Character.digit((binaryinput[i].charAt(j)),10 ) == 0)
				{
					fp.print( "{" + (++bindingIndex) + " "+ "0" + " " + bISpecial +" "+bINormal1[0]+ "}" + "[.1]" );
					fp.println("(white)");
				}
				else
				{
					fp.print( "{" + (++bindingIndex) + " "+ "0" + " " + bISpecial +" "+ bINormal1[2] + "}" + "[.1]" );
					fp.println("(red)");
				}
			}
			bISpecial = bindingIndex;
			fp.println("{"+(++bindingIndex)+" 0 " +bISpecial + " " +bISpecial2+ "}[.1](green)");
		}
		
		
		//writing 16 propagation tiles with proper binding domain index in the following order
		//first-row tiles, sifting tiles, conversion tiles
		fp.println("%---------------------Block no 1 -------------------");	
		fp.println("{3 1 1 1}(purple)");
		fp.println("{3 2 1 2}(purple)");
		fp.println("{5 1 2 1}(purple)");
		fp.println("{6 2 2 2}(purple)");
		
		fp.println("%---------------------Block no 2 (blue-1 pink-0) -------------------");
		fp.println("{3 3 3 3}(pink)");
		fp.println("{4 3 4 3}(pink)");
		fp.println("{5 4 3 3}(pink)");
		fp.println("{6 4 4 3}(blue)");
		fp.println("{3 3 5 4}(pink)");
		fp.println("{4 3 6 4}(blue)");
		fp.println("{5 4 5 4}(pink)");
		fp.println("{6 4 6 4}(blue)");
				
		fp.println("%---------------------Block no 3 (yellow-1 pink-0) -------------------");
		fp.println("{3 5 5 6}(pink)");
		fp.println("{3 5 3 5}(pink)");
		fp.println("{6 6 3 5}(yellow)");
		fp.println("{6 6 5 6}(yellow)");
		fp.println("{4 5 4 5}(yellow)");
		fp.println("{4 5 6 6}(yellow)");
		fp.println("{5 6 4 7}(pink)");
		fp.println("{5 6 6 8}(pink)");
				
		fp.println("%---------------------Block no 4 (orange-1 pink-0) -------------------");
		fp.println("{4 7 3 5}(orange)");
		fp.println("{4 7 5 6}(orange)");
		fp.println("{5 8 3 7}(pink)");
		fp.println("{5 8 5 8}(pink)");
		fp.println("{3 7 4 7}(pink)");
		fp.println("{3 7 6 8}(pink)");
		fp.println("{6 8 4 7}(orange)");
		fp.println("{6 8 6 8}(orange)");
		
		fp.println("%---------------------Block no 5 (cyan-1 magenta-0) -------------------");
		fp.println("{1 10 3 10}(magenta)");
		fp.println("{2 10 4 10}(cyan)");
		fp.println("{1 10 5 10}(magenta)");
		fp.println("{2 10 6 10}(cyan)");		
		fp.println("}");
			
		fp.println("binding strengths=");
		fp.print("{");
		for(i=0; i<8; i++)
			fp.print("1 ");
		fp.print("2 1 ");
		for(i=10;i<totalglues;i++)
			fp.print("2 ");
			
		fp.println("}");
		fp.println("size=24");
		fp.println("block=12");
		fp.println("T=2");
		fp.close();
	}
 }