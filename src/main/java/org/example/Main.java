package org.example;

import org.example.models.ModelBooks;
import org.example.models.ModelLend;
import org.example.models.ModelUsers;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Connection connection = DbHandler.connect();

        String userName;
        String email;
        String contact;

        List<ModelBooks> mybooks = DbHandler.getBooksFilter(connection,0);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name");
        userName = scanner.nextLine();
        System.out.println("Enter email");
        email = scanner.nextLine();
        System.out.println("Enter contact");
        contact = scanner.nextLine();


        ModelUsers modelUsers = new ModelUsers(-1,userName,contact,email);

        DbHandler.addUsers(modelUsers,connection);

        String date = new Date().toString();

        int userId = DbHandler.getUserIdFromUser(connection,"ishan");


        System.out.println(userId);

    }
}