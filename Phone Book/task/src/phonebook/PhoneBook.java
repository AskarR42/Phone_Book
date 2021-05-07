package phonebook;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PhoneBook {

    private final Person[] people;
    private final int lengthOfPhoneBook;

    public PhoneBook(File phoneBookAsFile) {
        lengthOfPhoneBook = countOfLinesInFile(phoneBookAsFile);
        people = new Person[lengthOfPhoneBook];

        try (Scanner scanner = new Scanner(phoneBookAsFile)) {
            for (int i = 0; i < lengthOfPhoneBook; i++) {
                String phoneNumber = scanner.next();
                String name = scanner.nextLine().trim();
                people[i] = new Person(phoneNumber, name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + phoneBookAsFile.getName());
        }
    }

    public PhoneBook(Person[] people) {
        this.people = people.clone();
        lengthOfPhoneBook = people.length;
    }

    public Person[] getPeople() {
        return people.clone();
    }

    public int getLengthOfPhoneBook() {
        return lengthOfPhoneBook;
    }

    private int countOfLinesInFile(File file) {
        int counter = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                scanner.nextLine();
                counter++;
            }
        } catch (FileNotFoundException ignored) {}

        return counter;
    }

}
