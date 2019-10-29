package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.AsuransiModel;
import apap.tugas1.sipas.model.EmergencyContactModel;
import apap.tugas1.sipas.repository.AsuransiDb;
import apap.tugas1.sipas.repository.EmergencyContactDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {
    @Autowired
    private EmergencyContactDb emergencyContactDb;

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContactModel) {
        emergencyContactDb.save(emergencyContactModel);
    }
}
