package tech.ada.school.service;

import java.util.*;

import org.springframework.stereotype.Service;

import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.v1.AlunoDto;
import tech.ada.school.domain.dto.v1.ProfessorDto;

@Service
public class AlunoService implements IAlunoService {

    private final List<AlunoDto> alunos = new ArrayList<>();
    private int id = 1;
    private Set<Integer> matriculasGeradas = new HashSet<>();
    private Random rand = new Random();

    @Override
    public AlunoDto criarAluno(AlunoDto novoAluno) {
        final AlunoDto a = new AlunoDto(
                id++,
                novoAluno.getMatricula(),
                novoAluno.getNome(),
                novoAluno.getCpf(),
                novoAluno.getEmail(),
                novoAluno.getTurma(),
                null
        );
        alunos.add(a);
        return a;
    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return alunos;
    }

    @Override
    public AlunoDto buscarAluno(int id) throws NotFoundException {
        return alunos
                .stream()
                .filter(it -> it.getId()==id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(AlunoDto.class, String.valueOf(id)));
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto pedido) {
        final AlunoDto aluno = alunos.stream().filter(it -> it.getId() == id).findFirst().orElse(null);
        if (aluno == null) {
            return null;
        }
        alunos.remove(aluno);
        final AlunoDto a = new AlunoDto(aluno.getId(), pedido.getMatricula(), pedido.getNome(), pedido.getCpf(), pedido.getEmail(), pedido.getTurma(), null);
        alunos.add(a);
        return a;
    }

    @Override
    public void removerAluno(int id) throws NotFoundException {
        final AlunoDto aluno = buscarAluno(id);
        alunos.remove(aluno);
    }

    @Override
    public AlunoDto buscarPorCpf(String cpf) throws NotFoundException {
        return null;
    }

    @Override
    public AlunoDto buscarPorMatricula(int matricula) throws NotFoundException {
        return null;
    }

    @Override
    public List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException {
        return null;
    }
}
