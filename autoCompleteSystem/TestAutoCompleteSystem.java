package autoCompleteSystem;

import java.util.List;

public class TestAutoCompleteSystem {

    public static void main(String[] args) {
        AutoCompleteSystem system = new AutoCompleteSystem();
        system.loadDictionary(List.of("apple", "app", "application", "apply", "apex", "apt", "app", "app", "apple", "apex"));

        List<String> suggestions = system.getSuggestions("ap");
        System.out.println(suggestions); // Output - [app, apple, apex, apply, application]
    }
}
