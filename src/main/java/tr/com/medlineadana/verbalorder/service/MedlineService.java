package tr.com.medlineadana.verbalorder.service;

import org.springframework.http.ResponseEntity;
import tr.com.medlineadana.verbalorder.model.*;

import java.util.List;

public interface MedlineService {
    OlayKararResponse approve(OlayRequest request) throws Exception;
    ResponseEntity<ApiResponse> getPageableKayit(OlayPageableSearchRequest request);
    ViewUpdateResponse viewUpdate(ViewUpdateRequest request);

    OlayDetayResponse getOlayIslemDetay(OlayDetayRequest request);
}
