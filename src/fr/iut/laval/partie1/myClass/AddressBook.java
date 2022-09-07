package fr.iut.laval.partie1.myClass;

import java.util.Scanner;

public class AddressBook {

    //Settings
    private final Address address = new Address();
    private final Scanner scanner = new Scanner(System.in);


    // Method to list all address
    public void readAllAddress(Address[] addressList) {
        for (Address address: addressList) {
            System.out.println(address);
        }
    }

    public void addNewAddress(Address[] addressList) {
        for (int i = 0; i < addressList.length; i++) {
            if (addressList[i] == null) {

                addressList[i] = this.address.newAddress();
                break;
            }
        }
    }

    public void addNewAddressWithSettings(Address[] addressList, String firstname, String lastname, String address) {
        for (int i = 0; i < addressList.length; i++) {
            if (addressList[i] == null) {
                addressList[i] = new Address(firstname, lastname, address);
                break;
            }
        }
    }

    public void searchAddress(Address[] addressList) {
        System.out.println("Please enter your search (you can search by firstname, lastname or address) ");
        String personSearch = scanner.nextLine();
        String[] mySearch = personSearch.split(" ");
        boolean result = false;

        for (Address value : addressList) {
            if (value != null) {
                String lastname = value.getLastname();
                String firstname = value.getFirstname();
                String address = value.getAddress();
                for (String search : mySearch) {
                    if (firstname.equals(search) || lastname.equals(search) || address.equals(search)) {
                        System.out.println(value);
                        result = true;
                    }
                }
            }
        }

        if (!result) {
            System.out.println("We couldn't find the person we were looking for");
        }
    }

    public void sortAddress(Address[] addresseList) {
        // System.out.println("You want to sort in ascending (A) or descending (D) order ?");
        // String sortOrder = scanner.nextLine();
        //TODO
    }

    public void removeAddress(Address[] addressList) {
        System.out.println("Please enter information of person you wish to remove");
        String personSearch = scanner.nextLine();
        String[] mySearch = personSearch.split(" ");

        String answer = "";

        for (int i = 0; i < addressList.length; i++) {
            if (addressList[i] != null) {
                String lastname = addressList[i].getLastname();
                String firstname = addressList[i].getFirstname();
                String address = addressList[i].getAddress();
                for (String search : mySearch) {
                    if (firstname.equals(search) || lastname.equals(search) || address.equals(search)) {
                        System.out.println("The person you want to remove is " + addressList[i] + " ? Y or N");
                        answer = scanner.nextLine();
                        if ("Y".equals(answer) || "y".equals(answer)) {
                            for (int x = i; x < addressList.length -1; x++) {
                                addressList[x] = addressList[x+1];
                            }
                            System.out.println("Okay this person as been deleted");
                            break;
                        }
                        System.out.println("We keep searching");
                    }
                }
                if ("Y".equals(answer) || "y".equals(answer)) {
                    break;
                }
            }
            if (i == addressList.length -1) {
                System.out.println("We couldn't find the person we were looking for");
            }
        }
    }

    public Address[] checkArrayLength(Address[] addressList) {

        Address[] addressableTempo;

        int countCaseEmpty = 0;
        for (Address address: addressList) {
            if (address == null){
                countCaseEmpty ++;
            }
        }

        if(countCaseEmpty < 5) {
            addressableTempo = new Address[addressList.length + 5];
            for (int i = 0; i< addressList.length; i++) {
                addressableTempo[i] = addressList[i];
            }
            return addressableTempo;
        }

        if (countCaseEmpty > 10) {
            addressableTempo = new Address[addressList.length - countCaseEmpty + 10];
            for (int i = 0; i< addressableTempo.length; i++) {
               addressableTempo[i] = addressList[i];
            }
            return addressableTempo;
        }

        return addressList;
    }
}
