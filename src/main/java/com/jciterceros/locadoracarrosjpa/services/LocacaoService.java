package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.ClientetelefoneDTO;
import com.jciterceros.locadoracarrosjpa.dto.LocacaoDTO;
import com.jciterceros.locadoracarrosjpa.entities.*;
import com.jciterceros.locadoracarrosjpa.repositories.*;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class LocacaoService {
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;

    private final FabricanteRepository fabricanteRepository;
    private final ModeloRepository modeloRepository;
    private final CarroRepository carroRepository;
    private final SeguradoraRepository seguradoraRepository;
    private final SeguradoratelefoneRepository seguradoratelefoneRepository;
    private final ClienteRepository clienteRepository;
    private final ClientetelefoneRepository clientetelefoneRepository;

    private final LocacaoRepository locacaoRepository;

    @Autowired
    public LocacaoService(ModelMapper mapper, FabricanteRepository fabricanteRepository, ModeloRepository modeloRepository, CarroRepository carroRepository, SeguradoraRepository seguradoraRepository, SeguradoratelefoneRepository seguradoratelefoneRepository, ClienteRepository clienteRepository, ClientetelefoneRepository clientetelefoneRepository, LocacaoRepository locacaoRepository) {
        this.mapper = mapper;
        this.fabricanteRepository = fabricanteRepository;
        this.modeloRepository = modeloRepository;
        this.carroRepository = carroRepository;
        this.seguradoraRepository = seguradoraRepository;
        this.seguradoratelefoneRepository = seguradoratelefoneRepository;
        this.clienteRepository = clienteRepository;
        this.clientetelefoneRepository = clientetelefoneRepository;
        this.locacaoRepository = locacaoRepository;
        configureMapper();
    }

    @Transactional(readOnly = true)
    public List<LocacaoDTO> findAll() {
        List<Locacao> locacoes = locacaoRepository.findAll();
        if (locacoes.isEmpty()) {
            throw new ResourceNotFoundException("Não existem locações cadastradas");
        }
        return locacoes.stream()
                .map(locacao -> mapper.map(locacao, LocacaoDTO.class))
                .sorted(Comparator.comparing(LocacaoDTO::getId))
                .toList();
    }

    @Transactional(readOnly = true)
    public LocacaoDTO findById(Long id) {
        return locacaoRepository.findById(id)
                .map(locacao -> mapper.map(locacao, LocacaoDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID da Locação não encontrado"));
    }

    @Transactional(readOnly = false)
    public LocacaoDTO insert(LocacaoDTO locacaoDTO) {
        Carro carro = carroRepository.findById(locacaoDTO.getId_carro())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Carro não encontrado"));
        Cliente cliente = clienteRepository.findById(locacaoDTO.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));
        Seguradora seguradora = seguradoraRepository.findById(locacaoDTO.getId_seguradora())
                .orElseThrow(() -> new ResourceNotFoundException("ID da Seguradora não encontrado"));

        Locacao locacao = mapper.map(locacaoDTO, Locacao.class);
        locacao.setCarro(carro);
        locacao.setCliente(cliente);
        locacao.setSeguradora(seguradora);

        return mapper.map(locacaoRepository.save(locacao), LocacaoDTO.class);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Locacao, LocacaoDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setDatalocacao(source.getDatalocacao());
                map().setDatadevolucao(source.getDatadevolucao());
                map().setDatadevolvida(source.getDatadevolvida());
                map().setValor(source.getValor());
                map().setValordesconto(source.getValordesconto());
                map().setValortotal(source.getValortotal());
                map().setId_carro(source.getCarro().getId());
                map().setId_cliente(source.getCliente().getId());
                map().setId_seguradora(source.getSeguradora().getId());
                map().setCarro(source.getCarro());
                map().setCliente(source.getCliente());
                map().setSeguradora(source.getSeguradora());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
