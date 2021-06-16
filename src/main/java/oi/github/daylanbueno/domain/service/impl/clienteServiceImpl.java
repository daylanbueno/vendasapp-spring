package oi.github.daylanbueno.domain.service.impl;

import lombok.RequiredArgsConstructor;
import oi.github.daylanbueno.domain.dto.ClienteDto;
import oi.github.daylanbueno.domain.entity.Cliente;
import oi.github.daylanbueno.domain.exception.ObjetoNaoEncontradoExeption;
import oi.github.daylanbueno.domain.exception.RegraNegocioException;
import oi.github.daylanbueno.domain.repository.ClienteRepository;
import oi.github.daylanbueno.domain.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class clienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Override
    public ClienteDto obterClientePorId(Integer id) {
        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoExeption("Cliente não encontrado!"));
        return modelMapper.map(cliente, ClienteDto.class);
    }

    @Override
    public ClienteDto save(ClienteDto clienteDto) {
        Cliente clienteExiste = obterClientePorCpf(clienteDto.getCpf());
        if(clienteExiste != null) {
            throw new RegraNegocioException("Já existe um cliente para o CPF informado");
        }
        Cliente novoCliente = modelMapper.map(clienteDto, Cliente.class);
        Cliente cliente = clienteRepository.save(novoCliente);
        return modelMapper.map(cliente, ClienteDto.class );
    }

    @Override
    public void deletar(Integer id) {
        clienteRepository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("Clinete de id "+id+" não encontrado"));
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDto alterar(ClienteDto clienteDto) {
        if (clienteDto.getId() == null) {
            throw new RegraNegocioException("O id da pessoa não foi informado");
        }
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        clienteRepository
                .findById(cliente.getId()).map(clienteAtual -> {
            cliente.setId(clienteAtual.getId());
            return clienteRepository.save(cliente);
        }).orElseThrow(() ->  new ObjetoNaoEncontradoExeption("Cliente não econtrado"));

        return modelMapper.map(cliente, ClienteDto.class);
    }

    @Override
    public List<ClienteDto> obterClientesPorFiltro(ClienteDto clienteDto) {
        Cliente filtro = modelMapper.map(clienteDto, Cliente.class);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> clientes = clienteRepository.findAll(example);

        List<ClienteDto> resultado = clientes.stream()
                .map(entity -> modelMapper.map(entity, ClienteDto.class))
                .collect(Collectors.toList());

        return resultado;
    }

    private Cliente obterClientePorCpf(String cpf) {
        return clienteRepository.findByCPF(cpf);
    }
}
