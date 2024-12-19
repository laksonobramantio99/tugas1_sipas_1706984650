package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.EmergencyContactModel;
import apap.tugas1.sipas.model.PasienModel;
import apap.tugas1.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }

    @Override
    public Optional<PasienModel> getPasienByNik(String nik) {
        return pasienDb.findByNik(nik);
    }

    @Override
    public Optional<PasienModel> getPasienByKode(String kode) {
        return pasienDb.findByKode(kode);
    }

    @Override
    public String generateRandomKode(Date tgl, Integer jenisKelamin) {
        String kode = "";

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime()); //Cari date today
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        int tahunHabis = Integer.parseInt(yearFormat.format(today)) + 5; //ambil tahunnya terus ditambah 5

        DateFormat df = new SimpleDateFormat("ddMMyy");
        String tanggalLahir = df.format(tgl);

        // Konkatenasi
        kode = kode + tahunHabis + tanggalLahir;

        Random rnd = new Random();
        for (int i=0; i<2; i++) {
            char c = (char) (rnd.nextInt(26) + 'A');
            kode += c;
        }

        return kode;
    }

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public PasienModel changePasienData(PasienModel pasienModel) {
        PasienModel targetPasien = pasienDb.findById(pasienModel.getId()).get();

        try {
            targetPasien.setNama(pasienModel.getNama());
            targetPasien.setNik(pasienModel.getNik());
            targetPasien.setJenisKelamin(pasienModel.getJenisKelamin());
            targetPasien.setTanggalLahir(pasienModel.getTanggalLahir());
            targetPasien.setTempatLahir(pasienModel.getTempatLahir());
            targetPasien.setKode(pasienModel.getKode());
            pasienDb.save(targetPasien);
            return targetPasien;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deletePasien(PasienModel pasien) {
        pasienDb.delete(pasien);
    }
}
