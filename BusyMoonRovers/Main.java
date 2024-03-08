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
            Scanner input = new Scanner(new File("BusyMoonRovers/puzzledata.txt"));
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

                distances.add(curr);

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

        //Process the routes
        ArrayList<String[]> routeList = new ArrayList<String[]>();

        String[] currRoute;
        String routes;

        int routeIndex = in.indexOf("") + 1;

        //System.out.println(in.get(routeIndex));
        for(int i = routeIndex; i < in.size(); i++){
            routes = in.get(i);
            routes = routes.substring(routes.indexOf(":") + 2);
            currRoute = routes.split("->");
            routeList.add(currRoute);
        }

        /*
        for(int i = 0; i < route.length; i++){
            route[i] = route[i].trim();
        }
        */

        //Indentify the correct distances depending on routes
        int sumDist = 0;

        for(String[] s : routeList){
            sumDist += getSum(s, distances);
        }

        System.out.println(sumDist);


    }

    public static int getSum(String[] s, ArrayList<String[]> d){
        String start, dest;
        int sum = 0;
        int rowPos = -1, colPos = -1;


        for(int i = 0; i < s.length - 1; i++){
            start = s[i].trim();
            dest = s[i+1].trim();

            for(int row = 0; row < d.size(); row++){
                //System.out.println("Current: " + distances.get(row)[0] + " looking for: " + start);
                if(d.get(row)[0].equals(start)){
                    //System.out.println("Found!");
                    rowPos = row;
                    break;
                }
            }


            if(rowPos != -1){
                String[] currArray = d.get(0);

                
                for(int col = 0; col < currArray.length; col++){
                    //System.out.println("Current: " + currArray[col] + " looking for: " + dest);
                    if(currArray[col].equals(dest)){
                        //System.out.println("Found!");
                        colPos = col - 1;
                        break;
                    }
                }
            }

            
            
            if(rowPos != -1 && colPos != -1){
                System.out.println("Position Found:  " + rowPos + ", " + colPos);
                sum += Integer.parseInt(d.get(rowPos)[colPos]);
                
            } else {
                System.out.println("No matches found. " + rowPos + ", " + colPos);
            }

            

        }
        return sum;
    }

    

    
}
