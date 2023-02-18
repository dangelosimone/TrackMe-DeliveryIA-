package projectJava.mainJava.control.service;

import projectJava.mainJava.sample.entity.Entity;

import java.util.Vector;
import java.util.function.Predicate;

/**
 * Interfaccia per un servizio generico per gestire le operazioni CRUD Create (creare i dati), Read o Retrieve (leggere i dati), Update (aggiornare i dati), Delete o Destroy (eliminare i dati) per un'entità.
 @param <T> La classe dell'entità per la quale il servizio è creato
 */
public interface Service<T extends Entity> {
    /**
     * Genera un ID univoco per l'entità.
     * @return un ID univoco per l'entità.
     */
    String generateID();

    /**
     * Inserisce un'entità nel database.
     * @param t l'entità da inserire nel database.
     */
    void insert(T t);
    /**
     * Seleziona tutte le entità che soddisfano una determinata condizione dal database.
     * @param condition la condizione per selezionare le entità
     * @return un Vector con le entità selezionate.
     */
    Vector<T> select(Predicate<T> condition);

    /**
     * Aggiorna un'entità nel database.
     * @param t l'entità da aggiornare nel database.
     */
    void update(T t);

    /**
     Crea un'entità a partire da una stringa.
     @param s la stringa da convertire in un'entità
     @return l'entità creata
     */
    T toEntityFromString(String s);

    /**
     Crea una stringa a partire da un'entità.
     @param t l'entità da convertire in una stringa
     @return la stringa creata
     */
    String fromEntityToString(T t);
}



