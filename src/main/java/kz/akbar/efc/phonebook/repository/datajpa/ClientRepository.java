package kz.akbar.efc.phonebook.repository.datajpa;

import kz.akbar.efc.phonebook.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ClientRepository extends JpaRepository<Client, Long>, PagingAndSortingRepository<Client, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Client c WHERE c.id=:id")
    int delete(@Param("id") long id);

    List<Client> getClientByName(@Param("name") String name);

    Client getClientByIin(@Param("iin") String iin);

    Client getClientByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    Client save(Client client);

}
