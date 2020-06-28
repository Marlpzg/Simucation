package main.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Saving {

    private static final String fileName = "simuData.txt";

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
                content = encrypt(content);
                Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static String load(){
        Path currentRelativePath = Paths.get("");
        String directory = currentRelativePath.toAbsolutePath().toString();

        Path path = Paths.get(directory, fileName);

        try {
            List<String> list = Files.readAllLines(path);
            if (list.size()>0)
                try {
                    return decrypt(list.get(0));
                }catch (Exception e){
                    return null;
                }
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
            case "5":
                return "mefSteps";
            case "6":
                return "mefSteps";
            case "7":
                return "mefSteps";
            case "8":
                return "mefSteps";
            case "9":
                return "mefSteps";
            case "10":
                return "defSteps";
            case "11":
                return "defSteps";
            case "12":
                return "defSteps";
            case "13":
                return "defSteps";
            case "14":
                return "defSteps";
            case "15":
                return "defSteps";
            case "16":
                return "defSteps";
            case "17":
                return "defSteps";
            case "18":
                return "defSteps";
            case "19":
                return "assembly";
            case "20":
                return "assembly";
            case "21":
                return "assembly";
            case "22":
                return "assembly";
            case "23":
                return "conditions";
            case "24":
                return "conditions";
            case "25":
                return "conditions";
            case "26":
                return "conditions";
            case "27":
                return "end";
            default:
                return null;
        }
    }

    public static void delete(){
        Path currentRelativePath = Paths.get("");
        String directory = currentRelativePath.toAbsolutePath().toString();
        Path path = Paths.get(directory, fileName);

        try {
            new PrintWriter(path.toString());
        }catch (Exception e){
            System.out.println("Error");
        }

    }

    private static String encrypt(String value){
        return "167854235982485176"+value+"512354951235412657519";
    }

    private static String decrypt(String value){
        String nv = value.substring(18);
        return nv.substring(0, nv.length() - 21);
    }

}
