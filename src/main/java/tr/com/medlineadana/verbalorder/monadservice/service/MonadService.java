package tr.com.medlineadana.verbalorder.monadservice.service;

import tr.com.medlineadana.verbalorder.monadservice.dto.MonadResponse;

public interface MonadService {
    MonadResponse getRelatedInfo(String orderNumber);
}
