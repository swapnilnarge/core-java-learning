package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalClassDemo {

    // Helper method to demonstrate lazy evaluation
    private static String fetchDefaultName() {
        System.out.println("--> Calling fallback method...");
        return "Default User";
    }

    public static void main(String[] args) {
        System.out.println("=== 1. Stream & Exception Handling ===");
        List<String> names = Arrays.asList("Swapnil", "John", "Smith", "Don");

        String name = names.stream()
                .filter(str -> str.contains("a"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with requested criteria."));
        System.out.println("Found: " + name);


        System.out.println("\n=== 2. Creating Optional Containers ===");
        // Deliberately empty container
        Optional<String> obj1 = Optional.empty();
        System.out.println("obj1 isPresent: " + obj1.isPresent());

        // Value must be non-null (fails fast if null)
        String box = "Swapnil";
        Optional<String> obj2 = Optional.of(box);
        System.out.println("obj2 value: " + obj2.get());

        // Safe for null values (creates empty Optional instead of crashing)
        String brokenBox = null;
        Optional<String> obj3 = Optional.ofNullable(brokenBox);
        System.out.println("obj3 isPresent: " + obj3.isPresent());


        System.out.println("\n=== 3. orElse vs orElseGet (Lazy Evaluation) ===");
        Optional<String> activeUser = Optional.of("Swapnil");

        // .orElse() executes the fallback method eagerly even though a value exists
        System.out.println("Testing .orElse():");
        String resultEager = activeUser.orElse(fetchDefaultName());
        System.out.println("Result: " + resultEager);

        // .orElseGet() executes the supplier only if the Optional is empty
        System.out.println("\nTesting .orElseGet():");
        String resultLazy = activeUser.orElseGet(() -> fetchDefaultName());
        System.out.println("Result: " + resultLazy);


        System.out.println("\n=== 4. Safe Consumer Execution ===");
        // Executes the lambda only when the value exists (avoids null checks)
        obj2.ifPresent(val -> System.out.println("Welcome, " + val));
        obj3.ifPresent(val -> System.out.println("This won't print because obj3 is empty"));
    }
}
