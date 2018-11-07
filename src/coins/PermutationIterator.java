package coins;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PermutationIterator implements Iterator {
    private final boolean[] comb; // Indica los elementos seleccionados
    private boolean hasNext;
    
    public PermutationIterator(int size) {
        hasNext = size != 0;
        comb = new boolean[size];
    }
    
    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public boolean[] next() {
        if(!hasNext) {
            throw new NoSuchElementException(
                    "No quedan más permutaciones posibles");
        }
        
        // Generamos una nueva permutación
        for (int i = 0; i < comb.length; i++) {
            if (!comb[i]) {
                comb[i] = true;
                break;
            }
            comb[i] = false;
        }
        
        // Comprobamos que el array de permutaciones sigue teniendo espacio
        hasNext = false;
        for (Boolean b : comb) {
            if (b == false) {
                hasNext = true;
                break;
            }
        }
        
        return comb;
    }
}