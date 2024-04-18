package tech.ada.school.service;

import java.util.List;

import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.v1.AlunoDto;
import tech.ada.school.domain.dto.v1.ProfessorDto;
import tech.ada.school.view.AlunoController;

public interface IAlunoService {

    AlunoDto criarAluno(AlunoDto pedido);

    List<AlunoDto> listarAlunos();

    AlunoDto buscarAluno(int id) throws NotFoundException;

    AlunoDto atualizarAluno(int id, AlunoDto pedido) throws NotFoundException;

    void removerAluno(int id) throws NotFoundException;

    AlunoDto buscarPorCpf(String cpf) throws NotFoundException;

    AlunoDto buscarPorMatricula(int matricula) throws NotFoundException;

    List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException;
}
