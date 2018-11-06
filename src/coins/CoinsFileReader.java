package coins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CoinsFileReader {
    /**
     * Lee de un fichero los valores de las monedas que usaremos.
     */
    public ArrayList<Integer> readFromFile(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        String aux;
        ArrayList<Integer> arr = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((aux = br.readLine()) != null) {
                arr.add(Integer.parseInt(aux));
            }
            br.close();
        }
        return arr;
    }
}
