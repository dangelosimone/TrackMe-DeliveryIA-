package projectJava.mainJava.control.service;

import projectJava.mainJava.control.service.util.ServiceProvider;
import projectJava.mainJava.sample.entity.*;
import projectJava.mainJava.sample.table.Table;
import projectJava.mainJava.sample.table.TableProvider;

import java.util.Vector;

public class PackageService extends GenericService<Collo> {
    public PackageService() {
        super(TableProvider.TableName.PACKAGES);
    }

    /**
     * Converti una stringa in un oggetto Collo.
     * @param s la stringa da convertire.
     * @return l'oggetto Collo creato.
     */
    @Override
    public Collo toEntityFromString(String s) {
        String[] info = s.split(Table.delimiter);
        ClientService clientService = (ClientService) ServiceProvider.getService(Client.class);
        Client sender = clientService.select(info[1]);
        Client receiver = clientService.select(info[2]);
        double weight = Double.parseDouble(info[3]);
        Vehicle vehicle = null;
        if (!info[4].equals("null")) {
            VehicleService vehicleService = (VehicleService) ServiceProvider.getService(Vehicle.class);
            vehicle = vehicleService.select(info[4]);
        }
        String site = info[5];
        return new Collo(info[0], sender, receiver, weight, vehicle, site);
    }

    /**
     * Converti un oggetto Collo in una stringa.
     * @param pack l'oggetto Collo da convertire.
     * @return la stringa creata.
     */
    @Override
    public String fromEntityToString(Collo pack) {
        StringBuilder builder = new StringBuilder();
        builder.append(pack.getID()).append(Table.delimiter).append(pack.getSender().getID())
                .append(Table.delimiter).append(pack.getReceiver().getID()).append(Table.delimiter)
                .append(pack.getWeight()).append(Table.delimiter);
        if (pack.getVeicolo() != null) {
            builder.append(pack.getVeicolo().getID());
            builder.append(Table.delimiter);
        }
        else if (pack.getVeicolo() == null) {
            builder.append("Veicolo non assegnato");
            builder.append(Table.delimiter);
        }
        if (pack.getSite() != null)
            builder.append(pack.getSite());
        else if (pack.getSite() == null) {
            builder.append("Stato : in fase di preparazione");
        }
        return builder.toString();
    }

    //funzione per il confronto tra id pacco inserito e quello presente nel package.csv
    private Vector<Collo> findTrackById(String code) {
        return select(pack -> pack.getID().equals(code));
    }

    public boolean track(String code) {
        Vector<Collo> codes = findTrackById(code);
        for (Collo c : codes)
            if (c.getID().equals(code))
                return true;
        return false;
    }

    public Vector<Collo> selectNotSent() {
        return select(pack -> pack.getVeicolo() == null);
    }
}

