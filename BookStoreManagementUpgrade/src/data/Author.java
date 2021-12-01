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
import manager.ListOfAuthor;

public class Author implements Serializable{
    private String authorID;
    private String name;
    
    public Author() {
        authorID = "";
        name = "";
    }

    public Author(String authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addAuthorInformation() {
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        
        System.out.print("Add author's ID: ");
        do {
            try {
                sc = new Scanner(System.in);
                authorID = sc.nextLine();
                if (authorID.equals("") || ListOfAuthor.findAuthorByID(authorID) == false)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another author's ID: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        System.out.print("Add author's name: ");
        do {
            try {
                sc = new Scanner(System.in);
                name = sc.nextLine();
                if (name.equals("") || ListOfAuthor.findAuthorByName(authorID, name) == false)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Add another name: ");
                stillAdd = true;
            }
        } while (stillAdd);
    }
    
    public void printAuthorInformation() {
        System.out.println("Author ID: " + authorID);
        System.out.println("Author name: " + name);
    }
}
