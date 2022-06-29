package tr.com.medlineadana.verbalorder.monadservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tr.com.medlineadana.verbalorder.exception.ServiceFaultException;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadRequest;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayIslemleriResponse;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.OlayService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OlayServiceImpl implements OlayService {

    @Value("${monad-service.main}")
    private String olayurl;

    @Override
    public OlayResponse getSozelOrderDetayFromMonad(String olayNumarasi) {
            WebClient webClient = WebClient.create(olayurl);
            Mono<OlayResponse> mono = webClient.post()
                    .uri("/sozel-order/detay")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(
                            MonadRequest.builder()
                                    .olayNumarasi(olayNumarasi)
                                    .build()
                    )
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, response -> {
                        log.error("HATA NOKTASI: MonadService - getSozelOrderDetayFromMonad(400 lü hata alanı)");
                        return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                    })
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                        log.error("HATA NOKTASI: MonadService - getSozelOrderDetayFromMonad(500 lü hata alanı) hata detayı: " + clientResponse);
                        return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                    })
                    .bodyToMono(OlayResponse.class);
            OlayResponse response = mono.block();
            return  response;
    }

    @Override
    public List<OlayIslemleriResponse> getSozelOrderIslemListesiFromMonad(String olayNumarasi) {
        WebClient webClient = WebClient.create(olayurl);
        Mono<List<OlayIslemleriResponse>> mono = webClient.post()
                .uri("/sozel-order/islem-listesi")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        MonadRequest.builder()
                                .olayNumarasi(olayNumarasi)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.error("HATA NOKTASI: MonadService - getSozelOrderIslemListesiFromMonad(400 lü hata alanı)");
                    return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("HATA NOKTASI: MonadService - getSozelOrderIslemListesiFromMonad(500 lü hata alanı) hata detayı: " + clientResponse);
                    return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                })
                .bodyToMono(new ParameterizedTypeReference<List<OlayIslemleriResponse>>() {});
        List<OlayIslemleriResponse> response = mono.block();
        return  response;
    }

    @Override
    public OlayResponse getPanikDegerDetayFromMonad(String olayNumarasi) {
        WebClient webClient = WebClient.create(olayurl);
        Mono<OlayResponse> mono = webClient.post()
                .uri("/panik-deger/detay")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        MonadRequest.builder()
                                .olayNumarasi(olayNumarasi)
                                .build()
                )
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.error("HATA NOKTASI: MonadService - GetRelatedSozelOrder(400 lü hata alanı)");
                    return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("HATA NOKTASI: MonadService - GetRelatedSozelOrder(500 lü hata alanı) hata detayı: " + clientResponse);
                    return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                })
                .bodyToMono(OlayResponse.class);
        OlayResponse response = mono.block();
        return  response;
    }

    @Override
    public List<OlayIslemleriResponse> getPanikDegerIslemListesiFromMonad(String olayNumarasi) {
        WebClient webClient = WebClient.create(olayurl);
        Mono<List<OlayIslemleriResponse>> mono = webClient.post()
                .uri("/panik-deger/islem-listesi")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        MonadRequest.builder()
                                .olayNumarasi(olayNumarasi)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.error("HATA NOKTASI: MonadService - getSozelOrderIslemListesiFromMonad(400 lü hata alanı)");
                    return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("HATA NOKTASI: MonadService - getSozelOrderIslemListesiFromMonad(500 lü hata alanı) hata detayı: " + clientResponse);
                    return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                })
                .bodyToMono(new ParameterizedTypeReference<List<OlayIslemleriResponse>>() {});
        List<OlayIslemleriResponse> response = mono.block();
        return  response;
    }

    @Override
    public OlayResponse getRadyolojiPanikDegerDetayFromMonad(String olayNumarasi) {
        WebClient webClient = WebClient.create(olayurl);
        Mono<OlayResponse> mono = webClient.post()
                .uri("/radyoloji-panik-deger/detay")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        MonadRequest.builder()
                                .olayNumarasi(olayNumarasi)
                                .build()
                )
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.error("HATA NOKTASI: MonadService - getRadyolojiPanikDegerDetayFromMonad(400 lü hata alanı)");
                    return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("HATA NOKTASI: MonadService - getRadyolojiPanikDegerDetayFromMonad(500 lü hata alanı) hata detayı: " + clientResponse);
                    return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                })
                .bodyToMono(OlayResponse.class);
        OlayResponse response = mono.block();
        return  response;
    }

    @Override
    public List<OlayIslemleriResponse> getRadyolojiPanikDegerIslemListesiFromMonad(String olayNumarasi) {
        WebClient webClient = WebClient.create(olayurl);
        Mono<List<OlayIslemleriResponse>> mono = webClient.post()
                .uri("/radyoloji-panik-deger/islem-listesi")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        MonadRequest.builder()
                                .olayNumarasi(olayNumarasi)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    log.error("HATA NOKTASI: MonadService - getRadyolojiPanikDegerIslemListesiFromMonad(400 lü hata alanı)");
                    return Mono.error( new ServiceFaultException("Hastane kayıtlarında ilgili numara ile eşleşen kayıt bulunamadı!"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("HATA NOKTASI: MonadService - getRadyolojiPanikDegerIslemListesiFromMonad(500 lü hata alanı) hata detayı: " + clientResponse);
                    return Mono.error(new ServiceFaultException("Kayıt doğrulama için kullanılan hastane sunucularına erişilemiyor"));
                })
                .bodyToMono(new ParameterizedTypeReference<List<OlayIslemleriResponse>>() {});
        List<OlayIslemleriResponse> response = mono.block();
        return  response;
    }
}
