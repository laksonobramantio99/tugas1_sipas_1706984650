package apap.tugas1.sipas.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

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
    private Date tanggalDiagnosis;

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

    public Date getTanggalDiagnosis() {
        return tanggalDiagnosis;
    }

    public void setTanggalDiagnosis(Date tanggalDiagnosis) {
        this.tanggalDiagnosis = tanggalDiagnosis;
    }
}
