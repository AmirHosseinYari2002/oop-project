package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Authentication {
    private static File users = new File("src\\sample\\users.txt");
    private String userName;
    private String password;
    private static Scanner scanner = new Scanner(System.in);

    public void initUserPass(String username, String password){
        this.userName = username;
        this.password = password;
    }

//    public Player signIn(){
//        Player player = null;
//        System.out.println(InputProcessor.ANSI_PURPLE+"<<<<    Sign in panel   >>>>");
//        //initUserPass();
//        while (!checkUserPass()){
//            System.err.println("ERROR! Sign in failed because of wrong username and/or password.");
//            System.err.println("Try again...");
//            System.out.println();
//            //initUserPass();
//            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Sign in failed");
//        }
//        System.out.println(InputProcessor.ANSI_GREEN+"Signed In successfully. Welcome "+this.userName);
//        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Signed In successfully");
//        player = initPlayer(this.userName);
//        return player;
//    }

    public boolean checkUserPass(){
        try {
            Scanner fileScanner = new Scanner(users);
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                String[] userPass = line.split(",");
                if (userPass[0].equals(this.userName)){
                    return userPass[1].equals(this.password);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public Player signUp(){
//        Player player;
//        System.out.println(InputProcessor.ANSI_CYAN+"<<<<   Sign up panel   >>>>");
//        System.out.print(InputProcessor.ANSI_WHITE+"Enter your Username: ");
//        String userNameInput = scanner.nextLine();
//        if (!checkNewUsername(userNameInput)){
//            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Sign up failed");
//            System.err.println("Sign up failed! this username already exist. use another username...");
//            return null;
//        }
//        this.userName = userNameInput;
//        System.out.print(InputProcessor.ANSI_WHITE+"Enter your password: ");
//        this.password = scanner.nextLine();
//        addUser();
//        System.out.println(InputProcessor.ANSI_GREEN+"Sign up successfully. Welcome "+this.userName);
//        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Sign up successfully");
//        player = initPlayer(this.userName);
//        return player;
//    }

    public boolean checkNewUsername(String name){
        try {
            Scanner fileScanner = new Scanner(users);
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                String[] userPass = line.split(",");
                if (userPass[0].equals(name))
                    return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void addUser(){
        try {
            FileWriter fileWriter = new FileWriter(users,true);
            fileWriter.append(this.userName+","+this.password+",1,200"+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Player initPlayer(String userName){
//        Player player = null;
//        int level;
//        int coins;
//        String password;
//        try {
//            Scanner scanner = new Scanner(users);
//            while (scanner.hasNext()){
//                String line = scanner.nextLine();
//                if (line.contains(userName)){
//                    String[] parts = line.split(",");
//                    password = parts[1];
//                    level = Integer.parseInt(parts[2]);
//                    coins = Integer.parseInt(parts[3]);
//                    player = new Player(userName,password,level,coins);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return player;
//    }

    public File getUsers() {
        return users;
    }

}
