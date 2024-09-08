package bong.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import bong.task.Deadline;
import bong.task.Event;
import bong.task.Task;
import bong.task.Todo;

/**
 * The {@code Storage} class handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws BongException If an error occurs while loading tasks from the file.
     */
    public ArrayList<Task> load() throws BongException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            createFileIfNotExists(file);
            return list;
        }
        return readTasksFromFile(file);
    }

    private void createFileIfNotExists(File file) {
        new File(file.getParent()).mkdirs();
    }

    private ArrayList<Task> readTasksFromFile(File file) throws BongException {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTaskFromLine(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            throw new BongException("Error loading tasks from file: " + e.getMessage());
        }
        return list;
    }

    private Task parseTaskFromLine(String line) throws BongException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        return switch (taskType) {
            case "T" -> new Todo(parts[2], isDone);
            case "D" -> new Deadline(parts[2], parts[3], isDone);
            case "E" -> new Event(parts[2], parts[3], parts[4], isDone);
            default -> {
                System.out.println("Unrecognized task format in file, skipping line: " + line);
                yield null;
            }
        };
    }

    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param list The {@code ArrayList} of tasks to save.
     * @throws BongException If an error occurs while saving tasks to the file.
     */
    public void save(ArrayList<Task> list) throws BongException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : list) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new BongException("Error saving tasks to file: " + e.getMessage());
        }
    }
}