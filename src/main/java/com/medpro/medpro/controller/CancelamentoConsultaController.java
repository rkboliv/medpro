package com.medpro.medpro.controller;

import com.medpro.medpro.model.entity.Consulta;
import com.medpro.medpro.model.dto.CancelamentoConsultaDTO;
import com.medpro.medpro.repository.ConsultaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class CancelamentoConsultaController {

    private final ConsultaRepository consultaRepository;

    public CancelamentoConsultaController(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@RequestBody CancelamentoConsultaDTO dto) {
        var consultaOptional = consultaRepository.findById(dto.id());

        if (consultaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Consulta consulta = consultaOptional.get();
        consulta.setCancelada(true);
        consulta.setMotivoCancelamento(dto.motivo());

        consultaRepository.save(consulta);

        return ResponseEntity.noContent().build(); // 204
    }
}
