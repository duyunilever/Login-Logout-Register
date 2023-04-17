import entity.User;
import view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("duy", "Duy.qaz", "duy@gmail.com");
        User user2 = new User("dung", "Dung.qaz", "dung@gmail.com.vn");
        users.add(user1);
        users.add(user2);
        System.out.println(users);
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.selectTask(scanner, users);
    }
}