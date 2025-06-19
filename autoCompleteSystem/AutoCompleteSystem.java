package autoCompleteSystem;

import java.util.*;

/**
 * A class showcasing the internal implementation of a real-world use case to return a list of suggestions returned for
 * a prefix input into a search bar by a user. E.g. - search on Google Chrome, search on YouTube, etc.
 */
public class AutoCompleteSystem {

    private final TrieNode root;
    private final Map<String, Integer> frequencyMap;

    public AutoCompleteSystem() {
        this.root = new TrieNode();
        this.frequencyMap = new HashMap<>();
    }

    /**
     * Utility for pre-loading dictionary
     * @param words - list of words to be maintained in the dictionary
     */
    public void loadDictionary(List<String> words) {
        for (String word : words) {
            insert(word);
        }
    }

    /**
     * Insert a word into the Trie and update frequency
     * @param word - word to be maintained in the dictionary
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
        node.frequency++;
        frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
    }

    /**
     * Get top 5 suggestions for a prefix
     * @param prefix - prefix input by the user in the search bar
     */
    public List<String> getSuggestions(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) return Collections.emptyList();
            node = node.children[index];
        }

        PriorityQueue<WordFrequency> top5Suggestions = new PriorityQueue<>(Comparator.comparingInt(wf -> wf.frequency));

        if (node.isEndOfWord) {
            addToHeap(top5Suggestions, new WordFrequency(prefix, frequencyMap.getOrDefault(prefix, 1)));
        }

        dfs(node, prefix, frequencyMap, top5Suggestions);

        // Convert to list and reverse for highest frequency first
        List<String> result = new ArrayList<>();
        while (!top5Suggestions.isEmpty()) {
            result.add(top5Suggestions.poll().word);
        }
        Collections.reverse(result); // because min-heap gives lowest first
        return result;
    }

    /**
     * DFS to explore words starting from current Trie node
     * @param node - Node to be visited while traversing the tree recursively
     * @param currentWord - Currently formed word during any instance at the DFS.
     * @param freqMap - Stores frequency of all the unique words present in the dictionary
     * @param heap - Stores the top 5 frequently searched words
     */
    private void dfs(TrieNode node, String currentWord, Map<String, Integer> freqMap, PriorityQueue<WordFrequency> heap) {
        for (int i = 0; i < 26; i++) {
            TrieNode child = node.children[i];
            if (child != null) {
                String nextWord = currentWord + (char) (i + 'a');
                if (child.isEndOfWord) {
                    int freq = freqMap.getOrDefault(nextWord, 1);
                    addToHeap(heap, new WordFrequency(nextWord, freq));
                }
                dfs(child, nextWord, freqMap, heap);
            }
        }
    }

    /**
     * Maintain top 5 frequent words in min-heap
     * @param heap - stores top 5 frequently searched words
     * @param wordFrequency - stores a word and it's corresponding frequency
     */
    private void addToHeap(PriorityQueue<WordFrequency> heap, WordFrequency wordFrequency) {
        if (heap.size() < 5) {
            heap.add(wordFrequency);
        } else if (heap.peek().frequency < wordFrequency.frequency) {
            heap.poll();
            heap.add(wordFrequency);
        }
    }
}
