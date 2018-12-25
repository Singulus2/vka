package com.devk.vka.service.mapper;

import com.devk.vka.domain.*;
import com.devk.vka.service.dto.VkaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vka and its DTO VkaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VkaMapper extends EntityMapper<VkaDTO, Vka> {



    default Vka fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vka vka = new Vka();
        vka.setId(id);
        return vka;
    }
}
