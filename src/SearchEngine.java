import invertedIndex.HTMLParser;
import invertedIndex.QueryParser;
import sources.Constants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchEngine {

    private static ArrayList<String> fileNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //Read the folder which contains the HTML files
        File folder = new File(Constants.FOLDER_NAME);
        File[] listOfFiles = folder.listFiles();

        //Send list of files for processing
        createInvertedIndex(listOfFiles);

        //Take user input and search in the trie
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your query: ");
        String input = scanner.next();
        QueryParser.findQuery(HTMLParser.getMap(), fileNames, input.toLowerCase());
        // QueryParser.findQuery(HTMLParser.getMap(),  fileNames ,"w3d");

        //Print the map
        // HTMLParser.printMap();

    }

    //Create inverted index using trie and list
    private static void createInvertedIndex(File[] listOfFiles) throws IOException {
        HTMLParser htmlParser = new HTMLParser();
        int totalFiles = (listOfFiles != null) ? listOfFiles.length : 0;
        System.out.println("Processing Files....");
        for (int i = 0; i < totalFiles; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
                String htmlText = htmlParser.processFile(listOfFiles[i]);
                htmlParser.buildTrie(htmlText, i, totalFiles);
            }
        }
        System.out.println("All files are successfully parsed!!");
    }
}
