package handle;

import entity.User;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserHandle {
    public void logIn(Scanner scanner, ArrayList<User> users) {
        int check = 0;
        do {
            int checkUser = 0;
            System.out.println("Nhập username");
            String userNameInput = scanner.nextLine();
            System.out.println("Nhập password");
            String passwordInput = scanner.nextLine();
            Menu menu = new Menu();
            for (User x : users
            ) {
                if (userNameInput.equals(x.getUserName())) {
                    if (passwordInput.equals(x.getPassword())){
                        System.out.println("Chào mừng " + userNameInput + ", bạn có thể thực hiện các công việc sau:");
                        menu.selectTaskAfterLogIn(scanner, users, x);
                        check++;
                        break;
                    } else {
                        System.out.println("1- Đăng nhập lại\n" +
                                "2 - Quên mật khẩu");
                        int n = Integer.parseInt(scanner.nextLine());
                        switch (n) {
                            case 1:
                                break;
                            case 2:
                                String emailCheck = scanner.nextLine();
                                if (emailCheck.equals(x.getEmail())){
                                    checkPass(scanner);
                                } else {
                                    System.out.println("Chưa tồn tại tài khoản");
                                }
                                break;
                        }
                    }
                } else{
                    if (userNameInput.equals(x.getUserName()) && passwordInput.equals(x.getPassword())) break;
                    checkUser++;
                }
            }
            if (checkUser != 0){
                System.out.println("Kiểm tra lại UserName");
            }
        }while (check == 0);
    }

    public User register(Scanner scanner, ArrayList<User>users){
        String userNameRegister = checkUserName(scanner, users);
        String emailRegister = checkEmail(scanner, users);
        String passwordRegister = checkPass(scanner);
        System.out.println("Tạo tài khoản thành công");
        return new User(userNameRegister, passwordRegister, emailRegister);
    }

    public String checkUserName (Scanner scanner, ArrayList<User>users){
        String userNameRegister ;
        int count;
        do {
            count = 0;
            System.out.println("Nhập username mới");
            userNameRegister = scanner.nextLine();
            for (User y:users) {
                if (y.getUserName().equals(userNameRegister)){
                    count++;
                    System.out.println("Username đã tồn tại");
                }
            }
        }
        while (count != 0);
        return userNameRegister;
    }

    public String checkEmail (Scanner scanner, ArrayList<User>users){
        String emailRegister ;
        int count;
        do {
            count = 0;
            System.out.println("Nhập Email mới");
            emailRegister = scanner.nextLine();
            String EMAIL_PATTERN =
                    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            boolean x =Pattern.matches(EMAIL_PATTERN, emailRegister);
            if (x == false) {
                System.out.println("Email không đúng định dạng");
                count++;
            }
            for (User y:users) {
                if (y.getEmail().equals(emailRegister)){
                    count++;
                    System.out.println("Email đã tồn tại");
                }
            }
        }
        while (count != 0);
        return emailRegister;
    }
    public String checkPass(Scanner scanner){
        String passwordRegister;
        int count;
        do {
            count = 0;
            System.out.println("Nhập password muốn tạo");
            passwordRegister = scanner.nextLine();
            //điều kiện pass từ 7 đến 15 kí tự
            if (7 <= passwordRegister.length() && passwordRegister.length() <= 15){
                //lấy từng kí tự của Pass nhập cho vào 1 mảng có kích thước bằng số kí tự Pass
                for (int i = 0; i < passwordRegister.length(); i++) {
                    String x = String.valueOf(passwordRegister.charAt(i));
                    ArrayList<String> charactersPass = new ArrayList<>();
                    charactersPass.add(x);
                    //duyệt mảng gồm các kí tự của pass nhập so sánh với từ kí tự đó khi in hoa
//                    và có cả kí tự đặc biệt
                    for (String y: charactersPass
                    ) {
                        if (y.equals(y.toUpperCase())){
                            if (y.equals(".") || y.equals(",") || y.equals("-") || y.equals("_") || y.equals(";")){
                                count++;
                            }
                        }
                    }
                }
                if (count != 0) break;
                System.out.println("Pass phải chứa kí tự in hoa và đặc biệt");
            } else {
                System.out.println("Độ dài Password phải từ 7 đến 15 kí tự");
            }
        }
        while (count == 0);
        return passwordRegister;
    }
}
