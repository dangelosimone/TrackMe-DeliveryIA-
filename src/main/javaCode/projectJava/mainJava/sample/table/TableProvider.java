package projectJava.mainJava.sample.table;

import java.util.HashMap;

public class    TableProvider {
    private static final HashMap<TableName, Table> tablePool;

    static {
        tablePool = new HashMap<>();
    }

    /**
     * Restituisce una tabella dal pool, creandola se non esiste.
     * @param tableName il nome della tabella da recuperare
     * @return la tabella identificata dal nome fornito
     */
    public static Table getTable(TableName tableName) {
        if (tablePool.containsKey(tableName))
            return tablePool.get(tableName);

        Table table = new Table(tableName.toString().toLowerCase() + ".csv");
        tablePool.put(tableName, table);
        return table;
    }

    /**
     * Enumerazione dei nomi delle tabelle disponibili nel pool
     */
    public enum TableName {
        CLIENTS, VEHICLES, PACKAGES, SEDE, UTENTE
    }
}
