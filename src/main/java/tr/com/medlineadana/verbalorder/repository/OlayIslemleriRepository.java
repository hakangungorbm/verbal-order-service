package tr.com.medlineadana.verbalorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.medlineadana.verbalorder.entity.OlayDetay;

import java.util.List;


public interface OlayIslemleriRepository extends JpaRepository<OlayDetay,Long> {

    List<OlayDetay> getAllByNumaraAndDeletedFalse(String numara);

}
