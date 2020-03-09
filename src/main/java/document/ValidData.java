package document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Component
public class ValidData implements Comparable<ValidData>, IData {

    private String emailId;
    private String reasons;

    @Override
    public int compareTo(ValidData o) {
        int status = emailId.compareTo(o.getEmailId());
        return status == 0 ? reasons.compareTo(o.getReasons()) : status;
    }

}
