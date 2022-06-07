package tr.com.medlineadana.verbalorder.monadservice.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.medlineadana.verbalorder.model.OlayRequest;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayIslemleriResponse;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.OlayService;

import java.util.List;

@RestController
@RequestMapping("/monad")
@RequiredArgsConstructor
public class MonadOlayController {

    private final OlayService olayService;

    @ApiOperation(value = "Monad servis üzerinden ilişkili sözel order detaylarını getirir")
    @PostMapping("/sozel-order/detay")
    public OlayResponse sozelOrderDetay(@RequestBody OlayRequest olayRequest) {
        String request = olayRequest.getNumber();
        return olayService.getSozelOrderDetayFromMonad(request);
    }

    @ApiOperation(value = "Monad servis üzerinden ilişkili sözel order detaylarını getirir")
    @PostMapping("/sozel-order/islem-listesi")
    public List<OlayIslemleriResponse> sozelOrderIslemListesi(@RequestBody OlayRequest olayRequest) {
        String request = olayRequest.getNumber();
        return olayService.getSozelOrderIslemListesiFromMonad(request);
    }


}


