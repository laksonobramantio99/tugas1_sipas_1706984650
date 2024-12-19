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

    @Override
    public EmergencyContactModel changeEmergencyContactData(EmergencyContactModel emergencyContactModel) {
        EmergencyContactModel targetEmergencyContact = emergencyContactDb.findById(emergencyContactModel.getId()).get();

        try {
            targetEmergencyContact.setNama(emergencyContactModel.getNama());
            targetEmergencyContact.setNik(emergencyContactModel.getNik());
            targetEmergencyContact.setNoHp(emergencyContactModel.getNoHp());
            emergencyContactDb.save(targetEmergencyContact);
            return targetEmergencyContact;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deleteEmergencyContact(EmergencyContactModel emergencyContact) {
        emergencyContactDb.delete(emergencyContact);
    }
}
