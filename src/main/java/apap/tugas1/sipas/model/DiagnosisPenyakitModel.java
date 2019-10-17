package apap.tugas1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "diagnosisPenyakit")
public class DiagnosisPenyakitModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 40)
    @Column(name = "kode", nullable = false)
    private String kode;

    @OneToMany(mappedBy = "diagnosisPenyakit", fetch = FetchType.LAZY)
    List<PasienDiagnosisPenyakitModel> listPasien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public List<PasienDiagnosisPenyakitModel> getListPasien() {
        return listPasien;
    }

    public void setListPasien(List<PasienDiagnosisPenyakitModel> listPasien) {
        this.listPasien = listPasien;
    }
}
