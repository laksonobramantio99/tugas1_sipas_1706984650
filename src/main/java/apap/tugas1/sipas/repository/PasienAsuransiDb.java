package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.PasienAsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienAsuransiDb extends JpaRepository<PasienAsuransiModel, Long> {
    List<PasienAsuransiModel> findByPasienId(Long id);
    List<PasienAsuransiModel> findByAsuransiId(Long id);
}
