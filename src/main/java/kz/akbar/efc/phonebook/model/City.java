package kz.akbar.efc.phonebook.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "city")
public class City extends AbstractNamedEntity {

    @Column(name = "code", nullable = false)
    @NotBlank
    @Size(min = 2, max = 10)
    private String code;

    @JoinColumn(name = "country_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;


    public String getCode(String ast) {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "code='" + code + '\'' +
                ", country=" + country +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
