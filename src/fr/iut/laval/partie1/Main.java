package fr.iut.laval.partie1;

import fr.iut.laval.partie1.myClass.Address;
import fr.iut.laval.partie1.myClass.AddressBook;
import fr.iut.laval.partie1.myClass.TextFile;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Address[] addressList = new Address[10];
        AddressBook addressBook = new AddressBook();
        TextFile readingTextFile = new TextFile();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 9) {
            addressList = addressBook.checkArrayLength(addressList);
            System.out.println("\n What do you want to do ? \n 1. List all addresses \n 2. Add an addresses \n 3. Remove an address" +
                    "\n 4. Key-words search \n 5. Search by criteria \n 6. Sort list \n 7. Load a list \n 8. Save List \n 9. Quit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addressBook.readAllAddress(addressList);
                    break;
                case 2:
                    addressBook.addNewAddress(addressList);
                    break;
                case 3:
                    addressBook.removeAddress(addressList);
                    break;
                case 4:
                    addressBook.searchAddressByKeyWords(addressList);
                    break;
                case 5:
                    addressBook.searchAddressByCriteria(addressList);
                    break;
                case 6:
                    addressBook.sortAddressByOneCriteria(addressList);
                    break;
                case 7:
                    readingTextFile.loadFile(addressList);
                    break;
                case 8:
                    readingTextFile.saveFile(addressList);
                    break;
                case 9:
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
}

    /*

        ## Code Première partie, saisir et afficher 2 personnes

        Address address2 = new Address("LAVAL", "Anthony", "20", "34 mon address");
        Address address3 = Address.newAddress();

        System.out.println(address1);
        System.out.println(address2);
        System.out.println(address3);

        -------------------------------------------------------------------------------------------


     */
