package kz.akbar.efc.phonebook;

import kz.akbar.efc.phonebook.model.City;
import kz.akbar.efc.phonebook.model.Client;
import kz.akbar.efc.phonebook.model.Country;

import java.util.Collections;
import java.util.Date;

import static kz.akbar.efc.phonebook.model.AbstractBaseEntity.START_SEQ;

public class ClientTestData {
    public static final int CLIENT_ID = START_SEQ;

    public static final Client CLIENT =
            new Client(CLIENT_ID, "Client", "client@yandex.ru", "password", 2005, );

    public static Client getNew() {

        final City city = new City();
        Country country = new Country();

        city.getCode("AST");
        city.setName("Astana");
        city.
        return new Client(null, "ClientKz", "123456789123", city);
    }

    public static Client getUpdated() {
        Client updated = new Client(CLIENT);
        updated.setName("ClientKz");
        return updated;
    }

}
