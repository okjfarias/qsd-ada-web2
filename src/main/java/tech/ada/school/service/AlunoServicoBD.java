package tech.ada.school.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.v1.AlunoDto;
import tech.ada.school.domain.dto.v1.ProfessorDto;
import tech.ada.school.domain.entities.Aluno;
import tech.ada.school.domain.entities.Professor;
import tech.ada.school.domain.mappers.AlunoMapper;
import tech.ada.school.domain.mappers.ProfessorMapper;
import tech.ada.school.external.FeignBoredApi;
import tech.ada.school.external.RestBoredApi;
import tech.ada.school.repositories.AlunoRepository;
import tech.ada.school.repositories.ProfessorRepository;

@Service
@RequiredArgsConstructor
@Primary
public class AlunoServicoBD implements IAlunoService {

    private final AlunoRepository repositorio;
    private final FeignBoredApi boredApi;

    @Override
    public AlunoDto criarAluno(AlunoDto pedido) {

        Aluno a = AlunoMapper.toEntity(pedido);

        return AlunoMapper.toDto(repositorio.save(a), boredApi.getActivity().activity());

    }

    @Override
    public List<AlunoDto> listarAlunos() {
        return repositorio
                .findAll()
                .stream()
                .map(ent -> AlunoMapper.toDto(ent, boredApi.getActivity().activity()))
                .toList();
    }

    @Override
    public AlunoDto buscarAluno(int id) throws NotFoundException {
        System.out.println(boredApi.getActivity());
        return AlunoMapper.toDto(buscarAlunoPorId(id), boredApi.getActivity().activity());
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto pedido) throws NotFoundException {
        final Aluno a = buscarAlunoPorId(id);
        a.setMatricula(pedido.getMatricula());
        a.setCpf(pedido.getCpf());
        a.setNome(pedido.getNome());
        a.setEMail(pedido.getEmail());
        a.setTurma(pedido.getTurma());
        return AlunoMapper.toDto(repositorio.save(a), boredApi.getActivity().activity());
    }

    @Override
    public void removerAluno(int id) throws NotFoundException {
        final Aluno a = buscarAlunoPorId(id);
        repositorio.delete(a);
        repositorio.deleteById(id);
    }

    private Aluno buscarAlunoPorId(int id) throws NotFoundException {
        return repositorio.findById(id).orElseThrow(() -> new NotFoundException(Aluno.class, String.valueOf(id)));
    }

    @Override
    public AlunoDto buscarPorCpf(String cpf) throws NotFoundException {
        return AlunoMapper.toDto(repositorio.findByCpf(cpf).orElseThrow(() -> new NotFoundException(Aluno.class, cpf)), boredApi.getActivity()
                .activity());
    }

    @Override
    public AlunoDto buscarPorMatricula(int matricula) throws NotFoundException {
        return AlunoMapper.toDto(repositorio.findByMatricula(matricula).orElseThrow(() -> new NotFoundException(Aluno.class, String.valueOf(matricula))), boredApi.getActivity()
                .activity());
    }

    @Override
    public List<AlunoDto> buscarPorTurma(String turma) throws NotFoundException {
        List<Aluno> alunos = repositorio.findByTurma(turma);
        if (alunos.isEmpty()) {
            throw new NotFoundException(Aluno.class, turma);
        }
        return alunos.stream()
                .map(aluno -> AlunoMapper.toDto(aluno, boredApi.getActivity().activity()))
                .collect(Collectors.toList());
    }

}
