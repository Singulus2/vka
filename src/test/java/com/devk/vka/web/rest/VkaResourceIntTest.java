package com.devk.vka.web.rest;

import com.devk.vka.VkaApp;

import com.devk.vka.domain.Vka;
import com.devk.vka.repository.VkaRepository;
import com.devk.vka.service.VkaService;
import com.devk.vka.service.dto.VkaDTO;
import com.devk.vka.service.mapper.VkaMapper;
import com.devk.vka.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static com.devk.vka.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VkaResource REST controller.
 *
 * @see VkaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VkaApp.class)
public class VkaResourceIntTest {

    private static final String DEFAULT_VNR = "AAAAAAAAAA";
    private static final String UPDATED_VNR = "BBBBBBBBBB";

    private static final String DEFAULT_VERS_ART = "AAAAAAAAAA";
    private static final String UPDATED_VERS_ART = "BBBBBBBBBB";

    private static final String DEFAULT_PRIORITAET = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITAET = "BBBBBBBBBB";

    private static final String DEFAULT_BEARB_DAT = "AAAAAAAAAA";
    private static final String UPDATED_BEARB_DAT = "BBBBBBBBBB";

    private static final String DEFAULT_BEARB_UHR = "AAAAAAAAAA";
    private static final String UPDATED_BEARB_UHR = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RD = "AAAAAAAAAA";
    private static final String UPDATED_RD = "BBBBBBBBBB";

    private static final String DEFAULT_GES = "AAAAAAAAAA";
    private static final String UPDATED_GES = "BBBBBBBBBB";

    private static final String DEFAULT_BZA = "AAAAAAAAAA";
    private static final String UPDATED_BZA = "BBBBBBBBBB";

    private static final String DEFAULT_TARIF = "AAAAAAAAAA";
    private static final String UPDATED_TARIF = "BBBBBBBBBB";

    private static final String DEFAULT_TARIF_UNR_1 = "AAAAAAAAAA";
    private static final String UPDATED_TARIF_UNR_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TARIF_UNR_2 = "AAAAAAAAAA";
    private static final String UPDATED_TARIF_UNR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MKT_1 = "AAAAAAAAAA";
    private static final String UPDATED_MKT_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BEWEG_SCHL = "AAAAAAAAAA";
    private static final String UPDATED_BEWEG_SCHL = "BBBBBBBBBB";

    private static final String DEFAULT_WIRKUNG_DAT = "AAAAAAAAAA";
    private static final String UPDATED_WIRKUNG_DAT = "BBBBBBBBBB";

    private static final String DEFAULT_ANT_AUFN_DAT = "AAAAAAAAAA";
    private static final String UPDATED_ANT_AUFN_DAT = "BBBBBBBBBB";

    private static final String DEFAULT_ANT_EING_DAT = "AAAAAAAAAA";
    private static final String UPDATED_ANT_EING_DAT = "BBBBBBBBBB";

    private static final String DEFAULT_ANZAHL_STRUK = "AAAAAAAAAA";
    private static final String UPDATED_ANZAHL_STRUK = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BTG = new BigDecimal(1);
    private static final BigDecimal UPDATED_BTG = new BigDecimal(2);

    @Autowired
    private VkaRepository vkaRepository;

    @Autowired
    private VkaMapper vkaMapper;

    @Autowired
    private VkaService vkaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restVkaMockMvc;

