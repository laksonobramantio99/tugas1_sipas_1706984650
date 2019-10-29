package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienModel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface PasienService {
    List<PasienModel> getAllPasien();
    Optional<PasienModel> getPasienByNik(String nik);
    String generateRandomKode(Date tgl, Integer jenisKelamin);
    Optional<PasienModel> getPasienByKode(String kode);
    void addPasien(PasienModel pasien);
}
