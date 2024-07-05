package med.voli.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.direccion.DatosDireccion;
import med.voli.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        // Return 201 Created
        // URL donde encontrar al medico
        // http://localhost:8080/medicos/xx
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(),
                medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listarMedicos(Pageable paginacion){
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(),
                medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));
    }

    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
    // DELETE EN BASE DE DATOS
    //    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedicos = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(),
                medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedicos);
    }
}
