package kz.akbar.efc.phonebook.repository.datajpa;

import kz.akbar.efc.phonebook.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public int delete(long id) {
        return clientRepository.delete(id);
    }

    public Client getById(long id) {
        return clientRepository.getOne(id);
    }

    public Client getClientByIin(String iin) {
        return clientRepository.getClientByIin(iin);
    }

    public Client getClientByPhoneNumber(String number) {
        return clientRepository.getClientByPhoneNumber(number);
    }

    public List<Client> getClientByName(String fullName) {
        return clientRepository.getClientByName(fullName);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Iterable<Client> findAll(Sort sort) {
        return clientRepository.findAll(sort);
    }

    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
