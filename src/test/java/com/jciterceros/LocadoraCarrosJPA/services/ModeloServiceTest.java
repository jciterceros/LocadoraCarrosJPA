package com.jciterceros.LocadoraCarrosJPA.services;

import com.jciterceros.LocadoraCarrosJPA.dto.ModeloDTO;
import com.jciterceros.LocadoraCarrosJPA.entities.Fabricante;
import com.jciterceros.LocadoraCarrosJPA.entities.Modelo;
import com.jciterceros.LocadoraCarrosJPA.repositories.ModeloRepository;
import com.jciterceros.LocadoraCarrosJPA.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ModeloServiceTest {

    @Mock
    private ModeloRepository modeloRepository;

    @InjectMocks
    private ModeloService modeloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testModeloService() {
        ModeloRepository modeloRepository = Mockito.mock(ModeloRepository.class);
        ModeloService modeloService = new ModeloService(modeloRepository);
        assertNotNull(modeloService, "ModeloService should have been instantiated");
    }

    @Test
    void testFindAll() {
        when(modeloRepository.findAll()).thenReturn(Collections.singletonList(new Modelo()));
        assertEquals(1, modeloService.findAll().size());
    }

    @Test
    void testFindAllSet() {
        when(modeloRepository.findAll()).thenReturn(Collections.singletonList(new Modelo()));
        assertEquals(1, modeloService.findAllSet().size());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Modelo modelo = new Modelo("UNO", new Fabricante(1L,"fabricante"));
        assertEquals("UNO",modelo.getNome());
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(modeloRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> modeloService.findById(id));
    }

    @Test
    void testInsert() {
        ModeloDTO modeloDTO = new ModeloDTO();
        Fabricante fabricante = new Fabricante(1L,"fabricante");
        Modelo modelo = new Modelo("UNO", fabricante);
        modeloDTO.setId(1L);
        modeloDTO.setNome("UNO");
        modeloDTO.setFabricante(fabricante);
        assertEquals(modeloDTO.getNome(), modelo.getNome());
    }
}