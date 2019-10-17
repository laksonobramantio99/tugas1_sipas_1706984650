package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.PasienAsuransiModel;
import apap.tugas1.sipas.model.PasienModel;
import apap.tugas1.sipas.service.PasienAsuransiService;
import apap.tugas1.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private PasienAsuransiService pasienAsuransiService;

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
}
