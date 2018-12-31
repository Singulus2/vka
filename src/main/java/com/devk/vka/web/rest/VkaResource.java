package com.devk.vka.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.devk.vka.service.VkaQueryService;
import com.devk.vka.service.VkaService;
import com.devk.vka.service.dto.VkaCriteria;
import com.devk.vka.service.dto.VkaDTO;
import com.devk.vka.web.rest.errors.BadRequestAlertException;
import com.devk.vka.web.rest.util.HeaderUtil;
import com.devk.vka.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Vka.
 */
@RestController
@RequestMapping("/api")
public class VkaResource {

    private final Logger log = LoggerFactory.getLogger(VkaResource.class);

    private static final String ENTITY_NAME = "vka";

    private final VkaService vkaService;

    private final VkaQueryService vkaQueryService;

    public VkaResource(VkaService vkaService, VkaQueryService vkaQueryService) {
        this.vkaService = vkaService;
        this.vkaQueryService = vkaQueryService;
    }

    /**
     * POST  /vkas : Create a new vka.
     *
     * @param vkaDTO the vkaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vkaDTO, or with status 400 (Bad Request) if the vka has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vkas")
    @Timed
    public ResponseEntity<VkaDTO> createVka(@RequestBody VkaDTO vkaDTO) throws URISyntaxException {
        log.debug("REST request to save Vka : {}", vkaDTO);
        if (vkaDTO.getId() != null) {
            throw new BadRequestAlertException("A new vka cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VkaDTO result = vkaService.save(vkaDTO);
        return ResponseEntity.created(new URI("/api/vkas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vkas : Updates an existing vka.
     *
     * @param vkaDTO the vkaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vkaDTO,
     * or with status 400 (Bad Request) if the vkaDTO is not valid,
     * or with status 500 (Internal Server Error) if the vkaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vkas")
    @Timed
    public ResponseEntity<VkaDTO> updateVka(@RequestBody VkaDTO vkaDTO) throws URISyntaxException {
        log.debug("REST request to update Vka : {}", vkaDTO);
        if (vkaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VkaDTO result = vkaService.save(vkaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vkaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vkas : get all the vkas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vkas in body
     */
    @GetMapping("/vkas")
    @Timed
    public ResponseEntity<List<VkaDTO>> getAllVkas(VkaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Vkas by criteria: {}", criteria);
        Page<VkaDTO> page = vkaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vkas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /vkas/count : count all the vkas.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/vkas/count")
    @Timed
    public ResponseEntity<Long> countVkas(VkaCriteria criteria) {
        log.debug("REST request to count Vkas by criteria: {}", criteria);
        return ResponseEntity.ok().body(vkaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /vkas/:id : get the "id" vka.
     *
     * @param id the id of the vkaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vkaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vkas/{id}")
    @Timed
    public ResponseEntity<VkaDTO> getVka(@PathVariable Long id) {
        log.debug("REST request to get Vka : {}", id);
        Optional<VkaDTO> vkaDTO = vkaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vkaDTO);
    }

    /**
     * DELETE  /vkas/:id : delete the "id" vka.
     *
     * @param id the id of the vkaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vkas/{id}")
    @Timed
    public ResponseEntity<Void> deleteVka(@PathVariable Long id) {
        log.debug("REST request to delete Vka : {}", id);
        vkaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
