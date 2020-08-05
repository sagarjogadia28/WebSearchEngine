# About the Project

Web search engine to convert HTML files into text files and then search for the keyword entered by the user. To convert HTML to text files, I used Jsoup which is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data

# Technologies Used

- [Java](https://java.com/en/download/)
- [Jsoup](https://jsoup.org/)

# Flow

#### Parse the HTML files
- Read the folder which contains all the HTML files
- Parse each HTML file to text file using Jsoup
- For each text file
  - Tokenize the words based on delimiter: <b> .;-^%\$#!@?&\*()\"\n\t</b>
  - Convert to lowercase
  - Each word is now a key for trie
  - If trie contains key, then increment the counter
  - Else add to trie and insert to map
  - "main" -> [ 0 | 0 | 0 | 6 ] => It means that “main” key occurs 0 times in first, second and third file and 6 times in fourth file

#### Parse the user search query

- Take user input for the keyword to search
- Get the integer array for the specific key entered by the user
- If the key exists
  - Create a max heap to get the maximum value from the top
- If the key does not exist in the trie
  - Calculate minimum distance between each key and query entered by user
  - Create a min heap to get the minimum distance from the heap
