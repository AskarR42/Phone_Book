package phonebook;

import java.time.Instant;
import java.time.Duration;

public class Sort {

    public static PhoneBook bubbleSortPhoneBookAlphabetically(PhoneBook unsortedPhoneBook, Duration timeOfLinearSearch) {
        Instant start = Instant.now();
        Person[] people = unsortedPhoneBook.getPeople();

        for (int i = 0; i < people.length; i++) {
            if (timeOfLinearSearch.multipliedBy(10).compareTo(Duration.between(start, Instant.now())) < 0) {
                return null;
            }
            for (int j = 0; j < people.length - 1 - i; j++) {
                if (people[j].getName().compareTo(people[j + 1].getName()) > 0) {
                    swap(people, j, j + 1);
                }
            }
        }
        return new PhoneBook(people);
    }

    public static PhoneBook quickSortPhoneBookAlphabetically(PhoneBook unsortedPhoneBook) {
        Person[] people = unsortedPhoneBook.getPeople();
        quickSort(people, 0, people.length - 1);
        return new PhoneBook(people);
    }

    private static void quickSort(Person[] people, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(people, left, right);
            quickSort(people, left, pivotIndex - 1);
            quickSort(people, pivotIndex + 1, right);
        }
    }

    private static int partition(Person[] people, int left, int right) {
        Person pivot = people[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            if (people[i].getName().compareTo(pivot.getName()) < 0) {
                swap(people, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(people, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(Person[] people, int i, int j) {
        Person t = people[i];
        people[i] = people[j];
        people[j] = t;
    }
}
