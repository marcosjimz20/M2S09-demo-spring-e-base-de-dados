package br.senai.lab365.demospringbd.Repository;

import br.senai.lab365.demospringbd.Entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

// FIM da classe PessoaRepository
}
