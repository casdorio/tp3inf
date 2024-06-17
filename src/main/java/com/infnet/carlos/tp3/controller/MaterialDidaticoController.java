package com.infnet.carlos.tp3.controller;

import com.infnet.carlos.tp3.model.MaterialDidatico;
import com.infnet.carlos.tp3.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/material")
@Validated
public class MaterialDidaticoController {

    @Autowired
    private MaterialDidaticoService materialDidaticoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterialDidatico> createMaterialDidatico(@Valid @RequestBody MaterialDidatico materialDidatico) {
        MaterialDidatico savedMaterial = materialDidaticoService.save(materialDidatico);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MaterialDidatico>> getAllMaterialDidatico() {
        List<MaterialDidatico> materialDidaticos = materialDidaticoService.findAll();
        return ResponseEntity.ok(materialDidaticos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterialDidatico> getMaterialDidaticoById(@PathVariable String id) {
        Optional<MaterialDidatico> materialDidatico = materialDidaticoService.findById(id);
        return materialDidatico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterialDidatico> updateMaterialDidatico(@PathVariable String id, @Valid @RequestBody MaterialDidatico materialDidatico) {
        Optional<MaterialDidatico> existingMaterialOptional = materialDidaticoService.findById(id);
        if (!existingMaterialOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        materialDidatico.setId(id);
        MaterialDidatico updatedMaterial = materialDidaticoService.save(materialDidatico);
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMaterialDidatico(@PathVariable String id) {
        Optional<MaterialDidatico> existingMaterialOptional = materialDidaticoService.findById(id);
        if (!existingMaterialOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        materialDidaticoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
