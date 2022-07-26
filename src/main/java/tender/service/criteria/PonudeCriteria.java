package tender.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link tender.domain.Ponude} entity. This class is used
 * in {@link tender.web.rest.PonudeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ponudes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class PonudeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sifraPostupka;

    private IntegerFilter sifraPonude;

    private IntegerFilter brojPartije;

    private StringFilter nazivProizvodjaca;

    private StringFilter zasticeniNaziv;

    private IntegerFilter ponudjanaKolicina;

    private DoubleFilter ponudjenaVrijednost;

    private DoubleFilter jedinicnaCijena;

    private IntegerFilter rokIsporuke;

    private IntegerFilter sifraPonudjaca;

    private BooleanFilter selected;

    private LongFilter postupciId;

    private LongFilter ponudjaciId;

    private LongFilter specifikacijeId;

    private Boolean distinct;

    public PonudeCriteria() {}

    public PonudeCriteria(PonudeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sifraPostupka = other.sifraPostupka == null ? null : other.sifraPostupka.copy();
        this.sifraPonude = other.sifraPonude == null ? null : other.sifraPonude.copy();
        this.brojPartije = other.brojPartije == null ? null : other.brojPartije.copy();
        this.nazivProizvodjaca = other.nazivProizvodjaca == null ? null : other.nazivProizvodjaca.copy();
        this.zasticeniNaziv = other.zasticeniNaziv == null ? null : other.zasticeniNaziv.copy();
        this.ponudjanaKolicina = other.ponudjanaKolicina == null ? null : other.ponudjanaKolicina.copy();
        this.ponudjenaVrijednost = other.ponudjenaVrijednost == null ? null : other.ponudjenaVrijednost.copy();
        this.jedinicnaCijena = other.jedinicnaCijena == null ? null : other.jedinicnaCijena.copy();
        this.rokIsporuke = other.rokIsporuke == null ? null : other.rokIsporuke.copy();
        this.sifraPonudjaca = other.sifraPonudjaca == null ? null : other.sifraPonudjaca.copy();
        this.selected = other.selected == null ? null : other.selected.copy();
        this.postupciId = other.postupciId == null ? null : other.postupciId.copy();
        this.ponudjaciId = other.ponudjaciId == null ? null : other.ponudjaciId.copy();
        this.specifikacijeId = other.specifikacijeId == null ? null : other.specifikacijeId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PonudeCriteria copy() {
        return new PonudeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSifraPostupka() {
        return sifraPostupka;
    }

    public IntegerFilter sifraPostupka() {
        if (sifraPostupka == null) {
            sifraPostupka = new IntegerFilter();
        }
        return sifraPostupka;
    }

    public void setSifraPostupka(IntegerFilter sifraPostupka) {
        this.sifraPostupka = sifraPostupka;
    }

    public IntegerFilter getSifraPonude() {
        return sifraPonude;
    }

    public IntegerFilter sifraPonude() {
        if (sifraPonude == null) {
            sifraPonude = new IntegerFilter();
        }
        return sifraPonude;
    }

    public void setSifraPonude(IntegerFilter sifraPonude) {
        this.sifraPonude = sifraPonude;
    }

    public IntegerFilter getBrojPartije() {
        return brojPartije;
    }

    public IntegerFilter brojPartije() {
        if (brojPartije == null) {
            brojPartije = new IntegerFilter();
        }
        return brojPartije;
    }

    public void setBrojPartije(IntegerFilter brojPartije) {
        this.brojPartije = brojPartije;
    }

    public StringFilter getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public StringFilter nazivProizvodjaca() {
        if (nazivProizvodjaca == null) {
            nazivProizvodjaca = new StringFilter();
        }
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(StringFilter nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public StringFilter getZasticeniNaziv() {
        return zasticeniNaziv;
    }

    public StringFilter zasticeniNaziv() {
        if (zasticeniNaziv == null) {
            zasticeniNaziv = new StringFilter();
        }
        return zasticeniNaziv;
    }

    public void setZasticeniNaziv(StringFilter zasticeniNaziv) {
        this.zasticeniNaziv = zasticeniNaziv;
    }

    public IntegerFilter getPonudjanaKolicina() {
        return ponudjanaKolicina;
    }

    public IntegerFilter ponudjanaKolicina() {
        if (ponudjanaKolicina == null) {
            ponudjanaKolicina = new IntegerFilter();
        }
        return ponudjanaKolicina;
    }

    public void setPonudjanaKolicina(IntegerFilter ponudjanaKolicina) {
        this.ponudjanaKolicina = ponudjanaKolicina;
    }

    public DoubleFilter getPonudjenaVrijednost() {
        return ponudjenaVrijednost;
    }

    public DoubleFilter ponudjenaVrijednost() {
        if (ponudjenaVrijednost == null) {
            ponudjenaVrijednost = new DoubleFilter();
        }
        return ponudjenaVrijednost;
    }

    public void setPonudjenaVrijednost(DoubleFilter ponudjenaVrijednost) {
        this.ponudjenaVrijednost = ponudjenaVrijednost;
    }

    public DoubleFilter getJedinicnaCijena() {
        return jedinicnaCijena;
    }

    public DoubleFilter jedinicnaCijena() {
        if (jedinicnaCijena == null) {
            jedinicnaCijena = new DoubleFilter();
        }
        return jedinicnaCijena;
    }

    public void setJedinicnaCijena(DoubleFilter jedinicnaCijena) {
        this.jedinicnaCijena = jedinicnaCijena;
    }

    public IntegerFilter getRokIsporuke() {
        return rokIsporuke;
    }

    public IntegerFilter rokIsporuke() {
        if (rokIsporuke == null) {
            rokIsporuke = new IntegerFilter();
        }
        return rokIsporuke;
    }

    public void setRokIsporuke(IntegerFilter rokIsporuke) {
        this.rokIsporuke = rokIsporuke;
    }

    public IntegerFilter getSifraPonudjaca() {
        return sifraPonudjaca;
    }

    public IntegerFilter sifraPonudjaca() {
        if (sifraPonudjaca == null) {
            sifraPonudjaca = new IntegerFilter();
        }
        return sifraPonudjaca;
    }

    public void setSifraPonudjaca(IntegerFilter sifraPonudjaca) {
        this.sifraPonudjaca = sifraPonudjaca;
    }

    public BooleanFilter getSelected() {
        return selected;
    }

    public BooleanFilter selected() {
        if (selected == null) {
            selected = new BooleanFilter();
        }
        return selected;
    }

    public void setSelected(BooleanFilter selected) {
        this.selected = selected;
    }

    public LongFilter getPostupciId() {
        return postupciId;
    }

    public LongFilter postupciId() {
        if (postupciId == null) {
            postupciId = new LongFilter();
        }
        return postupciId;
    }

    public void setPostupciId(LongFilter postupciId) {
        this.postupciId = postupciId;
    }

    public LongFilter getPonudjaciId() {
        return ponudjaciId;
    }

    public LongFilter ponudjaciId() {
        if (ponudjaciId == null) {
            ponudjaciId = new LongFilter();
        }
        return ponudjaciId;
    }

    public void setPonudjaciId(LongFilter ponudjaciId) {
        this.ponudjaciId = ponudjaciId;
    }

    public LongFilter getSpecifikacijeId() {
        return specifikacijeId;
    }

    public LongFilter specifikacijeId() {
        if (specifikacijeId == null) {
            specifikacijeId = new LongFilter();
        }
        return specifikacijeId;
    }

    public void setSpecifikacijeId(LongFilter specifikacijeId) {
        this.specifikacijeId = specifikacijeId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PonudeCriteria that = (PonudeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sifraPostupka, that.sifraPostupka) &&
            Objects.equals(sifraPonude, that.sifraPonude) &&
            Objects.equals(brojPartije, that.brojPartije) &&
            Objects.equals(nazivProizvodjaca, that.nazivProizvodjaca) &&
            Objects.equals(zasticeniNaziv, that.zasticeniNaziv) &&
            Objects.equals(ponudjanaKolicina, that.ponudjanaKolicina) &&
            Objects.equals(ponudjenaVrijednost, that.ponudjenaVrijednost) &&
            Objects.equals(jedinicnaCijena, that.jedinicnaCijena) &&
            Objects.equals(rokIsporuke, that.rokIsporuke) &&
            Objects.equals(sifraPonudjaca, that.sifraPonudjaca) &&
            Objects.equals(selected, that.selected) &&
            Objects.equals(postupciId, that.postupciId) &&
            Objects.equals(ponudjaciId, that.ponudjaciId) &&
            Objects.equals(specifikacijeId, that.specifikacijeId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sifraPostupka,
            sifraPonude,
            brojPartije,
            nazivProizvodjaca,
            zasticeniNaziv,
            ponudjanaKolicina,
            ponudjenaVrijednost,
            jedinicnaCijena,
            rokIsporuke,
            sifraPonudjaca,
            selected,
            postupciId,
            ponudjaciId,
            specifikacijeId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PonudeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sifraPostupka != null ? "sifraPostupka=" + sifraPostupka + ", " : "") +
            (sifraPonude != null ? "sifraPonude=" + sifraPonude + ", " : "") +
            (brojPartije != null ? "brojPartije=" + brojPartije + ", " : "") +
            (nazivProizvodjaca != null ? "nazivProizvodjaca=" + nazivProizvodjaca + ", " : "") +
            (zasticeniNaziv != null ? "zasticeniNaziv=" + zasticeniNaziv + ", " : "") +
            (ponudjanaKolicina != null ? "ponudjanaKolicina=" + ponudjanaKolicina + ", " : "") +
            (ponudjenaVrijednost != null ? "ponudjenaVrijednost=" + ponudjenaVrijednost + ", " : "") +
            (jedinicnaCijena != null ? "jedinicnaCijena=" + jedinicnaCijena + ", " : "") +
            (rokIsporuke != null ? "rokIsporuke=" + rokIsporuke + ", " : "") +
            (sifraPonudjaca != null ? "sifraPonudjaca=" + sifraPonudjaca + ", " : "") +
            (selected != null ? "selected=" + selected + ", " : "") +
            (postupciId != null ? "postupciId=" + postupciId + ", " : "") +
            (ponudjaciId != null ? "ponudjaciId=" + ponudjaciId + ", " : "") +
            (specifikacijeId != null ? "specifikacijeId=" + specifikacijeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
