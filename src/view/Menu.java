package view;

import entity.User;
import handle.UserHandle;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public void selectTask (Scanner scanner, ArrayList<User> users){
        System.out.println(
                " _______________\n" +
                "| Mời bạn chọn  |\n" +
                "|---------------|\n" +
                "| 1 - Đăng nhập |\n" +
                "| 2 - Đăng ký   |\n" +
                "|_______________|");
        int n = Integer.parseInt(scanner.nextLine());
        UserHandle userHandle = new UserHandle();
        switch (n){
            case 1: userHandle.logIn(scanner, users);
                break;
            case 2: users.add(userHandle.register(scanner, users));
                    selectTask(scanner, users);
                break;
        }
    }
    public  void selectTaskAfterLogIn (Scanner scanner, ArrayList<User>users, User user){
        String continues;
        do {
            System.out.println("1 - Thay đổi username\n" +
                    "2 - Thay đổi email\n" +
                    "3 - Thay đổi mật khẩu\n" +
                    "4 - Đăng xuất\n" +
                    "0 - Thoát chương trình");
            int n = Integer.parseInt(scanner.nextLine());
            UserHandle userHandle = new UserHandle();
            int exit =0;
            switch (n){
                case 1: user.setUserName(userHandle.checkUserName(scanner, users));
                    break;
                case 2: user.setEmail(userHandle.checkEmail(scanner, users));
                    break;
                case 3: user.setPassword(userHandle.checkPass(scanner));
                    break;
                case 4: selectTask(scanner, users);
                    break;
                case 0:
                    exit++;
                    break;
            }
            if (exit != 0)break;
            System.out.println("bạn muốn làm gì tiếp không Y/N");
            continues = scanner.nextLine();
        } while (continues.equalsIgnoreCase("y"));
    }
}
