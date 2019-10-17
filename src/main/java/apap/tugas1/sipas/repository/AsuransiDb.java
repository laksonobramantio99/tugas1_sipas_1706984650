package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.AsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsuransiDb extends JpaRepository<AsuransiModel, Long> {

}