    private Vka vka;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VkaResource vkaResource = new VkaResource(vkaService);
        this.restVkaMockMvc = MockMvcBuilders.standaloneSetup(vkaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vka createEntity(EntityManager em) {
        Vka vka = new Vka()
            .vnr(DEFAULT_VNR)
            .versArt(DEFAULT_VERS_ART)
            .prioritaet(DEFAULT_PRIORITAET)
            .bearbDat(DEFAULT_BEARB_DAT)
            .bearbUhr(DEFAULT_BEARB_UHR)
            .status(DEFAULT_STATUS)
            .rd(DEFAULT_RD)
            .ges(DEFAULT_GES)
            .bza(DEFAULT_BZA)
            .tarif(DEFAULT_TARIF)
            .tarifUnr1(DEFAULT_TARIF_UNR_1)
            .tarifUnr2(DEFAULT_TARIF_UNR_2)
            .mkt1(DEFAULT_MKT_1)
            .bewegSchl(DEFAULT_BEWEG_SCHL)
            .wirkungDat(DEFAULT_WIRKUNG_DAT)
            .antAufnDat(DEFAULT_ANT_AUFN_DAT)
            .antEingDat(DEFAULT_ANT_EING_DAT)
            .anzahlStruk(DEFAULT_ANZAHL_STRUK)
            .btg(DEFAULT_BTG);
        return vka;
    }

    @Before
    public void initTest() {
        vka = createEntity(em);
    }

    @Test
    @Transactional
    public void createVka() throws Exception {
        int databaseSizeBeforeCreate = vkaRepository.findAll().size();

        // Create the Vka
        VkaDTO vkaDTO = vkaMapper.toDto(vka);
        restVkaMockMvc.perform(post("/api/vkas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vkaDTO)))
            .andExpect(status().isCreated());

        // Validate the Vka in the database
        List<Vka> vkaList = vkaRepository.findAll();
        assertThat(vkaList).hasSize(databaseSizeBeforeCreate + 1);
        Vka testVka = vkaList.get(vkaList.size() - 1);
        assertThat(testVka.getVnr()).isEqualTo(DEFAULT_VNR);
        assertThat(testVka.getVersArt()).isEqualTo(DEFAULT_VERS_ART);
        assertThat(testVka.getPrioritaet()).isEqualTo(DEFAULT_PRIORITAET);
        assertThat(testVka.getBearbDat()).isEqualTo(DEFAULT_BEARB_DAT);
        assertThat(testVka.getBearbUhr()).isEqualTo(DEFAULT_BEARB_UHR);
        assertThat(testVka.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVka.getRd()).isEqualTo(DEFAULT_RD);
        assertThat(testVka.getGes()).isEqualTo(DEFAULT_GES);
        assertThat(testVka.getBza()).isEqualTo(DEFAULT_BZA);
        assertThat(testVka.getTarif()).isEqualTo(DEFAULT_TARIF);
        assertThat(testVka.getTarifUnr1()).isEqualTo(DEFAULT_TARIF_UNR_1);
        assertThat(testVka.getTarifUnr2()).isEqualTo(DEFAULT_TARIF_UNR_2);
        assertThat(testVka.getMkt1()).isEqualTo(DEFAULT_MKT_1);
        assertThat(testVka.getBewegSchl()).isEqualTo(DEFAULT_BEWEG_SCHL);
        assertThat(testVka.getWirkungDat()).isEqualTo(DEFAULT_WIRKUNG_DAT);
        assertThat(testVka.getAntAufnDat()).isEqualTo(DEFAULT_ANT_AUFN_DAT);
        assertThat(testVka.getAntEingDat()).isEqualTo(DEFAULT_ANT_EING_DAT);
        assertThat(testVka.getAnzahlStruk()).isEqualTo(DEFAULT_ANZAHL_STRUK);
        assertThat(testVka.getBtg()).isEqualTo(DEFAULT_BTG);
    }

    @Test
    @Transactional
    public void createVkaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vkaRepository.findAll().size();

        // Create the Vka with an existing ID
        vka.setId(1L);
        VkaDTO vkaDTO = vkaMapper.toDto(vka);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVkaMockMvc.perform(post("/api/vkas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vkaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vka in the database
        List<Vka> vkaList = vkaRepository.findAll();
        assertThat(vkaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVkas() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList
        restVkaMockMvc.perform(get("/api/vkas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vka.getId().intValue())))
            .andExpect(jsonPath("$.[*].vnr").value(hasItem(DEFAULT_VNR.toString())))
            .andExpect(jsonPath("$.[*].versArt").value(hasItem(DEFAULT_VERS_ART.toString())))
            .andExpect(jsonPath("$.[*].prioritaet").value(hasItem(DEFAULT_PRIORITAET.toString())))
            .andExpect(jsonPath("$.[*].bearbDat").value(hasItem(DEFAULT_BEARB_DAT.toString())))
            .andExpect(jsonPath("$.[*].bearbUhr").value(hasItem(DEFAULT_BEARB_UHR.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].rd").value(hasItem(DEFAULT_RD.toString())))
            .andExpect(jsonPath("$.[*].ges").value(hasItem(DEFAULT_GES.toString())))
            .andExpect(jsonPath("$.[*].bza").value(hasItem(DEFAULT_BZA.toString())))
            .andExpect(jsonPath("$.[*].tarif").value(hasItem(DEFAULT_TARIF.toString())))
            .andExpect(jsonPath("$.[*].tarifUnr1").value(hasItem(DEFAULT_TARIF_UNR_1.toString())))
            .andExpect(jsonPath("$.[*].tarifUnr2").value(hasItem(DEFAULT_TARIF_UNR_2.toString())))
            .andExpect(jsonPath("$.[*].mkt1").value(hasItem(DEFAULT_MKT_1.toString())))
            .andExpect(jsonPath("$.[*].bewegSchl").value(hasItem(DEFAULT_BEWEG_SCHL.toString())))
            .andExpect(jsonPath("$.[*].wirkungDat").value(hasItem(DEFAULT_WIRKUNG_DAT.toString())))
            .andExpect(jsonPath("$.[*].antAufnDat").value(hasItem(DEFAULT_ANT_AUFN_DAT.toString())))
            .andExpect(jsonPath("$.[*].antEingDat").value(hasItem(DEFAULT_ANT_EING_DAT.toString())))
            .andExpect(jsonPath("$.[*].anzahlStruk").value(hasItem(DEFAULT_ANZAHL_STRUK.toString())))
            .andExpect(jsonPath("$.[*].btg").value(hasItem(DEFAULT_BTG.intValue())));
    }
    
    @Test
    @Transactional
    public void getVka() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get the vka
        restVkaMockMvc.perform(get("/api/vkas/{id}", vka.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vka.getId().intValue()))
            .andExpect(jsonPath("$.vnr").value(DEFAULT_VNR.toString()))
            .andExpect(jsonPath("$.versArt").value(DEFAULT_VERS_ART.toString()))
            .andExpect(jsonPath("$.prioritaet").value(DEFAULT_PRIORITAET.toString()))
            .andExpect(jsonPath("$.bearbDat").value(DEFAULT_BEARB_DAT.toString()))
            .andExpect(jsonPath("$.bearbUhr").value(DEFAULT_BEARB_UHR.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.rd").value(DEFAULT_RD.toString()))
            .andExpect(jsonPath("$.ges").value(DEFAULT_GES.toString()))
            .andExpect(jsonPath("$.bza").value(DEFAULT_BZA.toString()))
            .andExpect(jsonPath("$.tarif").value(DEFAULT_TARIF.toString()))
            .andExpect(jsonPath("$.tarifUnr1").value(DEFAULT_TARIF_UNR_1.toString()))
            .andExpect(jsonPath("$.tarifUnr2").value(DEFAULT_TARIF_UNR_2.toString()))
            .andExpect(jsonPath("$.mkt1").value(DEFAULT_MKT_1.toString()))
            .andExpect(jsonPath("$.bewegSchl").value(DEFAULT_BEWEG_SCHL.toString()))
            .andExpect(jsonPath("$.wirkungDat").value(DEFAULT_WIRKUNG_DAT.toString()))
            .andExpect(jsonPath("$.antAufnDat").value(DEFAULT_ANT_AUFN_DAT.toString()))
            .andExpect(jsonPath("$.antEingDat").value(DEFAULT_ANT_EING_DAT.toString()))
            .andExpect(jsonPath("$.anzahlStruk").value(DEFAULT_ANZAHL_STRUK.toString()))
            .andExpect(jsonPath("$.btg").value(DEFAULT_BTG.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVka() throws Exception {
        // Get the vka
        restVkaMockMvc.perform(get("/api/vkas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVka() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        int databaseSizeBeforeUpdate = vkaRepository.findAll().size();

        // Update the vka
        Vka updatedVka = vkaRepository.findById(vka.getId()).get();
        // Disconnect from session so that the updates on updatedVka are not directly saved in db
        em.detach(updatedVka);
        updatedVka
            .vnr(UPDATED_VNR)
            .versArt(UPDATED_VERS_ART)
            .prioritaet(UPDATED_PRIORITAET)
            .bearbDat(UPDATED_BEARB_DAT)
            .bearbUhr(UPDATED_BEARB_UHR)
            .status(UPDATED_STATUS)
            .rd(UPDATED_RD)
            .ges(UPDATED_GES)
            .bza(UPDATED_BZA)
            .tarif(UPDATED_TARIF)
            .tarifUnr1(UPDATED_TARIF_UNR_1)
            .tarifUnr2(UPDATED_TARIF_UNR_2)
            .mkt1(UPDATED_MKT_1)
            .bewegSchl(UPDATED_BEWEG_SCHL)
            .wirkungDat(UPDATED_WIRKUNG_DAT)
            .antAufnDat(UPDATED_ANT_AUFN_DAT)
            .antEingDat(UPDATED_ANT_EING_DAT)
            .anzahlStruk(UPDATED_ANZAHL_STRUK)
            .btg(UPDATED_BTG);
        VkaDTO vkaDTO = vkaMapper.toDto(updatedVka);

        restVkaMockMvc.perform(put("/api/vkas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vkaDTO)))
            .andExpect(status().isOk());

        // Validate the Vka in the database
        List<Vka> vkaList = vkaRepository.findAll();
        assertThat(vkaList).hasSize(databaseSizeBeforeUpdate);
        Vka testVka = vkaList.get(vkaList.size() - 1);
        assertThat(testVka.getVnr()).isEqualTo(UPDATED_VNR);
        assertThat(testVka.getVersArt()).isEqualTo(UPDATED_VERS_ART);
        assertThat(testVka.getPrioritaet()).isEqualTo(UPDATED_PRIORITAET);
        assertThat(testVka.getBearbDat()).isEqualTo(UPDATED_BEARB_DAT);
        assertThat(testVka.getBearbUhr()).isEqualTo(UPDATED_BEARB_UHR);
        assertThat(testVka.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVka.getRd()).isEqualTo(UPDATED_RD);
        assertThat(testVka.getGes()).isEqualTo(UPDATED_GES);
        assertThat(testVka.getBza()).isEqualTo(UPDATED_BZA);
        assertThat(testVka.getTarif()).isEqualTo(UPDATED_TARIF);
        assertThat(testVka.getTarifUnr1()).isEqualTo(UPDATED_TARIF_UNR_1);
        assertThat(testVka.getTarifUnr2()).isEqualTo(UPDATED_TARIF_UNR_2);
        assertThat(testVka.getMkt1()).isEqualTo(UPDATED_MKT_1);
        assertThat(testVka.getBewegSchl()).isEqualTo(UPDATED_BEWEG_SCHL);
        assertThat(testVka.getWirkungDat()).isEqualTo(UPDATED_WIRKUNG_DAT);
        assertThat(testVka.getAntAufnDat()).isEqualTo(UPDATED_ANT_AUFN_DAT);
        assertThat(testVka.getAntEingDat()).isEqualTo(UPDATED_ANT_EING_DAT);
        assertThat(testVka.getAnzahlStruk()).isEqualTo(UPDATED_ANZAHL_STRUK);
        assertThat(testVka.getBtg()).isEqualTo(UPDATED_BTG);
    }

    @Test
    @Transactional
    public void updateNonExistingVka() throws Exception {
        int databaseSizeBeforeUpdate = vkaRepository.findAll().size();

        // Create the Vka
        VkaDTO vkaDTO = vkaMapper.toDto(vka);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVkaMockMvc.perform(put("/api/vkas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vkaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vka in the database
        List<Vka> vkaList = vkaRepository.findAll();
        assertThat(vkaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVka() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        int databaseSizeBeforeDelete = vkaRepository.findAll().size();

        // Get the vka
        restVkaMockMvc.perform(delete("/api/vkas/{id}", vka.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vka> vkaList = vkaRepository.findAll();
        assertThat(vkaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vka.class);
        Vka vka1 = new Vka();
        vka1.setId(1L);
        Vka vka2 = new Vka();
        vka2.setId(vka1.getId());
        assertThat(vka1).isEqualTo(vka2);
        vka2.setId(2L);
        assertThat(vka1).isNotEqualTo(vka2);
        vka1.setId(null);
        assertThat(vka1).isNotEqualTo(vka2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VkaDTO.class);
        VkaDTO vkaDTO1 = new VkaDTO();
        vkaDTO1.setId(1L);
        VkaDTO vkaDTO2 = new VkaDTO();
        assertThat(vkaDTO1).isNotEqualTo(vkaDTO2);
        vkaDTO2.setId(vkaDTO1.getId());
        assertThat(vkaDTO1).isEqualTo(vkaDTO2);
        vkaDTO2.setId(2L);
        assertThat(vkaDTO1).isNotEqualTo(vkaDTO2);
        vkaDTO1.setId(null);
        assertThat(vkaDTO1).isNotEqualTo(vkaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vkaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vkaMapper.fromId(null)).isNull();
    }
}
