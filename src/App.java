import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        Set<Character> endOfSentence = new HashSet<>(Arrays.asList('.', '!', '?'));
        Map<String, Integer> map = new HashMap<>();
        int total_word_count = 0;
        int max_frequency = 0;
        String most_used_word = null;
        String last_sentence = "";
        int c;

        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            SentenceReader sentenceReader =
                    new SentenceReader(
                            new FileReader(rootPath + "/passage.txt"));
            while ((c = sentenceReader.read()) >= 0) {
                if (endOfSentence.contains((char) c)) {
                    String sentence = sentenceReader.getSentence();
                    if (sentence.length() == 0) continue;
                    String[] words = sentence.split(" ");
                    total_word_count=total_word_count+ words.length;
                    for (String word : words) {
                        //update frequency of the words
                        map.put(word, map.getOrDefault(word, 0) + 1);
                        if (map.get(word) > max_frequency) {
                            max_frequency = map.get(word);//update
                            most_used_word = word;
                            last_sentence = sentence;
                        }
                    }
                }
            }
            System.out.println("Total word count : " + total_word_count);
            System.out.println("Last Sentence that contains the word \""+most_used_word+"\" : "+last_sentence);
            System.out.println("Top 10 words used sorted by frequency\n--------------");
            Map<String, Integer> sortByFrequency = map.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(10)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            System.out.println(sortByFrequency);

            System.out.println("\nTop 10 words used sorted alphabetically\n--------------");
            List<String> sortedAlphabetically=sortByFrequency.entrySet()
                    .stream()
                    .map(Map.Entry::getKey)
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println(sortedAlphabetically);

            sentenceReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
