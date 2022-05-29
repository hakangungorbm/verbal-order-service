package tr.com.medlineadana.verbalorder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import tr.com.medlineadana.verbalorder.entity.OlayKayit;
import tr.com.medlineadana.verbalorder.exception.ServiceFaultException;
import tr.com.medlineadana.verbalorder.mapper.MedlineMapper;
import tr.com.medlineadana.verbalorder.model.*;
import tr.com.medlineadana.verbalorder.monadservice.dto.MonadResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.MonadService;
import tr.com.medlineadana.verbalorder.repository.MedlineRepository;
import tr.com.medlineadana.verbalorder.service.MedlineService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MedlineServiceImpl implements MedlineService {

    public static final String RESPONSE_OK = "OK";

    private final MedlineRepository repository;
    private final MedlineMapper medlineMapper;

    private final MonadService monadService;

    private final List<String> types = Arrays.asList("tetkik", "order");


    @Override
    public OlayKararResponse approve(OlayRequest request) throws ServiceFaultException {

        checkRulesForRequestData(request);

        String olayNumarasi = request.getNumber();

        final Optional<OlayKayit> kayit = repository.findByNumara(olayNumarasi);
        if (kayit.isPresent()) {
            throw new ServiceFaultException("Bu kayıt için daha önce işlem yapılmış. Tekrar işlem yapılamaz", "400");
        }

        if (request.getNumber().length() == 6) {

            try {
                OlayKayit olayKayit = medlineMapper.toEntity(request);

                MonadResponse monadResponse = monadService.getRelatedInfo(olayNumarasi);

                if (monadResponse != null) {
                    olayKayit.setOrderCreatedDate(monadResponse.getKayitTarihi());
                    olayKayit.setPatientNo(monadResponse.getHastaNo());
                    olayKayit.setPatientBirthdate(monadResponse.getDogumTarihi());
                }

                olayKayit.setDoctorApproveDate(LocalDateTime.now());
                OlayKayit result = repository.save(olayKayit);

                OlayKararResponse response = new OlayKararResponse();
                response.setNumber(request.getNumber());
                response.setOnayDurumu(result.getOnayDurumu());


                return response;

            } catch (Exception e) {
                log.error("HATA NOKTASI: MedlineService - approve - HATA DETAYI" + e.getMessage());
                throw new ServiceFaultException("Onay işlemi esnasında hata: " + e.getMessage(), "404");
            }
        } else {
            throw new ServiceFaultException("Geçersiz numara. Olay numarası istenilen uzunlukta degil!");
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getPageableKayit(@RequestBody OlayPageableSearchRequest request) {

        try {
            Sort.Order order = new Sort.Order(request.getPage().getSortDirection(), request.getPage().getSortField()).ignoreCase();


            Pageable pageable = PageRequest.of(request.getPage().getFirst() / request.getPage().getRows(), request.getPage().getRows(), Sort.by(order));
            final Page<OlayKayitResponse> olayKayitListesi = repository.findAll(pageable).map(medlineMapper::toOlayKayitResponse);

            return ResponseEntity.ok(handleSuccess(new ApiResponse<>(olayKayitListesi.getSize(), olayKayitListesi, RESPONSE_OK)));

        } catch (Exception e) {
            throw new ServiceFaultException("Kayıtlar getirilirken bir hata oluştu " + e.getMessage(), "404");
        }

    }

    @Override
    public ViewUpdateResponse viewUpdate(ViewUpdateRequest request) {
        final OlayKayit olayKayit = repository.getById(request.getId());
        if (!olayKayit.isGorulmeDurumu() && olayKayit.getViewDate() == null) {
            try {
                olayKayit.setViewDate(LocalDateTime.now());
                olayKayit.setGorulmeDurumu(true);
                final OlayKayit updatedOlayKayit = repository.save(olayKayit);
                return new ViewUpdateResponse(updatedOlayKayit.getNumara(), updatedOlayKayit.isGorulmeDurumu(), RESPONSE_OK);
            } catch (Exception e) {
                throw new ServiceFaultException("Görülme kaydı onayı esnasında bir hata oluştu: ", e.getMessage());
            }
        } else {
            throw new ServiceFaultException("Bu kayda daha önce görülme onayı verilmiş durumda! Tekrar işlem yapamazsınız!");
        }
    }


    private boolean checkType(String tip) {
        return types.stream().anyMatch(s -> s.contains(tip));
    }


    private boolean onlyDigits(String number) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    private void checkRulesForRequestData(OlayRequest request) {
        if (request.getType() == null || request.getNumber() == null) {
            throw new ServiceFaultException("Hatalı İşlem: İşlem yapılan olay tipi ve numarası boş olamaz!", "400");
        }
        if (request.getType().isEmpty() || request.getNumber().isEmpty()) {
            throw new ServiceFaultException("Hatalı İşlem: İşlem yapılan olay tipi ve numarası boş olamaz!", "400");
        }
        if (!checkType(request.getType())) {
            throw new ServiceFaultException("Hatalı İşlem: Olay tipi geçersiz. Bu tip için işlem yapılamaz!", "404");
        }
        if (!onlyDigits(request.getNumber())) {
            throw new ServiceFaultException("Hatalı İşlem: Kayıt numarası sadece sayısal değerlerden oluşmalıdır. Bu numara ile işlem yapılamaz!", "400");
        }
    }

    public ApiResponse handleSuccess(Object object) {
        ApiResponse response = new ApiResponse();
        response.setResponse(object);
        response.setCode(RESPONSE_OK);
        return response;
    }

//    @PostConstruct
//    public void initDB() {
//        Random random = new Random();
//        List<OlayKayit> olayKayitList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            final OlayKayit olayKayit = medlineMapper.fakeRequetToEntity(new FakeRequest(LocalDateTime.now(), "order", String.valueOf(random.nextInt(50)), "onayliyorum"));
//            olayKayitList.add(olayKayit);
//        }
//        repository.saveAll(olayKayitList);
//    }

}
