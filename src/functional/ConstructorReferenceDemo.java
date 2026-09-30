package functional;

import java.util.*;
import java.util.stream.Collectors;

public class ConstructorReferenceDemo {
    // Record creates constructor, getters, equals, hashCode, and toString in 1 line
    record Account(String id) {}

    public static void main(String[] args) {
        List<String> ids = List.of("ACC-101", "ACC-102", "ACC-103");

        // 1. Object creation in Stream (Function: s -> new Account(s))
        List<Account> accounts = ids.stream().map(Account::new).toList();

        // 2. Target collection factory (Supplier: () -> new LinkedList<>())
        LinkedList<Account> linkedList = ids.stream()
                .map(Account::new)
                .collect(Collectors.toCollection(LinkedList::new));

        // 3. Typed array allocation (IntFunction: size -> new Account[size])
        Account[] accountArray = accounts.toArray(Account[]::new);
        System.out.println("List: " + accounts);
        System.out.println("LinkedList: " + linkedList);
        System.out.println("Array Length: " + accountArray.length);
    }
}