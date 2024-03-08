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
            Scanner input = new Scanner(new File("BusyMoonRovers/testinput.txt"));
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

        ArrayList<String[]> distances = new ArrayList<String[]>();
        String[] curr;

        //Process the inputs of base distances into an organized structure
        for(int i = 0; i < in.size(); i++){
            if(!in.get(i).equals("")){
                curr = in.get(i).split("\\s+");

                if(i == 0){
                    String[] temp = new String[curr.length + 1];
                    temp[0] = "";

                    int counter = 1;

                    for(String s : curr){
                        temp[counter] = s;
                        counter++;
                    }

                    curr = temp;
                }

            } else {
                break;
            }
            /* 
            //Debugging
            for(int j = 0; j < curr.length; j++){
                System.out.print(curr[j] + " " );
            }
            System.out.println();
            */
        }

        //Process the route
        String[] route;

        int routeIndex = in.indexOf("") + 1;

        //System.out.println(in.get(routeIndex));

        String routes = in.get(routeIndex);
        routes = routes.substring(routes.indexOf(":") + 2);
        route = routes.split("->");

        System.out.println(routes);
    }

    

    
}
