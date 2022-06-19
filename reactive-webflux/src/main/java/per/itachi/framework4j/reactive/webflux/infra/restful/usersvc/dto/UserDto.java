package per.itachi.framework4j.reactive.webflux.infra.restful.usersvc.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String username;

    private String userNbr;

    private LocalDate birthday;
}
