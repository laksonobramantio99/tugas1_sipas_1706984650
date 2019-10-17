package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {

}
