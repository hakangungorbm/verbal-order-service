package tr.com.medlineadana.verbalorder.monadservice.service;

import tr.com.medlineadana.verbalorder.monadservice.dto.OlayIslemleriResponse;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayResponse;

import java.util.List;

public interface OlayService {
    OlayResponse getSozelOrderDetayFromMonad(String olayNumarasi);
    List<OlayIslemleriResponse> getSozelOrderIslemListesiFromMonad(String olayNumarasi);

    OlayResponse getPanikDegerDetayFromMonad(String olayNumarasi);

    List<OlayIslemleriResponse> getPanikDegerIslemListesiFromMonad(String olayNumarasi);

    OlayResponse getRadyolojiPanikDegerDetayFromMonad(String olayNumarasi);

    List<OlayIslemleriResponse> getRadyolojiPanikDegerIslemListesiFromMonad(String olayNumarasi);
}
