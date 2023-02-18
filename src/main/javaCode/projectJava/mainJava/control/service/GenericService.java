package projectJava.mainJava.control.service;

import projectJava.mainJava.sample.table.TableAdapter;
import projectJava.mainJava.sample.table.TableProvider;
import projectJava.mainJava.sample.entity.Entity;

import java.util.Vector;
import java.util.function.Predicate;

/**
 * La classe è un'astrazione di un Service, il cui scopo è quello di fornire
 * tutti i metodi necessari per inserire, leggere e modificare i dati conservati sui file
 * @param <T> Qualsiasi sottoclasse di Relation che rappresenti una tabella conservata in un file
 */
public abstract class GenericService<T extends Entity> implements Service<T> {
    protected final TableAdapter table;

    protected GenericService(TableProvider.TableName tableName) {
        table = new TableAdapter(TableProvider.getTable(tableName));
    }

    @Override
    public String generateID() {
        //generazione randomica dell'ID da associare alle entitá che lo necessitano
        String language = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final StringBuilder builder = new StringBuilder();
        /*
        a ogni ciclo del for genero un carattere e lo accodo
        alla stringa ID controllando se quel carattere non sia già stato preso.
        */
        do {
            for (int i = 0; i < 15; i++) {
                int rand = (int) (Math.random() * language.length());
                builder.append(language.charAt(rand));
            }
        } while(!select(t -> t.getID().equals(builder.toString())).isEmpty());
        return builder.toString();
    }

    @Override
    public void insert(T t) {
        if (t.getID() == null){
            t.setID(generateID());
        }
        table.insert(fromEntityToString(t));
    }

    @Override
    public Vector<T> select(Predicate<T> condition) {
        Vector<String> records = table.select((condition != null) ? data -> condition.test(toEntityFromString(data)) : null);
        Vector<T> entities = new Vector<>();
        for (String s : records)
            entities.add(toEntityFromString(s));
        return entities;
    }

    /**
     * Utilizzando il metodo select(Predicate T), seleziona tutti i record della tabella.
     * @return Un'istanza di Vector contenente gli oggetti relativi a tutti i record della tabella.
     */
    public Vector<T> selectAll() {
        Vector<String> data = table.select(null);
        Vector<T> entities = new Vector<>();
        for (String s : data)
            entities.add(toEntityFromString(s));
        return entities;
    }

    @Override
    public void update(T t) {
        table.update(data -> toEntityFromString(data).getID().equals(t.getID()), () -> fromEntityToString(t));
    }

    public T select(String id) {
        Vector<T> entities = select(t -> t.getID().equals(id));
        if (!entities.isEmpty())
            return entities.get(0);
        return null;
    }
}
