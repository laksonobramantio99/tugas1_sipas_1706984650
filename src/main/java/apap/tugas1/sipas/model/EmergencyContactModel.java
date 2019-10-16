package apap.tugas1.sipas.model;

import apap.tugas1.sipas.service.PasienService;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "emergencyContact")
public class EmergencyContactModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 16)
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Size(max = 40)
    @Column(name = "noHp", nullable = false)
    private String noHp;

    @OneToOne(mappedBy = "emergencyContact")
    private PasienModel pasien;

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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public PasienModel getPasien() {
        return pasien;
    }

    public void setPasien(PasienModel pasien) {
        this.pasien = pasien;
    }
}
