package com.jciterceros.locadoracarrosjpa.services;

import com.jciterceros.locadoracarrosjpa.dto.LocacaoDTO;
import com.jciterceros.locadoracarrosjpa.entities.Carro;
import com.jciterceros.locadoracarrosjpa.entities.Cliente;
import com.jciterceros.locadoracarrosjpa.entities.Locacao;
import com.jciterceros.locadoracarrosjpa.entities.Seguradora;
import com.jciterceros.locadoracarrosjpa.repositories.CarroRepository;
import com.jciterceros.locadoracarrosjpa.repositories.ClienteRepository;
import com.jciterceros.locadoracarrosjpa.repositories.LocacaoRepository;
import com.jciterceros.locadoracarrosjpa.repositories.SeguradoraRepository;
import com.jciterceros.locadoracarrosjpa.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class LocacaoService {
    private final ModelMapper mapper;
    private Boolean mapperAllreadyConfigured = false;
    private final CarroRepository carroRepository;
    private final SeguradoraRepository seguradoraRepository;
    private final ClienteRepository clienteRepository;

    private final LocacaoRepository locacaoRepository;

    @Autowired
    public LocacaoService(ModelMapper mapper, CarroRepository carroRepository, SeguradoraRepository seguradoraRepository,
                          ClienteRepository clienteRepository, LocacaoRepository locacaoRepository){
        this.mapper = mapper;

        this.carroRepository = carroRepository;
        this.seguradoraRepository = seguradoraRepository;
        this.clienteRepository = clienteRepository;
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
        System.out.println("Carro: " + carro.toString());
        if (!carro.getDisponivel()) {
            throw new ResourceNotFoundException("O carro não está disponível para locação");
        }

        Cliente cliente = clienteRepository.findById(locacaoDTO.getId_cliente())
                .orElseThrow(() -> new ResourceNotFoundException("ID do Cliente não encontrado"));
        System.out.println("Cliente: " + cliente.toString());

        Seguradora seguradora = seguradoraRepository.findById(locacaoDTO.getId_seguradora())
                .orElseThrow(() -> new ResourceNotFoundException("ID da Seguradora não encontrado"));
        System.out.println("Seguradora: " + seguradora.toString());

        /*
        Validação das Datas:
            A data de locação (datalocacao) deve ser anterior à data de devolução (datadevolucao).
            A data de devolução (datadevolucao) deve ser posterior ou igual à data devolução (datadevolucao).
        */
        LocalDate datalocacao;
        LocalDate datadevolucao;
        try {
            datalocacao = locacaoDTO.getDatalocacao();
            datadevolucao = locacaoDTO.getDatadevolucao();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao converter a data");
        }
        Integer diasLocacao = datalocacao.until(datadevolucao).getDays();
        System.out.println("Data Locação: " + datalocacao);
        System.out.println("Data Devolução: " + datadevolucao);
        System.out.println("Days: " + diasLocacao);

        List<Locacao> locacoes = locacaoRepository.findByCarroIdBetweenDatas(locacaoDTO.getId_carro(), datalocacao, datadevolucao);
        if (!locacoes.isEmpty()) {
            System.out.println("Locações: " + locacoes.toString());
            throw new ResourceNotFoundException("O carro não está disponível para locação entre a datalocacao e datadevolucao");
        }

        /*
        Validação dos Valores:
            Os campos de valor (valor), valor de desconto (valordesconto) e valor total (valortotal) não podem ser nulos.
            Os valores devem ser maiores que zero.
        */
        if (locacaoDTO.getValor() == null || locacaoDTO.getValordesconto() == null || locacaoDTO.getValortotal() == null) {
            throw new ResourceNotFoundException("Os campos de valor, valor de desconto e valor total não podem ser nulos");
        }

        /*
        Cálculo do Valor Total:
            O valor total (valortotal) deve ser calculado como a diferença entre o valor total inicial
            (calculado com base nos dias de locação e valor diário do automóvel) e o valor de desconto (valordesconto), se houver.
        */
        //long diasLocacao = locacaoDTO.getDatalocacao().until(locacaoDTO.getDatadevolucao()).getDays();
        BigDecimal valorTotal = carro.getValorLocacao().multiply(BigDecimal.valueOf(diasLocacao));
        System.out.println("Valor Total sem desconto: " + valorTotal);
        System.out.println("Valor do Desconto: " + locacaoDTO.getValordesconto());

        /*
        Se nao houver desconto, o valor total é o valor da locação
        */
        if (locacaoDTO.getValordesconto().compareTo(BigDecimal.ZERO) == 0) {
            locacaoDTO.setValortotal(valorTotal);
        } else {
            locacaoDTO.setValortotal(valorTotal.subtract(locacaoDTO.getValordesconto()));
        }
        System.out.println("Valor Total com desconto: " + locacaoDTO.getValortotal());
        System.out.println("Numero de diarias: " + diasLocacao);

        /*
        Caso todas as informações estejam corretas, a locação deve ser confirmada e salva no banco de dados.
        */
        Locacao locacao = mapper.map(locacaoDTO, Locacao.class);
        locacao.setCarro(carro);
        locacao.setCliente(cliente);
        locacao.setSeguradora(seguradora);

        //TODO: Retirar hardcode Data Devolvida
        locacao.setDatadevolvida(locacao.getDatadevolucao());

        return mapper.map(locacaoRepository.save(locacao), LocacaoDTO.class);
//        return mapper.map(locacao, LocacaoDTO.class);
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
