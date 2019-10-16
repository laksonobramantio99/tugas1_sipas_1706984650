package apap.tugas1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 13)
    @Column(name = "kode", nullable = false)
    private String kode;

    @NotNull
    @Size(max = 16)
    @Column(name = "nik", nullable = false)
    private String nik;

    @NotNull
    @Column(name = "tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 20)
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmergencyContact", referencedColumnName = "id")
    private EmergencyContactModel emergencyContact;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY)
    List<PasienAsuransiModel> listAsuransi;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY)
    List<PasienDiagnosisPenyakitModel> listDiagnosisPenyakit;

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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public EmergencyContactModel getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContactModel emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public List<PasienAsuransiModel> getListAsuransi() {
        return listAsuransi;
    }

    public void setListAsuransi(List<PasienAsuransiModel> listAsuransi) {
        this.listAsuransi = listAsuransi;
    }

    public List<PasienDiagnosisPenyakitModel> getListDiagnosisPenyakit() {
        return listDiagnosisPenyakit;
    }

    public void setListDiagnosisPenyakit(List<PasienDiagnosisPenyakitModel> listDiagnosisPenyakit) {
        this.listDiagnosisPenyakit = listDiagnosisPenyakit;
    }
}
