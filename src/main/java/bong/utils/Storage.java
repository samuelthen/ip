package bong.utils;

import bong.task.Deadline;
import bong.task.Event;
import bong.task.Task;
import bong.task.Todo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
            new File(file.getParent()).mkdirs();
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (taskType) {
                    case "T":
                        list.add(new Todo(parts[2], isDone));
                        break;
                    case "D":
                        list.add(new Deadline(parts[2], parts[3], isDone));
                        break;
                    case "E":
                        list.add(new Event(parts[2], parts[3], parts[4], isDone));
                        break;
                    default:
                        System.out.println("Unrecognized bong.task format in file, skipping line: " + line);
                        break;
                }
            }
        } catch (IOException e) {
            throw new BongException("Error loading tasks from file: " + e.getMessage());
        }

        return list;
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