package misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    
    public static int[] ReadFileIntoIntArray(String path) throws FileNotFoundException, IOException {
    
    ArrayList<String> lines = ReadFileIntoStringArrayList(path);
    int[] intArray = new int[lines.size()];
    for (int i = 0; i < lines.size(); i++) {
        intArray[i] = Integer.parseInt(lines.get(i));
    }
    
    return intArray;
    }
    
    public static ArrayList<String> ReadFileIntoStringArrayList(String path) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        
        return lines;        
    }
}
