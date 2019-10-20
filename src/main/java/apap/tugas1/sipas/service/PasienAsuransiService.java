package apap.tugas1.sipas.service;

import apap.tugas1.sipas.model.PasienAsuransiModel;

import java.util.List;


public interface PasienAsuransiService {
    List<PasienAsuransiModel> getAsuransiByPasien(Long id);
}
