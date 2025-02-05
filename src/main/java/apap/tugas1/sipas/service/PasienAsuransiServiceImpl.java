package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienAsuransiModel;
import apap.tugas1.sipas.repository.PasienAsuransiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienAsuransiServiceImpl implements PasienAsuransiService {
    @Autowired
    private PasienAsuransiDb pasienAsuransiDb;

    public List<PasienAsuransiModel> getAsuransiByPasien(Long id) {
        return pasienAsuransiDb.findByPasienId(id);
    }
}
