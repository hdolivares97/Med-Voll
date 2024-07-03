package med.voli.api.direccion;

public record DatosDireccion(
        String calle,
        String distrito,
        String ciudad,
        int numero,
        String complemento
) {
}
