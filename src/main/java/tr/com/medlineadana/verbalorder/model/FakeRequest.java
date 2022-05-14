package tr.com.medlineadana.verbalorder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FakeRequest {
    private LocalDateTime doctorApproveDate;
    private String type;
    private String number;
    private String cevap;

}