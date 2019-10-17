package apap.tugas1.sipas.controller;

import apap.tugas1.sipas.model.PasienModel;
import apap.tugas1.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<PasienModel> listPasien = pasienService.getAllPasien();
        model.addAttribute("listPasien", listPasien);

        return "home-viewall-pasien";
    }
}
