package br.com.precatorio.cliente.conjugue;

import br.com.precatorio.cliente.conjugue.Conjugue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConjugueRepository extends JpaRepository<Conjugue, Long> {

    Optional<Conjugue> findById(Long id);

}