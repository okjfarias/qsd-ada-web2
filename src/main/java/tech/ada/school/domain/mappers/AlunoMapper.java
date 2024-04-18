package tech.ada.school.domain.mappers;

import tech.ada.school.domain.dto.v1.AlunoDto;
import tech.ada.school.domain.dto.v1.ProfessorDto;
import tech.ada.school.domain.entities.Aluno;
import tech.ada.school.domain.entities.Professor;

public class AlunoMapper {

    public static Aluno toEntity(AlunoDto dto) {
        return Aluno
                .builder()
                .matricula(dto.getMatricula())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .eMail(dto.getEmail())
                .turma(dto.getTurma())
                .build();
    }

    public static AlunoDto toDto(Aluno entity, String activity) {
        return new AlunoDto(
                entity.getId(),
                entity.getMatricula(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEMail(),
                entity.getTurma(),
                activity
        );
    }

}
