package com.infnet.carlos.tp3.controller;

import com.infnet.carlos.tp3.model.Aluno;
import com.infnet.carlos.tp3.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    /**
     * Cria um novo aluno.
     */
    @PostMapping
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.save(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    /**
     * Obtém todos os alunos.
     */
    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    /**
     * Obtém um aluno pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        return alunoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza um aluno pelo ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        Optional<Aluno> existingAlunoOptional = alunoService.findById(id);
        if (!existingAlunoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Aluno existingAluno = existingAlunoOptional.get();
        existingAluno.setNome(aluno.getNome());
        Aluno updatedAluno = alunoService.save(existingAluno);
        return ResponseEntity.ok(updatedAluno);
    }

    /**
     * Deleta um aluno pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.findById(id);
        if (!alunoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
