package tr.com.medlineadana.verbalorder.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.medlineadana.verbalorder.model.*;
import tr.com.medlineadana.verbalorder.service.MedlineService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/medline")
public class MedlineController {

    private final MedlineService medlineService;

    @PostMapping("/order")
    public OlayKararResponse query(@RequestBody OlayRequest request) throws Exception {
            return medlineService.approve(request);
    }

    @PostMapping("/search")
    @ApiOperation(value = "Verilen filtreye gore veri tabanindaki tum kayitlari sayfali getirir")
    public ResponseEntity<ApiResponse> getAllKayitByFilter(@RequestBody @Valid OlayPageableSearchRequest request) {
        return medlineService.getPageableKayit(request);
    }

    @PostMapping("/viewUpdate")
    public ViewUpdateResponse viewUpdate(@RequestBody ViewUpdateRequest request) throws Exception {
        return medlineService.viewUpdate(request);
    }

    @GetMapping("/hello")
    public String helloWorld () {
        return "Uygulama calisiyor";
    }
}
