package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findByNik(String nik);
    Optional<PasienModel> findByKode(String kode);
}
