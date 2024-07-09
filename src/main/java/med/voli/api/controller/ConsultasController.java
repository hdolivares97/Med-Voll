package med.voli.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.AgendaDeConsultaService;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.domain.consulta.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class ConsultasController {

    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = { "consulta", "post" })
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
       var response = agendaDeConsultaService.agendar(datos);
        return ResponseEntity.ok(response);
    }
}
