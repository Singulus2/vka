package com.devk.vka.web.rest;

import com.devk.vka.service.dto.VkaCriteria;
import com.devk.vka.service.dto.VkaDTO;
import com.devk.vka.service.mapper.VkaMapper;
import com.devk.vka.web.rest.errors.ExceptionTranslator;
import com.devk.vka.VkaApp;
import com.devk.vka.domain.Vka;
import com.devk.vka.repository.VkaRepository;
import com.devk.vka.service.VkaQueryService;
import com.devk.vka.service.VkaService;

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
    private VkaQueryService vkaQueryService;

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
        final VkaResource vkaResource = new VkaResource(vkaService, vkaQueryService);
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
    public void getAllVkasByVnrIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where vnr equals to DEFAULT_VNR
        defaultVkaShouldBeFound("vnr.equals=" + DEFAULT_VNR);

        // Get all the vkaList where vnr equals to UPDATED_VNR
        defaultVkaShouldNotBeFound("vnr.equals=" + UPDATED_VNR);
    }

    @Test
    @Transactional
    public void getAllVkasByVnrIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where vnr in DEFAULT_VNR or UPDATED_VNR
        defaultVkaShouldBeFound("vnr.in=" + DEFAULT_VNR + "," + UPDATED_VNR);

        // Get all the vkaList where vnr equals to UPDATED_VNR
        defaultVkaShouldNotBeFound("vnr.in=" + UPDATED_VNR);
    }

    @Test
    @Transactional
    public void getAllVkasByVnrIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where vnr is not null
        defaultVkaShouldBeFound("vnr.specified=true");

        // Get all the vkaList where vnr is null
        defaultVkaShouldNotBeFound("vnr.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByVersArtIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where versArt equals to DEFAULT_VERS_ART
        defaultVkaShouldBeFound("versArt.equals=" + DEFAULT_VERS_ART);

        // Get all the vkaList where versArt equals to UPDATED_VERS_ART
        defaultVkaShouldNotBeFound("versArt.equals=" + UPDATED_VERS_ART);
    }

    @Test
    @Transactional
    public void getAllVkasByVersArtIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where versArt in DEFAULT_VERS_ART or UPDATED_VERS_ART
        defaultVkaShouldBeFound("versArt.in=" + DEFAULT_VERS_ART + "," + UPDATED_VERS_ART);

        // Get all the vkaList where versArt equals to UPDATED_VERS_ART
        defaultVkaShouldNotBeFound("versArt.in=" + UPDATED_VERS_ART);
    }

    @Test
    @Transactional
    public void getAllVkasByVersArtIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where versArt is not null
        defaultVkaShouldBeFound("versArt.specified=true");

        // Get all the vkaList where versArt is null
        defaultVkaShouldNotBeFound("versArt.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByPrioritaetIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where prioritaet equals to DEFAULT_PRIORITAET
        defaultVkaShouldBeFound("prioritaet.equals=" + DEFAULT_PRIORITAET);

        // Get all the vkaList where prioritaet equals to UPDATED_PRIORITAET
        defaultVkaShouldNotBeFound("prioritaet.equals=" + UPDATED_PRIORITAET);
    }

    @Test
    @Transactional
    public void getAllVkasByPrioritaetIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where prioritaet in DEFAULT_PRIORITAET or UPDATED_PRIORITAET
        defaultVkaShouldBeFound("prioritaet.in=" + DEFAULT_PRIORITAET + "," + UPDATED_PRIORITAET);

        // Get all the vkaList where prioritaet equals to UPDATED_PRIORITAET
        defaultVkaShouldNotBeFound("prioritaet.in=" + UPDATED_PRIORITAET);
    }

    @Test
    @Transactional
    public void getAllVkasByPrioritaetIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where prioritaet is not null
        defaultVkaShouldBeFound("prioritaet.specified=true");

        // Get all the vkaList where prioritaet is null
        defaultVkaShouldNotBeFound("prioritaet.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByBearbDatIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbDat equals to DEFAULT_BEARB_DAT
        defaultVkaShouldBeFound("bearbDat.equals=" + DEFAULT_BEARB_DAT);

        // Get all the vkaList where bearbDat equals to UPDATED_BEARB_DAT
        defaultVkaShouldNotBeFound("bearbDat.equals=" + UPDATED_BEARB_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByBearbDatIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbDat in DEFAULT_BEARB_DAT or UPDATED_BEARB_DAT
        defaultVkaShouldBeFound("bearbDat.in=" + DEFAULT_BEARB_DAT + "," + UPDATED_BEARB_DAT);

        // Get all the vkaList where bearbDat equals to UPDATED_BEARB_DAT
        defaultVkaShouldNotBeFound("bearbDat.in=" + UPDATED_BEARB_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByBearbDatIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbDat is not null
        defaultVkaShouldBeFound("bearbDat.specified=true");

        // Get all the vkaList where bearbDat is null
        defaultVkaShouldNotBeFound("bearbDat.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByBearbUhrIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbUhr equals to DEFAULT_BEARB_UHR
        defaultVkaShouldBeFound("bearbUhr.equals=" + DEFAULT_BEARB_UHR);

        // Get all the vkaList where bearbUhr equals to UPDATED_BEARB_UHR
        defaultVkaShouldNotBeFound("bearbUhr.equals=" + UPDATED_BEARB_UHR);
    }

    @Test
    @Transactional
    public void getAllVkasByBearbUhrIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbUhr in DEFAULT_BEARB_UHR or UPDATED_BEARB_UHR
        defaultVkaShouldBeFound("bearbUhr.in=" + DEFAULT_BEARB_UHR + "," + UPDATED_BEARB_UHR);

        // Get all the vkaList where bearbUhr equals to UPDATED_BEARB_UHR
        defaultVkaShouldNotBeFound("bearbUhr.in=" + UPDATED_BEARB_UHR);
    }

    @Test
    @Transactional
    public void getAllVkasByBearbUhrIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bearbUhr is not null
        defaultVkaShouldBeFound("bearbUhr.specified=true");

        // Get all the vkaList where bearbUhr is null
        defaultVkaShouldNotBeFound("bearbUhr.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where status equals to DEFAULT_STATUS
        defaultVkaShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the vkaList where status equals to UPDATED_STATUS
        defaultVkaShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVkasByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultVkaShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the vkaList where status equals to UPDATED_STATUS
        defaultVkaShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllVkasByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where status is not null
        defaultVkaShouldBeFound("status.specified=true");

        // Get all the vkaList where status is null
        defaultVkaShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByRdIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where rd equals to DEFAULT_RD
        defaultVkaShouldBeFound("rd.equals=" + DEFAULT_RD);

        // Get all the vkaList where rd equals to UPDATED_RD
        defaultVkaShouldNotBeFound("rd.equals=" + UPDATED_RD);
    }

    @Test
    @Transactional
    public void getAllVkasByRdIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where rd in DEFAULT_RD or UPDATED_RD
        defaultVkaShouldBeFound("rd.in=" + DEFAULT_RD + "," + UPDATED_RD);

        // Get all the vkaList where rd equals to UPDATED_RD
        defaultVkaShouldNotBeFound("rd.in=" + UPDATED_RD);
    }

    @Test
    @Transactional
    public void getAllVkasByRdIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where rd is not null
        defaultVkaShouldBeFound("rd.specified=true");

        // Get all the vkaList where rd is null
        defaultVkaShouldNotBeFound("rd.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByGesIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where ges equals to DEFAULT_GES
        defaultVkaShouldBeFound("ges.equals=" + DEFAULT_GES);

        // Get all the vkaList where ges equals to UPDATED_GES
        defaultVkaShouldNotBeFound("ges.equals=" + UPDATED_GES);
    }

    @Test
    @Transactional
    public void getAllVkasByGesIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where ges in DEFAULT_GES or UPDATED_GES
        defaultVkaShouldBeFound("ges.in=" + DEFAULT_GES + "," + UPDATED_GES);

        // Get all the vkaList where ges equals to UPDATED_GES
        defaultVkaShouldNotBeFound("ges.in=" + UPDATED_GES);
    }

    @Test
    @Transactional
    public void getAllVkasByGesIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where ges is not null
        defaultVkaShouldBeFound("ges.specified=true");

        // Get all the vkaList where ges is null
        defaultVkaShouldNotBeFound("ges.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByBzaIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bza equals to DEFAULT_BZA
        defaultVkaShouldBeFound("bza.equals=" + DEFAULT_BZA);

        // Get all the vkaList where bza equals to UPDATED_BZA
        defaultVkaShouldNotBeFound("bza.equals=" + UPDATED_BZA);
    }

    @Test
    @Transactional
    public void getAllVkasByBzaIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bza in DEFAULT_BZA or UPDATED_BZA
        defaultVkaShouldBeFound("bza.in=" + DEFAULT_BZA + "," + UPDATED_BZA);

        // Get all the vkaList where bza equals to UPDATED_BZA
        defaultVkaShouldNotBeFound("bza.in=" + UPDATED_BZA);
    }

    @Test
    @Transactional
    public void getAllVkasByBzaIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bza is not null
        defaultVkaShouldBeFound("bza.specified=true");

        // Get all the vkaList where bza is null
        defaultVkaShouldNotBeFound("bza.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByTarifIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarif equals to DEFAULT_TARIF
        defaultVkaShouldBeFound("tarif.equals=" + DEFAULT_TARIF);

        // Get all the vkaList where tarif equals to UPDATED_TARIF
        defaultVkaShouldNotBeFound("tarif.equals=" + UPDATED_TARIF);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarif in DEFAULT_TARIF or UPDATED_TARIF
        defaultVkaShouldBeFound("tarif.in=" + DEFAULT_TARIF + "," + UPDATED_TARIF);

        // Get all the vkaList where tarif equals to UPDATED_TARIF
        defaultVkaShouldNotBeFound("tarif.in=" + UPDATED_TARIF);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarif is not null
        defaultVkaShouldBeFound("tarif.specified=true");

        // Get all the vkaList where tarif is null
        defaultVkaShouldNotBeFound("tarif.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr1IsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr1 equals to DEFAULT_TARIF_UNR_1
        defaultVkaShouldBeFound("tarifUnr1.equals=" + DEFAULT_TARIF_UNR_1);

        // Get all the vkaList where tarifUnr1 equals to UPDATED_TARIF_UNR_1
        defaultVkaShouldNotBeFound("tarifUnr1.equals=" + UPDATED_TARIF_UNR_1);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr1IsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr1 in DEFAULT_TARIF_UNR_1 or UPDATED_TARIF_UNR_1
        defaultVkaShouldBeFound("tarifUnr1.in=" + DEFAULT_TARIF_UNR_1 + "," + UPDATED_TARIF_UNR_1);

        // Get all the vkaList where tarifUnr1 equals to UPDATED_TARIF_UNR_1
        defaultVkaShouldNotBeFound("tarifUnr1.in=" + UPDATED_TARIF_UNR_1);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr1IsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr1 is not null
        defaultVkaShouldBeFound("tarifUnr1.specified=true");

        // Get all the vkaList where tarifUnr1 is null
        defaultVkaShouldNotBeFound("tarifUnr1.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr2IsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr2 equals to DEFAULT_TARIF_UNR_2
        defaultVkaShouldBeFound("tarifUnr2.equals=" + DEFAULT_TARIF_UNR_2);

        // Get all the vkaList where tarifUnr2 equals to UPDATED_TARIF_UNR_2
        defaultVkaShouldNotBeFound("tarifUnr2.equals=" + UPDATED_TARIF_UNR_2);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr2IsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr2 in DEFAULT_TARIF_UNR_2 or UPDATED_TARIF_UNR_2
        defaultVkaShouldBeFound("tarifUnr2.in=" + DEFAULT_TARIF_UNR_2 + "," + UPDATED_TARIF_UNR_2);

        // Get all the vkaList where tarifUnr2 equals to UPDATED_TARIF_UNR_2
        defaultVkaShouldNotBeFound("tarifUnr2.in=" + UPDATED_TARIF_UNR_2);
    }

    @Test
    @Transactional
    public void getAllVkasByTarifUnr2IsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where tarifUnr2 is not null
        defaultVkaShouldBeFound("tarifUnr2.specified=true");

        // Get all the vkaList where tarifUnr2 is null
        defaultVkaShouldNotBeFound("tarifUnr2.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByMkt1IsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where mkt1 equals to DEFAULT_MKT_1
        defaultVkaShouldBeFound("mkt1.equals=" + DEFAULT_MKT_1);

        // Get all the vkaList where mkt1 equals to UPDATED_MKT_1
        defaultVkaShouldNotBeFound("mkt1.equals=" + UPDATED_MKT_1);
    }

    @Test
    @Transactional
    public void getAllVkasByMkt1IsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where mkt1 in DEFAULT_MKT_1 or UPDATED_MKT_1
        defaultVkaShouldBeFound("mkt1.in=" + DEFAULT_MKT_1 + "," + UPDATED_MKT_1);

        // Get all the vkaList where mkt1 equals to UPDATED_MKT_1
        defaultVkaShouldNotBeFound("mkt1.in=" + UPDATED_MKT_1);
    }

    @Test
    @Transactional
    public void getAllVkasByMkt1IsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where mkt1 is not null
        defaultVkaShouldBeFound("mkt1.specified=true");

        // Get all the vkaList where mkt1 is null
        defaultVkaShouldNotBeFound("mkt1.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByBewegSchlIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bewegSchl equals to DEFAULT_BEWEG_SCHL
        defaultVkaShouldBeFound("bewegSchl.equals=" + DEFAULT_BEWEG_SCHL);

        // Get all the vkaList where bewegSchl equals to UPDATED_BEWEG_SCHL
        defaultVkaShouldNotBeFound("bewegSchl.equals=" + UPDATED_BEWEG_SCHL);
    }

    @Test
    @Transactional
    public void getAllVkasByBewegSchlIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bewegSchl in DEFAULT_BEWEG_SCHL or UPDATED_BEWEG_SCHL
        defaultVkaShouldBeFound("bewegSchl.in=" + DEFAULT_BEWEG_SCHL + "," + UPDATED_BEWEG_SCHL);

        // Get all the vkaList where bewegSchl equals to UPDATED_BEWEG_SCHL
        defaultVkaShouldNotBeFound("bewegSchl.in=" + UPDATED_BEWEG_SCHL);
    }

    @Test
    @Transactional
    public void getAllVkasByBewegSchlIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where bewegSchl is not null
        defaultVkaShouldBeFound("bewegSchl.specified=true");

        // Get all the vkaList where bewegSchl is null
        defaultVkaShouldNotBeFound("bewegSchl.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByWirkungDatIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where wirkungDat equals to DEFAULT_WIRKUNG_DAT
        defaultVkaShouldBeFound("wirkungDat.equals=" + DEFAULT_WIRKUNG_DAT);

        // Get all the vkaList where wirkungDat equals to UPDATED_WIRKUNG_DAT
        defaultVkaShouldNotBeFound("wirkungDat.equals=" + UPDATED_WIRKUNG_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByWirkungDatIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where wirkungDat in DEFAULT_WIRKUNG_DAT or UPDATED_WIRKUNG_DAT
        defaultVkaShouldBeFound("wirkungDat.in=" + DEFAULT_WIRKUNG_DAT + "," + UPDATED_WIRKUNG_DAT);

        // Get all the vkaList where wirkungDat equals to UPDATED_WIRKUNG_DAT
        defaultVkaShouldNotBeFound("wirkungDat.in=" + UPDATED_WIRKUNG_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByWirkungDatIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where wirkungDat is not null
        defaultVkaShouldBeFound("wirkungDat.specified=true");

        // Get all the vkaList where wirkungDat is null
        defaultVkaShouldNotBeFound("wirkungDat.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByAntAufnDatIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antAufnDat equals to DEFAULT_ANT_AUFN_DAT
        defaultVkaShouldBeFound("antAufnDat.equals=" + DEFAULT_ANT_AUFN_DAT);

        // Get all the vkaList where antAufnDat equals to UPDATED_ANT_AUFN_DAT
        defaultVkaShouldNotBeFound("antAufnDat.equals=" + UPDATED_ANT_AUFN_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByAntAufnDatIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antAufnDat in DEFAULT_ANT_AUFN_DAT or UPDATED_ANT_AUFN_DAT
        defaultVkaShouldBeFound("antAufnDat.in=" + DEFAULT_ANT_AUFN_DAT + "," + UPDATED_ANT_AUFN_DAT);

        // Get all the vkaList where antAufnDat equals to UPDATED_ANT_AUFN_DAT
        defaultVkaShouldNotBeFound("antAufnDat.in=" + UPDATED_ANT_AUFN_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByAntAufnDatIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antAufnDat is not null
        defaultVkaShouldBeFound("antAufnDat.specified=true");

        // Get all the vkaList where antAufnDat is null
        defaultVkaShouldNotBeFound("antAufnDat.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByAntEingDatIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antEingDat equals to DEFAULT_ANT_EING_DAT
        defaultVkaShouldBeFound("antEingDat.equals=" + DEFAULT_ANT_EING_DAT);

        // Get all the vkaList where antEingDat equals to UPDATED_ANT_EING_DAT
        defaultVkaShouldNotBeFound("antEingDat.equals=" + UPDATED_ANT_EING_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByAntEingDatIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antEingDat in DEFAULT_ANT_EING_DAT or UPDATED_ANT_EING_DAT
        defaultVkaShouldBeFound("antEingDat.in=" + DEFAULT_ANT_EING_DAT + "," + UPDATED_ANT_EING_DAT);

        // Get all the vkaList where antEingDat equals to UPDATED_ANT_EING_DAT
        defaultVkaShouldNotBeFound("antEingDat.in=" + UPDATED_ANT_EING_DAT);
    }

    @Test
    @Transactional
    public void getAllVkasByAntEingDatIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where antEingDat is not null
        defaultVkaShouldBeFound("antEingDat.specified=true");

        // Get all the vkaList where antEingDat is null
        defaultVkaShouldNotBeFound("antEingDat.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByAnzahlStrukIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where anzahlStruk equals to DEFAULT_ANZAHL_STRUK
        defaultVkaShouldBeFound("anzahlStruk.equals=" + DEFAULT_ANZAHL_STRUK);

        // Get all the vkaList where anzahlStruk equals to UPDATED_ANZAHL_STRUK
        defaultVkaShouldNotBeFound("anzahlStruk.equals=" + UPDATED_ANZAHL_STRUK);
    }

    @Test
    @Transactional
    public void getAllVkasByAnzahlStrukIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where anzahlStruk in DEFAULT_ANZAHL_STRUK or UPDATED_ANZAHL_STRUK
        defaultVkaShouldBeFound("anzahlStruk.in=" + DEFAULT_ANZAHL_STRUK + "," + UPDATED_ANZAHL_STRUK);

        // Get all the vkaList where anzahlStruk equals to UPDATED_ANZAHL_STRUK
        defaultVkaShouldNotBeFound("anzahlStruk.in=" + UPDATED_ANZAHL_STRUK);
    }

    @Test
    @Transactional
    public void getAllVkasByAnzahlStrukIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where anzahlStruk is not null
        defaultVkaShouldBeFound("anzahlStruk.specified=true");

        // Get all the vkaList where anzahlStruk is null
        defaultVkaShouldNotBeFound("anzahlStruk.specified=false");
    }

    @Test
    @Transactional
    public void getAllVkasByBtgIsEqualToSomething() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where btg equals to DEFAULT_BTG
        defaultVkaShouldBeFound("btg.equals=" + DEFAULT_BTG);

        // Get all the vkaList where btg equals to UPDATED_BTG
        defaultVkaShouldNotBeFound("btg.equals=" + UPDATED_BTG);
    }

    @Test
    @Transactional
    public void getAllVkasByBtgIsInShouldWork() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where btg in DEFAULT_BTG or UPDATED_BTG
        defaultVkaShouldBeFound("btg.in=" + DEFAULT_BTG + "," + UPDATED_BTG);

        // Get all the vkaList where btg equals to UPDATED_BTG
        defaultVkaShouldNotBeFound("btg.in=" + UPDATED_BTG);
    }

    @Test
    @Transactional
    public void getAllVkasByBtgIsNullOrNotNull() throws Exception {
        // Initialize the database
        vkaRepository.saveAndFlush(vka);

        // Get all the vkaList where btg is not null
        defaultVkaShouldBeFound("btg.specified=true");

        // Get all the vkaList where btg is null
        defaultVkaShouldNotBeFound("btg.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultVkaShouldBeFound(String filter) throws Exception {
        restVkaMockMvc.perform(get("/api/vkas?sort=id,desc&" + filter))
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

        // Check, that the count call also returns 1
        restVkaMockMvc.perform(get("/api/vkas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultVkaShouldNotBeFound(String filter) throws Exception {
        restVkaMockMvc.perform(get("/api/vkas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restVkaMockMvc.perform(get("/api/vkas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
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
