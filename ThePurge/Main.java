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
    static int numFoldersDeleted = 0;


    public static void main(String[] args)
    {

        //Try and read in the numbers from input.txt
        try {
            Scanner input = new Scanner(new File("ThePurge/puzzledata.txt"));
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
        

        ArrayList<String[]> currFolder;
        String[] currFile;
        int sumOfBytesDeleted = 0, currSize = 0;

        for(int i = 0; i < folders.size(); i++){
            currFolder = folders.get(i);
            
            if(currFolder != null){
                currSize = currFolder.size();
            } else {
                currSize = 0;
            }

            System.out.println("Going through folder " + i + " now with " + currSize + " items");

            if(currFolder != null){
                for(int j = 0; j < currFolder.size(); j++){
                    System.out.println("running...");
                    currFile = currFolder.get(j);

                    if(currFile[0].contains("delete") || currFile[0].contains("temporary")){
                        //check if it is a file or a folder
                        System.out.println("Processing..." + currFile[0] + " with file info " + currFile[1]);
                        if(currFile[1].indexOf("FOLDER") != -1){
                            int folderNum = Integer.parseInt(currFile[1].substring(8, currFile[1].indexOf("]")));
                            System.out.println("Getting ready to delete Folder " + folderNum);
                            sumOfBytesDeleted+= removeDirectory(folderNum);
                            currFolder.remove(j);
                            j--;
                        } else {
                            sumOfBytesDeleted+= Integer.parseInt(currFile[1]);
                            currFolder.remove(j);
                            j--;
                        }
                    }

                }
            }

        }

        printDirectory();

        System.out.println("Number of bytes deleted: " + sumOfBytesDeleted);


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
                file[0] = s.substring(s.indexOf("-") + 2, s.indexOf(" ", 3));
                file[1] = s.substring(s.indexOf(" ", 3) + 1);

                //System.out.println(file[0] + ", " + file[1]);

                folder.add(file);

                file = new String[2];
            }
        }

        folders.add(folder);

    }

    public static void printDirectory(){
        int folderCount = 0;
    
        for(ArrayList<String[]> f : folders){
            System.out.println("Folder: " + folderCount);
            if(f != null){
                for(String[] fi : f){
                    System.out.print("\t-");
                    for(String c : fi){
                        System.out.print(c + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("[EMPTY]");
            }

            folderCount++;
        }

    }

    public static int removeDirectory(int dir){
        ArrayList<String[]> currFolder = folders.get(dir);
        String[] currFile;
        int sumOfBytesDeleted = 0;

        if(currFolder != null){
            //Go through everything in the folder and add the bytes
            for(int j = 0; j < currFolder.size(); j++){
                currFile = currFolder.get(0);


                //check if it is a file or a folder
                if(currFile != null){
                    if (currFile[1].contains("FOLDER")){
                        
                        int folderNum = Integer.parseInt(currFile[1].substring(8, currFile[1].indexOf("]")));
                        System.out.println("\tGetting ready to delete Folder " + folderNum);
                        sumOfBytesDeleted+= removeDirectory(folderNum);
                        currFolder.remove(j);
                        j--;
                    
                    } else {
                        sumOfBytesDeleted+= Integer.parseInt(currFile[1]);
                        currFolder.remove(j);
                        j--;
                    }

                    
                }
            }
            System.out.println("removed Folder " + dir + " saved " + sumOfBytesDeleted + " bytes");
            folders.set(dir, null);
        }

        System.out.println("Folder " + dir + " is empty.");



        //folders.remove(dir);
        return sumOfBytesDeleted;
    }

    
}
