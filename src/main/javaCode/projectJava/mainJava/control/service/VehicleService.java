package projectJava.mainJava.control.service;

import projectJava.mainJava.sample.table.Table;
import projectJava.mainJava.sample.table.TableProvider;
import projectJava.mainJava.sample.entity.Vehicle;

public class VehicleService extends GenericService<Vehicle> {
    public VehicleService() {
        super(TableProvider.TableName.VEHICLES);
    }

    /**
     Converti una stringa in un oggetto Vehicle.
     @param s la stringa da convertire.
     @return l'oggetto Vehicle creato.
     */
    @Override
    public Vehicle toEntityFromString(String s) {
        String[] info = s.split(Table.delimiter);
        return new Vehicle(info[0], info[1], Double.parseDouble(info[2]));
    }

    /**
     Converti un oggetto Vehicle in una stringa.
     @param vehicle l'oggetto Client da convertire.
     @return la stringa creata.
     */
    @Override
    public String fromEntityToString(Vehicle vehicle) {
        return vehicle.getID() + Table.delimiter + vehicle.getType() + Table.delimiter + vehicle.getCapacity();
    }

}
