package tr.com.medlineadana.verbalorder.service;

import org.springframework.http.ResponseEntity;
import tr.com.medlineadana.verbalorder.model.*;

public interface MedlineService {
    OlayKararResponse approve(OlayRequest request) throws Exception;
    ResponseEntity<ApiResponse> getPageableKayit(OlayPageableSearchRequest request);
    ViewUpdateResponse viewUpdate(ViewUpdateRequest request);
}
