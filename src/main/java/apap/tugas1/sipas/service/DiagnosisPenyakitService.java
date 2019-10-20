package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.DiagnosisPenyakitModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisPenyakitService {
    List<DiagnosisPenyakitModel> getAllDiagnosisPenyakit();
    Optional<DiagnosisPenyakitModel> getDiagnosisPenyakitById(Long id);
    void addDiagnosisPenyakit(DiagnosisPenyakitModel diagnosisPenyakit);
}
