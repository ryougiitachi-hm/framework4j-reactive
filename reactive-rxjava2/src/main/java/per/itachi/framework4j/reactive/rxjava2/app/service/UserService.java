package per.itachi.framework4j.reactive.rxjava2.app.service;

import per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc.dto.UserDto;

public interface UserService {

    void showUsers();

    UserDto getUserByNbr(String userNbr);
}
