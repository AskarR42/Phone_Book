package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Instant startOfLinearSearch = Instant.now();
        Duration timeOfLinearSearch;
        File phoneBookFile = new File("/home/askar/Downloads/PhoneBook/directory.txt");
        File peopleToFindFile = new File("/home/askar/Downloads/PhoneBook/find.txt");
        PhoneBook unsortedPhoneBook = new PhoneBook(phoneBookFile);

        try (Scanner scanner = new Scanner(peopleToFindFile)) {
            System.out.println("Start searching (linear search)...");
            int foundCount = 0;
            int totalCount = 0;
            while (scanner.hasNext()) {
                if (Search.linearSearchPerson(unsortedPhoneBook, scanner.nextLine()) != -1) {
                    foundCount++;
                }
                totalCount++;
            }
            timeOfLinearSearch = Duration.between(startOfLinearSearch, Instant.now());
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. \n", foundCount, totalCount, timeOfLinearSearch.toMinutesPart(), timeOfLinearSearch.toSecondsPart(), timeOfLinearSearch.toSecondsPart());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + peopleToFindFile.getName());
            timeOfLinearSearch = Duration.ZERO;
        }

        try (Scanner scanner = new Scanner(peopleToFindFile)) {
            System.out.println("Start searching (bubble sort + jump search)...");

            Instant startOfSorting = Instant.now();
            PhoneBook sortedPhoneBook = Sort.bubbleSortPhoneBookAlphabetically(unsortedPhoneBook, timeOfLinearSearch);
            Duration timeOfSorting = Duration.between(startOfSorting, Instant.now());

            Instant startOfSearching = Instant.now();
            int foundCount = 0;
            int totalCount = 0;
            if (sortedPhoneBook == null) {
                while (scanner.hasNext()) {
                    if (Search.linearSearchPerson(unsortedPhoneBook, scanner.nextLine()) != -1) {
                        foundCount++;
                    }
                    totalCount++;
                }
            }
            else {
                while (scanner.hasNext()) {
                    if (Search.jumpSearchPerson(sortedPhoneBook, scanner.nextLine()) != -1) {
                        foundCount++;
                    }
                    totalCount++;
                }
            }
            Duration timeOfSearching = Duration.between(startOfSearching, Instant.now());

            Duration totalTime = Duration.between(startOfSorting, Instant.now());
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. \n", foundCount, totalCount, totalTime.toMinutesPart(), totalTime.toSecondsPart(), totalTime.toMillisPart());
            System.out.printf("Sorting time: %d min. %d sec. %d ms.", timeOfSorting.toMinutesPart(), timeOfSorting.toSecondsPart(), timeOfSorting.toMillisPart());
            if (sortedPhoneBook == null) {
                System.out.println(" - STOPPED, moved to linear search");
            }
            System.out.printf("Searching time: %d min. %d sec. %d ms.\n", timeOfSearching.toMinutesPart(), timeOfSearching.toSecondsPart(), timeOfSearching.toMillisPart());
            System.out.println();
        } catch (FileNotFoundException ignored) {}

        try (Scanner scanner = new Scanner(peopleToFindFile)) {
            System.out.println("Start searching (quick sort + binary search)...");

            Instant startOfSorting = Instant.now();
            PhoneBook sortedPhoneBook = Sort.quickSortPhoneBookAlphabetically(unsortedPhoneBook);
            Duration timeOfSorting = Duration.between(startOfSorting, Instant.now());

            Instant startOfSearching = Instant.now();
            int foundCount = 0;
            int totalCount = 0;
            while (scanner.hasNext()) {
                if (Search.binarySearch(sortedPhoneBook, scanner.nextLine()) != -1) {
                    foundCount++;
                }
                totalCount++;
            }
            Duration timeOfSearching = Duration.between(startOfSearching, Instant.now());

            Duration totalTime = Duration.between(startOfSorting, Instant.now());
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. \n", foundCount, totalCount, totalTime.toMinutesPart(), totalTime.toSecondsPart(), totalTime.toMillisPart());
            System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", timeOfSorting.toMinutesPart(), timeOfSorting.toSecondsPart(), timeOfSorting.toMillisPart());
            System.out.printf("Searching time: %d min. %d sec. %d ms.\n", timeOfSearching.toMinutesPart(), timeOfSearching.toSecondsPart(), timeOfSearching.toMillisPart());
            System.out.println();
        } catch (FileNotFoundException ignored) {}

        try (Scanner scanner = new Scanner(peopleToFindFile)) {
            System.out.println("Start searching (hash table)...");

            Instant startOfCreating = Instant.now();
            HashMap<String, String> hashMap = new HashMap<>();
            Person[] people = unsortedPhoneBook.getPeople();
            for (Person p : people) {
                hashMap.put(p.getName(), p.getPhoneNumber());
            }
            Duration timeOfCreating = Duration.between(startOfCreating, Instant.now());

            Instant startOfSearching = Instant.now();
            int foundCount = 0;
            int totalCount = 0;
            while (scanner.hasNext()) {
                if (hashMap.get(scanner.nextLine()) != null) {
                    foundCount++;
                }
                totalCount++;
            }
            Duration timeOfSearching = Duration.between(startOfSearching, Instant.now());

            Duration totalTime = Duration.between(startOfCreating, Instant.now());
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. \n", foundCount, totalCount, totalTime.toMinutesPart(), totalTime.toSecondsPart(), totalTime.toMillisPart());
            System.out.printf("Creating time: %d min. %d sec. %d ms.\n", timeOfCreating.toMinutesPart(), timeOfCreating.toSecondsPart(), timeOfCreating.toMillisPart());
            System.out.printf("Searching time: %d min. %d sec. %d ms.\n", timeOfSearching.toMinutesPart(), timeOfSearching.toSecondsPart(), timeOfSearching.toMillisPart());
            System.out.println();
        } catch (FileNotFoundException ignored) {}
    }
}
