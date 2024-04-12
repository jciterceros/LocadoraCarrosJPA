package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.ClientetelefoneDTO;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Clientetelefone;
import com.jciterceros.locadoracarrosjpa.repositories.ClienteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.ClientetelefoneRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ClientetelefoneService {
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;

    private final ClienteRepository clienteRepository;
    private final ClientetelefoneRepository clientetelefoneRepository;

    @Autowired
    public ClientetelefoneService(ModelMapper mapper, ClienteRepository clienteRepository, ClientetelefoneRepository clientetelefoneRepository) {
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.clientetelefoneRepository = clientetelefoneRepository;
        configureMapper();
    }

    @Transactional(readOnly = true)
    public List<ClientetelefoneDTO> findAll() {
        List<Clientetelefone> clientetelefones = clientetelefoneRepository.findAll();
        if (clientetelefones.isEmpty()) {
            throw new ResourceNotFoundException("Não existem telefones cadastrados");
        }
        return clientetelefones.stream()
                .map(clientetelefone -> mapper.map(clientetelefone, ClientetelefoneDTO.class))
                .sorted(Comparator.comparing(ClientetelefoneDTO::getId))
                .toList();
    }

    @Transactional(readOnly = true)
    public ClientetelefoneDTO findById(Long id) {
        return clientetelefoneRepository.findById(id)
                .map(clientetelefone -> mapper.map(clientetelefone, ClientetelefoneDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("ID do Telefone não encontrado"));
    }

    @Transactional(readOnly = false)
    public ClientetelefoneDTO insert(ClientetelefoneDTO clientetelefoneDTO) {
        Cliente cliente = clienteRepository.findById(clientetelefoneDTO.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));

        Clientetelefone clientetelefone = mapper.map(clientetelefoneDTO, Clientetelefone.class);
        clientetelefone.setCliente(cliente);

        return mapper.map(clientetelefoneRepository.save(clientetelefone), ClientetelefoneDTO.class);
    }

    @Transactional(readOnly = false)
    public ClientetelefoneDTO update(Long id, ClientetelefoneDTO clientetelefoneDTO) {
        Clientetelefone clientetelefone = clientetelefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Telefone não encontrado"));

        Cliente cliente = clienteRepository.findById(clientetelefoneDTO.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));

        clientetelefone.setTelefone(clientetelefoneDTO.getTelefone());
        clientetelefone.setCliente(cliente);

        return mapper.map(clientetelefoneRepository.save(clientetelefone), ClientetelefoneDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        Clientetelefone clientetelefone = clientetelefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID do Telefone não encontrado"));

        clientetelefoneRepository.delete(clientetelefone);
    }

    private void configureMapper() {
        if (Boolean.TRUE.equals(mapperAllreadyConfigured)) return;
        mapper.addMappings(new PropertyMap<Clientetelefone, ClientetelefoneDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTelefone(source.getTelefone());
                map().setId_cliente(source.getCliente().getId());
                map().setCliente(source.getCliente());
            }
        });
        mapperAllreadyConfigured = true;
    }
}
