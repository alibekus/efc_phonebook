package kz.akbar.efc.phonebook.service;

import kz.akbar.efc.phonebook.model.UserRequest;
import kz.akbar.efc.phonebook.repository.datajpa.DataJpaUserReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("userRequestService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserRequestService {

    private final DataJpaUserReqRepository repository;

    @Autowired
    public UserRequestService(DataJpaUserReqRepository repository) {
        this.repository = repository;
    }

    public List<UserRequest> getByUserId(long id) {
        return repository.getAllByUserId(id);
    }

    public List<UserRequest> getAll() {
        return repository.getAll();
    }

    public Page<UserRequest> getAllByPage(Pageable pageable) {
        return repository.getAllByPage(pageable);
    }

    void logRequest(int userId, String param) {
        UserRequest request = new UserRequest();
        request.setUserId(userId);
        request.setReqParam(param);
        request.setRequestDatetime(LocalDateTime.now());
        repository.save(request);
    }

}
