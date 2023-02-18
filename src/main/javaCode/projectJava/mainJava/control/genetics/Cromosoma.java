package projectJava.mainJava.control.genetics;

import projectJava.mainJava.sample.entity.Collo;
import projectJava.mainJava.sample.entity.Vehicle;

import java.util.List;
import java.util.Vector;

/**
 * Classe che rappresenta un cromosoma per un algoritmo genetico di tipo vehicle routing problem.
 * Un cromosoma è un insieme di pacchetti (Collo) che devono essere trasportati da un veicolo (Vehicle).
 */
public class Cromosoma extends Vector<Collo> {
    private static final double mutationRate = 0.2;

    private final Vehicle vehicle;
    private double totalWeight, body;

    /**
     Costruttore che inizializza il veicolo associato al cromosoma.
     @param vehicle veicolo associato al cromosoma.
     */
    public Cromosoma(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
        body = -1;
    }

    /**
     Inizializza il cromosoma con un insieme di pacchi selezionati casualmente.
     @param allPackages La lista di tutti i pacchi disponibili.
     */
    public void random(List<Collo> allPackages) {
        while(size() < allPackages.size() && totalWeight < vehicle.getCapacity())
            add(selectionRandom(allPackages));

        if (totalWeight > vehicle.getCapacity())
            remove(size()-1);
    }

    /**
     Seleziona un pacchetto casualmente dalla lista di tutti i pacchi disponibili.
     @param allPackages La lista di tutti i pacchi disponibili.
     @return Il pacchetto selezionato casualmente.
     */
    private Collo selectionRandom(List<Collo> allPackages) {
        Collo scelto;
        do {
            int r = (int) (Math.random() * allPackages.size());
            scelto = allPackages.get(r);
        } while(contains(scelto));
        return scelto;
    }

    /**
     Aggiunge un pacchetto al cromosoma.
     @param collo Il pacchetto da aggiungere.
     @return true se il pacchetto è stato aggiunto con successo, false altrimenti.
     */
    @Override
    public boolean add(Collo collo) {
        if (super.add(collo)) {
            totalWeight += collo.getWeight();
            return true;
        }

        return false;
    }

    /**
     Sostituisce un pacchetto del cromosoma con un altro pacchetto.
     @param index L'indice del pacchetto da sostituire.
     @param collo Il pacchetto da inserire al posto del pacchetto sostituito.
     @return Il pacchetto sostituito.
     */
    @Override
    public Collo set(int index, Collo collo) {
        Collo old = super.set(index, collo);
        totalWeight -= old.getWeight();
        totalWeight += collo.getWeight();
        return old;
    }

    /**
     Effettua la mutazione del cromosoma sostituendo un pacchetto con un altro selezionato casualmente dalla lista di tutti i pacchi disponibili.
     @param allPackages La lista di tutti i pacchi disponibili.
     */
    public void wetsuit(List<Collo> allPackages) {
        if (size() == allPackages.size())
            return;

        for (int i = 0; i < size(); i++)
            if (Math.random() < mutationRate)
                do
                    set(i, selectionRandom(allPackages));
                while (totalWeight > vehicle.getCapacity());
    }

    /**
     Rimuove un pacchetto dal cromosoma.
     @param index L'indice del pacchetto da rimuovere.
     @return Il pacchetto rimosso.
     */
    @Override
    public Collo remove(int index) {
        Collo collo = super.remove(index);
        totalWeight -= collo.getWeight();
        return collo;
    }

    /**
     Restituisce la dimensione del cromosoma.
     @return la dimensione del cromosoma.
     */
    public double body() {
        if (body == -1)
            body = size();
        return body;
    }

    /**
     Restituisce il veicolo associato al cromosoma.
     @return il veicolo associato al cromosoma.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     Restituisce il rapporto tra il peso totale dei pacchi del cromosoma e la capacità massima del veicolo.
     @return il rapporto tra il peso totale dei pacchi del cromosoma e la capacità massima del veicolo.
     */
    public double ratioWeight() {
        return totalWeight / vehicle.getCapacity();
    }

    /**
     Crea una copia del cromosoma.
     @return una copia del cromosoma.
     */
    @Override
    public Object clone() {
        Cromosoma clone = new Cromosoma(vehicle);
        clone.totalWeight = totalWeight;
        clone.addAll(this);
        return clone;
    }
}
