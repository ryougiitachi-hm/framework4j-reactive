package per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc.dto.UserDto;

public interface UserPort {

    UserDto getUserByName(String username);

    CompletableFuture<UserDto> getUserByNameAsync(String username);

    UserDto getUserByNbr(String userNbr);

    CompletableFuture<UserDto> getUserByNbrAsync(String userNbr);

    List<UserDto> listUsersByBirthday(LocalDate birthday);

}
