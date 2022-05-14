package tr.com.medlineadana.verbalorder.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PageSortInfo {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @ApiModelProperty(example = "1" )
    @Min(0)
    private int first;

    @ApiModelProperty(example = "20")
    @Min(0)@Max(100)
    private int rows;

    private Sort.Direction sortDirection = Sort.Direction.DESC;

    private String sortField = "doctorApproveDate";

}
