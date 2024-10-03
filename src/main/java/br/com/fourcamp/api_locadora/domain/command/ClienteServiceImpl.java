package br.com.fourcamp.api_locadora.domain.command;

import br.com.fourcamp.api_locadora.adapter.output.ClienteRepositoryImpl;
import br.com.fourcamp.api_locadora.port.input.IClienteService;
import br.com.fourcamp.api_locadora.domain.dto.ClienteDTO;
import br.com.fourcamp.api_locadora.domain.dto.EnderecoDTO;
import br.com.fourcamp.api_locadora.domain.entity.Cliente;
import br.com.fourcamp.api_locadora.domain.entity.Endereco;
import br.com.fourcamp.api_locadora.port.output.IClienteRepository;
import br.com.fourcamp.api_locadora.port.output.IEnderecoRepository;
import br.com.fourcamp.api_locadora.domain.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository IClienteRepository;
    private final IEnderecoRepository IEnderecoRepository;
    @Autowired
    private ClienteRepositoryImpl clienteRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Cliente cadastrarCliente(ClienteDTO clienteDTO){
        validarCliente(clienteDTO);

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        LocalDate dataNascimento = LocalDate.parse(clienteDTO.getDataNascimento(), DATE_FORMATTER);
        cliente.setDataNascimento(dataNascimento);
        cliente.setTelefone(clienteDTO.getTelefone());

        EnderecoDTO enderecoDTO = clienteDTO.getEnderecoDTO();
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getCidade());
        endereco.setCep(enderecoDTO.getCep());

        Endereco enderecoCliente = IEnderecoRepository.cadastrar(endereco);

        cliente.setEndereco(enderecoCliente);

        return IClienteRepository.cadastrar(cliente);
    }

    private void validarCliente(ClienteDTO clienteDTO) {
        if (!NomeValidator.validarNome(clienteDTO.getNome())) {
            throw new IllegalArgumentException("Nome inválido. Deve conter, no mínimo, 10 caracteres.");
        }

        if (!CPFValidator.validarCPF(clienteDTO.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if(!DataNascimentoValidator.validarFormatoData(clienteDTO.getDataNascimento().toString())){
            throw new IllegalArgumentException("Formato de data inválido");
        }

        if (clienteRepository.buscarClientePorCPF(clienteDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (!DataNascimentoValidator.validarMaioridade(LocalDate.parse(clienteDTO.getDataNascimento(), DATE_FORMATTER))){
            throw new IllegalArgumentException("Cliente deve ser maior de idade.");
        }

        if (!TelefoneValidator.validarTelefone(clienteDTO.getTelefone())) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

        EnderecoDTO endereco = clienteDTO.getEnderecoDTO();
        if (!LogradouroValidator.validarLogradouro(endereco.getLogradouro())) {
            throw new IllegalArgumentException("Logradouro inválido.");
        }

        if (!NumeroValidator.validarNumero(endereco.getNumero())) {
            throw new IllegalArgumentException("Número inválido.");
        }

        if (!BairroValidator.validarBairro(endereco.getBairro())) {
            throw new IllegalArgumentException("Bairro inválido.");
        }

        if (!CidadeValidator.validarCidade(endereco.getCidade())) {
            throw new IllegalArgumentException("Cidade inválida.");
        }

        if (!UFValidator.validarUF(endereco.getUf())) {
            throw new IllegalArgumentException("UF inválida.");
        }

        if (!CEPValidator.validarCEP(endereco.getCep())) {
            throw new IllegalArgumentException("CEP inválido.");
        }
    }
}
