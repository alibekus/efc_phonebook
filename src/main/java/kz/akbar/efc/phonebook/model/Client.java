package kz.akbar.efc.phonebook.model;

import kz.akbar.efc.phonebook.util.DateTimeUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"iin"}, name = "client_iin_unique_idx")})
public class Client extends AbstractNamedEntity {

    @Column(name = "iin", nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 12, max = 12)
    private String iin;

    @Column(name = "birthdate", nullable = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private Date birthDate;

    @Column(name = "phone_number", nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String phoneNumber;

    @JoinColumn(name = "city_id", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    public Client(Client client) {
    }

    public Client(Long id, String name, String iin, City city, String phoneNumber, Date birthDate) {
        super(id,name);
        this.iin = iin;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Client{" +
                "iin='" + iin + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city=" + city +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}


