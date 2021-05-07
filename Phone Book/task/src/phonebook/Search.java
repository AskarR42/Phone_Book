package phonebook;

public class Search {

    public static int linearSearchPerson(PhoneBook phoneBook, String name) {
        Person[] people = phoneBook.getPeople();
        for (int i = 0; i < people.length; i++) {
            if (name.equals(people[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public static int jumpSearchPerson(PhoneBook sortedPhoneBook, String name) {
        Person[] people = sortedPhoneBook.getPeople();

        int currentRight = 0;
        int prevRight = 0;

        if (people.length == 0) {
            return -1;
        }

        if (name.equals(people[0].getName())) {
            return 0;
        }

        int jumpLength = (int) Math.sqrt(people.length);
        while (currentRight < people.length - 1) {
            currentRight = Math.min(people.length - 1, currentRight + jumpLength);

            if (name.compareTo(people[currentRight].getName()) <= 0) {
                break;
            }

            prevRight = currentRight;
        }

        if (currentRight == people.length - 1 && name.compareTo(people[currentRight].getName()) > 0) {
            return -1;
        }

        return backwardLinearSearch(sortedPhoneBook, name, prevRight, currentRight);
    }

    public static int binarySearch(PhoneBook sortedPhoneBook, String name) {
        Person[] people = sortedPhoneBook.getPeople();
        int left = 0;
        int right = people.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (people[mid].getName().equals(name)) {
                return mid;
            } else if (people[mid].getName().compareTo(name) > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int backwardLinearSearch(PhoneBook phoneBook, String name, int leftExc, int rightInc) {
        Person[] people = phoneBook.getPeople();
        for (int i = rightInc; i > leftExc; i--) {
            if (name.equals(people[i].getName())) {
                return i;
            }
        }
        return -1;
    }
}
