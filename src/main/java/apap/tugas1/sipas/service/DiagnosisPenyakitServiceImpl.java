package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.DiagnosisPenyakitModel;
import apap.tugas1.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService {
    @Autowired
    private DiagnosisPenyakitDb diagnosisPenyakitDb;

    public List<DiagnosisPenyakitModel> getAllDiagnosisPenyakit() {
        return diagnosisPenyakitDb.findAll();
    }
}
