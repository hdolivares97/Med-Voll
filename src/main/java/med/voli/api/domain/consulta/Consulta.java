package med.voli.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voli.api.domain.medico.Medico;
import med.voli.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private LocalDateTime fecha;

//    @Column(name = "motivo_cancelamiento")
//    @Enumerated(EnumType.STRING)
//    private MotivoCancelamiento motivoCancelamiento;
//
    public Consulta( Medico medico, Paciente paciente, LocalDateTime fecha) {
        this.medico=medico;
        this.paciente=paciente;
        this.fecha=fecha;
    }
//
//    public void cancelar(MotivoCancelamiento motivo) {
//        this.motivoCancelamiento = motivo;
//    }
}
