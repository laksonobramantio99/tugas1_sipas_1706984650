package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienModel;
import apap.tugas1.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }
}
