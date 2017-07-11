/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 6.
 * Building Index using BST.
 *
 * Andrew ID: hramasub.
 *
 * @author HaryKrishnan Ramasubramanian.
 */
import java.util.HashSet;
import java.util.Set;
/**
 * Word class that implements Comparable interface.
 *
 */
public class Word implements Comparable<Word> {
    /**
     * private word instance of type String used to store a word.
     */
    private String word = null;
    /**
     * private index HashSet instance of Integer type to hold the line
     * numbers of a word in the file.
     */
    private Set<Integer> index = new HashSet<Integer>();
    /**
     * private frequency instance of type Integer to keep track of the
     * frequency of a word.
     */
    private Integer frequency = 0;
/**
 * Word constructor that sets the word instance.
 * @param word1 parameter used to set the word instance.
 */
public Word(String word1) {
    this.word = word1;
}
/**
 * addToIndex method adds the line to the Set index instance.
 * @param line Interger line parameter that is added to the Set index instance.
 */
public void addToIndex(Integer line) {
    index.add(line);
}

/**
 * getIndex getter returns the Set<Integer>.
 * @return returns the Set<Integer> index instance.
 */
public Set<Integer> getIndex() {
    return index;
}
/**
 * getWord getter of the word instance.
 * @return returns the word instance.
 */
public String getWord() {
    return this.word;
}
/**
 * getFrequency getter of the frequency instance.
 * @return returns the frequency instance.
 */
public int getFrequency() {
    return this.frequency;
}
/**
 * setWord setter used to set the word instance.
 * @param word1 parameter used to set the word instance.
 */
public void setWord(String word1) {
    this.word = word1;
}
/**
 * setFrequency setter sets the frequency instance.
 * @param freq parameter used to set the frequency instance.
 */
public void setFrequency(int freq) {
    this.frequency = freq;
}
@Override
public String toString() {
    StringBuilder output = new StringBuilder();
    output.append(this.getWord()).append(" ").append(this.getFrequency()).append(" ");
    output.append(this.getIndex());

    return output.toString();
}
@Override
public int compareTo(Word o) {
    return this.getWord().compareTo(o.getWord());
}
}
