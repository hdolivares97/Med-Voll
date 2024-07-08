package med.voli.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import med.voli.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if (datosAgendarConsulta.idPaciente() == null) {
            return;
        }

        var pacienteActivo = pacienteRepository.findActivoById(datosAgendarConsulta.idPaciente());

        if (!pacienteActivo) {
            throw new ValidationException("No se permite agendar con pacientes inactivos en el sistema");
        }
    }
}
