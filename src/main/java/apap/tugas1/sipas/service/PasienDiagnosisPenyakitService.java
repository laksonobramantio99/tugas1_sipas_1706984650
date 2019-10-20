package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienDiagnosisPenyakitModel;

import java.util.List;

public interface PasienDiagnosisPenyakitService{
    List<PasienDiagnosisPenyakitModel> getPasienByDiagnosisPenyakit(Long id);
    void addPasienDiagnosisPenyakit(PasienDiagnosisPenyakitModel pasienDiagnosisPenyakitModel);
}
