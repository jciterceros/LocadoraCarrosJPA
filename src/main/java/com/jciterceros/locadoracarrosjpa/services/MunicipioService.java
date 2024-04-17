package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.MunicipioDTO;
import com.jciterceros.locadoracarrosjpa.entities.Estado;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import com.jciterceros.locadoracarrosjpa.repositories.ClienteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.EstadoRepository;
import com.jciterceros.locadoracarrosjpa.repositories.MunicipioRepository;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoraRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;

    private final EstadoRepository estadoRepository;
    private final MunicipioRepository municipioRepository;

    private final ClienteRepository clienteRepository;
    private final SeguradoraRepository seguradoraRepository;
    private EntityManager entityManager;

    @Autowired
    public MunicipioService(EstadoRepository estadoRepository, MunicipioRepository municipioRepository, ModelMapper mapper, ClienteRepository clienteRepository, SeguradoraRepository seguradoraRepository, EntityManager entityManager) {
        this.estadoRepository = estadoRepository;
        this.municipioRepository = municipioRepository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.seguradoraRepository = seguradoraRepository;
        this.entityManager = entityManager;
        configureMapper();
    }

    @Transactional
    public List<MunicipioDTO> findAll() {
        // TODO - Verificar a necesidade de Refatorar as demais classes para Criteria API
        //      - Excelente Tempo de Resposta primeira consulta 11s depois da tercerira consulta < 3s

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Municipio> criteriaQuery = criteriaBuilder.createQuery(Municipio.class);

        Root<Municipio> root = criteriaQuery.from(Municipio.class);
        criteriaQuery.select(root);

        List<Municipio> municipios = entityManager.createQuery(criteriaQuery).getResultList();

        // List<Municipio> municipios = municipioRepository.searchAll(); //.findAll();
        if (municipios.isEmpty()) {
            throw new ResourceNotFoundException("Não existem municípios cadastrados");
        }

        return municipios.stream()
                .map(municipio -> mapper.map(municipio, MunicipioDTO.class))
                .sorted(Comparator.comparing(MunicipioDTO::getId))
                .toList();
    }

    @Transactional(readOnly = true)
    public MunicipioDTO findById(Long id) {
        return municipioRepository.searchById(id).stream()//.findById(id)
                .map(municipio -> mapper.map(municipio, MunicipioDTO.class))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));
    }

    @Transactional(readOnly = false)
    public MunicipioDTO insert(MunicipioDTO municipioDTO) {
        Estado estado = estadoRepository.findById(municipioDTO.getId_estado())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Estado não encontrado"));

        if (municipioRepository.existsByDescricaoAndEstadoId(municipioDTO.getDescricao(), municipioDTO.getId_estado())) {
            throw new DatabaseException("Já existe um Município com esta descrição para o Estado informado");
        }

        Municipio municipio = new Municipio();
        municipio.setDescricao(municipioDTO.getDescricao());
        municipio.setEstado(estado);
        return mapper.map(municipioRepository.save(municipio), MunicipioDTO.class);
    }

    @Transactional(readOnly = false)
    public MunicipioDTO update(Long id, MunicipioDTO municipioDTO) {
        Municipio entity = municipioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));

        Estado estado = estadoRepository.findById(municipioDTO.getId_estado())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Estado não encontrado"));

        Optional<Municipio> municipioIsAlreadyExist = municipioRepository.findByDescricaoAndEstadoId(municipioDTO.getDescricao(), municipioDTO.getId_estado());
        if (municipioIsAlreadyExist.isPresent() && !municipioIsAlreadyExist.get().getId().equals(id)) {
            throw new DatabaseException("Já existe um Município com esta descrição para o Estado informado");
        }

        entity.setDescricao(municipioDTO.getDescricao());
        entity.setEstado(estado);
        return mapper.map(municipioRepository.save(entity), MunicipioDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {

        if(!municipioRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID do Município não encontrado");
        }

        if (clienteRepository.existsByMunicipioId(id)) {
            throw new DatabaseException("Não é possível excluir um município que possui clientes associados");
        }

        if (seguradoraRepository.existsByMunicipioId(id)) {
            throw new DatabaseException("Não é possível excluir um município que possui seguradoras associadas");
        }

        municipioRepository.deleteById(id);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Municipio, MunicipioDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDescricao(source.getDescricao());
                map().setId_estado(source.getEstado().getId());
                map().setEstado(source.getEstado());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
