package med.voli.api.medico;

import med.voli.api.direccion.DatosDireccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String numero,
        Especialidad especialidad,
        DatosDireccion direccion) {
}
