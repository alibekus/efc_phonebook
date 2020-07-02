package kz.akbar.efc.phonebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "country")
public class Country extends AbstractNamedEntity {

    @Column(name = "code", nullable = false)
    @NotNull
    @Size(min = 2, max = 10)
    private String code;

    @Column(name = "capital", nullable = false)
    @NotNull
    @Size(min = 3, max = 255)
    private String capital;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", capital='" + capital + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
