package tr.com.medlineadana.verbalorder.monadservice.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadRequest;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.MonadService;

@RestController
@RequestMapping("/monad")
@RequiredArgsConstructor
public class MonadController {

    private final MonadService monadService;

    @ApiOperation(value = "Monad servis üzerinden ilişkili kaydın detaylarını getirir")
    @PostMapping("/kayit-detay")
    public MonadResponse kayitDetayi(@RequestBody String request) {
        return monadService.getRelatedInfo(request);
    }
}
