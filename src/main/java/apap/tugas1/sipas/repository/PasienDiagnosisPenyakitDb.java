package apap.tugas1.sipas.repository;

import apap.tugas1.sipas.model.PasienDiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDiagnosisPenyakitDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long> {
    List<PasienDiagnosisPenyakitModel> findByDiagnosisPenyakitId(Long id);
}
