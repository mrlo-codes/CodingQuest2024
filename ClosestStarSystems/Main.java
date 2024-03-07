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
            Scanner input = new Scanner(new File("ClosestStarSystems/puzzledata.txt"));
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
        ArrayList<Double> dist = new ArrayList<Double>();
        String[] currStar = new String[5];
        String tempString = "";
        String[] temp;

        int posDist, posX, posY, posZ;
        String currText = "";


        for(String s : in){
            temp = s.split("\\s+");
            //System.out.println(Arrays.toString(temp));
            for(int i = temp.length - 1; i >= 0; i--){
                //System.out.println(i);
                if(i == temp.length - 1){
                    currStar[4] = temp[i];
                } else if (i == temp.length - 2){
                    currStar[3] = temp[i];
                } else if (i == temp.length - 3){
                    currStar[2] = temp[i];
                } else if (i == temp.length - 4){
                    currStar[1] = temp[i];
                } else {
                    currText = temp[i] + " " + currText;
                }
            }

            currStar[0] = currText;

            //System.out.println(Arrays.toString(currStar));

            stars.add(currStar);

            currStar = new String[5];
            currText = "";

        }

        //Process the distances
        stars.remove(0);
            
        double x1, y1, z1, x2, y2, z2;

        for(int i = 0; i < stars.size(); i++){
            for(int j = i + 1; j < stars.size(); j++){
                x1 = Double.parseDouble(stars.get(i)[2]);
                y1 = Double.parseDouble(stars.get(i)[3]);
                z1 = Double.parseDouble(stars.get(i)[4]);
                x2 = Double.parseDouble(stars.get(j)[2]);
                y2 = Double.parseDouble(stars.get(j)[3]);
                z2 = Double.parseDouble(stars.get(j)[4]);

                dist.add(pythagoreas(x1, y1, z1, x2, y2, z2));
            }
        }

        //System.out.println(dist);
        double smallest = dist.get(0);

        for(double d : dist){
            if(d < smallest){
                smallest = d;
            }
        }

        System.out.println("Smallest is: " + smallest);
    
    }

    public static double pythagoreas(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    
}
