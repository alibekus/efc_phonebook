package kz.akbar.efc.phonebook.repository.datajpa;

import kz.akbar.efc.phonebook.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequest, Long>, PagingAndSortingRepository<UserRequest, Long> {

    List<UserRequest> getByUserId(@Param("userId") long userId);

    UserRequest save(UserRequest userRequest);

}
