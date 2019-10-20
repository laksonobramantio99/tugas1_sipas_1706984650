package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas1.sipas.repository.PasienDiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienDiagnosisPenyakitServiceImpl implements PasienDiagnosisPenyakitService {
    @Autowired
    private PasienDiagnosisPenyakitDb pasienDiagnosisPenyakitDb;

    @Override
    public List<PasienDiagnosisPenyakitModel> getPasienByDiagnosisPenyakit(Long id) {
        return pasienDiagnosisPenyakitDb.findByDiagnosisPenyakitId(id);
    }
}
