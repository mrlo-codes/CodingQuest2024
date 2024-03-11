package TimeToPlayFair;
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
            Scanner input = new Scanner(new File("TimeToPlayFair/puzzledata.txt"));
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
        String eKey = in.get(0).substring(12);
        String thePhrase = in.get(2).substring(9);
    
        System.out.println(eKey);
        System.out.println(thePhrase);

        String[][] cipher = createCipher(eKey, thePhrase);

        printCipher(cipher);

        String newPhrase = generatePhrase(cipher, thePhrase);
        System.out.println();

        System.out.println("New Phrase: " + newPhrase);



    }

    static ArrayList<String> usedLetters = new ArrayList<String>();

    public static String[][] createCipher(String key, String phrase){
        String[][] result = new String[5][5];
        String currLetter = "";
        int currRow = 0, currCol = 0;

        for(int i = 0; i < key.length(); i++){
            //get letter
            currLetter = key.substring(i, i+1);

            //check if letter is a j, if so, replace with an i.
            if(currLetter.equals("j")){
                currLetter = "i";
            }

            //check if letter already exists in used letters 
            if(!usedLetters.contains(currLetter)){
                usedLetters.add(currLetter);
                result[currRow][currCol] = currLetter;
                currCol++;
                
                //move cursor to next row if column is at the end of the grid
                if(currCol == 5 && currRow < 4){
                    currRow++;
                    currCol = 0;
                }
            }
        }

        //fill remaining letters with letters from alphabet
        char currChar = 'a';

        for(int r = currRow; r < 5; r++){
            if(r != currRow){
                currCol = 0;
            }

            for(int c = currCol; c < 5; c++){
                //check if current letter is in the used letter list.
                while(usedLetters.contains("" + currChar) || currChar == 'j'){
                    currChar++;
                }

                //System.out.println("Current coord: " + r + ", " + c + " to be added with letter: " + currChar);

                result[r][c] = "" + currChar;
                currChar++;
            }
        }

        

        return result;
    }

    public static void printCipher(String[][] s){
        for(String[] r : s){
            for(String c : r){
                System.out.print("[" + c + "]");
            }
            System.out.println();
        }
    }

    public static String generatePhrase(String[][] ci, String p){
        String result = "";
        ArrayList<String[]> pairs = new ArrayList<String[]>();
        ArrayList<Integer> spaces = new ArrayList<Integer>();
        String[] currPair = new String[2];

        String curr = "";
        int pos = 0;

        for(int i = 0; i < p.length(); i++){
            curr = p.substring(i, i+1);

            if(!curr.equals(" ")){
                currPair[pos] = curr;
                pos++;
            } else {
                if(pos != 0){
                    if(!usedLetters.contains("x")){
                        currPair[1] = "x";
                        usedLetters.add("x");
                    } else {
                        if(!usedLetters.contains("q")){
                            currPair[1] = "q";
                            usedLetters.add("q");
                        }
                    }
                }
                spaces.add(i);
            }

            if(pos == 2){
                pos = 0;
                pairs.add(currPair);
                currPair = new String[2];
            }

        }

        printPairs(pairs);
        System.out.print(spaces);

        //Process and create the new phrase
        int firstR = -1, firstC = -1, secondR = -1, secondC = -1;
        String first, second, firstLoc, secondLoc;
        int currPos = 0;


        for(String[] cpair : pairs){
            if(spaces.contains(currPos)){
                System.out.println("Position " + currPos + " has a space.");
                result+= " ";
                currPos++;
            }

            first = cpair[0];
            second = cpair[1];
            firstLoc = findLetter(ci, first);
            secondLoc = findLetter(ci, second);
            System.out.print(first + ", " + second + " at positions " + firstLoc + ", " + secondLoc);

            firstR = Integer.valueOf(firstLoc.substring(0, firstLoc.indexOf(".")));
            secondR = Integer.valueOf(secondLoc.substring(0, secondLoc.indexOf(".")));
            firstC = Integer.valueOf(firstLoc.substring(firstLoc.indexOf(".") + 1));
            secondC = Integer.valueOf(secondLoc.substring(secondLoc.indexOf(".") + 1));

            

            if(firstR == secondR && firstR != -1 && secondR != -1){
                firstC--;
                secondC--;

                if(firstC == -1){
                    firstC = 4;
                }
                if(secondC == -1){
                    secondC = 4;
                }
                
                int temp = firstC;
                firstC = secondC;
                secondC = temp;

            } else if(firstC == secondC && firstC != -1 && secondC != -1){
                firstR--;
                secondR--;

                if(firstR == -1){
                    firstR = 4;
                }
                if(secondR == -1){
                    secondR = 4;
                }
                /*
                int temp = firstR;
                firstR = secondR;
                secondR = temp;
                */
                
            } 

            //System.out.print( " current: " + firstR + ", " + firstC + " " + secondR + ", " + secondC + " ");

            System.out.print(" target: " + ci[firstR][secondC] + " at " + firstR + ", " + secondC);

            result+= ci[firstR][secondC];

            currPos++;

            if(spaces.contains(currPos)){
                System.out.println("Position " + currPos + " has a space.");
                result+= " ";
                currPos++;
            }

            System.out.println(" target: " + ci[secondR][firstC] + " at " + secondR + ", " + firstC);

            result+= ci[secondR][firstC];
            currPos++;

            /* 
            

            first = cpair[1];
            second = cpair[0];
            firstLoc = findLetter(ci, first);
            secondLoc = findLetter(ci, second);

            firstR = Integer.valueOf(firstLoc.substring(0, firstLoc.indexOf(".")));
            secondR = Integer.valueOf(secondLoc.substring(0, firstLoc.indexOf(".")));
            firstC = Integer.valueOf(firstLoc.substring(firstLoc.indexOf(".") + 1));
            secondC = Integer.valueOf(secondLoc.substring(secondLoc.indexOf(".") + 1));


            if(firstR == secondR && firstR != -1 && secondR != -1){
                firstC--;
                secondC--;

                if(firstC == -1){
                    firstC = 4;
                }
                if(secondC == -1){
                    secondC = 4;
                }
            }


            if(firstC == secondC && firstC != -1 && secondC != -1){
                firstR--;
                secondR--;

                if(firstR == -1){
                    firstR = 4;
                }
                if(secondR == -1){
                    secondR = 4;
                }
            }

            result+= ci[firstR][secondC];
            currPos++;
            */

        }

        return result;
    }

    public static void printPairs(ArrayList<String[]> p){
        for(String[] s : p){
            System.out.print(Arrays.toString(s));
        }

        System.out.println();

    }

    //returns the found coordinates of letter in the format:
    // (row).(col)
    public static String findLetter(String[][] ci, String target){
        String result = "-1.-1";

        for(int r = 0; r < ci.length; r++){
            for(int c = 0; c < ci[r].length; c++){
                if(ci[r][c].equals(target)){
                    result = r + "." + c;
                    return result;
                }
            }
        }


        return result;
    }

    
}
