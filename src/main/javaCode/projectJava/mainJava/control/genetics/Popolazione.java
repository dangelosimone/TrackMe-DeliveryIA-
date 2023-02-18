package projectJava.mainJava.control.genetics;

import projectJava.mainJava.sample.entity.Collo;
import projectJava.mainJava.sample.entity.Vehicle;

import java.util.Vector;

/**
 * Classe che rappresenta una popolazione di soluzioni candidate per un problema di ottimizzazione.
 * Estende la classe Vector [Cromosoma] per gestire un insieme di Cromosomi.
 */
public class Popolazione extends Vector<Cromosoma> {

    /**
     * Costruttore che inizializza la dimensione della popolazione.
     * @param dimension La dimensione della popolazione
     */
    public Popolazione(int dimension) {
        super(dimension);
    }

    /**
     * Metodo che scopre la soluzione migliore per un singolo veicolo.
     * @param vehicles Il veicolo per cui trovare la soluzione migliore
     * @param pack Il set di pacchi da caricare sul veicolo
     * @param generations Il numero di generazioni da generare
     * @return Il cromosoma che rappresenta la soluzione migliore
     */
    public Cromosoma discoverBestSolutionForSingleVehicle(Vehicle vehicles, Vector<Collo> pack, int generations) {
        clear();
        for (int i = 0; i < capacity(); i++) {
            Cromosoma solution = new Cromosoma(vehicles);
            solution.random(pack);
            add(solution);
        }

        for (int i = 0; i < generations; i++) {
            double bodySum = sumBody();
            // Crea una nuova generazione basandosi sulle soluzioni migliori di quella precedente
            for (int j = 0; j < size(); j++) {
                Cromosoma parent = pickOne(bodySum);
                Cromosoma child = (Cromosoma) parent.clone();
                child.wetsuit(pack);
                set(j, child);
            }
        }
        return findBest();
    }

    /**
     * Metodo che seleziona un cromosoma in base al suo valore di 'body'
     * @param bodySum La somma dei valori 'body' di tutti i cromosomi nella popolazione
     * @return Il cromosoma selezionato
     */
    private Cromosoma pickOne(double bodySum) {
        if (bodySum == 0)
            return get(0);
        int index = 0;
        while(bodySum > 0) {
            bodySum -= get(index++).body();
        }
        return get(index-1);
    }

    /**
     * Metodo che calcola la somma dei valori 'body' di tutti i cromosomi nella popolazione
     * @return La somma dei valori 'body'
     */
    private double sumBody() {
        double bodySum = 0;
        for (Cromosoma c : this)
            bodySum += c.body();
        return bodySum;
    }

    /**
     * Trova il cromosoma che rappresenta la soluzione migliore
     * @return ritorna il cromosoma che rappresenta la soluzione migliore dopo che é stato trovato
     */
    private Cromosoma findBest() {
        Cromosoma best = get(0);
        for (Cromosoma c : this)
            if (c.body() > best.body())
                best = c;
        return best;
    }
}
