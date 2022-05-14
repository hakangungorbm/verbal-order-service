package tr.com.medlineadana.verbalorder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tr.com.medlineadana.verbalorder.entity.OlayKayit;
import tr.com.medlineadana.verbalorder.enums.Olaylar;
import tr.com.medlineadana.verbalorder.enums.OnayDurumlari;
import tr.com.medlineadana.verbalorder.model.FakeRequest;
import tr.com.medlineadana.verbalorder.model.OlayKayitResponse;
import tr.com.medlineadana.verbalorder.model.OlayRequest;

@Mapper
public interface MedlineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctorApproveDate", ignore = true)
    @Mapping(target = "onayDurumu", source = "cevap", qualifiedByName="onaydurum")
    @Mapping(target = "numara", source="number")
    @Mapping(target = "olay", source = "type", qualifiedByName = "olaylar")
    OlayKayit toEntity(OlayRequest dto);


    OlayKayitResponse toOlayKayitResponse(OlayKayit olayKayit);

    @Named("onaydurum")
    default OnayDurumlari onaydurum(Object o) {
        if(o == null) {
            return null;
        }else {
            return switch ((String) o) {
                case "onayliyorum" -> OnayDurumlari.ONAYLANDI;
                case  "reddediyorum" -> OnayDurumlari.RED_EDILDI;
                default -> null;
            };
        }
    }

    @Named("olaylar")
    default Olaylar olaylar(Object o) {
        if(o == null) {
            return null;
        }else {
            return switch ((String) o) {
                case "order" -> Olaylar.ORDER;
                case  "tetkik" -> Olaylar.TETKIK;
                default -> null;
            };
        }
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "onayDurumu", source = "cevap", qualifiedByName="onaydurum")
    @Mapping(target = "numara", source="number")
    @Mapping(target = "olay", source = "type", qualifiedByName = "olaylar")
    OlayKayit fakeRequetToEntity(FakeRequest dto);

}
