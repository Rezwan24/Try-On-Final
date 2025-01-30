package com.example.freshstart.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final String FILE_NAME = System.getProperty("user.home") + File.separator + "tryon_users.txt";
    private static Map<String, String> users = new HashMap<>();

    static {
        loadUsers();
    }

    public static void addUser(String username, String password) {
        users.put(username, password);
        saveUsers();
    }

    public static boolean validateUser(String username, String password) {
        return password.equals(users.get(username));
    }

    private static void loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // File doesn't exist yet, which is fine for a new application
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add this method to get the file path for debugging
    public static String getFilePath() {
        return FILE_NAME;
    }
}

