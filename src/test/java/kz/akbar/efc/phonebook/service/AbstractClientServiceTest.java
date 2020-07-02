package kz.akbar.efc.phonebook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import kz.akbar.efc.phonebook.model.Client;
import kz.akbar.efc.phonebook.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static kz.akbar.efc.phonebook.ClientTestData.*;

public abstract class AbstractClientServiceTest extends AbstractServiceTest {

    @Autowired
    protected ClientService service;

    @Test
    void create() throws Exception {
        Client newClient = getNew();
        Client created = service.create(new Client(newClient));
        Long newId = created.getId();
        newClient.setId(newId);
    }


    @Test
    void delete() throws Exception {
        service.delete(CLIENT_ID);
        assertThrows(NotFoundException.class, () ->
                service.delete(CLIENT_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

}