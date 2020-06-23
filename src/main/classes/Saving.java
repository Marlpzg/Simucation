package main.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Saving {

    public static void save(String val){
        Path currentRelativePath = Paths.get("");
        String directory = currentRelativePath.toAbsolutePath().toString();
        String fileName = "simuData.txt";

        String content = val;
        Path path = Paths.get(directory, fileName);

        int current;
        try{
            current = Integer.parseInt(load());
        }catch (Exception e){
            current = 0;
        }

        try {
            if(Integer.parseInt(content) > current) {
                new PrintWriter(path.toString());
                Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static String load(){
        Path currentRelativePath = Paths.get("");
        String directory = currentRelativePath.toAbsolutePath().toString();
        String fileName = "simuData.txt";

        Path path = Paths.get(directory, fileName);

        try {
            List<String> list = Files.readAllLines(path);
            if (list.size()>0)
                return list.get(0);
            return "";
        } catch (IOException e) {
            return null;
        }
    }

    public static String getProgress(){
        String cond = load();
        if (cond == null)
            cond = "";

        switch (cond){
            case "1":
                return "intro";
            case "2":
                return "model";
            case "3":
                return "condIntro";
            case "4":
                return "mefSteps";
            default:
                return null;
        }
    }

    public static void delete(){
        Path currentRelativePath = Paths.get("");
        String directory = currentRelativePath.toAbsolutePath().toString();
        String fileName = "simuData.txt";
        Path path = Paths.get(directory, fileName);

        try {
            new PrintWriter(path.toString());
        }catch (Exception e){
            System.out.println("Error");
        }

    }

}
