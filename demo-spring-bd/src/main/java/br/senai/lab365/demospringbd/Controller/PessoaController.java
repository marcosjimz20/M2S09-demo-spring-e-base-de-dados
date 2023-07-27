package br.senai.lab365.demospringbd.Controller;

import br.senai.lab365.demospringbd.DTO.PessoaDto;
import br.senai.lab365.demospringbd.Entity.PessoaEntity;
import br.senai.lab365.demospringbd.Servicy.PessoaService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PessoaController {
// Atributos
    private PessoaService pessoaService;

// Constructor
    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


// Métodos
    @PostMapping("/pessoas")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody List<PessoaEntity> pessoa) {
        try {
            List<PessoaEntity> pessoaNovo = pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaNovo);
        }  catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/pessoas")
    public List<PessoaEntity> getAll() {
        return this.pessoaService.getAll();
    }


    @GetMapping("/pessoas/{id}")
    public ResponseEntity<?> findOneById(@PathVariable Long id) {

        Optional<PessoaEntity> pessoaId = pessoaService.findById(id);

        if (pessoaId.isPresent())
            return ResponseEntity.ok(pessoaId.get());
        else
            return ResponseEntity.notFound().build();
    }

    //true
//    @GetMapping("/pessoas/{true}")
//    public ResponseEntity<?> findOneById(@PathVariable Boolean true) {
//
//        Optional<PessoaEntity> pessoaId = pessoaService.findById(true);
//
//        if (pessoaId.isPresent())
//            return ResponseEntity.ok(pessoaId.get());
//        else
//            return ResponseEntity.notFound().build();
//    }


    @PutMapping("/pessoas/{id}")
    public PessoaEntity atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDto pessoaDto) {
        return pessoaService.atualizarPessoa(id, pessoaDto).getBody();
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<PessoaEntity> deletar(@PathVariable Long id) {
        return this.pessoaService.deletar(id);
    }




// FIM da classe PessoaController
}
