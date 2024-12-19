package apap.tugas1.sipas.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import apap.tugas1.sipas.model.*;
import apap.tugas1.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private PasienAsuransiService pasienAsuransiService;

    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Autowired
    private PasienDiagnosisPenyakitService pasienDiagnosisPenyakitService;

    @Autowired
    private AsuransiService asuransiService;

    @Autowired
    private EmergencyContactService emergencyContactService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<PasienModel> listPasien = pasienService.getAllPasien();
        model.addAttribute("listPasien", listPasien);

        return "home-viewall-pasien";
    }

    @RequestMapping(path = "/pasien", method = RequestMethod.GET)
    public String viewPasien(
            @RequestParam(value = "nikPasien") String nik, Model model
    ) {
        PasienModel pasien = pasienService.getPasienByNik(nik).get();
        model.addAttribute("pasien", pasien);

        List<PasienAsuransiModel> listAsuransi = pasienAsuransiService.getAsuransiByPasien(pasien.getId());
        model.addAttribute("listAsuransi", listAsuransi);

        return "view-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String formAddPasien(Model model) {
        PasienModel pasien = new PasienModel();
        java.util.Date utilDate = new Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime());
        pasien.setTanggalLahir(today);

        AsuransiModel asuransiModel = new AsuransiModel();
        asuransiModel.setId(1L);

        PasienAsuransiModel pasienAsuransi = new PasienAsuransiModel();
        List<PasienAsuransiModel> listAsuransiPasien = new ArrayList<>();
        listAsuransiPasien.add(pasienAsuransi);
        pasien.setListAsuransi(listAsuransiPasien);
        pasienAsuransi.setPasien(pasien);
        pasienAsuransi.setAsuransi(asuransiModel);

        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        pasien.setEmergencyContact(emergencyContact);
        emergencyContact.setPasien(pasien);

        model.addAttribute("pasien", pasien);

        List<AsuransiModel> listAllAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("listAllAsuransi", listAllAsuransi);

        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST, params = {"addAsuransi"})
    public String addAsuransiRow(@ModelAttribute PasienModel pasien, Model model) {
        pasien.getListAsuransi().add(new PasienAsuransiModel());
        model.addAttribute("pasien", pasien);

        List<AsuransiModel> listAllAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("listAllAsuransi", listAllAsuransi);

        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String formAddPasienSubmit(@ModelAttribute PasienModel pasienModel, Model model) {
        EmergencyContactModel emergencyContact = new EmergencyContactModel();
        emergencyContact.setNama(pasienModel.getEmergencyContact().getNama());
        emergencyContact.setNik(pasienModel.getEmergencyContact().getNik());
        emergencyContact.setNoHp(pasienModel.getEmergencyContact().getNoHp());
        emergencyContactService.addEmergencyContact(emergencyContact);
        pasienModel.setEmergencyContact(emergencyContact);

        // Assign kode ke pasien model
        while (true) {
            String kode = pasienService.generateRandomKode(pasienModel.getTanggalLahir(), pasienModel.getJenisKelamin());
            Optional<PasienModel> hasilCari = pasienService.getPasienByKode(kode);
            if (hasilCari.isEmpty()) {
                pasienModel.setKode(kode);
                break;
            }
        }

        // Save pasien model
        pasienService.addPasien(pasienModel);

        for (PasienAsuransiModel pasienAsuransi : pasienModel.getListAsuransi()) {
            AsuransiModel asuransiModel = asuransiService.getAsuransiById(pasienAsuransi.getAsuransi().getId()).get();

            PasienAsuransiModel pasienAsuransiModel = new PasienAsuransiModel();
            pasienAsuransiModel.setPasien(pasienModel);
            pasienAsuransiModel.setAsuransi(asuransiModel);
            pasienAsuransiService.addPasienAsuransiModel(pasienAsuransiModel);
        }

        model.addAttribute("pasien", pasienModel);

        return "add-pasien";
    }

    @RequestMapping(path = "/pasien/{nik}/tambah-diagnosis", method = RequestMethod.GET)
    public String tambahDiagnosisPasienView(
            @PathVariable(value = "nik") String nik, Model model
    ) {
        PasienModel pasien = pasienService.getPasienByNik(nik).get();
        model.addAttribute("pasien", pasien);

        List<PasienDiagnosisPenyakitModel> listPenyakitPasien = pasien.getListDiagnosisPenyakit();
        model.addAttribute("listPenyakitPasien", listPenyakitPasien);

        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getAllDiagnosisPenyakit();
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        DiagnosisPenyakitModel diagnosisPenyakit = new DiagnosisPenyakitModel();
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);

        return "view-diagnosis-penyakit-pasien";
    }

    @RequestMapping(path = "/pasien/{nik}/tambah-diagnosis", method = RequestMethod.POST)
    public String tambahDiagnosisPasienSubmit(
            @PathVariable(value = "nik") String nik,
            @ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit,
            Model model
    ) {
        DiagnosisPenyakitModel targetDiagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(diagnosisPenyakit.getId()).get();
        PasienModel pasien = pasienService.getPasienByNik(nik).get();

        model.addAttribute("targetDiagnosisPenyakit", targetDiagnosisPenyakit);
        model.addAttribute("pasien", pasien);

        boolean sudahAda = false;
        for (PasienDiagnosisPenyakitModel i : pasien.getListDiagnosisPenyakit()) {
            if (i.getDiagnosisPenyakit() == targetDiagnosisPenyakit) {
                sudahAda = true;
                break;
            }
        }
        model.addAttribute("sudahAda", sudahAda);

        if (sudahAda) {
            return "add-diagnosis-penyakit-pasien";
        }

        PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit = new PasienDiagnosisPenyakitModel();
//        java.util.Date utilDate = new Date();
//        java.sql.Date today = new java.sql.Date(utilDate.getTime());
        LocalDateTime lt = LocalDateTime.now();
        pasienDiagnosisPenyakit.setTanggalDiagnosis(lt);
        pasienDiagnosisPenyakit.setPasien(pasien);
        pasienDiagnosisPenyakit.setDiagnosisPenyakit(targetDiagnosisPenyakit);
        // Add to DB
        pasienDiagnosisPenyakitService.addPasienDiagnosisPenyakit(pasienDiagnosisPenyakit);
        model.addAttribute("pasienDiagnosisPenyakit", pasienDiagnosisPenyakit);

        return "add-diagnosis-penyakit-pasien";
    }

    @RequestMapping(value = "/pasien/cari/", method = RequestMethod.GET)
    public String cariPasien(Model model) {
        List<AsuransiModel> listAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("listAsuransi", listAsuransi);

        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getAllDiagnosisPenyakit();
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        DiagnosisPenyakitModel diagnosisPenyakit = new DiagnosisPenyakitModel();
        diagnosisPenyakit.setId(0L);
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);

        AsuransiModel asuransiModel = new AsuransiModel();
        asuransiModel.setId(0L);
        model.addAttribute("asuransi", asuransiModel);

        return "cari-pasien";
    }

    @RequestMapping(value = "/pasien/cari", method = RequestMethod.GET)
    public String cariPasienAsuransiDiagnosis(Model model,
                                              @RequestParam(value = "idAsuransi") Long idAsuransi,
                                              @RequestParam(value = "idDiagnosis") Long idDiagnosis
    ) {
        List<AsuransiModel> listAsuransi = asuransiService.getAllAsuransi();
        model.addAttribute("listAsuransi", listAsuransi);

        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getAllDiagnosisPenyakit();
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        model.addAttribute("idAsuransi", idAsuransi);
        model.addAttribute("idDiagnosis", idDiagnosis);

        List<PasienModel> listPasien = new ArrayList<>();

        if (idAsuransi != 0 && idDiagnosis != 0) {
            List<PasienDiagnosisPenyakitModel> listPasienDiagnosis = pasienDiagnosisPenyakitService.getPasienByDiagnosisPenyakit(idDiagnosis);
            List<PasienAsuransiModel> listPasienAsuransi = pasienAsuransiService.getPasienByAsuransi(idAsuransi);

            List<PasienModel> pasienFromDiagnosis = new ArrayList<>();
            List<PasienModel> pasienFromAsuransi = new ArrayList<>();

            for (PasienDiagnosisPenyakitModel x : listPasienDiagnosis) {
                pasienFromDiagnosis.add(x.getPasien());
            }
            for (PasienAsuransiModel x : listPasienAsuransi) {
                pasienFromAsuransi.add(x.getPasien());
            }

            // Mencari Irisan
            for (PasienModel x : pasienFromDiagnosis) {
                if (pasienFromAsuransi.contains(x)) {
                    listPasien.add(x);
                }
            }
        }
        else if (idAsuransi == 0 && idDiagnosis == 0) {

        }
        else if (idAsuransi == 0) {
            List<PasienDiagnosisPenyakitModel> listPasienDiagnosis = pasienDiagnosisPenyakitService.getPasienByDiagnosisPenyakit(idDiagnosis);
            for (PasienDiagnosisPenyakitModel x : listPasienDiagnosis) {
                listPasien.add(x.getPasien());
            }
        }
        else {
            List<PasienAsuransiModel> listPasienAsuransi = pasienAsuransiService.getPasienByAsuransi(idAsuransi);
            for (PasienAsuransiModel x : listPasienAsuransi) {
                listPasien.add(x.getPasien());
            }
        }

        model.addAttribute("listPasien", listPasien);

        return "cari-pasien-asuransi-diagnosis";
    }

    @RequestMapping(value = "/pasien/cari/lakilaki-perempuan", method = RequestMethod.GET)
    public String cariPasienLakiPerempuanResult(Model model,
                                                @ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit,
                                                @RequestParam(value = "id") Long idDiagnosisPenyakit
        ) {
        List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakitList = pasienDiagnosisPenyakitService.getPasienByDiagnosisPenyakit(idDiagnosisPenyakit);
        DiagnosisPenyakitModel newDiagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(idDiagnosisPenyakit).get();

        int lakilaki = 0;
        int perempuan = 0;
        for (PasienDiagnosisPenyakitModel x : pasienDiagnosisPenyakitList) {
            if (x.getPasien().getJenisKelamin() == 1) {
                lakilaki++;
            } else {
                perempuan++;
            }
        }
        model.addAttribute("lakilaki", lakilaki);
        model.addAttribute("perempuan", perempuan);
        model.addAttribute("diagnosisPenyakit", newDiagnosisPenyakit);

        return "cari-pasien-laki-perempuan-result";
    }

    @RequestMapping(path = "/pasien/ubah/{nik}", method = RequestMethod.GET)
    public String ubahDataPasienForm(
            @PathVariable(value = "nik") String nik,
            Model model
    ) {
        PasienModel pasien = pasienService.getPasienByNik(nik).get();
        model.addAttribute("pasien", pasien);

        return "form-ubah-pasien";
    }

    @RequestMapping(path = "/pasien/ubah/{nik}", method = RequestMethod.POST)
    public String ubahDataPasienSubmit(
            @PathVariable(value = "nik") String nik,
            @ModelAttribute PasienModel pasien,
            Model model
    ) {
        EmergencyContactModel emergencyContact = pasien.getEmergencyContact();
        emergencyContactService.changeEmergencyContactData(emergencyContact);

        // Assign kode ke pasien model
        while (true) {
            String kode = pasienService.generateRandomKode(pasien.getTanggalLahir(), pasien.getJenisKelamin());
            Optional<PasienModel> hasilCari = pasienService.getPasienByKode(kode);
            if (hasilCari.isEmpty()) {
                pasien.setKode(kode);
                break;
            }
        }
        pasienService.changePasienData(pasien);

        model.addAttribute("pasien", pasien);
        return "ubah-pasien";
    }

    @RequestMapping(value = "/pasien/hapus/{nik}", method = RequestMethod.POST)
    public String deletePasien(@PathVariable String nik, Model model) {
        PasienModel pasien = pasienService.getPasienByNik(nik).get();
        model.addAttribute("pasien", pasien);

        pasienService.deletePasien(pasien);
        return "delete-pasien";
    }
}
