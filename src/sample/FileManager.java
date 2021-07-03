package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void addToFile(File file, String input) {
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.append(input+"\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(File file, String input) {
        try {
            ArrayList<String> text = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(file,true);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (!line.contains(input)){
                    text.add(line);
                }
            }
            FileWriter fileWriter1 = new FileWriter(file);
            fileWriter1.write("");
            fileWriter1.close();
            for (String str : text){
                fileWriter.append(str+"\n");
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replace(File file, String replace, String input) {
        try {
            ArrayList<String> text = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(file,true);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (!line.startsWith(replace)){
                    text.add(line);
                }else {
                    text.add(input);
                }
            }
            FileWriter fileWriter1 = new FileWriter(file);
            fileWriter1.write("");
            fileWriter1.close();
            for (String str : text){
                fileWriter.append(str+"\n");
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFind(File file, String input) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(input))
                    return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Player initPlayer(String userName){
        Player player = null;
        int level;
        int coins;
        String password;
        try {
            Scanner scanner = new Scanner(new Authentication().getUsers());
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.contains(userName)){
                    String[] parts = line.split(",");
                    password = parts[1];
                    level = Integer.parseInt(parts[2]);
                    coins = Integer.parseInt(parts[3]);
                    player = new Player(userName,password,level,coins);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return player;
    }
}