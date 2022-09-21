package fr.iut.laval.partie1.myClass;

import java.util.Scanner;

public class AddressBook {

    //Settings
    private final Address ADDRESS = new Address();
    private final Scanner SCANNER = new Scanner(System.in);


    // Method to list all address
    public void readAllAddress(Address[] addressList) {
        for (Address address: addressList) {
            System.out.println(address);
        }
    }

    public void addNewAddress(Address[] addressList) {
        for (int i = 0; i < addressList.length; i++) {
            if (addressList[i] == null) {
                addressList[i] = this.ADDRESS.newAddress();
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

    public void searchAddressByKeyWords(Address[] addressList) {
        System.out.println("Please enter your search (you can search by firstname, lastname or address) ");
        String personSearch = SCANNER.nextLine();
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
                        break;
                    }
                }
            }
        }
        isResult(result);
    }

    public void searchAddressByCriteria(Address[] addressList) {
        System.out.println("You want to search by \n 1. Firstname \n 2. Lastname \n 3. Address \n Please enter all criteria");
        String[] criteria = SCANNER.nextLine().replace(" ", "").split("");

        int nbLine = addressList.length - this.countNbNullInArray(addressList);

        String[] searchSettings = new String[criteria.length * nbLine];
        boolean result = false;

        System.out.println("Please enter your search");
        String mySearch = SCANNER.nextLine();

        for (Address value : addressList) {
            if (value != null) {
                for (String cri: criteria) {
                    for (int i = 0; i < searchSettings.length; i++) {
                        if (searchSettings[i] == null) {
                            if (cri.equals("1")) {
                                searchSettings[i] = value.getFirstname();
                                break;
                            }
                            if (cri.equals("2")) {
                                searchSettings[i] = value.getLastname();
                                break;
                            }
                            if (cri.equals("3")) {
                                searchSettings[i] = value.getAddress();
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (Address value : addressList) {
            if (value != null) {
                for (String searchSetting : searchSettings) {
                    boolean isValidResult = this.isValidAddressComparedSearch(value, searchSetting);
                    if (isValidResult) {
                        if (searchSetting.equals(mySearch)) {
                            System.out.println(value);
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        this.isResult(result);
    }

    private boolean isValidAddressComparedSearch(Address address, String search) {
        return search.equals(address.getAddress()) || search.equals(address.getLastname()) || search.equals(address.getFirstname());
    }

    private void isResult(Boolean result) {
        if (!result) System.out.println("We couldn't find the person we were looking for");
    }

    private int countNbNullInArray(Address[] addressList) {
        int nbNull = 0;
        for (Address address : addressList) {
            if (address == null) nbNull++;
        }
        return nbNull;
    }

    public void startBinarySearch(Address[] addressList) {
        System.out.println("You want to search by \n 1. Firstname \n 2. Lastname \n 3. Address");
        int criteria = SCANNER.nextInt();

        SCANNER.nextLine();

        System.out.println("Please enter your search ");
        String search = SCANNER.nextLine();

        this.sortAddressByOneCriteria(addressList, criteria, 1);

        String[] values = new String[addressList.length - this.countNbNullInArray(addressList)];
        switch (criteria) {
            case 1:
                for (int i = 0; i < addressList.length; i++) {
                    if (addressList[i] != null) values[i] = addressList[i].getFirstname();
                }
                break;
            case 2:
                for (int i = 0; i < addressList.length; i++) {
                    if (addressList[i] != null) values[i] = addressList[i].getLastname();
                }
                break;
            case 3:
                for (int i = 0; i < addressList.length; i++) {
                    if (addressList[i] != null) values[i] = addressList[i].getAddress();
                }
                break;
            default:
                System.out.println("invalid criteria");
                break;
        }

        search.replace(" ", "");
        for (String value : values) {
            value.replace(" ", "");
        }

        String resultSearch = binarySearch(addressList, values, 0, values.length, search);
        System.out.println(resultSearch);
    }

    private String binarySearch(Address[] addressList, String[] arr, int lowerElement, int hightElement, String search) {
        if (hightElement >= lowerElement && lowerElement < arr.length) {
            int mid = lowerElement + (hightElement - lowerElement) / 2;
            String test = arr[mid];
            int compare = search.compareToIgnoreCase(test);

            if (compare == 0) {
                return addressList[mid].toString();
            }

            if (arr[arr.length - 1].equals(arr[mid]) || arr[0].equals(arr[mid]) || mid == lowerElement || mid == hightElement) {
                return "No element found";
            } else {
                if (compare < 0) {
                    return binarySearch(addressList, arr, lowerElement, mid, search);
                } else {
                    return binarySearch(addressList, arr, mid, hightElement, search);
                }
            }
        }
        return "No element found";
    }
    public void sortAddressByOneCriteria(Address[] addressList, int choiceSpe, int orderSpe) {

        int choiceSortCriteria = choiceSpe;
        if (choiceSpe == 0) {
            System.out.println("You want to sort by \n 1. Firstname \n 2. Lastname \n 3. Address ");
            choiceSortCriteria = SCANNER.nextInt();
        }

        int sortChoice = orderSpe;
        if(orderSpe == 0) {
            System.out.println("You want to by \n 1. ascending order \n 2. descending order");
            sortChoice = SCANNER.nextInt();
        }


        int numberOfElementToSort = addressList.length - this.countNbNullInArray(addressList);
        String[] criteria = new String[numberOfElementToSort];

        boolean isFirstname = false;
        boolean isLastname = false;
        boolean isAddress = false;

        for (Address address : addressList) {
            for (int i = 0; i < criteria.length; i++) {
                if (criteria[i] == null) {
                    switch (choiceSortCriteria) {
                        case 1:
                            criteria[i] = address.getFirstname();
                            isFirstname = true;
                            break;
                        case 2:
                            criteria[i] = address.getLastname();
                            isLastname = true;
                            break;
                        case 3:
                            criteria[i] = address.getAddress();
                            isAddress = true;
                            break;
                        default:
                            System.out.println("Invalid input !!");
                    }
                    break;
                }
            }
        }

        String temp;

        for (int i = 0; i < numberOfElementToSort; i++) {
            for (int j = i + 1; j < numberOfElementToSort; j++) {
                assert criteria[i] != null;
                int compare = criteria[i].compareTo(criteria[j]);
                if (sortChoice == 1) {
                    if (compare > 0) {
                        temp = criteria[i];
                        criteria[i] = criteria[j];
                        criteria[j] = temp;
                    }
                } else {
                    if (compare < 0) {
                        temp = criteria[i];
                        criteria[i] = criteria[j];
                        criteria[j] = temp;
                    }
                }
            }
        }

        Address[] tempo = new Address[addressList.length];
        for (int i = 0; i < numberOfElementToSort; i++) {
            for (Address address : addressList) {
                if ((isFirstname && address.getFirstname().equals(criteria[i])) || (isLastname && address.getLastname().equals(criteria[i])) || (isAddress && address.getAddress().equals(criteria[i]))) {
                    for (int l = 0; l < tempo.length; l++){
                        if (tempo[l] == null){
                            tempo[l] = address;
                            break;
                        }
                    }
                    break;
                }
            }
        }

        System.arraycopy(tempo, 0, addressList, 0, addressList.length);
    }

    public void removeAddress(Address[] addressList) {
        System.out.println("Please enter information of person you wish to remove");
        String personSearch = SCANNER.nextLine();
        String[] mySearch = personSearch.split(" ");

        String answer = "";

        for (int i = 0; i < addressList.length; i++) {
            if (addressList[i] != null) {
                for (String search : mySearch) {
                    boolean isValidResult = this.isValidAddressComparedSearch(addressList[i], search);
                    if (isValidResult) {
                        System.out.println("The person you want to remove is " + addressList[i] + " ? Y or N");
                        answer = SCANNER.nextLine();
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

        int countCaseEmpty = this.countNbNullInArray(addressList);

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
