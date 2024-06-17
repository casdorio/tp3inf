package com.infnet.carlos.tp3.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class MaterialDidatico {
    @Id
    private String id;
    private String nome;
    private String descricao;
}
