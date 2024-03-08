package BusyMoonRovers;
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
            Scanner input = new Scanner(new File("ClosestStarSystems/testinput.txt"));
            while(input.hasNextLine()){
                in.add(input.nextLine());
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("Error reading or parsing file");
        }


        test();

    }

    public static void test(){
        String start, dest;

        ArrayList<String[]> distances = new ArrayList<String]>;

        

    
    }

    

    
}
