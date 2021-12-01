/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package manager;

/**
 *
 * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
 */
import data.Author;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListOfAuthor {
    public static ArrayList<Author> listAuthor = new ArrayList<>();
    
    public void loadAuthorInformationFromFile() {
        String fileNameOfBook = "author.dat";
        try {
            File f = new File(fileNameOfBook); //checking the file
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Author a;
            while ((a = (Author)fo.readObject()) != null) {                
                listAuthor.add(a);
            }
            fo.close(); fi.close();
        } catch (Exception e) {
        }
    }
    
    public void printAllAuthor() {
        for (Author author : listAuthor) {
            author.printAuthorInformation();
        }
    }
    
    public static boolean findAuthorByID(String authorID) {
        for (Author authorInformation : listAuthor) {
            if (authorInformation.getAuthorID().equals(authorID))
                return true;
        }
        return false;
    }
    
    public static boolean findAuthorByName(String authorName) {
        for (Author author : listAuthor) {
            if (author.getName().equals(authorName))
                return true;
        }
        return false;
    }
    
    public static boolean findAuthorByName(String authorID, String authorName) {
        for (Author author : listAuthor) {
            if (author.getAuthorID().equals(authorID))
                if (author.getName().equals(authorName))
                    return true;
        }
        return false;
    }
    
    public static boolean deleteAuthorByName(String authorName) {
        for (Author author : listAuthor) {
            if (author.getName().equals(authorName))
                listAuthor.remove(author);
            return true;
        }
        return false;
    }
}
