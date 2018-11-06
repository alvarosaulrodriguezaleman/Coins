package coins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Coins implements Iterable {
    private ArrayList<Integer> arr; // ArrayList de valores de monedas
    
    public Coins(String path) {
        try {
            arr = new CoinsFileReader().readFromFile(path);
        } catch (IOException ex) {
            System.out.println("Can't read data from file: " + path);
            arr = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) {
        Coins c = new Coins("coins.txt");
        
        Scanner in = new Scanner(System.in);
        System.out.println("Insert the desired value:");
        int num = in.nextInt();
        
        c.showInput(); // Mostramos los valores de monedas leídos del fichero
        
        Iterator pi = c.iterator();
        boolean[] res = null; // Almacena la mejor combinación encontrada
        while (pi.hasNext()) {
            boolean[] b = (boolean[]) pi.next();
            if (c.parseCombinationSum(b) == num){
                System.out.println(c.parseCombinationSelection(b));
                res = new boolean[b.length];
                System.arraycopy(b, 0, res, 0, b.length);
            }
        }
        if (res == null) {
            System.out.println("No result was found");
        } else {
            System.out.println("-------------------------------");
            System.out.println("Best computed items selection:");
            System.out.println("Selection:\t" + c.parseCombinationSelection(res));
            System.out.println("Values: \t" + c.parseCombinationValues(res));
        }
    }
    
    /**
     * Devuelve el valor númerico de la suma de las monedas indicadas en la
     * combinación pasada por parámetro.
     */
    public int parseCombinationSum(boolean[] comb) {
        int res = 0;
        for (int i = 0; i < comb.length; i++) {
            if (comb[i]) {
                res += arr.get(i);
            }
        }
        return res;
    }
    
    /**
     * Devuelve una representación en forma de String de los valores de las
     * monedas que forman parte de la combinación pasada por parámetro.
     */
    public String parseCombinationValues(boolean[] comb) {
        String res = "";
        for (int i = 0; i < comb.length; i++) {
            if (comb[i]) {
                res += arr.get(i) + " ";
            }
        }
        return res;
    }
    
    /**
     * Devuelve una representación en forma de String de los índices del
     * ArrayList de monedas que se encuentran representados en la combinación.
     */
    public String parseCombinationSelection(boolean[] comb) {
        String res = "";
        for (int i = 0; i < comb.length; i++) {
            if (comb[i]) {
                res += i + " ";
            }
        }
        return res;
    }
    
    /**
     * Muestra por la salida estándar los valores de las monedas actuales.
     */
    public void showInput() {
        System.out.println("*** Input");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i + " => " + arr.get(i));
        }
        System.out.println("-------------------------------");
    }

    @Override
    public Iterator iterator() {
        return new PermutationIterator(arr);
    }
}

