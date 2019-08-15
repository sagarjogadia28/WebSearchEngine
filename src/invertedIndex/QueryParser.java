package invertedIndex;

import heap.Heapify;
import spellChecker.EditDistanceCalculator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryParser {

    /*Search for the query in the trie
     * If the query exists then return the URL
     * Else find 5 words with minimum distance
     */
    public static void findQuery(LinkedHashMap<String, ArrayList<Integer>> map, ArrayList<String> fileNames, String query) {
        ArrayList<Integer> list = map.get(query);

        //If query exists in the trie
        if (list != null) {
            // int maxValue = Collections.max(list);
            //Create a max heap to get the maximum value from the top
            Heapify maxHeap = new Heapify(false);
            maxHeap.insertElements(list);
            System.out.println("Found in\n ------------------------------");
            while (maxHeap.hasElement()) {
                int maxValue = maxHeap.removeAndGetElement();
                if (maxValue != 0)
                    System.out.println(fileNames.get(list.indexOf(maxValue)));
            }
            // int maxValue = maxHeap.getElement();
            // System.out.println("Found in : " + fileNames.get(list.indexOf(maxValue)));
        } else {
            //Calculate minimum distance from all the words and store it in list
            List<String> keys = new ArrayList<>(map.keySet());
            System.out.println("Cannot find the word in the dictionary\n Try below suggestions: ");
            System.out.println("-----------------------------");
            ArrayList<Integer> distanceArray = new ArrayList<>();

            //Calculate the minimum distance between the words
            for (Map.Entry<String, ArrayList<Integer>> item : map.entrySet()) {
                int distance = EditDistanceCalculator.editDistance(query, item.getKey());
                distanceArray.add(distance);
            }

            //Create minHeap and extract top five
            Heapify minHeap = new Heapify(true);
            minHeap.insertElements(distanceArray);
            //Suggest 5 words with minimum distance
            for (int i = 0; i < 5; i++) {

                // int minValue = Collections.min(distanceArray);
                int minValue = minHeap.removeAndGetElement();
                int minPosition = distanceArray.indexOf(minValue);
                System.out.println(keys.get(minPosition));
                distanceArray.set(minPosition, Integer.MAX_VALUE);
            }


        }
    }
}
