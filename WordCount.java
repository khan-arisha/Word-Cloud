 /**
 * WordCount.java
 * @author Arisha Khan
 * creates an object that contains a word and its associated count.
 * March 4th 2022
 */

    public class WordCount implements Comparable<WordCount>{
    public String word;                // word associated with object
    public int count;                  // number of times the word is repeated.

     /** 
     * Constructor
     * @param word word associated with object
     * @param count number of times the word is repeated.
     */
    public WordCount (String word, int count){
        this.word = word;
        this.count = count;
    }

    /**
     * implements a compareTo method.
     * sort by counts using the comparable interface.
     * @param other the object to sort
     */
    @Override
    public int compareTo(WordCount other) {
        return Integer.compare(this.count, other.count);
    }
}
