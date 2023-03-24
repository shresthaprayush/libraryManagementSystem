package org.example;

import org.example.models.ModelBooks;
import org.example.models.ModelLend;
import org.example.models.ModelUsers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    //Table Names
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_LENDING = "lending";

    //Column Names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "book_name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_ISBN = "book_code";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_ISACTIVE = "isactive";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_BookId = "bookid";
    public static final String COLUMN_USERId = "userid";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_ReturnDATE = "returndate";
    public static final String COLUMN_Active= "isactive";


    public static Connection connect(){

        Connection connect = null;

        String url = "jdbc:sqlite:src/main/resources/database/library_management.db";

        try {
            connect = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connect;
    }


    public static int getUserIdFromUser(Connection connection, String nameuser) {
        String query = "SELECT * FROM "+ TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = " + nameuser;
        List<ModelUsers> modelUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

                while(resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);

                 ModelUsers modelUsers1 = new ModelUsers(id,"","","");
                modelUsers.add(modelUsers1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return modelUsers.get(0).getId();
    }

    public List<ModelBooks> getAllBooks(Connection conn,String names){

        String query = "SELECT * FROM "+ TABLE_BOOKS + " WHERE "+COLUMN_NAME + " like %" + names + "%";

        List<ModelBooks> modelBooks = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                String author = resultSet.getString(COLUMN_AUTHOR);
                String genre = resultSet.getString(COLUMN_GENRE);
                int active = resultSet.getInt(COLUMN_Active);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return modelBooks;

    }


    public static List<ModelBooks> getBooksFilter(Connection conn,int active){

        String query = "SELECT * FROM "+ TABLE_BOOKS + " WHERE " + COLUMN_Active + " = " + active;
        List<ModelBooks> modelBooks = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                String author = resultSet.getString(COLUMN_AUTHOR);
                String genre = resultSet.getString(COLUMN_GENRE);
                String bookCode = resultSet.getString(COLUMN_ISBN);
                int isactive = resultSet.getInt(COLUMN_ISACTIVE);

                ModelBooks modelBooks1 = new ModelBooks(id,name,bookCode,author,genre,isactive);
                modelBooks.add(modelBooks1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return modelBooks;
    }


    public static void addUsers(ModelUsers modeUser, Connection connection){

        String sql = "INSERT INTO " + TABLE_USERS + "("+ COLUMN_USERNAME + "," + COLUMN_CONTACT + "," + COLUMN_EMAIL +") " +
                "VALUES(?,?,?)";

//        insert into tableName (name,contact,email) values("RAM",984321651,p@gmail.com)
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, modeUser.getUsername());
            pstmt.setString(2, modeUser.getContact());
            pstmt.setString(3, modeUser.getEmail());
            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void addLend(ModelLend modelLend, Connection connection){

        String sql = "INSERT INTO lending (user_id,date,return_date,active,book_id), values(?,?,?,?,?)";

//        insert into tableName (name,contact,email) values("RAM",984321651,p@gmail.com)
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, modelLend.getUserid());
            pstmt.setString(2, modelLend.getDate());
            pstmt.setString(3, modelLend.getReturndate());
            pstmt.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
