package kz.akbar.efc.phonebook.repository.datajpa;


import kz.akbar.efc.phonebook.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserReqRepository {

    final UserRequestRepository repository;

    @Autowired
    public DataJpaUserReqRepository(UserRequestRepository repository) {
        this.repository = repository;
    }

    public UserRequest save(UserRequest request) {
        return repository.save(request);
    }

    public List<UserRequest> getAllByUserId(long userId) {
        return repository.getByUserId(userId);
    }

    public List<UserRequest> getAll() {
        return repository.findAll();
    }

    public Page<UserRequest> getAllByPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
