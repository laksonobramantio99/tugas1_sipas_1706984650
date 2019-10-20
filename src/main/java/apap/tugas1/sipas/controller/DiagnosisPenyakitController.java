package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.DiagnosisPenyakitModel;
import apap.tugas1.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas1.sipas.service.DiagnosisPenyakitService;
import apap.tugas1.sipas.service.PasienDiagnosisPenyakitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiagnosisPenyakitController {

    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Autowired
    private PasienDiagnosisPenyakitService pasienDiagnosisPenyakitService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewallDiagnosisPenyakit(Model model) {
        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getAllDiagnosisPenyakit();
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        return "viewall-diagnosis-penyakit";
    }

    @RequestMapping(path = "/diagnosis-penyakit", method = RequestMethod.GET)
    public String viewDiagnosisPenyakit(
            @RequestParam(value = "idDiagnosis") Long id, Model model
    ) {
        DiagnosisPenyakitModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(id).get();
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);

        List<PasienDiagnosisPenyakitModel> listPasien = pasienDiagnosisPenyakitService.getPasienByDiagnosisPenyakit(diagnosisPenyakit.getId());
        model.addAttribute("listPasien", listPasien);

        return "view-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String addDiagnosisPenyakitFormPage(Model model) {
        DiagnosisPenyakitModel newDiagnosisPenyakit = new DiagnosisPenyakitModel();
        model.addAttribute("diagnosisPenyakit", newDiagnosisPenyakit);
        return "form-add-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String addDiagnosisPenyakitSubmit(@ModelAttribute DiagnosisPenyakitModel diagnosisPenyakit, Model model) {
        diagnosisPenyakitService.addDiagnosisPenyakit(diagnosisPenyakit);
        return "add-diagnosis-penyakit";
    }
}
