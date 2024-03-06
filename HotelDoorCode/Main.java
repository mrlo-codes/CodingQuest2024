package HotelDoorCode;
import java.io.*;
import java.util.*;

/**
 * Write a description of class Main here.
 *
 * @author Lo
 * @version 3-6-24
 */

public class Main
{
    static ArrayList<Integer> in = new ArrayList<Integer>();

    public static void main(String[] args)
    {

        //Try and read in the numbers from input.txt
        try {
            Scanner input = new Scanner(new File("HotelDoorCode/puzzledata.txt"));
            while(input.hasNextInt()){
                in.add(input.nextInt());
            }
            input.close();
        }
        catch(Exception e){
            System.out.println("Error reading or parsing file");
        }


        

        String[][] result = test();

        print(result);

    }

    public static String[][] test(){
        //String[][] image = new String[6][10];
        String[][] image = new String[80][100];
        

        int currRunningPos = 0, currDestinationPos = in.get(0), currNumPos = 0;

        for(int r = 0; r < image.length; r++){
            for(int c = 0; c < image[0].length; c++){
                if(currRunningPos < currDestinationPos){
                    if(currNumPos % 2 == 0){
                        image[r][c] = ".";
                        currRunningPos++;
                    } else {
                        image[r][c] = "#";
                        currRunningPos++;
                    }
                } else {
                    currNumPos++;
                    if(currNumPos < in.size())
                        currDestinationPos += in.get(currNumPos);
                    c--;
                }

            }
        }
        
        return image;
    }

    public static void print(String[][] res){
        for(int r = 0; r < res.length; r++){
            for(int c = 0; c < res[0].length; c++){
                System.out.print(res[r][c]);
            }
            System.out.println();
        }
    }

    
    
}
