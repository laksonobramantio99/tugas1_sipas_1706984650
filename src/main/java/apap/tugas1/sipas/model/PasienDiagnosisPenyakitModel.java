package apap.tugas1.sipas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "pasienDiagnosisPenyakit")
public class PasienDiagnosisPenyakitModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPasien", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idDiagnosisPenyakit", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiagnosisPenyakitModel diagnosisPenyakit;

    @NotNull
    @Column(name = "tanggalDiagnosis", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tanggalDiagnosis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }

    public DiagnosisPenyakitModel getDiagnosisPenyakit() {
        return diagnosisPenyakit;
    }

    public void setDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit) {
        this.diagnosisPenyakit = diagnosisPenyakit;
    }

    public LocalDateTime getTanggalDiagnosis() {
        return tanggalDiagnosis;
    }

    public void setTanggalDiagnosis(LocalDateTime tanggalDiagnosis) {
        this.tanggalDiagnosis = tanggalDiagnosis;
    }
}
