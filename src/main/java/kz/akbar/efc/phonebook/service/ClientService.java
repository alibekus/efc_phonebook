package kz.akbar.efc.phonebook.service;

import kz.akbar.efc.phonebook.model.Client;
import kz.akbar.efc.phonebook.repository.datajpa.DataJpaClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.List;

import static kz.akbar.efc.phonebook.util.ValidationUtil.checkNotFoundWithId;

@Service("clientService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ClientService {

    private final DataJpaClientRepository repository;
    private final UserRequestService userRequestService;

    @Autowired
    public ClientService(DataJpaClientRepository repository, UserRequestService userRequestService) {
        this.repository = repository;
        this.userRequestService = userRequestService;
    }

    @Transactional
    public Client create(Client client) {
        Assert.notNull(client, "client must not be null");
        userRequestService.logRequest(2, "save " + client.toString());
        return repository.save(client);
    }

    @Transactional
    public void delete(int id) {
        userRequestService.logRequest(2, "delete client with id = " + id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Transactional(readOnly=true)
    public Client get(int id) {
        userRequestService.logRequest(2, "get client with id = " + id);
        return checkNotFoundWithId(repository.getById(id), id);
    }

    @Transactional(readOnly=true)
    public Client getByIin(String iin) {
        userRequestService.logRequest(2, "get client by iin = " + iin);
        return repository.getClientByIin(iin);
    }

    @Transactional(readOnly=true)
    public Client getByPhoneNumber(String phoneNumber) {
        userRequestService.logRequest(2, "get client by phone number = " + phoneNumber);
        return repository.getClientByPhoneNumber(phoneNumber);
    }

    @Transactional(readOnly=true)
    public List<Client> getByName(String name) {
        userRequestService.logRequest(2, "get client by name " + name);
        return repository.getClientByName(name);
    }

    @Transactional(readOnly=true)
    public List<Client> getAll() {
        userRequestService.logRequest(2, "get all clients");
        return repository.getAllClients();
    }

    @Transactional(readOnly=true)
    public Page<Client> getAllByPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
