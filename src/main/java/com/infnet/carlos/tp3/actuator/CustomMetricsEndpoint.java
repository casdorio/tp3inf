package com.infnet.carlos.tp3.actuator;

import com.infnet.carlos.tp3.service.AlunoService;
import com.infnet.carlos.tp3.service.CursoService;
import com.infnet.carlos.tp3.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "customMetrics")
public class CustomMetricsEndpoint {

    private final AlunoService alunoService;
    private final CursoService cursoService;
    private final MaterialDidaticoService materialDidaticoService;

    @Autowired
    public CustomMetricsEndpoint(AlunoService alunoService, CursoService cursoService, MaterialDidaticoService materialDidaticoService) {
        this.alunoService = alunoService;
        this.cursoService = cursoService;
        this.materialDidaticoService = materialDidaticoService;
    }

    @ReadOperation
    public Map<String, Object> customMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        
        long totalAlunos = alunoService.count();
        long totalCursos = cursoService.count();
        long totalMateriais = materialDidaticoService.count();
        double taxaUtilizacaoCursos = totalCursos == 0 ? 0 : (double) totalAlunos / totalCursos;

        metrics.put("total.alunos", totalAlunos);
        metrics.put("total.cursos", totalCursos);
        metrics.put("total.materiaisDidaticos", totalMateriais);
        metrics.put("taxa.utilizacaoCursos", taxaUtilizacaoCursos);

        return metrics;
    }
}
