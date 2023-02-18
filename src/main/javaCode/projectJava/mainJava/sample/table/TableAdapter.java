package projectJava.mainJava.sample.table;

import java.util.Vector;
import java.util.function.Predicate;
import java.util.function.Supplier;
/**
 * Converte i valori di un hashMap che consistono di una coppia chiave valore in un
 * array di stringhe
 */
public class TableAdapter {
    private final Table table;

    public TableAdapter(Table table) {
        this.table = table;
    }

    /**
     * Inserisce un nuovo record nella tabella.
     *
     * @param data i dati da inserire
     */
    public void insert(String data) {
        table.addRecord(data);
    }

    /**
     * Seleziona tutti i record che soddisfano la condizione specificata.
     *
     * @param condition la condizione da abbinare
     * @return un vettore di record selezionati
     */
    public Vector<String> select(Predicate<String> condition) {
        return new Vector<>(table.selectRecords(condition).values());
    }

    /**
     * Aggiorna tutti i record che soddisfano la condizione specificata.
     *
     * @param condition la condizione da abbinare
     * @param newData il fornitore di nuovi dati
     */
    public void update(Predicate<String> condition, Supplier<String> newData) {
        table.updateRecords(condition, newData);
    }
}
