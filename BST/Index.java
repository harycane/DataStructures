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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
/**
 *
 * Index class used to build an index tree.
 *
 */
public class Index {
/**
 * bst instance variable of type BST used to build the BST index.
 */
private BST<Word> bst = new BST<Word>();

/**
 * buildIndex method that builds the index BST using the given fileName String.
 * @param fileName String fileName.
 * @return BST built using the given parameter.
 */
    public BST<Word> buildIndex(String fileName) {
        File file = new File(fileName);
        if (file != null) {
        Scanner scanner = null;
        Integer lines = 0;
        try {
            scanner = new Scanner(file, "latin1");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    ++lines;
                    String[] wordsFromText = line.split("\\W");

                    for (String word : wordsFromText) {
                              if (word == null) {
                                  continue;
                              }
                                if (word.matches("[a-zA-Z]+")) {

                                    Word w = new Word(word);
                                    Word exists = bst.search(w);
                                    if (exists == null) {
                                        w.setFrequency(1);
                                        w.addToIndex(lines);
                                        bst.insert(w);

                                    } else {
                                        Integer freq = exists.getFrequency();
                                        freq = freq + 1;
                                        exists.setFrequency(freq);
                                        exists.addToIndex(lines);
                                    }
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
        return bst;
    }
    /**
     * buildIndex method builds an index BST using fileName and Comparator parameters.
     * @param fileName String fileName.
     * @param comparator Comparator that defines the sorting order.
     * @return BST built using the given parameters.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        File file = new File(fileName);

        if (file != null) {
            Scanner scanner = null;
            Integer lines = 0;
            bst = new BST<Word>(comparator);

            try {
                scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        ++lines;
                        String[] wordsFromText = line.split("\\W");

                        for (String word : wordsFromText) {
                                if (word == null) {
                                    continue;
                                }
                                    if (word.matches("[a-zA-Z]+")) {
                                        if (comparator instanceof IgnoreCase) {
                                            word = word.toLowerCase();
                                        }
                                        Word w = new Word(word);


                                        Word exists = bst.search(w);
                                        if (exists == null) {
                                            w.setFrequency(1);
                                            w.addToIndex(lines);
                                            bst.insert(w);

                                        } else {
                                            Integer freq = exists.getFrequency();
                                            exists.setFrequency(++freq);
                                            exists.addToIndex(lines);


                                        }
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
        return bst;
    }
    /**
     * buildIndex method accepts ArrayList of Words and a comparator to build the
     * index BST.
     * @param list ArrayList of Words
     * @param comparator Comparator that defines a sorting order.
     * @return BST built by using the given parameters.
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        if (list != null && !list.isEmpty()) {
            bst = new BST<Word>(comparator);

                    for (Word wordObj : list) {

                                if (wordObj.getWord() == null) {
                                    continue;
                                }
                                    if (wordObj.getWord().matches("[a-zA-Z]+")) {
                                        if (comparator instanceof IgnoreCase) {
                                            wordObj.setWord(wordObj.getWord().toLowerCase());
                                        }


                                            bst.insert(wordObj);



                                    }
                                }
                        }
        return bst;
                    }

/**
 *
 * @param tree BST which has to be sorted by alphabet and then frequency.
 * @return ArrayList of Words that are first sorted by alphabets and in case
 * of a tie,by frequency in ascending order.
 */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {

       Iterator<Word> itr =  tree.iterator();
       int size = 2 * tree.getNumberOfNodes();
       ArrayList<Word> result = new ArrayList<Word>(size);

           while (itr.hasNext()) {

               result.add(itr.next());

       }
       Collections.sort(result, new AlphaFreq());
       return result;
    }
/**
 *
 * @param tree BST which has to be sorted By Frequency (descending order).
 * @return ArrayList of Words that are in descending order of Frequency.
 */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        Iterator<Word> itr =  tree.iterator();
        int size = 2 * tree.getNumberOfNodes();
        ArrayList<Word> result = new ArrayList<Word>(size);

        while (itr.hasNext()) {

            result.add(itr.next());

        }
        Collections.sort(result, new Frequency());
        return result;
        }
/**
 *
 * @param tree BST for which HighestFrequency words have to be determined.
 * @return ArrayList of highest frequency Words in the given tree.
 */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> result = new ArrayList<Word>();
        ArrayList<Word> byFreq = this.sortByFrequency(tree);

        Word high = byFreq.get(0);
        int freq = high.getFrequency();
        for (Word w : byFreq) {
            if (w.getFrequency() == freq) {
                result.add(w);
            } else {
                break;
            }
        }
        return result;
    }
}
