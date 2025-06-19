package autoCompleteSystem;

public class TrieNode {

    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
    int frequency = 0; // Optional: Only used if storing frequency in TrieNode
}
