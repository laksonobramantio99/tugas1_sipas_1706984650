package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.PasienAsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienAsuransiDb extends JpaRepository<PasienAsuransiModel, Long> {

}
