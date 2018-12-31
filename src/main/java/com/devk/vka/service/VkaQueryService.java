package com.devk.vka.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devk.vka.domain.Vka;
import com.devk.vka.domain.Vka_;
import com.devk.vka.repository.VkaRepository;
import com.devk.vka.service.dto.VkaCriteria;
import com.devk.vka.service.dto.VkaDTO;
import com.devk.vka.service.mapper.VkaMapper;

import io.github.jhipster.service.QueryService;


/**
 * Service for executing complex queries for Vka entities in the database.
 * The main input is a {@link VkaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VkaDTO} or a {@link Page} of {@link VkaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VkaQueryService extends QueryService<Vka> {

    private final Logger log = LoggerFactory.getLogger(VkaQueryService.class);

    private final VkaRepository vkaRepository;

    private final VkaMapper vkaMapper;

    public VkaQueryService(VkaRepository vkaRepository, VkaMapper vkaMapper) {
        this.vkaRepository = vkaRepository;
        this.vkaMapper = vkaMapper;
    }

    /**
     * Return a {@link List} of {@link VkaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VkaDTO> findByCriteria(VkaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Vka> specification = createSpecification(criteria);
        return vkaMapper.toDto(vkaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VkaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VkaDTO> findByCriteria(VkaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vka> specification = createSpecification(criteria);
        return vkaRepository.findAll(specification, page)
            .map(vkaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VkaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vka> specification = createSpecification(criteria);
        return vkaRepository.count(specification);
    }

    /**
     * Function to convert VkaCriteria to a {@link Specification}
     */
    private Specification<Vka> createSpecification(VkaCriteria criteria) {
        Specification<Vka> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getVnr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVnr(), Vka_.vnr));
            }
            if (criteria.getMkt1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMkt1(), Vka_.mkt1));
            }
            if (criteria.getWirkungDatAb() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWirkungDatAb(), Vka_.wirkungDat));
            }
        }
        return specification;
    }
}
