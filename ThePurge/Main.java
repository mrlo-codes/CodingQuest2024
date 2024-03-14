package ThePurge;
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

    static ArrayList<ArrayList<String[]>> folders = new ArrayList<ArrayList<String[]>>();

    public static void main(String[] args)
    {

        //Try and read in the numbers from input.txt
        try {
            Scanner input = new Scanner(new File("ThePurge/testInput.txt"));
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
        processFiles();
        folders.remove(0);
        printDirectory();

    }

    public static void processFiles(){
        ArrayList<String[]> folder = new ArrayList<String[]>();
        String[] file = new String[2];
        //File denoted by [name][size/folder]

        for(String s : in){
            if(s.contains("Folder: ")){
                folders.add(folder);
                folder = new ArrayList<String[]>();
            } else {
                file[0] = s.substring(s.indexOf("-") + 2, s.lastIndexOf(" "));
                file[1] = s.substring(s.lastIndexOf(" ") + 1);

                folder.add(file);

                file = new String[2];
            }
        }

    }

    public static void printDirectory(){
        int folderCount = 0;
        for(ArrayList<String[]> f : folders){
            System.out.println("Folder: " + folderCount);

            for(String[] fi : f){
                System.out.print("\t-");
                for(String c : fi){
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            folderCount++;
        }

    }

    

    
}
