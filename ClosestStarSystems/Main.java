package ClosestStarSystems;
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
        ArrayList<String[]> stars = new ArrayList<String[]>();
        String[] currStar = new String[5];

        int posDist, posX, posY, posZ;
        String currText = "";


        for(String s : in){
            
            /*posZ = s.lastIndexOf(" ") + 1;
            posY = s.lastIndexOf(" ", posZ) + 1;
            posX = s.lastIndexOf(" ", posY) + 1;
            posDist = s.lastIndexOf(" ", posX) + 1;

            System.out.println(s);
            System.out.println(posDist + " " + posX + " " + posY + " " + posZ);
            System.out.println(s.length());
            */

            //currName = s.substring(0, s.indexOf(" "));
            //currDist = s.substring(posDist, posX - 1);
            //currX = s.substring(posX, posY - 1);
            //currY = s.substring(posY, posZ - 1);
            //currZ = s.substring(posZ);
        }

        
        //System.out.println(currName + " " + currDist + " " + currX + " " + currY + " " + currZ);
    
    }

    public static double pythagoreas(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    
}
