package BrokenFirewall;
import java.io.*;
import java.util.*;

/**
 * Write a description of class Main here.
 *
 * @author Lo
 * @version 3-5-24
 */
public class Main
{
    static ArrayList<String> in = new ArrayList<String>();

    public static void main(String[] args)
    {

        //Try and read in the numbers from input.txt
        try {
            Scanner input = new Scanner(new File("BrokenFirewall/puzzledata.txt"));
            while(input.hasNextLine()){
                in.add(input.nextLine());
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("Error reading or parsing file");
        }


        System.out.println("Answer: " + test());

    }

    public static String test(){
        int currLength, currSource1, currSource2, currDestination1, currDestination2, totalInternal = 0, totalPassenger = 0; 
        String lengthStr, sourceHeader1, sourceHeader2, destinationHeader1, destinationHeader2;

        for(String line : in){
            lengthStr = line.substring(4, 8);
            sourceHeader1  = line.substring(24, 26);
            sourceHeader2 = line.substring(26, 28);
            destinationHeader1 = line.substring(32, 34);
            destinationHeader2 = line.substring(34, 36);

            currLength = Integer.valueOf(lengthStr, 16);
            currSource1= Integer.valueOf(sourceHeader1, 16);
            currSource2= Integer.valueOf(sourceHeader2, 16);
            currDestination1 = Integer.valueOf(destinationHeader1, 16);
            currDestination2 = Integer.valueOf(destinationHeader2, 16);

            //debugging
            System.out.println("Length " + currLength + ", source IP header " + currSource1 + "." + currSource2 + ", destination IP header " + currDestination1 + "." + currDestination2);

            if(currSource1 == 10 && currSource2 == 0){
                totalPassenger+= currLength;
            } else if (currSource1 == 192 && currSource2 == 168){
                totalInternal+= currLength;
            }

            if(currDestination1 == 10 && currDestination2 == 0){
                totalPassenger+= currLength;
            } else if (currDestination1 == 192 && currDestination2 == 168){
                totalInternal+= currLength;
            }


        }

       
        return "Ratio: " + totalInternal + "/" + totalPassenger;
    }

    
}
