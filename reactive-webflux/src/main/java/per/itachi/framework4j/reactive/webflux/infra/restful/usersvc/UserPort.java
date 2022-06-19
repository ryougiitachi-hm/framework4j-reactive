package per.itachi.framework4j.reactive.webflux.infra.restful.usersvc;

import per.itachi.framework4j.reactive.webflux.infra.restful.usersvc.dto.UserDto;

public interface UserPort {

    UserDto getUserByNbr(String userNbr);

    void addUser(UserDto userDto);
}
