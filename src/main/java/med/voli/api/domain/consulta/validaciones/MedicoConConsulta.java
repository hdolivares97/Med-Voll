package med.voli.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        if (datosAgendarConsulta.idMedico() == null) {
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(
                datosAgendarConsulta.idMedico(),datosAgendarConsulta.fecha());

        if (medicoConConsulta) {
            throw new ValidationException("Este medico ya tiene una consulta en ese horario");
        }



    }
}
