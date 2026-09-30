package functional;

import java.util.Arrays;
import java.util.*;
import java.util.function.*;

public class MethodReferenceDemo {

    public static void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Swapnil", "Rohit", "Aniket");

        // 1. Reference to a Static Method (Class::staticMethod)
        // Lambda: s -> MethodReferenceDemo.printUpperCase(s)
        names.forEach(MethodReferenceDemo::printUpperCase);

        // 2. Reference to an Instance Method of a Particular Object (instance::method)
        // Lambda: s -> System.out.println(s)
        names.forEach(System.out::println);

        // 3. Reference to an Instance Method of an Arbitrary Object of a Type (Class::instanceMethod)
        // Lambda: s -> s.length()
        List<Integer> lengths = names.stream()
                .map(String::length)
                .toList();
        System.out.println("Lengths: " + lengths);

        // 4. Reference to a Constructor (Class::new)
        // Lambda: () -> new ArrayList<>()
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> dynamicList = listSupplier.get();
        dynamicList.add("VaultWire");
        System.out.println("Constructed List: " + dynamicList);
    }
}
