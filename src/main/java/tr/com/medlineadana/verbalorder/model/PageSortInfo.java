package tr.com.medlineadana.verbalorder.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PageSortInfo {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Schema(description = "Sayfa numarası (0'dan başlar)", example = "0", minimum = "0")
    @Min(0)
    private int first = 0;

    @Schema(description = "Sayfa başına kayıt sayısı", example = "20", minimum = "1", maximum = "100", defaultValue = "20")
    @Min(1)
    @Max(100)
    private int rows = DEFAULT_PAGE_SIZE;

    private Sort.Direction sortDirection = Sort.Direction.DESC;

    private String sortField = "doctorApproveDate";

}
