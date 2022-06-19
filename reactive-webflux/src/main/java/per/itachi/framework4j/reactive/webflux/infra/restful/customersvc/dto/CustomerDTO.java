package per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private String name;

    private String idCard;

    private String customerNbr;

    private LocalDate birthday;
}
