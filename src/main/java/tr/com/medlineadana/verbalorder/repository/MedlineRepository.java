package tr.com.medlineadana.verbalorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.medlineadana.verbalorder.entity.OlayKayit;

import java.util.Optional;

public interface MedlineRepository extends JpaRepository<OlayKayit,Long> {

    Optional<OlayKayit> findByNumara(String number);

}
