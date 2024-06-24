package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.ClienteDTO;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Municipio;
import com.jciterceros.locadoracarrosjpa.repositories.ClienteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.MunicipioRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.DatabaseException;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ClienteService {

    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;

    private final ClienteRepository clienteRepository;

    private final MunicipioRepository municipioRepository;

    public ClienteService(ModelMapper mapper, ClienteRepository clienteRepository, MunicipioRepository municipioRepository) {
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.municipioRepository = municipioRepository;
        configureMapper();
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();//searchAll();
        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException("Não existem clientes cadastrados");
        }

        return clientes.stream()
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .sorted(Comparator.comparing(ClienteDTO::getId))
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        return clienteRepository.searchById(id).stream()
                .map(cliente -> mapper.map(cliente, ClienteDTO.class))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));
    }

    @Transactional(readOnly = false)
    public ClienteDTO insert(ClienteDTO clienteDTO) {
        Municipio municipio = municipioRepository.findById(clienteDTO.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));

        if(clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new DatabaseException("CPF já cadastrado");
        }

        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        cliente.setEstado(municipio.getEstado());
        cliente.setMunicipio(municipio);

        return mapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    @Transactional(readOnly = false)
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));

        if(clienteRepository.existsByCpf(clienteDTO.getCpf()) && !clienteDTO.getCpf().equals(cliente.getCpf())) {
            throw new DatabaseException("CPF já cadastrado");
        }
        Municipio municipio = municipioRepository.findById(clienteDTO.getId_municipio())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Município não encontrado"));

        cliente = mapper.map(clienteDTO, Cliente.class);
        cliente.setId(id);
        cliente.setEstado(municipio.getEstado());
        cliente.setMunicipio(municipio);

        return mapper.map(clienteRepository.save(cliente), ClienteDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));
        if (!cliente.getClienteLocacoes().isEmpty()) {
            throw new DatabaseException("Cliente possui locações associadas");
        }
        clienteRepository.delete(cliente);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Cliente, ClienteDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNome(source.getNome());
                map().setRg(source.getRg());
                map().setCpf(source.getCpf());
                map().setLogradouro(source.getLogradouro());
                map().setCnh(source.getCnh());
                map().setDatavencimentocnh(source.getDatavencimentocnh());
                map().setEmail(source.getEmail());
                map().setId_estado(source.getMunicipio().getEstado().getId());
                map().setId_municipio(source.getMunicipio().getId());
                map().setEstado(source.getMunicipio().getEstado());
                map().setMunicipio(source.getMunicipio());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
