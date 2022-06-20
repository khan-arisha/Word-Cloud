 /**
 * WordCloudMap.java
 * @author Arisha Khan
 * Implements a Binary Search tree that maintains a list of words and their counts.
 * March 4th 2022
 */

import java.util.ArrayList;
import java.util.Collections;      // for sorting

public class WordCountMap {
    public Node root;              // root of tree

    /** 
     * Constructor
     */
    public WordCountMap(){
        root = null;                //initialize
    }

    
    private class Node {
        public String word;
        public int count;
        public Node left;
        public Node right;
    }

    /** Returns true if the specified word is contained in the WordCountMap
    * and false otherwise.
    */
    public boolean contains(String word) {
        return help(root, word);
    }
    public boolean help(Node cur, String word){

           
        if(cur == null)
        {
            return false;
        }
        int temp = word.compareTo(cur.word);  // compare current node's word to the specified word

        if(temp > 0)
        {
            return help(cur.right, word);
        } else if(temp < 0)
        {
            return help(cur.left, word);
        } else
        {
            return true;
        }
    }

    /**
    * If the specified word is already in this WordCountMap, then its
    * count is increased by one. Otherwise, the word is added to this map
    * with a count of 1.
    */
        public void incrementCount(String word) 
        {
            root = helper(root, word);
        } 

        private Node helper(Node cur, String word){
            if(cur == null){               // add new node with the word and a count of 1
                Node add = new Node();
                add.count = 1;
                add.word = word;
                return add;

            }
            int temp = word.compareTo(cur.word);  // compare current node's word to the specified word
            if(temp < 0)
            {
                cur.left = helper(cur.left, word);
            
            }else if (temp > 0)
            {
                cur.right = helper(cur.right, word);
            }else
            {
                cur.count++;                    // increment count because this is a repeated word
            }
            return cur;
        }


    /**
    * Returns a list of WordCount objects, one per word stored in this
    * WordCountMap, sorted alphabetically by word.
    */

        public ArrayList<WordCount> getWordCountsByWord() 
        {
        ArrayList<WordCount>  words = new ArrayList<WordCount> ();
        Inorder(root, words);
        return words;
        }

        /**
         * In order traversal of tree for alphabetical order.
         * @param node node to start traversal with
         * @param array list of WordCount objects
         */
        private void Inorder(Node node, ArrayList<WordCount> list)
        {
            if (node == null)
            {
                return;
            }
            /* first recur on left subtree */
            Inorder(node.left, list);

            /* then add the data in the node to a list of WordCount objects*/
            list.add(new WordCount(node.word, node.count));

            /* now, recur on the right subtree */
            Inorder(node.right, list);
        }

    /**
    * Returns an array list of WordCount objects, one per word stored in this
    * WordCountMap, sorted in decreasing order by count.
    */
    public ArrayList<WordCount> getWordCountsByCount()
    {
    ArrayList<WordCount> counts = new ArrayList<WordCount>();
    Inorder(root, counts);
    Collections.sort(counts, Collections.reverseOrder());  // Java sorts this list of WordCOunt objects in reverse order by Counts
    return counts;
    }

    
    public static void main(String[] args) {
        /**
         * Main method 
         */
        WordCountMap m= new WordCountMap();
        m.incrementCount("z");
        m.incrementCount("d");
        m.incrementCount("c");
        m.incrementCount("b");
        m.incrementCount("e");
        m.incrementCount("z");
        m.incrementCount("a");
        m.incrementCount("d");
        m.incrementCount("b");
        m.incrementCount("y");
        m.incrementCount("a");
        System.out.println(m.contains("e"));
        ArrayList<WordCount>  c = new ArrayList<WordCount> ();
        c = m.getWordCountsByWord();
        for (int i = 0; i < c.size();i++) 
        { 		      
            System.out.print(c.get(i).word+ "" + ": "); 	
    
            System.out.println(c.get(i).count);
        }  
    }
}
