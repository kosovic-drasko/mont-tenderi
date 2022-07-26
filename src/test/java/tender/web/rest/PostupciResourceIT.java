package tender.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tender.IntegrationTest;
import tender.domain.Postupci;
import tender.repository.PostupciRepository;

/**
 * Integration tests for the {@link PostupciResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PostupciResourceIT {

    private static final Integer DEFAULT_SIFRA_POSTUPKA = 1;
    private static final Integer UPDATED_SIFRA_POSTUPKA = 2;

    private static final String DEFAULT_BROJ_TENDERA = "AAAAAAAAAA";
    private static final String UPDATED_BROJ_TENDERA = "BBBBBBBBBB";

    private static final String DEFAULT_OPIS_POSTUPKA = "AAAAAAAAAA";
    private static final String UPDATED_OPIS_POSTUPKA = "BBBBBBBBBB";

    private static final String DEFAULT_VRSTA_POSTUPKA = "AAAAAAAAAA";
    private static final String UPDATED_VRSTA_POSTUPKA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_OBJAVE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_OBJAVE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATUM_OTVARANJA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_OTVARANJA = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/postupcis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PostupciRepository postupciRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPostupciMockMvc;

    private Postupci postupci;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Postupci createEntity(EntityManager em) {
        Postupci postupci = new Postupci()
            .sifraPostupka(DEFAULT_SIFRA_POSTUPKA)
            .brojTendera(DEFAULT_BROJ_TENDERA)
            .opisPostupka(DEFAULT_OPIS_POSTUPKA)
            .vrstaPostupka(DEFAULT_VRSTA_POSTUPKA)
            .datumObjave(DEFAULT_DATUM_OBJAVE)
            .datumOtvaranja(DEFAULT_DATUM_OTVARANJA);
        return postupci;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Postupci createUpdatedEntity(EntityManager em) {
        Postupci postupci = new Postupci()
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojTendera(UPDATED_BROJ_TENDERA)
            .opisPostupka(UPDATED_OPIS_POSTUPKA)
            .vrstaPostupka(UPDATED_VRSTA_POSTUPKA)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumOtvaranja(UPDATED_DATUM_OTVARANJA);
        return postupci;
    }

    @BeforeEach
    public void initTest() {
        postupci = createEntity(em);
    }

    @Test
    @Transactional
    void createPostupci() throws Exception {
        int databaseSizeBeforeCreate = postupciRepository.findAll().size();
        // Create the Postupci
        restPostupciMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isCreated());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeCreate + 1);
        Postupci testPostupci = postupciList.get(postupciList.size() - 1);
        assertThat(testPostupci.getSifraPostupka()).isEqualTo(DEFAULT_SIFRA_POSTUPKA);
        assertThat(testPostupci.getBrojTendera()).isEqualTo(DEFAULT_BROJ_TENDERA);
        assertThat(testPostupci.getOpisPostupka()).isEqualTo(DEFAULT_OPIS_POSTUPKA);
        assertThat(testPostupci.getVrstaPostupka()).isEqualTo(DEFAULT_VRSTA_POSTUPKA);
        assertThat(testPostupci.getDatumObjave()).isEqualTo(DEFAULT_DATUM_OBJAVE);
        assertThat(testPostupci.getDatumOtvaranja()).isEqualTo(DEFAULT_DATUM_OTVARANJA);
    }

    @Test
    @Transactional
    void createPostupciWithExistingId() throws Exception {
        // Create the Postupci with an existing ID
        postupci.setId(1L);

        int databaseSizeBeforeCreate = postupciRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostupciMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isBadRequest());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSifraPostupkaIsRequired() throws Exception {
        int databaseSizeBeforeTest = postupciRepository.findAll().size();
        // set the field null
        postupci.setSifraPostupka(null);

        // Create the Postupci, which fails.

        restPostupciMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isBadRequest());

        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBrojTenderaIsRequired() throws Exception {
        int databaseSizeBeforeTest = postupciRepository.findAll().size();
        // set the field null
        postupci.setBrojTendera(null);

        // Create the Postupci, which fails.

        restPostupciMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isBadRequest());

        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVrstaPostupkaIsRequired() throws Exception {
        int databaseSizeBeforeTest = postupciRepository.findAll().size();
        // set the field null
        postupci.setVrstaPostupka(null);

        // Create the Postupci, which fails.

        restPostupciMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isBadRequest());

        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPostupcis() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        // Get all the postupciList
        restPostupciMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(postupci.getId().intValue())))
            .andExpect(jsonPath("$.[*].sifraPostupka").value(hasItem(DEFAULT_SIFRA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].brojTendera").value(hasItem(DEFAULT_BROJ_TENDERA)))
            .andExpect(jsonPath("$.[*].opisPostupka").value(hasItem(DEFAULT_OPIS_POSTUPKA)))
            .andExpect(jsonPath("$.[*].vrstaPostupka").value(hasItem(DEFAULT_VRSTA_POSTUPKA)))
            .andExpect(jsonPath("$.[*].datumObjave").value(hasItem(DEFAULT_DATUM_OBJAVE.toString())))
            .andExpect(jsonPath("$.[*].datumOtvaranja").value(hasItem(DEFAULT_DATUM_OTVARANJA.toString())));
    }

    @Test
    @Transactional
    void getPostupci() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        // Get the postupci
        restPostupciMockMvc
            .perform(get(ENTITY_API_URL_ID, postupci.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(postupci.getId().intValue()))
            .andExpect(jsonPath("$.sifraPostupka").value(DEFAULT_SIFRA_POSTUPKA))
            .andExpect(jsonPath("$.brojTendera").value(DEFAULT_BROJ_TENDERA))
            .andExpect(jsonPath("$.opisPostupka").value(DEFAULT_OPIS_POSTUPKA))
            .andExpect(jsonPath("$.vrstaPostupka").value(DEFAULT_VRSTA_POSTUPKA))
            .andExpect(jsonPath("$.datumObjave").value(DEFAULT_DATUM_OBJAVE.toString()))
            .andExpect(jsonPath("$.datumOtvaranja").value(DEFAULT_DATUM_OTVARANJA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPostupci() throws Exception {
        // Get the postupci
        restPostupciMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPostupci() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();

        // Update the postupci
        Postupci updatedPostupci = postupciRepository.findById(postupci.getId()).get();
        // Disconnect from session so that the updates on updatedPostupci are not directly saved in db
        em.detach(updatedPostupci);
        updatedPostupci
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojTendera(UPDATED_BROJ_TENDERA)
            .opisPostupka(UPDATED_OPIS_POSTUPKA)
            .vrstaPostupka(UPDATED_VRSTA_POSTUPKA)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumOtvaranja(UPDATED_DATUM_OTVARANJA);

        restPostupciMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPostupci.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPostupci))
            )
            .andExpect(status().isOk());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
        Postupci testPostupci = postupciList.get(postupciList.size() - 1);
        assertThat(testPostupci.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPostupci.getBrojTendera()).isEqualTo(UPDATED_BROJ_TENDERA);
        assertThat(testPostupci.getOpisPostupka()).isEqualTo(UPDATED_OPIS_POSTUPKA);
        assertThat(testPostupci.getVrstaPostupka()).isEqualTo(UPDATED_VRSTA_POSTUPKA);
        assertThat(testPostupci.getDatumObjave()).isEqualTo(UPDATED_DATUM_OBJAVE);
        assertThat(testPostupci.getDatumOtvaranja()).isEqualTo(UPDATED_DATUM_OTVARANJA);
    }

    @Test
    @Transactional
    void putNonExistingPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(
                put(ENTITY_API_URL_ID, postupci.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(postupci))
            )
            .andExpect(status().isBadRequest());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(postupci))
            )
            .andExpect(status().isBadRequest());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePostupciWithPatch() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();

        // Update the postupci using partial update
        Postupci partialUpdatedPostupci = new Postupci();
        partialUpdatedPostupci.setId(postupci.getId());

        partialUpdatedPostupci
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojTendera(UPDATED_BROJ_TENDERA)
            .opisPostupka(UPDATED_OPIS_POSTUPKA)
            .vrstaPostupka(UPDATED_VRSTA_POSTUPKA);

        restPostupciMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPostupci.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostupci))
            )
            .andExpect(status().isOk());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
        Postupci testPostupci = postupciList.get(postupciList.size() - 1);
        assertThat(testPostupci.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPostupci.getBrojTendera()).isEqualTo(UPDATED_BROJ_TENDERA);
        assertThat(testPostupci.getOpisPostupka()).isEqualTo(UPDATED_OPIS_POSTUPKA);
        assertThat(testPostupci.getVrstaPostupka()).isEqualTo(UPDATED_VRSTA_POSTUPKA);
        assertThat(testPostupci.getDatumObjave()).isEqualTo(DEFAULT_DATUM_OBJAVE);
        assertThat(testPostupci.getDatumOtvaranja()).isEqualTo(DEFAULT_DATUM_OTVARANJA);
    }

    @Test
    @Transactional
    void fullUpdatePostupciWithPatch() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();

        // Update the postupci using partial update
        Postupci partialUpdatedPostupci = new Postupci();
        partialUpdatedPostupci.setId(postupci.getId());

        partialUpdatedPostupci
            .sifraPostupka(UPDATED_SIFRA_POSTUPKA)
            .brojTendera(UPDATED_BROJ_TENDERA)
            .opisPostupka(UPDATED_OPIS_POSTUPKA)
            .vrstaPostupka(UPDATED_VRSTA_POSTUPKA)
            .datumObjave(UPDATED_DATUM_OBJAVE)
            .datumOtvaranja(UPDATED_DATUM_OTVARANJA);

        restPostupciMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPostupci.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostupci))
            )
            .andExpect(status().isOk());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
        Postupci testPostupci = postupciList.get(postupciList.size() - 1);
        assertThat(testPostupci.getSifraPostupka()).isEqualTo(UPDATED_SIFRA_POSTUPKA);
        assertThat(testPostupci.getBrojTendera()).isEqualTo(UPDATED_BROJ_TENDERA);
        assertThat(testPostupci.getOpisPostupka()).isEqualTo(UPDATED_OPIS_POSTUPKA);
        assertThat(testPostupci.getVrstaPostupka()).isEqualTo(UPDATED_VRSTA_POSTUPKA);
        assertThat(testPostupci.getDatumObjave()).isEqualTo(UPDATED_DATUM_OBJAVE);
        assertThat(testPostupci.getDatumOtvaranja()).isEqualTo(UPDATED_DATUM_OTVARANJA);
    }

    @Test
    @Transactional
    void patchNonExistingPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, postupci.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(postupci))
            )
            .andExpect(status().isBadRequest());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(postupci))
            )
            .andExpect(status().isBadRequest());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPostupci() throws Exception {
        int databaseSizeBeforeUpdate = postupciRepository.findAll().size();
        postupci.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostupciMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(postupci)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Postupci in the database
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePostupci() throws Exception {
        // Initialize the database
        postupciRepository.saveAndFlush(postupci);

        int databaseSizeBeforeDelete = postupciRepository.findAll().size();

        // Delete the postupci
        restPostupciMockMvc
            .perform(delete(ENTITY_API_URL_ID, postupci.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Postupci> postupciList = postupciRepository.findAll();
        assertThat(postupciList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
