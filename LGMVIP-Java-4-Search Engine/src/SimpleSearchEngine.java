import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class WebPage {
    private String title;
    private String content;

    public WebPage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

class SearchEngine {
    private Map<String, List<WebPage>> index;

    public SearchEngine() {
        index = new HashMap<>();
    }

    public void addPage(WebPage page) {
        String[] words = page.getContent().split("\\s+");
        for (String word : words) {
            word = word.toLowerCase();
            if (!index.containsKey(word)) {
                index.put(word, new ArrayList<>());
            }
            index.get(word).add(page);
        }
    }

    public List<WebPage> search(String query) {
        query = query.toLowerCase();
        if (index.containsKey(query)) {
            return index.get(query);
        } else {
            return new ArrayList<>();
        }
    }
}

public class SimpleSearchEngine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchEngine searchEngine = new SearchEngine();

        // Sample web pages
        WebPage page1 = new WebPage("Page 1", "This is the content of Page 1.");
        WebPage page2 = new WebPage("Page 2", "Page 2 contains some sample text.");
        WebPage page3 = new WebPage("Page 3", "Content for Page 3.");

        // Add pages to the search engine
        searchEngine.addPage(page1);
        searchEngine.addPage(page2);
        searchEngine.addPage(page3);

        while (true) {
            System.out.print("Enter a search query (or 'exit' to quit): ");
            String query = scanner.nextLine().trim();

            if (query.equalsIgnoreCase("exit")) {
                break;
            }

            List<WebPage> results = searchEngine.search(query);

            if (results.isEmpty()) {
                System.out.println("No results found.");
            } else {
                System.out.println("Search results:");
                for (WebPage result : results) {
                    System.out.println("- " + result.getTitle());
                }
            }
        }

        System.out.println("Goodbye!");
    }
}
