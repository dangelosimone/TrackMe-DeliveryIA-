package projectJava.mainJava.control.service.util;

import projectJava.mainJava.control.service.*;
import projectJava.mainJava.sample.entity.*;

import java.util.HashMap;

public abstract class ServiceProvider {
    private static final HashMap<Class<? extends Entity>, GenericService<? extends Entity>> servicePool = new HashMap<>();

    /**
     La classe ServiceProvider fornisce un pool di servizi per le diverse entità utilizzate nel sistema.
     @param type la classe dell'entità per la quale si desidera ottenere un servizio.
     @return un'istanza del servizio richiesto.
     */
    public static GenericService<? extends Entity> getService(Class<? extends Entity> type) {
        if (servicePool.containsKey(type))
            return servicePool.get(type);

        GenericService<? extends Entity> service = createService(type);
        servicePool.put(type, service);
        return service;
    }

    /**
     Crea un'istanza del servizio richiesto in base al tipo di entita.
     @param type la classe dell'entità per la quale si desidera creare un servizio.
     @return un'istanza del servizio richiesto.
     */
    private static GenericService<? extends Entity> createService(Class<? extends Entity> type) {
        if (Client.class.equals(type))
            return new ClientService();
        else if (Collo.class.equals(type))
            return new PackageService();
        else if (User.class.equals(type))
            return new UserService();
        else if (Vehicle.class.equals(type))
            return new VehicleService();
        else if(Site.class.equals(type))
            return new SedeService();
        else if(User.class.equals(type))
            return new UserService();
        return null;
    }
}
