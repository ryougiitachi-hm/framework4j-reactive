package per.itachi.framework4j.reactive.webflux.app.service;

import per.itachi.framework4j.reactive.webflux.domain.User;

public interface UserService {

    User getUserByNbr(String userNbr);

    void addUser(User user);
}
