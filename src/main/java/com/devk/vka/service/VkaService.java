package com.devk.vka.service;

import com.devk.vka.domain.Vka;
import com.devk.vka.repository.VkaRepository;
import com.devk.vka.service.dto.VkaDTO;
import com.devk.vka.service.mapper.VkaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vka.
 */
@Service
@Transactional
public class VkaService {

    private final Logger log = LoggerFactory.getLogger(VkaService.class);

    private final VkaRepository vkaRepository;

    private final VkaMapper vkaMapper;

    public VkaService(VkaRepository vkaRepository, VkaMapper vkaMapper) {
        this.vkaRepository = vkaRepository;
        this.vkaMapper = vkaMapper;
    }

    /**
     * Save a vka.
     *
     * @param vkaDTO the entity to save
     * @return the persisted entity
     */
    public VkaDTO save(VkaDTO vkaDTO) {
        log.debug("Request to save Vka : {}", vkaDTO);

        Vka vka = vkaMapper.toEntity(vkaDTO);
        vka = vkaRepository.save(vka);
        return vkaMapper.toDto(vka);
    }

    /**
     * Get all the vkas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<VkaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vkas");
        return vkaRepository.findAll(pageable)
            .map(vkaMapper::toDto);
    }


    /**
     * Get one vka by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<VkaDTO> findOne(Long id) {
        log.debug("Request to get Vka : {}", id);
        return vkaRepository.findById(id)
            .map(vkaMapper::toDto);
    }

    /**
     * Delete the vka by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Vka : {}", id);
        vkaRepository.deleteById(id);
    }
}
