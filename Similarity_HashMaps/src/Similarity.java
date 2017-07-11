/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 5 HashMap Similarity between documents.
 *
 * Andrew ID: hramasub.
 *
 * @author HaryKrishnan Ramasubramanian.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
/**
 * Similarity Class used to compute the similarity of documents.
 * HashMap Data Structure from Collections Framework (implementing the Map interface) is chosen
 * because it has constant runtime of O(1) for search and
 * amortized constant runtime of O(1) for insertion.
 * And by definition HashMap consists of unique keys with values mapped to it
 * which is ideal to determine the frequency of words of a document as in our case.
 * @author HaryKrishnan Ramasubramanian.
 *
 */
public class Similarity {
/**
 * freqOfWords HashMap instance to compute the frequency of words in a String and File objects.
 * HashMap Data Structure from Collections Framework (implementing the Map interface) is chosen
 * because it has constant runtime of O(1) for search and
 * amortized constant runtime of O(1) for insertion.
 * And by definition HashMap consists of unique keys with values mapped to it
 * which is ideal to determine the frequency of words of a document as in our case.
 */
    private Map<String, BigInteger> freqOfWords = new HashMap<>(16, 0.65f);
/**
 * noOfLines instance variable to calculate the no: of Lines in the file.
 */
    private int noOfLines;
/**
 * parameter constructor to add the words and their frequencies present in a String.
 * @param string containing the reference to the String object.
 */
    public Similarity(String string) {
        if (string == null || string.trim().length() == 0) {
            return;
        }
        String[] words =  string.split("\\W");

        for (String word : words) {
            if (word == null) {
                return;
            }
            if (word.matches("[a-zA-Z]+")) {

            BigInteger frequency = freqOfWords.get(word.toLowerCase());
            if (frequency == null) {
                frequency = BigInteger.ONE;
            } else {
                frequency = frequency.add(BigInteger.ONE);
            }
            freqOfWords.put(word.toLowerCase(), frequency);
        }
        }
    }
/**
 * parameter constructor to add words and their frequencies present in a file.
 * @param file file containing the reference to the File object.
 */
    public Similarity(File file) {
        if (file == null || file.length() == 0) {
            return;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file, "latin1");
                while (scanner.hasNextLine()) {
                    ++noOfLines;
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                    for (String word : wordsFromText) {
                        if (word == null) {
                            return;
                        }
                        if (word.matches("[a-zA-Z]+")) {

                        BigInteger frequency = freqOfWords.get(word.toLowerCase());
                        if (frequency == null) {
                            frequency = BigInteger.ONE;
                        } else {
                            frequency = frequency.add(BigInteger.ONE);
                        }
                        freqOfWords.put(word.toLowerCase(), frequency);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
/**
 * method to compute the no of lines in the file.
 * @return int no of lines in the file.
 */
    public int numOfLines() {
       return noOfLines;
        }
/**
 * method to compute the total no of words including duplicates in the document.
 * @return BigInteger no: of words (with duplicates) in the map.
 */
    public BigInteger numOfWords() {
        BigInteger count = BigInteger.ZERO;
        for (BigInteger val : this.freqOfWords.values()) {
            count = count.add((val));
        }
        return count;
    }
/**
 * method that computes the size of the keyset holding distinct words.
 * @return int number of distinct words.
 */
    public int numOfWordsNoDups() {
        return freqOfWords.keySet().size();

    }
/**
 * method to calculate the euclideanNorm of the frequency vector of the calling
 * object.
 * @return euclideanNorm of the the frequency vector is returned.
 */
    public double euclideanNorm() {
        BigInteger sum = BigInteger.ZERO;

        for (BigInteger frequency : this.freqOfWords.values()) {
            BigInteger val = frequency;
            val = val.pow(2);
            sum = sum.add(val);
            }
        return Math.sqrt(new BigDecimal(sum).doubleValue());

    }
/**
 *
 * @param map map instance between which and the calling object's instance, the
 * dot product has to be computed.
 * @return dot product between two frequency vectors.
 * Runtime is O(n) and not O(n^2) because dot product is computed
 * for only the common words between two documents.
 * containsKey method is O(1) and therefore total runtime is
 * limited to O(n) it takes to iterate over all frequency values of one document.
 */
    public double dotProduct(Map<String, BigInteger> map) {
        if (map == null) {
            return 0.0;
        }
        BigInteger prod = BigInteger.ZERO;
        Iterator<String> itr1 = this.freqOfWords.keySet().iterator();
        while (itr1.hasNext()) {
            String word1 = itr1.next();
            if (map.containsKey(word1)) {
                BigInteger val1 = this.freqOfWords.get(word1);
                BigInteger val2 = map.get(word1);

                prod = prod.add(val1.multiply(val2));
            }
            }

        return prod.doubleValue();
    }
/**
 *
 * @param map map instance between which and the calling object's instance, the
 * distance has to be computed.
 * @return distance between two document vectors.
 */
    public double distance(Map<String, BigInteger> map) {

        double numerator = dotProduct(map);
        if (numerator == 0) {
            return Math.PI / 2;
        }

        BigInteger sum = BigInteger.ZERO;

        for (BigInteger frequency : map.values()) {
            BigInteger val = frequency;
            val = val.pow(2);
            sum = sum.add(val);
            }
        double denominator2 = Math.sqrt(new BigDecimal(sum).doubleValue());
        double denominator1 = this.euclideanNorm();
        if (denominator1 == denominator2) {
            return 0.0;
        }
        double dist = numerator / (denominator1 * denominator2);

        return Math.acos(dist);
    }
/**
 * getMap method returns a defensive copy of the map instance of the calling object.
 * @return map instance of the calling object.
 */
    public Map<String, BigInteger> getMap() {
        Map<String, BigInteger> defensiveCopy = new HashMap<>();
        for (Map.Entry<String, BigInteger> entry : this.freqOfWords.entrySet()) {
            defensiveCopy.put(entry.getKey(), entry.getValue());
        }
        return defensiveCopy;
    }
}
