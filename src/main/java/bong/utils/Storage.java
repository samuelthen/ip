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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
