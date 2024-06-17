package com.infnet.carlos.tp3.dto;

import com.infnet.carlos.tp3.model.Curso;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CursoDTO {
    private Long id;
    private String nome;
    private Set<Long> alunoIds;

    public CursoDTO() {
        this.alunoIds = new HashSet<>();
    }

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.alunoIds = curso.getAlunos().stream()
                          .map(aluno -> aluno.getId())
                          .collect(Collectors.toSet());
    }

    public CursoDTO(Curso curso, Set<Long> alunoIds) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.alunoIds = alunoIds;
    }

    public void addAlunoId(Long alunoId) {
        this.alunoIds.add(alunoId);
    }

    public void removeAlunoId(Long alunoId) {
        this.alunoIds.remove(alunoId);
    }

    public Curso toEntity() {
        Curso curso = new Curso();
        curso.setId(this.id);
        curso.setNome(this.nome);
        
        return curso;
    }
}
