package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.CarroDTO;
import com.jciterceros.locadoracarrosjpa.dto.ModeloDTO;
import com.jciterceros.locadoracarrosjpa.entities.Carro;
import com.jciterceros.locadoracarrosjpa.entities.Modelo;
import com.jciterceros.locadoracarrosjpa.repositories.CarroRepository;
import com.jciterceros.locadoracarrosjpa.repositories.FabricanteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.ModeloRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    public static final String ID_DO_MODELO_NAO_ENCONTRADO = "ID do Modelo não encontrado";
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;
    private final CarroRepository carroRepository;
    private final FabricanteRepository fabricanteRepository;
    private final ModeloRepository modeloRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository, FabricanteRepository fabricanteRepository, ModeloRepository modeloRepository, ModelMapper mapper) {
        this.carroRepository = carroRepository;
        this.fabricanteRepository = fabricanteRepository;
        this.modeloRepository = modeloRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        if (carros.isEmpty()) {
            throw new ResourceNotFoundException("Não existem carros cadastrados");
        }

        configureMapper();

        return carros.stream()
                .map(carro -> mapper.map(carro, CarroDTO.class))
                .sorted(Comparator.comparing(CarroDTO::getId)).toList();
    }

    @Transactional(readOnly = true)
    public CarroDTO findById(Long id) {
        return carroRepository.findById(id)
                .map(carro -> mapper.map(carro, CarroDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID do Carro não encontrado"));
    }

    @Transactional(readOnly = false)
    public CarroDTO insert(CarroDTO carroDTO) {

        boolean exists = carroRepository.existsByPlaca(carroDTO.getPlaca());
        if (exists) {
            throw new DatabaseException("Placa do Carro já existe");
        }

        Modelo modelo = modeloRepository.findById(carroDTO.getId_modelo())
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO));

        configureMapper();

        Carro entity = mapper.map(carroDTO, Carro.class);
        entity.setFabricante(modelo.getFabricante());
        entity.setModelo(modelo);

        return mapper.map(carroRepository.save(entity), CarroDTO.class);
    }

    @Transactional(readOnly = false)
    public CarroDTO update(Long id, CarroDTO carroDTO) {
        boolean exists = carroRepository.existsByPlaca(carroDTO.getPlaca());
        if (!exists) {
            throw new DatabaseException("Placa do Carro não existe");
        }

        Optional<Carro> carro = carroRepository.findById(id);
        if(carro.isEmpty()) {
            throw new ResourceNotFoundException("ID do Carro não encontrado");
        }

        if(carroRepository.existsByPlacaAndIdNot(carroDTO.getPlaca(), id)){
            throw new DatabaseException("Placa do Carro já esta associada a outro carro");
        }

        Modelo modelo = modeloRepository.findById(carroDTO.getId_modelo())
                .orElseThrow(() -> new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO));
        if(modelo==null) {
            throw new ResourceNotFoundException(ID_DO_MODELO_NAO_ENCONTRADO);
        }

        configureMapper();

        Carro entity = mapper.map(carroDTO, carro.get().getClass());
        entity.setId(id);
        entity.setFabricante(modelo.getFabricante());
        entity.setModelo(modelo);

        return mapper.map(carroRepository.save(entity), CarroDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Carro entity = carroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Carro não encontrado"));

        // verify if there are any locacoes associated with the carro
        if(!entity.getLocacoes().isEmpty()){
            throw new DatabaseException("Carro possui locações associadas");
        }
        carroRepository.deleteById(entity.getId());
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Carro, CarroDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setPlaca(source.getPlaca());
                map().setCor(source.getCor());
                map().setDisponivel(source.getDisponivel());
                map().setValorLocacao(source.getValorLocacao());
                map().setAno(source.getAno());
                map().setId_fabricante(source.getFabricante().getId());
                map().setNome_fabricante(source.getFabricante().getNome());
                map().setId_modelo(source.getModelo().getId());
                map().setNome_modelo(source.getModelo().getNome());
            }
        });
        mapperAllreadyConfigured = true;
    }
}

