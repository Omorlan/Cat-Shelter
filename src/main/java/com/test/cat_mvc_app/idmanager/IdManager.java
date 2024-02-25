package com.test.cat_mvc_app.idmanager;


import java.io.*;

public class IdManager {
    private static final String FILENAME = "src/main/java/com/test/cat_mvc_app/idmanager/lastId.txt";
    private static int id = 0;

    public int readIdFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                id = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void writeIdToFile(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}