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
import java.util.Comparator;
/**
 *
 * IgnoreCase Class implements a comparator to compare two words
 * while ignoring their case.
 *
 */
public class IgnoreCase implements Comparator<Word> {
/**
 * compare method compares two words of the two Word obj while ignoring case.
 */
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().compareToIgnoreCase(o2.getWord());
    }

}
