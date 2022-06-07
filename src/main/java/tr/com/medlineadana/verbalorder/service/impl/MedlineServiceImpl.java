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
import tr.com.medlineadana.verbalorder.entity.OlayDetay;
import tr.com.medlineadana.verbalorder.entity.OlayKayit;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;
import tr.com.medlineadana.verbalorder.exception.ServiceFaultException;
import tr.com.medlineadana.verbalorder.mapper.MedlineMapper;
import tr.com.medlineadana.verbalorder.model.*;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayIslemleriResponse;
import tr.com.medlineadana.verbalorder.monadservice.dto.OlayResponse;
import tr.com.medlineadana.verbalorder.monadservice.service.OlayService;
import tr.com.medlineadana.verbalorder.repository.OlayIslemleriRepository;
import tr.com.medlineadana.verbalorder.repository.OlayRepository;
import tr.com.medlineadana.verbalorder.service.MedlineService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MedlineServiceImpl implements MedlineService {

    public static final String RESPONSE_OK = "OK";

    private final OlayRepository olayRepository;
    private final OlayIslemleriRepository olayIslemleriRepository;
    private final MedlineMapper medlineMapper;

    private final OlayService olayService;

    private final List<String> types = Arrays.asList("tetkik", "order", "panikdeger");


    @Override
    public OlayKararResponse approve(OlayRequest request) throws ServiceFaultException {

        checkRulesForRequestData(request);

        String olayNumarasi = request.getNumber();
        String olayTipi = request.getType();

        final Optional<OlayKayit> kayit = olayRepository.findByNumara(olayNumarasi);
        if (kayit.isPresent()) {
            throw new ServiceFaultException("Bu kayıt için daha önce işlem yapılmış. Tekrar işlem yapılamaz", "400");
        }

        if (!(request.getNumber().length() >= 6 && request.getNumber().length() <=8)) {
            throw new ServiceFaultException("Geçersiz numara. Olay numarası istenilen uzunlukta degil!");
        }

        try {

            //1. Adim: Onyuzden Gelen verileri entity e mapledim
            OlayKayit olayKayit = medlineMapper.toEntity(request);

            //2. Adim: olay numarasi ile olayin detayini monad servisten getirdim
            OlayResponse olayResponse = olayService.getSozelOrderDetayFromMonad(olayNumarasi);

            if (olayResponse != null && !olayResponse.getHastaNo().isEmpty()) {

                //3. Adim: olaya ait islem listesini monad servisten getirip sorunsuz kayit edildiyse digerlerini de kayit ediyorum.
                if(islemListesiIslemleri(olayNumarasi,olayTipi)) {
                    olayKayit.setDoctorName(olayResponse.getTedaviDoktoru());
                    olayKayit.setNurseName(olayResponse.getKayitedenPersonel());
                    olayKayit.setPatientName(olayResponse.getHastaadiSoyadi());
                    olayKayit.setOrderCreatedDate(olayResponse.getKayitTarihi());
                    olayKayit.setPatientNo(olayResponse.getHastaNo());
                    olayKayit.setPatientBirthdate(olayResponse.getDogumTarihi());
                    olayKayit.setFloor(olayResponse.getYatakgrubuKodu());
                    olayKayit.setFloorDetail(olayResponse.getYatakgrubuAdi());

                    if(Objects.equals(olayResponse.getYatakgrubuAdi(), "TABURCU")) {
                        olayKayit.setOnayDurumu(OnayDurumlari.TABURCU);
                    }
                }

            }

            olayKayit.setDoctorApproveDate(LocalDateTime.now());



            OlayKayit result = olayRepository.save(olayKayit);

            OlayKararResponse response = new OlayKararResponse();
            response.setNumber(request.getNumber());
            response.setOnayDurumu(result.getOnayDurumu());


            return response;

        } catch (Exception e) {
            log.error("HATA NOKTASI: MedlineService - approve - HATA DETAYI" + e.getMessage());
            throw new ServiceFaultException("Onay işlemi esnasında hata: " + e.getMessage(), "404");
        }
    }

    public Boolean islemListesiIslemleri(String olayNumarasi, String olayTipi) throws ServiceFaultException {
        List<OlayIslemleriResponse> islemListesi = olayService.getSozelOrderIslemListesiFromMonad(olayNumarasi);

        if(!islemListesi.isEmpty() && !islemListesi.get(0).getIslemAdi().isEmpty()) {
            try {
                List<OlayDetay> olayDetayList = islemListesi.stream().map(medlineMapper::toOlayDetayEntity).collect(Collectors.toList());
                for(OlayDetay islem: olayDetayList) {
                    islem.setNumara(olayNumarasi);
                    islem.setOlay(medlineMapper.olaylar(olayTipi));
                    islem.setCreatedDate(LocalDateTime.now());
                }
                olayIslemleriRepository.saveAll(olayDetayList);
                return true;
            } catch (Exception e) {
                log.error("HATA NOKTASI: MedlineService - islemListesiIslemleri methodu - HATA DETAYI" + e.getMessage());
                throw new ServiceFaultException("Olaya ait işlem listesi işlenemedi. " + e.getMessage(), "404");
            }
        }else {
            throw new ServiceFaultException("Olayın işlem listesine erişilemedi.", "404");
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getPageableKayit(@RequestBody OlayPageableSearchRequest request) throws ServiceFaultException {

        try {
            Sort.Order order = new Sort.Order(request.getPage().getSortDirection(), request.getPage().getSortField()).ignoreCase();


            Pageable pageable = PageRequest.of(request.getPage().getFirst() / request.getPage().getRows(), request.getPage().getRows(), Sort.by(order));
            final Page<OlayKayitResponse> olayKayitListesi = olayRepository.findAll(pageable).map(medlineMapper::toOlayKayitResponse);

            return ResponseEntity.ok(handleSuccess(new ApiResponse<>(olayKayitListesi.getSize(), olayKayitListesi, RESPONSE_OK)));

        } catch (Exception e) {
            throw new ServiceFaultException("Kayıtlar getirilirken hata oluştu " + e.getMessage(), "404");
        }

    }

    @Override
    public OlayDetayResponse getOlayIslemDetay(OlayDetayRequest request) throws ServiceFaultException {
        String numara = request.getOlayNumarasi();

        try {
            List<OlayDetay> detayList = olayIslemleriRepository.getAllByNumaraAndDeletedFalse(numara);
            LocalDate islemTarihi = detayList.get(0).getIslemTarihi();
            List<IslemDto> islemListesi = detayList.stream().map(medlineMapper::toIslemDto).collect(Collectors.toList());
            return new OlayDetayResponse(islemTarihi,islemListesi,RESPONSE_OK, detayList.get(0).getSonuc() != null);
        }catch (Exception e) {
            throw new ServiceFaultException("Görüntüleme hatası. Detay: İşlem detaylarına veri tabanında ulaşılamamıştır! Bu kayıt eski kayıt olabilir.", e.getMessage(), "404");
        }

    }

    @Override
    public ViewUpdateResponse viewUpdate(ViewUpdateRequest request) throws ServiceFaultException {
        final OlayKayit olayKayit = olayRepository.getById(request.getId());
        if (!olayKayit.isGorulmeDurumu() && olayKayit.getViewDate() == null) {
            try {
                olayKayit.setViewDate(LocalDateTime.now());
                olayKayit.setGorulmeDurumu(true);
                final OlayKayit updatedOlayKayit = olayRepository.save(olayKayit);
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

    private void checkRulesForRequestData(OlayRequest request) throws ServiceFaultException {
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

}
