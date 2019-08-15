package invertedIndex;

import org.jsoup.Jsoup;
import sources.TST;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static sources.Constants.FOLDER_NAME;

public class HTMLParser {

    private static TST<Integer> tst = new TST<>();
    private static LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>();

    //Parse HTML document into string using JSoup and return it
    public  String processFile(File file) throws IOException {
        File myFile = new File(FOLDER_NAME + "//" + file.getName());
        org.jsoup.nodes.Document doc = Jsoup.parse(myFile, "UTF-8");
        return doc.text();
    }

    /*Construct trie with all possible words and list contains number of occurrences in each file
     * Map<String , ArrayList<Integer>>
     * "is"   ->  [ 1 | 2 | 5 | 4 ]
     * "the"  ->  [ 0 | 2 | 0 | 1 ]
     * "main" ->  [ 0 | 0 | 0 | 4 ]
     */
    public void buildTrie(String text, int position, int totalFiles) {
        int i = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(text, " .;-^%$#!@?&*()\"\n\t");
        while (stringTokenizer.hasMoreTokens()) {
            String key = stringTokenizer.nextToken().toLowerCase();
            // If trie already contains the word, then increment the counter in the list
            if (tst.contains(key)) {
                int count = map.get(key).get(position);
                map.get(key).set(position, count + 1);
            } else {
                //Create new list and set 0 as the initial value
                tst.put(key, i++);
                map.put(key, new ArrayList<>(Collections.nCopies(totalFiles, 0)));
                map.get(key).set(position, 1);
            }

        }
    }

    //Print the map of word and number of occurrences
    public static void printMap() {
        for (Map.Entry<String, ArrayList<Integer>> item : map.entrySet()) {
            String key = item.getKey();
            ArrayList<Integer> value = item.getValue();
            System.out.println("Key : " + key + "\n------------------------------------------");
            for (int a : value) {
                System.out.print(a + " .. ");
            }
            System.out.println();
        }
    }

    //Return the hashmap
    public static LinkedHashMap<String, ArrayList<Integer>> getMap() {
        return map;
    }

}
