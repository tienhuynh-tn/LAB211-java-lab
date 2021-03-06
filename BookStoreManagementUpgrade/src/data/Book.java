/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package data;

/**
 *
 * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
 */
import java.io.Serializable;
import java.util.Scanner;
import manager.ListOfBook;

public class Book implements Serializable{
    private String ISBN;
    private String bookID;
    private String title;
    private int price;
    private Author author;
    
    public Book() {
        ISBN = "";
        bookID = "";
        title = "";
        price = 0;
        author = new Author();
    }

    public Book(String ISBN, String bookID, String title, int price, Author author) {
        this.ISBN = ISBN;
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public void addBookInformation() {
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        
        System.out.print("Add book's ISBN: ");
        do {            
            try {
                sc = new Scanner(System.in);
                ISBN = sc.nextLine();
                if (ISBN.equals("") || ListOfBook.findBookByISBN(ISBN) != null)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another ISBN: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        System.out.print("Add book's ID: ");
        do {            
            try {
                sc = new Scanner(System.in);
                bookID = sc.nextLine();
                if (bookID.equals("") || ListOfBook.findBookByID(bookID) != null)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another book's ID: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        System.out.print("Add title: ");
        do {
            try {
                sc = new Scanner(System.in);
                title = sc.nextLine();
                if (title.equals(""))
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another title: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        System.out.print("Add price: ");
        do {
            try {
                sc = new Scanner(System.in);
                price = sc.nextInt();
                if (price <= 0)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another price: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        author.addAuthorInformation();
    }
    
    public void printBookInformation() {
        System.out.println("---");
        System.out.println("ISBN: " + ISBN);
        System.out.println("ID: " + bookID);
        System.out.println("Title: " + title);
        System.out.println("Price: " + price);
        author.printAuthorInformation();
        System.out.println("---");
    }
}
