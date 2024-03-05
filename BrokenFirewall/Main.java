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
            Scanner input = new Scanner(new File("BrokenFirewall/testInput.txt"));
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
        String currName, currCommand;
        int currVal, indexColon, indexFirstSpace, indexSecondSpace;

        ArrayList<Object[]> totals = new ArrayList<Object[]>();



        for(String line : in){
            indexColon = line.indexOf(":");
            indexFirstSpace = line.indexOf(" ", indexColon);
            indexSecondSpace = line.indexOf(" ", indexFirstSpace + 1);

            currName = line.substring(0, indexColon);
            currCommand = line.substring(indexFirstSpace + 1, indexSecondSpace);
            currVal = Integer.parseInt(line.substring(indexSecondSpace + 1));

            //System.out.println("Current name: " + currName + " Current Command: " + currCommand + " Current value: " + currVal);

            if(find(totals, currName) == -1){
                Object[] temp = {(String)currName, (Integer)0};
                totals.add(temp);
                //System.out.println("Added " + currName);
            }


            if(currCommand.equals("Discount") || currCommand.equals("Rebate")){
                Object[] currItem = totals.get(find(totals, currName));

                currItem[1] = (Integer)currItem[1] - currVal;

                totals.set(find(totals, currName), currItem);

            } else {
                Object[] currItem = totals.get(find(totals, currName));

                currItem[1] = (Integer)currItem[1] + currVal;

                totals.set(find(totals, currName), currItem);
            }



        }

        /*

        //Output current values of each company [TESTING PURPOSES ONLY]
        for(Object[] o: totals){
            System.out.println(o[0] + " " + o[1]);
        }
        */

        //Find smallest
        String company = "";
        int lowestCost = (Integer)totals.get(0)[1];

        for(Object[] o: totals){
            if((Integer)o[1] < lowestCost){
                lowestCost = (Integer)o[1];
                company = (String)o[0];
            }
        }

        return "Lowest company: " + company + " with cost of $" + lowestCost;
    }

    public static int find(ArrayList<Object[]> a, String name){
        int found = -1;
        int pos = 0;

        for(Object[] o : a){
            if(o[0].equals(name)){
                found = pos;
            }
            pos++;
        }

        return found;
    }

    
}
