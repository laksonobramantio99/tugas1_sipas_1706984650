package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.DiagnosisPenyakitModel;
import apap.tugas1.sipas.service.DiagnosisPenyakitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DiagnosisPenyakitController {

    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewallDiagnosisPenyakit(Model model) {
        List<DiagnosisPenyakitModel> listDiagnosisPenyakit = diagnosisPenyakitService.getAllDiagnosisPenyakit();
        model.addAttribute("listDiagnosisPenyakit", listDiagnosisPenyakit);

        return "viewall-diagnosis-penyakit";
    }
}
