package tender.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ponude.
 */
@Entity
@Table(name = "ponude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ponude implements Serializable {

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
    @Column(name = "sifra_ponude", nullable = false)
    private Integer sifraPonude;

    @NotNull
    @Column(name = "broj_partije", nullable = false)
    private Integer brojPartije;

    @Column(name = "naziv_proizvodjaca")
    private String nazivProizvodjaca;

    @Column(name = "zasticeni_naziv")
    private String zasticeniNaziv;

    @NotNull
    @Column(name = "ponudjana_kolicina", nullable = false)
    private Integer ponudjanaKolicina;

    @NotNull
    @Column(name = "ponudjena_vrijednost", nullable = false)
    private Double ponudjenaVrijednost;

    @Column(name = "jedinicna_cijena")
    private Double jedinicnaCijena;

    @NotNull
    @Column(name = "rok_isporuke", nullable = false)
    private Integer rokIsporuke;

    @NotNull
    @Column(name = "sifra_ponudjaca", nullable = false)
    private Integer sifraPonudjaca;

    @Column(name = "selected")
    private Boolean selected;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ponudes" }, allowSetters = true)
    private Postupci postupci;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ponudes" }, allowSetters = true)
    private Ponudjaci ponudjaci;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ponudes" }, allowSetters = true)
    private Specifikacije specifikacije;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ponude id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSifraPostupka() {
        return this.sifraPostupka;
    }

    public Ponude sifraPostupka(Integer sifraPostupka) {
        this.setSifraPostupka(sifraPostupka);
        return this;
    }

    public void setSifraPostupka(Integer sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public Integer getSifraPonude() {
        return this.sifraPonude;
    }

    public Ponude sifraPonude(Integer sifraPonude) {
        this.setSifraPonude(sifraPonude);
        return this;
    }

    public void setSifraPonude(Integer sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public Integer getBrojPartije() {
        return this.brojPartije;
    }

    public Ponude brojPartije(Integer brojPartije) {
        this.setBrojPartije(brojPartije);
        return this;
    }

    public void setBrojPartije(Integer brojPartije) {
        this.brojPartije = brojPartije;
    }

    public String getNazivProizvodjaca() {
        return this.nazivProizvodjaca;
    }

    public Ponude nazivProizvodjaca(String nazivProizvodjaca) {
        this.setNazivProizvodjaca(nazivProizvodjaca);
        return this;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public String getZasticeniNaziv() {
        return this.zasticeniNaziv;
    }

    public Ponude zasticeniNaziv(String zasticeniNaziv) {
        this.setZasticeniNaziv(zasticeniNaziv);
        return this;
    }

    public void setZasticeniNaziv(String zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
    }

    public Integer getPonudjanaKolicina() {
        return this.ponudjanaKolicina;
    }

    public Ponude ponudjanaKolicina(Integer ponudjanaKolicina) {
        this.setPonudjanaKolicina(ponudjanaKolicina);
        return this;
    }

    public void setPonudjanaKolicina(Integer ponudjanaKolicina) {
        this.ponudjanaKolicina = ponudjanaKolicina;
    }

    public Double getPonudjenaVrijednost() {
        return this.ponudjenaVrijednost;
    }

    public Ponude ponudjenaVrijednost(Double ponudjenaVrijednost) {
        this.setPonudjenaVrijednost(ponudjenaVrijednost);
        return this;
    }

    public void setPonudjenaVrijednost(Double ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public Double getJedinicnaCijena() {
        return this.jedinicnaCijena;
    }

    public Ponude jedinicnaCijena(Double jedinicnaCijena) {
        this.setJedinicnaCijena(jedinicnaCijena);
        return this;
    }

    public void setJedinicnaCijena(Double jedinicnaCijena) {
        this.jedinicnaCijena = jedinicnaCijena;
    }

    public Integer getRokIsporuke() {
        return this.rokIsporuke;
    }

    public Ponude rokIsporuke(Integer rokIsporuke) {
        this.setRokIsporuke(rokIsporuke);
        return this;
    }

    public void setRokIsporuke(Integer rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public Integer getSifraPonudjaca() {
        return this.sifraPonudjaca;
    }

    public Ponude sifraPonudjaca(Integer sifraPonudjaca) {
        this.setSifraPonudjaca(sifraPonudjaca);
        return this;
    }

    public void setSifraPonudjaca(Integer sifraPonudjaca) {
        this.sifraPonudjaca = sifraPonudjaca;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public Ponude selected(Boolean selected) {
        this.setSelected(selected);
        return this;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Postupci getPostupci() {
        return this.postupci;
    }

    public void setPostupci(Postupci postupci) {
        this.postupci = postupci;
    }

    public Ponude postupci(Postupci postupci) {
        this.setPostupci(postupci);
        return this;
    }

    public Ponudjaci getPonudjaci() {
        return this.ponudjaci;
    }

    public void setPonudjaci(Ponudjaci ponudjaci) {
        this.ponudjaci = ponudjaci;
    }

    public Ponude ponudjaci(Ponudjaci ponudjaci) {
        this.setPonudjaci(ponudjaci);
        return this;
    }

    public Specifikacije getSpecifikacije() {
        return this.specifikacije;
    }

    public void setSpecifikacije(Specifikacije specifikacije) {
        this.specifikacije = specifikacije;
    }

    public Ponude specifikacije(Specifikacije specifikacije) {
        this.setSpecifikacije(specifikacije);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ponude)) {
            return false;
        }
        return id != null && id.equals(((Ponude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ponude{" +
            "id=" + getId() +
            ", sifraPostupka=" + getSifraPostupka() +
            ", sifraPonude=" + getSifraPonude() +
            ", brojPartije=" + getBrojPartije() +
            ", nazivProizvodjaca='" + getNazivProizvodjaca() + "'" +
            ", zasticeniNaziv='" + getZasticeniNaziv() + "'" +
            ", ponudjanaKolicina=" + getPonudjanaKolicina() +
            ", ponudjenaVrijednost=" + getPonudjenaVrijednost() +
            ", jedinicnaCijena=" + getJedinicnaCijena() +
            ", rokIsporuke=" + getRokIsporuke() +
            ", sifraPonudjaca=" + getSifraPonudjaca() +
            ", selected='" + getSelected() + "'" +
            "}";
    }
}
