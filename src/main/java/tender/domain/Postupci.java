package tender.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Postupci.
 */
@Entity
@Table(name = "postupci")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Postupci implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "sifra_postupka", nullable = false)
    private Integer sifraPostupka;

    @NotNull
    @Column(name = "broj_tendera", nullable = false)
    private String brojTendera;

    @Column(name = "opis_postupka")
    private String opisPostupka;

    @NotNull
    @Column(name = "vrsta_postupka", nullable = false)
    private String vrstaPostupka;

    @Column(name = "datum_objave")
    private LocalDate datumObjave;

    @Column(name = "datum_otvaranja")
    private LocalDate datumOtvaranja;

    @OneToMany(mappedBy = "postupci")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postupci", "ponudjaci", "specifikacije" }, allowSetters = true)
    private Set<Ponude> ponudes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Postupci id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public Postupci sifraPostupka(Integer sifraPostupka) {
        this.setSifraPostupka(sifraPostupka);
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public String getBrojTendera() {
        return this.brojTendera;
    }

    public Postupci brojTendera(String brojTendera) {
        this.setBrojTendera(brojTendera);
        return this;
    }

    public void setBrojTendera(String brojTendera) {
        this.brojTendera = brojTendera;
    }

    public String getOpisPostupka() {
        return this.opisPostupka;
    }

    public Postupci opisPostupka(String opisPostupka) {
        this.setOpisPostupka(opisPostupka);
        return this;
    }

    public void setOpisPostupka(String opisPostupka) {
        this.opisPostupka = opisPostupka;
    }

    public String getVrstaPostupka() {
        return this.vrstaPostupka;
    }

    public Postupci vrstaPostupka(String vrstaPostupka) {
        this.setVrstaPostupka(vrstaPostupka);
        return this;
    }

    public void setVrstaPostupka(String vrstaPostupka) {
        this.vrstaPostupka = vrstaPostupka;
    }

    public LocalDate getDatumObjave() {
        return this.datumObjave;
    }

    public Postupci datumObjave(LocalDate datumObjave) {
        this.setDatumObjave(datumObjave);
        return this;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }

    public LocalDate getDatumOtvaranja() {
        return this.datumOtvaranja;
    }

    public Postupci datumOtvaranja(LocalDate datumOtvaranja) {
        this.setDatumOtvaranja(datumOtvaranja);
        return this;
    }

    public void setDatumOtvaranja(LocalDate datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public Set<Ponude> getPonudes() {
        return this.ponudes;
    }

    public void setPonudes(Set<Ponude> ponudes) {
        if (this.ponudes != null) {
            this.ponudes.forEach(i -> i.setPostupci(null));
        }
        if (ponudes != null) {
            ponudes.forEach(i -> i.setPostupci(this));
        }
        this.ponudes = ponudes;
    }

    public Postupci ponudes(Set<Ponude> ponudes) {
        this.setPonudes(ponudes);
        return this;
    }

    public Postupci addPonude(Ponude ponude) {
        this.ponudes.add(ponude);
        ponude.setPostupci(this);
        return this;
    }

    public Postupci removePonude(Ponude ponude) {
        this.ponudes.remove(ponude);
        ponude.setPostupci(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Postupci)) {
            return false;
        }
        return id != null && id.equals(((Postupci) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Postupci{" +
            "id=" + getId() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", brojTendera='" + getBrojTendera() + "'" +
            ", opisPostupka='" + getOpisPostupka() + "'" +
            ", vrstaPostupka='" + getVrstaPostupka() + "'" +
            ", datumObjave='" + getDatumObjave() + "'" +
            ", datumOtvaranja='" + getDatumOtvaranja() + "'" +
            "}";
    }
}
