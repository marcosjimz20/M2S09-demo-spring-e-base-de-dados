package br.senai.lab365.demospringbd.Servicy;

import br.senai.lab365.demospringbd.DTO.PessoaDto;
import br.senai.lab365.demospringbd.Entity.PessoaEntity;
import br.senai.lab365.demospringbd.Repository.PessoaRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
// Atributos
    private PessoaRepository pessoaRepository;

// Constructor
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

// Métodos
    public List<PessoaEntity> cadastrarPessoa(List<PessoaEntity> pessoa) {
        try {
            return pessoaRepository.saveAll(pessoa);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public List<PessoaEntity> getAll() {
        Optional<PessoaEntity> by =
                this.pessoaRepository.findById(1L);
        by.get();
        return this.pessoaRepository.findAll();
    }

    public Optional<PessoaEntity> findById(Long id) {
        return this.pessoaRepository.findById(id);
    }


//true
//    public Optional<PessoaEntity> findById(Long id) {
//        return this.pessoaRepository.findById(id);
//    }



    public ResponseEntity<PessoaEntity> atualizarPessoa(Long id, PessoaDto pessoaDto) {

        PessoaEntity pessoaAtual = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        try {

            pessoaAtual.setNome(pessoaDto.getNome());
            pessoaAtual.setEmail(pessoaDto.getEmail());
            pessoaAtual.setStatus(pessoaDto.getStatus());

            PessoaEntity pessoaAtualizada = pessoaRepository.save(pessoaAtual);
            return ResponseEntity.ok(pessoaAtualizada);
        }  catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public ResponseEntity<PessoaEntity> deletar(Long id) {
        if (!pessoaRepository.existsById(id) ) {
            return ResponseEntity.notFound().build();
        }
        pessoaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



// FIM da classe PessoaService
}
