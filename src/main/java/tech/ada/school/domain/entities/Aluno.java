package tech.ada.school.domain.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int matricula;

    @Column(name = "nome_completo")
    private String nome;

    @Column(unique = true)
    private String cpf;

    private String eMail;

    private String turma;

    @CreationTimestamp
    private Instant createdAt;
}
