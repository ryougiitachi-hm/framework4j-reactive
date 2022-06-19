package per.itachi.framework4j.reactive.webflux.infra.restful.usersvc;

import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import per.itachi.framework4j.reactive.webflux.infra.restful.usersvc.dto.UserDto;

@Component
public class UserAdapter implements UserPort{

    @Autowired
    private WebClient userSvcWebClient;

    @Autowired
    private ExecutorService commonExecutorService;

    @Override
    public UserDto getUserByNbr(String userNbr) {
        return null;
    }

    @Override
    public void addUser(UserDto userDto) {

    }
}
