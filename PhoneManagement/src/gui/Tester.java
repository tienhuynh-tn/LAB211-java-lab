package gui;

import java.util.Scanner;
import data.Phone;
import java.io.IOException;
import manager.ListOfPhone;

public class Tester {
    public static void main(String[] args) {
        int choice = 0;
        boolean stillAdd;
        Scanner sc = new Scanner(System.in);
        
        ListOfPhone listOfPhone = new ListOfPhone();
        String fileName = "PhoneInformation.txt";
        
        do {            
            System.out.println("----------------------------------------------------------------");
            System.out.println("Welcome to HKT Store - @ 2021 by <SE151104 - Huỳnh Lê Thủy Tiên>");
            System.out.println("Select the options below: ");
            System.out.println("1. Load data from file");
            System.out.println("2. Add a phone");
            System.out.println("3. Search a phone by model");
            System.out.println("4. Remove the phone by model");
            System.out.println("5. Print the phone list in the descending order of Model");
            System.out.println("6. Save data to file");
            System.out.println("7. Quit");
            System.out.println("----------------------------------------------------------------");
            System.out.print("Your choice is: ");
            
            //try catch for input of choice
            do {                
                try {
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if (choice <= 0 || choice > 7) {
                        throw new Exception();
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Add another choice: ");
                    stillAdd = true;
                }
            } while (stillAdd);
                
            switch (choice) {
                case 1: //Load data from file
                    listOfPhone.readFile(fileName);
                    System.out.println("Added success!");
                    System.out.print("Press enter to go back main menu!");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------
                case 2: //Thêm một điện thoại mới vào danh sách
                    String statusYesNo = "";   
                    
                    //Nhập thông tin điện thoại
                    Phone phoneInformation = new Phone();
                    phoneInformation.addPhoneInformation();
                    
                    if (listOfPhone.addNewPhoneToList(phoneInformation)) System.out.println("Added success!");
                    else System.out.println("Added fail!");
                    
                    //Người dùng chọn nhập tiếp một điện thoại khác hoặc quay lại menu chính
                    System.out.print("Continue adding another phone? (Yes or no) ");
                    do {                            
                        try {
                            sc = new Scanner(System.in);
                            statusYesNo = sc.nextLine();
                            if (!statusYesNo.equalsIgnoreCase("yes") && !statusYesNo.equalsIgnoreCase("no"))
                                throw new Exception();
                            stillAdd = false;
                        } catch (Exception e) {
                            System.out.print("Just yes or no: ");
                            stillAdd = true;
                        }
                    } while (stillAdd);

                    //Kiểm tra xem người dùng chọn nhập tiếp điện thoại khác hay quay lại menu chính
                    while (statusYesNo.equalsIgnoreCase("yes")) {                            
                        Phone phoneInformation1 = new Phone();
                        phoneInformation1.addPhoneInformation();
                        
                        if (listOfPhone.addNewPhoneToList(phoneInformation)) System.out.println("Added success!");
                        else System.out.println("Added fail!");
                        
                        
                        System.out.print("Continue adding another phone? (Yes or no) ");
                        do {                                    
                            try {
                                sc = new Scanner(System.in);
                                statusYesNo = sc.nextLine();
                                if (!statusYesNo.equalsIgnoreCase("yes") && !statusYesNo.equalsIgnoreCase("no"))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Just yes or no: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);
                    } 
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------                
                case 3: //Tìm kiếm điện thoại dựa trên model
                    if (listOfPhone.checkListEmpty()) {
                    } else {
                        String modelWantSearch = "";

                        //Nhập model muốn tìm kiếm trong danh sách
                        System.out.print("Add model of phone that you want to search: ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                modelWantSearch = sc.nextLine();
                                modelWantSearch = modelWantSearch.toUpperCase();
                                if (modelWantSearch.equals(""))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Add another model: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        //Kiểm tra xem model có trong danh sách hay không. Nếu có thì xuất thông tin điện thoại tương ứng
                        if (listOfPhone.findPhoneByModel(modelWantSearch) != null) {
                            System.out.println("Found!");
                            System.out.println("The information of phone has this model are: ");
                            listOfPhone.findPhoneByModel(modelWantSearch).printOutPhoneInformation();
                        } else System.out.println("This phone's model does not exist!");  

                        //Cho người dùng chọn tìm kiếm model tiếp tục hoặc quay trở lại menu
                        String statusYesNo1 = "";
                        System.out.print("Continue searching another phone's model? (yes or no) ");
                        do {                        
                            try {
                                sc = new Scanner(System.in);
                                statusYesNo1 = sc.nextLine();
                                if (!statusYesNo1.equalsIgnoreCase("yes") && !statusYesNo1.equalsIgnoreCase("no"))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Just yes or no: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        //Nếu người dùng nhập yes thì chạy vòng lặp tìm kiếm -> nhập yes no. 
                        //Nếu người dùng nhập no thì quay lại menu chính
                        while (statusYesNo1.equalsIgnoreCase("yes")) {
                            System.out.print("Add model of phone that you want to search: ");
                            do {        
                                try {
                                    sc = new Scanner(System.in);
                                    modelWantSearch = sc.nextLine();
                                    modelWantSearch = modelWantSearch.toUpperCase();
                                    if (modelWantSearch.equals(""))
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Add another model: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);

                            if (listOfPhone.findPhoneByModel(modelWantSearch) != null) {
                                System.out.println("Found!");
                                System.out.println("The information of phone has this model are: ");
                                listOfPhone.findPhoneByModel(modelWantSearch).printOutPhoneInformation();
                            } else System.out.println("This phone's model does not exist!");

                            System.out.print("Continue searching another phone's model? (yes or no) ");
                            do {                            
                                try {
                                    sc = new Scanner(System.in);
                                    statusYesNo1 = sc.nextLine();
                                    if (!statusYesNo1.equalsIgnoreCase("yes") && !statusYesNo1.equalsIgnoreCase("no"))
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Just yes or no: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------                   
                case 4: //Xóa một điện thoại dựa trên model
                    if (listOfPhone.checkListEmpty()) {
                    } else {
                        String modelWantRemove = "";
                    
                        //Nhập model muốn remove
                        System.out.print("Add model of phone that you want to remove: ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                modelWantRemove = sc.nextLine();
                                modelWantRemove = modelWantRemove.toUpperCase();
                                if (modelWantRemove.equals(""))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Add another model: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        //remove object
                        if (listOfPhone.removePhoneByModel(modelWantRemove))
                            System.out.println("Removed success!");
                        else System.out.println("Removed fail!");

                        //cho người dùng chọn tiếp tục remove hoặc quay về main menu
                        String statusYesNo2 = "";
                        System.out.print("Continue removing another phone's model (yes or no): ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                statusYesNo2 = sc.nextLine();
                                if (!statusYesNo2.equalsIgnoreCase("yes") && !statusYesNo2.equalsIgnoreCase("no"))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Just yes or no: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        //nếu người dùng chon tiếp tục remove 
                        if (listOfPhone.checkListEmpty());
                        else {
                            while (statusYesNo2.equalsIgnoreCase("yes")) {
                                System.out.print("Add model of phone that you want to remove: ");
                                do {                            
                                    try {
                                        sc = new Scanner(System.in);
                                        modelWantRemove = sc.nextLine();
                                        modelWantRemove = modelWantRemove.toUpperCase();
                                        if (modelWantRemove.equals(""))
                                            throw new Exception();
                                        stillAdd = false;
                                    } catch (Exception e) {
                                        System.out.print("Add another model: ");
                                        stillAdd = true;
                                    }
                                } while (stillAdd);

                                if (listOfPhone.removePhoneByModel(modelWantRemove))
                                    System.out.println("Removed success!");
                                else System.out.println("Removed fail!");

                                System.out.print("Continue removing another phone's model (yes or no): ");
                                do {
                                    try {
                                        sc = new Scanner(System.in);
                                        statusYesNo2 = sc.nextLine();
                                        if (!statusYesNo2.equalsIgnoreCase("yes") && !statusYesNo2.equalsIgnoreCase("no"))
                                            throw new Exception();
                                        stillAdd = false;
                                    } catch (Exception e) {
                                        System.out.print("Just yes or no: ");
                                        stillAdd = true;
                                    }
                                } while (stillAdd);
                            }
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------                   
                case 5: //Sắp xếp danh sách điện thoại giảm dần theo model
                    if (listOfPhone.checkListEmpty()) {
                    } else {
                        listOfPhone.printListPhoneInDescendingOrder();
                        System.out.print("Press enter to go back main menu!");
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------             
                case 6: //Write data to file
                    if (listOfPhone.checkListEmpty()) {
                    } else {
                        String fileNameWant = "";
                        System.out.print("Add name of file that you want to save data to (format name.txt): ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                fileNameWant = sc.nextLine();
                                if (fileNameWant.equals(""))
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Add another name: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        if (listOfPhone.saveDataToFile(fileNameWant))
                            System.out.println("Saved success!");
                        else System.out.println("Saved fail!");
                        System.out.print("Press enter to go back main menu!");
                        try {
                            System.in.read();
                        } catch (IOException e) {
                        }
                    }
                    break;
//---------------------------------------------------------------------------------------------------------------------------------------                
                case 7: //Thoát khỏi menu chính
                    System.out.println("Quit the menu. Good bye!");
                    break;
            }
        } while (choice > 0 && choice < 7);
    }
}
