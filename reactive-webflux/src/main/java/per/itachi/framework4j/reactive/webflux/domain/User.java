package per.itachi.framework4j.reactive.webflux.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private String username;

    private String userNbr;

    private LocalDate birthday;
}
