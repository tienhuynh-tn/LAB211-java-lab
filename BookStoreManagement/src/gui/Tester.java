package gui;

import data.Book;
import java.sql.ResultSet;
import java.util.Scanner;
import manager.ListOfAuthor;
import manager.ListOfBook;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean stillAdd = true;
        
        ListOfBook list = new ListOfBook();
        ListOfAuthor list1 = new ListOfAuthor();
        list1.loadAuthorInformationFromFile();
        //ListOfAuthor.loadAuthorInformationFromFile();
        
        do {
            System.out.println("------------");
            System.out.println("1. Load data from file to screen");
            System.out.println("2. Add new book");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Search book");
            System.out.println("6. Print out all book");
            System.out.println("7. Store data to file ");
            System.out.println("8. Search by price");
            System.out.println("9. Quit");
            System.out.println("---------------");
            System.out.print("Your choice is: ");
            
            do {
                try {
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if (choice <= 0 || choice > 9)
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Add another choice: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            switch (choice) {
                case 1: //Load data from file to screen
                    list.loadDataFromFile();
                    list.printListOfBook();
                    break;
                case 2: //Add new book
                    int statusYesNo;
                    
                    Book bookInformation = new Book();
                    bookInformation.addBookInformation();
                    
                    if (list.addNewBook(bookInformation)) {
                        System.out.println("Added success!");
                        System.out.println("Continue adding another book?");
                        statusYesNo = list.addStatusYesNo();
                        
                        //Kiểm tra xem người dùng chọn nhập tiếp sách khác hay quay lại menu chính
                        while (statusYesNo == 1) {                            
                            Book bookInformation1 = new Book();
                            bookInformation1.addBookInformation();
                            if (list.addNewBook(bookInformation1)) {
                                System.out.println("Added success!");
                                System.out.println("Continue adding another book?");
                                statusYesNo = list.addStatusYesNo();
                            }
                        }
                    }
                    else System.out.println("Added fail!");
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 3: //Update book
                    int statusYesNo1;
                    
                    if (list.checkListEmpty()) {
                    } else {
                        String IDWantUpdate = "";

                        System.out.print("Add ID of book that you want to update: ");
                        IDWantUpdate = list.addId();
                        
                        list.updateBookByID(IDWantUpdate);
                            
                        System.out.println("Continue updating another book?");
                        statusYesNo1 = list.addStatusYesNo();

                        //Kiểm tra xem người dùng chọn update tiếp sách khác hay quay lại menu chính
                        while (statusYesNo1 == 1) {                            
                            System.out.print("Add ID of book that you want to update: ");
                            IDWantUpdate = list.addId();

                            list.updateBookByID(IDWantUpdate);

                            System.out.println("Continue updating another book?");
                            statusYesNo1 = list.addStatusYesNo();
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 4: //delete book
                    int statusYesNo2;
                    
                    if (list.checkListEmpty()) {
                    } else {
                        String IDWantDelete = "";
                        System.out.print("Add ID of book that you want to delete: ");
                        IDWantDelete = list.addId();
                        
                        if (list.deleteBookByID(IDWantDelete)) {
                            System.out.println("Deleted success!");
                            
                            System.out.println("Continue deleting another book?");
                            statusYesNo2 =  list.addStatusYesNo();

                            if (list.checkListEmpty()) {
                            } else {
                                while (statusYesNo2 == 1) {                            
                                    System.out.print("Add ID of book that you want to delete: ");
                                    IDWantDelete = list.addId();

                                    if (list.deleteBookByID(IDWantDelete)) {
                                        System.out.println("Deleted success!");
                                        System.out.println("Continue deleting another book?");
                                        statusYesNo2 = list.addStatusYesNo();
                                    } else System.out.println("Deleted fail!");
                                }
                            }
                        }
                        else System.out.println("Deleted fail!");
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 5: //search book
                    if (list.checkListEmpty()) {
                    } else {
                        String text = "";
                        int statusYesNo3;

                        System.out.print("Add text you want to search from the name of the book that contains: ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                text = sc.nextLine();
                                if (text.equals(""))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Add another text: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        list.searchAllBookByText(text);
                        
                        System.out.println("Continue searching another book?");
                        statusYesNo3 = list.addStatusYesNo();
                        
                        while (statusYesNo3  == 1) {                            
                            System.out.print("Add text you want to search from the name of the book that contains: ");
                            do {
                                try {
                                    sc = new Scanner(System.in);
                                    text = sc.nextLine();
                                    if (text.equals(""))
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Add another text: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);

                            list.searchAllBookByText(text);
        
                            System.out.println("Continue searching another book?");
                            statusYesNo3 = list.addStatusYesNo();
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 6: //print out book
                    if (list.checkListEmpty()) {
                    } else list.printListOfBook();
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 7: //store data to file
                    if (list.checkListEmpty()) {
                    } else {
                        list.storeDataToFile();
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 8: //search by price
                    int priceWantSearch = 0;
                    System.out.print("Add price: ");
                    do {                        
                        try {
                            sc = new Scanner(System.in);
                            priceWantSearch = sc.nextInt();
                            if (priceWantSearch <= 0)
                                throw new Exception();
                            stillAdd = false;
                        } catch (Exception e) {
                            System.out.println("Add another price: ");
                            stillAdd = true;
                        }
                    } while (stillAdd);
                    
                    list.searchBookByPrice(priceWantSearch);
                    break;
                case 9: System.out.println("Quit menu. Good bye!"); break;
            }
        } while (choice > 0 && choice < 9);
    }
}
