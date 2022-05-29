package tr.com.medlineadana.verbalorder.monadservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tr.com.medlineadana.verbalorder.exception.ServiceFaultException;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadRequest;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.MonadService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonadServiceImpl implements MonadService {

    @Value("${monad-service.url}")
    private String olayDetailUri;


    @Override
    public MonadResponse getRelatedInfo(String orderNumber) {

            WebClient webClient = WebClient.create(olayDetailUri);

            Mono<MonadResponse> mono = webClient.post()
                    .uri("/olay-detail")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(
                            MonadRequest.builder()
                                    .orderNumber(orderNumber)
                                    .build()
                    )
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        log.error("HATA NOKTASI: MonadService - GetRelatedInfo(400 lü hata alanı)");
                        return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                    })
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                        log.error("HATA NOKTASI: MonadService - GetRelatedInfo(500 lü hata alanı) hata detayı: " + clientResponse);
                        return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                    })
                    .bodyToMono(MonadResponse.class);
            MonadResponse response = mono.block();
            return  response;
    }
}
