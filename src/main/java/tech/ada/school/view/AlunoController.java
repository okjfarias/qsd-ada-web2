package tech.ada.school.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.v1.AlunoDto;
import tech.ada.school.domain.utils.MatriculaUtils;
import tech.ada.school.service.IAlunoService;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private IAlunoService servico;

    public AlunoController(IAlunoService servico) {
        this.servico = servico;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> lerAlunos() {
        return ResponseEntity.ok(servico.listarAlunos());
    }

    @PostMapping
    public ResponseEntity<AlunoDto> criarAluno(
            @RequestBody @Valid AlunoDto pedido
    ) {
        int matricula = MatriculaUtils.gerarMatriculaUnica();
        pedido.setMatricula(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criarAluno(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> atualizarAluno(
            @PathVariable("id") int id,
            @RequestBody AlunoDto pedido
    ) throws NotFoundException {
        final AlunoDto p = servico.atualizarAluno(id, pedido);

        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> buscarAluno(
            @PathVariable("id") int id
    ) throws NotFoundException {
        return ResponseEntity.ok(servico.buscarAluno(id));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirements(@SecurityRequirement(name = "JWT"))
    public ResponseEntity<Void> removerAluno(
            @PathVariable("id") int id
    ) throws NotFoundException {
        servico.removerAluno(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AlunoDto> buscarPorCpf(@PathVariable("cpf") String cpf) throws NotFoundException {
        return ResponseEntity.ok(servico.buscarPorCpf(cpf));
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<AlunoDto> buscarPorMatricula(@PathVariable("matricula") int matricula) throws NotFoundException {
        return ResponseEntity.ok(servico.buscarPorMatricula(matricula));
    }

    @GetMapping("/turma/{turma}")
    public ResponseEntity<List<AlunoDto>> buscarPorTurma(@PathVariable("turma") String turma) throws NotFoundException {
        return ResponseEntity.ok(servico.buscarPorTurma(turma));
    }


}
