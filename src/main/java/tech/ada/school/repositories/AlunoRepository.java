package tech.ada.school.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.ada.school.domain.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByMatricula(int matricula);

    List<Aluno> findByTurma(String turma);

    boolean existsByCpf(@Param("cpf") String cpf);
    boolean existsByMatricula(@Param("matricula") int matricula);
    boolean existsByTurma(@Param("turma") String turma);

}
