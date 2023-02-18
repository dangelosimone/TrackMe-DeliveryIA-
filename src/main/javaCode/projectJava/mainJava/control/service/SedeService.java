package projectJava.mainJava.control.service;

import projectJava.mainJava.sample.entity.Site;
import projectJava.mainJava.sample.entity.Vehicle;
import projectJava.mainJava.sample.table.Table;
import projectJava.mainJava.sample.table.TableProvider;

public class SedeService extends GenericService<Site>{
    public SedeService(){super(TableProvider.TableName.SEDE);}


    /**
     * Converti una stringa in un oggetto Vehicle.
     * @param s la stringa da convertire.
     * @return l'oggetto Sede creato.
     */
    @Override
    public Site toEntityFromString(String s){
        String[] info = s.split(Table.delimiter);
        return new Site(info[0], info[1], info[2]);
    }

    /**
     * Converti un oggetto Vehicle in una stringa.
     * @param site l'oggetto Sede da convertire.
     * @return la stringa creata.
     */
    @Override
    public String fromEntityToString(Site site) {
        return site.getID() + Table.delimiter + site.getName() + Table.delimiter + site.getAddress() + Table.delimiter + site.getCity();
    }
}
