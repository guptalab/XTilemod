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


import java.util.*;
import java.io.*;

public class prime {

    public static void main(String[] args)throws Exception {

    int n, length;
   	String binaryn;
   	int totaltiles; //------this variable stores total tiles to be used------
   	int totalglue;  //------this variable stores total glues to be used------

    Scanner inp = new Scanner(System.in);
	System.out.print("enter the number for its primality testing:-   "); //------taking the integer whose primality is to be checked-----
	n = inp.nextInt();


    PrintWriter fp;
    String fileName = "primeltytestof_"+ n+".tiles";

    //------writing to file-------
	fp = new PrintWriter(new FileWriter(fileName)); //opening file
	fp.println("% " + fileName);
	fp.println("% Mentor : Manish Gupta");
	fp.println("% Author : Sandeep Vasani, Abhishek Chhajer, Jaley Dholakiya");
    fp.println("% topmost row shows the output");

    binaryn= Integer.toBinaryString(n);
    length = binaryn.length();

    totaltiles= 114+length;
    totalglue = 30+ 1+ length;

	fp.println("\n\ntile edges matches {{N E S W}*}");
	fp.println("num tile types="+ totaltiles);
	fp.println("num binding types="+ totalglue+"\n");
	fp.println("tile edges={");



    fp.println("{ 22 0 0 31} (green)");   //------Seed tile-----
	int x, count=31;

	//--------generating the input tiles------
	for (int i=(length-1); i>=0;i--, count++)
	{
		x=Character.digit(binaryn.charAt(i),10);
		if(x==0)
			x=19;
		else
			x=18;
		fp.print("{ "+x+" "+count+" 0 "+ (count+1));
		if(x==18)
			fp.println(" }(brown)");
		else
			fp.println(" }(yellow)");
	}

    fp.println("{ 28 "+count+ " 0 0 }(green)");     //--------Left Frame Bottom Corner Tile--------

    //-------Tile set for checking "c>1"------------
   	fp.println("{ 1 9 1 9 }(green)");
   	fp.println("{ 2 10 2 9 }(green)");
   	fp.println("{ 7 9 7 9 }(green)");
   	fp.println("{ 8 10 8 9 }(green)");
   	fp.println("{ 1 11 1 10 }(green)");
   	fp.println("{ 2 11 2 10 }(green)");
   	fp.println("{ 7 11 7 10 }(green)");
   	fp.println("{ 8 11 8 10 }(green)");
   	fp.println("{ 1 11 1 11 }(green)");
   	fp.println("{ 2 11 2 11 }(green)");
   	fp.println("{ 7 11 7 11 }(green)");
   	fp.println("{ 8 11 8 11 }(green)");

    //------computation tiles for traversing right to left---------
    fp.println("{ 1 12 1 12 }(green)");
   	fp.println("{ 2 12 2 12 }(green)");
    fp.println("{ 7 12 7 12 }(green)");
    fp.println("{ 8 12 8 12 }(green)");

    //------Tiles for testing c > b------------
    //------here when c>b the east symbol/glue is:'>' (13)---------
    //------when c<b the east symbol/glue is:'<' (14)------------
    //------when c=b the east symbol/glue is:'=' (15)------------
    fp.println("{ 1 13 1 13 }(blue)");
    fp.println("{ 2 14 2 13 }(blue)");
    fp.println("{ 3 15 3 13 }(blue)");
    fp.println("{ 4 13 4 13 }(blue)");
    fp.println("{ 5 13 5 13 }(blue)");
    fp.println("{ 6 14 6 13 }(blue)");
    fp.println("{ 7 15 7 13 }(blue)");
    fp.println("{ 8 13 8 13 }(blue)");

    //----other tiles required for testing "c > b"------
    for(int i=1;i<9 ;i++)
    {
            fp.println("{ "+i+" 14 " +i+ " 14 }(blue)");
            fp.println("{ "+i+" 15 " +i+ " 15 }(blue)");
    }

    //-----Tiles for calculating "b-c"------
    fp.println("{ 1 16 1 16 }(grey)");
    fp.println("{ 4 16 2 17 }(grey)");
    fp.println("{ 3 16 3 16 }(grey)");
    fp.println("{ 2 16 4 16 }(grey)");
    fp.println("{ 3 17 1 17 }(grey)");
    fp.println("{ 2 17 2 17 }(grey)");
    fp.println("{ 1 17 3 16 }(grey)");
    fp.println("{ 4 17 4 17 }(grey)");
    fp.println("{ 5 16 5 16 }(grey)");
    fp.println("{ 8 16 6 17 }(grey)");
    fp.println("{ 7 16 7 16 }(grey)");
    fp.println("{ 6 16 8 16 }(grey)");
    fp.println("{ 7 17 5 17 }(grey)");
    fp.println("{ 6 17 6 17 }(grey)");
    fp.println("{ 5 17 7 16 }(grey)");
    fp.println("{ 8 17 8 17 }(grey)");

    //-----Tiles for calculating "c-1"------
    fp.println("{ 2 18 1 18 }(pink)");
    fp.println("{ 1 18 2 19 }(pink)");
    fp.println("{ 2 18 3 18 }(pink)");
    fp.println("{ 1 18 4 19 }(pink)");
    fp.println("{ 8 18 5 18 }(pink)");
    fp.println("{ 7 18 6 19 }(pink)");
    fp.println("{ 8 18 7 18 }(pink)");
    fp.println("{ 7 18 8 19 }(pink)");
    fp.println("{ 1 19 1 19 }(pink)");
    fp.println("{ 2 19 2 19 }(pink)");
    fp.println("{ 1 19 3 19 }(pink)");
    fp.println("{ 2 19 4 19 }(pink)");
    fp.println("{ 7 19 5 19 }(pink)");
    fp.println("{ 8 19 6 19 }(pink)");
    fp.println("{ 7 19 7 19 }(pink)");
    fp.println("{ 8 19 8 19 }(pink)");

    //-------Tiles to transform 1 bit to 3 bits------
    fp.println("{ 1 19 19 19 }(cyan)");
    fp.println("{ 7 18 18 19 }(cyan)");
    fp.println("{ 2 19 19 18 }(cyan)");
    fp.println("{ 8 18 18 18 }(cyan)");

    //-------Tiles for generating Output--------
    for (int i=1; i<9; i++)
    {
        fp.println("{ 0 20 " +i+" 20 }(white)");
        fp.println("{ 0 21 " +i+" 21 }(red)");
    }

    //------Left Frame tile set--------
    fp.println("{ 23 19 28 0 }(orange)");
    fp.println("{ 22 9 23 0 }(orange)");
    fp.println("{ 24 12 22 0 }(orange)");
    fp.println("{ 22 13 24 0 }(orange)");
    fp.println("{ 24 16 22 0 }(orange)");
    fp.println("{ 23 19 22 0 }(orange)");
    fp.println("{ 0 20 22 0 }(white)");     //-------Left frame Top corner Tile corresponding to zero-----
    fp.println("{ 0 21 22 0 }(red)");       //-------Left frame Top corner Tile corresponding to one------


    //----------Right Corner Tile set-------
    fp.println("{ 22 0 22 19 }(orange)");
   	fp.println("{ 22 0 22 18 }(orange)");
    fp.println("{ 30 0 22 10 }(orange)");
    fp.println("{ 27 0 22 11 }(orange)");
    fp.println("{ 22 0 27 12 }(orange)");
    fp.println("{ 29 0 22 13 }(orange)");
    fp.println("{ 26 0 22 14 }(orange)");
    fp.println("{ 25 0 22 15 }(orange)");
    fp.println("{ 22 0 25 16 }(orange)");
    fp.println("{ 22 0 26 18 }(orange)");
    fp.println("{ 0 0 30 20 }(white)");     //------Right frame Top corner Tile corresponding to zero-----
    fp.println("{ 0 0 29 21 }(red)");       //------Right frame TOp corner tile corresponding to one------

    fp.println("}\n\n");

    //------writng the binding strength line to the file-----
    fp.println("binding strengths=");
    fp.print("{");
    for(int i=1;i<23;i++)
    {
        fp.print("1 ");
    }
    for(int i=23; i<(count+1);i++)
    {
        fp.print("2 ");
    }
    fp.println("}");
    fp.println("size=24");
    fp.println("block=12");
    fp.println("T=2");

    //-----now we have finished writing to the file thus closing the file------
    fp.close();
    }
}
