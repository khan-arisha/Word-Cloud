 /**
 * WordCounter.java
 * @author Arisha Khan
 * Contains main program.
 * adds words from a textfile in BST.
 * Outputs specified by parsing command line arguments.
 * March 4th 2022
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
public class WordCounter {

public static void main(String[] args) {

    File stopWords = new File("StopWords.txt");         // check for stop words
    Scanner scan = null;                                // new scanner
    try {
        scan = new Scanner(stopWords);
    } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }

    ArrayList<String> stopWordsList = new ArrayList<String>();

    while (scan.hasNextLine()) 
    {
        stopWordsList.add(scan.nextLine());             // put all stop words in a list
    }

    WordCountMap tree = new WordCountMap();             // create WordCountMap object to implement BST

    File file = new File(args[1]);                      // use the text file through command line argument
    Scanner input = null;                               // new scanner

    try {
        input = new Scanner(file);
    } catch (FileNotFoundException e) {                 //print error if file not found
        System.err.println(e);
        System.exit(1);
    }

    while(input.hasNextLine())                          // Checks if there is another line and loops through every line
    {
        scan = new Scanner(input.nextLine());
        while(scan.hasNext()){                          // Checks if there is another word and loops through every word
            String word = scan.next();

            boolean found = false;
            word = word.replaceAll("[^a-zA-Z]","");
            word = word.toLowerCase();
            for(int i = 0; i < stopWordsList.size(); i++)
            {
                if(word.equalsIgnoreCase(stopWordsList.get(i)))
                {
                    found = true;
                    break;                              // break loop if theres a stop word so we dont add it to BST
                }
            }
            if(!found){
                if(!word.equals(""))
                {
                    tree.incrementCount(word);   // if not a stop word, put it into BST
                }

            }
        }
    }
 
    //close scanners
    input.close();  
    scan.close();    

    // do the output based on the command line argument
    List<WordCount>  list = new ArrayList<WordCount> ();

    // print out a WordCount list of words and their occurrence counts in alphabetical order
    if( args[0].equals("alphabetical"))
    {
        list = tree.getWordCountsByWord();
        for (int i = 0; i < list.size();i++) 
        { 		      
            System.out.print(list.get(i).word+ "" + ": "); 	
    
            System.out.println(list.get(i).count);
        }  
    }
    
    //print out a list of words and their occurrence counts
    //sorted in decreasing order by frequency (or count)
    else if( args[0].equals("frequency"))
    {
        list = tree.getWordCountsByCount();
        for (int i = 0; i < list.size();i++) 
        { 		      
            System.out.print(list.get(i).word+ "" + ": "); 	
    
            System.out.println(list.get(i).count);
        }  
    }

    //print HTML to the screen containing a word cloud 
    else if(args[0].equals("cloud"))
    {
        list = tree.getWordCountsByCount();
        int lastIndex = Integer.parseInt(args[2]);
        if(lastIndex < list.size()){
            list = list.subList(0, lastIndex);
        } 
        System.out.println(WordCloudMaker.getWordCloudHTML("Word Cloud", list));
    }
    }
}
