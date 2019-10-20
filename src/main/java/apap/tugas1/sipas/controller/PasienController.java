package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.DiagnosisPenyakitModel;
import apap.tugas1.sipas.model.PasienAsuransiModel;
import apap.tugas1.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas1.sipas.model.PasienModel;
import apap.tugas1.sipas.service.DiagnosisPenyakitService;
import apap.tugas1.sipas.service.PasienAsuransiService;
import apap.tugas1.sipas.service.PasienDiagnosisPenyakitService;
import apap.tugas1.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        java.util.Date utilDate = new Date();
        java.sql.Date today = new java.sql.Date(utilDate.getTime());
        pasienDiagnosisPenyakit.setTanggalDiagnosis(today);
        pasienDiagnosisPenyakit.setPasien(pasien);
        pasienDiagnosisPenyakit.setDiagnosisPenyakit(targetDiagnosisPenyakit);
        // Add to DB
        pasienDiagnosisPenyakitService.addPasienDiagnosisPenyakit(pasienDiagnosisPenyakit);
        model.addAttribute("pasienDiagnosisPenyakit", pasienDiagnosisPenyakit);

        return "add-diagnosis-penyakit-pasien";
    }
}
