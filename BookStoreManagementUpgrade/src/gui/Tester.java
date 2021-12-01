/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package gui;

/**
 *
 * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
 */
import data.Book;
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
            System.out.println("8. Delete author");
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
                        list.storeDataToFile();
                        System.out.println("Continue adding another book?");
                        statusYesNo = list.addStatusYesNo();
                        
                        //Kiểm tra xem người dùng chọn nhập tiếp sách khác hay quay lại menu chính
                        while (statusYesNo == 1) {                            
                            Book bookInformation1 = new Book();
                            bookInformation1.addBookInformation();
                            if (list.addNewBook(bookInformation1)) {
                                System.out.println("Added success!");
                                list.storeDataToFile();
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
                        list.storeDataToFile();
                            
                        System.out.println("Continue updating another book?");
                        statusYesNo1 = list.addStatusYesNo();

                        //Kiểm tra xem người dùng chọn update tiếp sách khác hay quay lại menu chính
                        while (statusYesNo1 == 1) {                            
                            System.out.print("Add ID of book that you want to update: ");
                            IDWantUpdate = list.addId();

                            list.updateBookByID(IDWantUpdate);
                            list.storeDataToFile();

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
                            list.storeDataToFile();
                            
                            System.out.println("Continue deleting another book?");
                            statusYesNo2 =  list.addStatusYesNo();

                            if (list.checkListEmpty()) {
                            } else {
                                while (statusYesNo2 == 1) {                            
                                    System.out.print("Add ID of book that you want to delete: ");
                                    IDWantDelete = list.addId();

                                    if (list.deleteBookByID(IDWantDelete)) {
                                        System.out.println("Deleted success!");
                                        list.storeDataToFile();
                                        
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
                        int choice1 = 0;
                        
                        System.out.println("1. Search by book name");
                        System.out.println("2. Search by author name");
                        System.out.print("Your choice: ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                choice1 = sc.nextInt();
                                if (choice1 != 1 && choice1 != 2)
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Just choose 1 or 2: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);
                        
                        if (choice1 == 1) {
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
                        } else {
                            System.out.print("Add author name that you want to find their book: ");
                            do {
                                try {
                                    sc = new Scanner(System.in);
                                    text = sc.nextLine();
                                    if (text.equals(""))
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Add another name: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);
                            list.searchAllBookByAuthorName(text);
                        }
                        
                        System.out.println("Continue searching another book?");
                        statusYesNo3 = list.addStatusYesNo();
                        while (statusYesNo3  == 1) {
                            System.out.println("1. Search by book name");
                            System.out.println("2. Search by author name");
                            System.out.print("Your choice: ");
                            do {
                                try {
                                    sc = new Scanner(System.in);
                                    choice1 = sc.nextInt();
                                    if (choice1 != 1 && choice1 != 2)
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Just choose 1 or 2: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);

                            if (choice1 == 1) {
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
                            } else {
                                System.out.print("Add author name that you want to find their book: ");
                                do {
                                    try {
                                        sc = new Scanner(System.in);
                                        text = sc.nextLine();
                                        if (text.equals(""))
                                            throw new Exception();
                                        stillAdd = false;
                                    } catch (Exception e) {
                                        System.out.print("Add another name: ");
                                        stillAdd = true;
                                    }
                                } while (stillAdd);
                                list.searchAllBookByAuthorName(text);
                                System.out.println("Continue searching another book?");
                                statusYesNo3 = list.addStatusYesNo();
                            }
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
                case 8: //delete author
                    int statusYesNo4;
                    String authorNameWantFind = "";
                    if (list.checkListEmpty()) {
                    } else {
                        System.out.print("Add author name that want to delete: ");
                        do {                            
                            try {
                                sc = new Scanner(System.in);
                                authorNameWantFind = sc.nextLine();
                                if (authorNameWantFind.equals(""))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Add another name: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);
                        if (ListOfAuthor.findAuthorByName(authorNameWantFind) == false) {
                            System.out.println("Do not have this author name in file author.dat");
                        } else {
                            if (list.findBookByAuthorName(authorNameWantFind) == null) {
                                System.out.println("Any book of this author is not existed");
                                System.out.println("This author will be deleted in author.dat file");
                                if (ListOfAuthor.deleteAuthorByName(authorNameWantFind)) {
                                    System.out.println("Deleted success!");
                                    list.storeAuthorInformationToFile();
                                }
                                else System.out.println("Deleted fail!");
                            } else {
                                System.out.println("This author has a book in the store, you cannot delete this author");
                                System.out.println("Deleted fail!");
                            }
                        }
                        System.out.println("Continue deleting another author?");
                        statusYesNo4 = list.addStatusYesNo();
                        
                        while (statusYesNo4 == 1) {
                            System.out.print("Add author name that want to delete: ");
                            do {                            
                                try {
                                    sc = new Scanner(System.in);
                                    authorNameWantFind = sc.nextLine();
                                    if (authorNameWantFind.equals(""))
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Add another name: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);
                            if (ListOfAuthor.findAuthorByName(authorNameWantFind) == false) {
                                System.out.println("Do not have this author name in file author.dat");
                            } else {
                                if (list.findBookByAuthorName(authorNameWantFind) == null) {
                                    System.out.println("Any book of this author is not existed");
                                    System.out.println("This author will be deleted in author.dat file");
                                    if (ListOfAuthor.deleteAuthorByName(authorNameWantFind)) {
                                        System.out.println("Deleted success!");
                                        list.storeAuthorInformationToFile();
                                    }
                                    else System.out.println("Deleted fail!");
                                } else {
                                    System.out.println("This author has a book in the store, you cannot delete this author");
                                    System.out.println("Deleted fail!");
                                }
                            }
                            System.out.println("Continue deleting another author?");
                            statusYesNo4 = list.addStatusYesNo();
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------
                case 9: System.out.println("Quit menu. Good bye!"); break;
            }
        } while (choice > 0 && choice < 9);
    }
}
