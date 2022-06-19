package per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import per.itachi.framework4j.reactive.rxjava2.infra.restful.usersvc.dto.UserDto;

@Slf4j
@Component
public class MockUserAdapter implements UserPort {

    private static final long SLEEP_TIME_SEC = 3L;

    @Autowired
    private ExecutorService commonExecutorService;

    @Autowired
    private Observer<UserDto> userDtoObserver;

    @Override
    public UserDto getUserByName(String username) {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_TIME_SEC);
        }
        catch (InterruptedException e) {
            log.error("Error occurred when invoking getUserByName, username={}. ",
                    username, e);
        }
        UserDto dto = new UserDto();
        dto.setUsername(UUID.randomUUID().toString());
        dto.setUserNbr("getUserByName");
        dto.setBirthday(LocalDate.now());
        return dto;
    }

    @Override
    public CompletableFuture<UserDto> getUserByNameAsync(String username) {
        Observable<UserDto> observable = Observable.create(emitter -> {
            try {
                UserDto dto = getUserByName(username);
                emitter.onNext(dto);
            }
            catch (Exception e) {
                emitter.onError(e);
            }
            emitter.onComplete();
        });
//        observable.subscribe(userDtoObserver);
        return CompletableFuture.supplyAsync(()->{
            try {
                log.info("CompletableFuture started getUserByNameAsync. ");
                Future<UserDto> future = observable.toFuture();
                return future.get();
            }
            catch (ExecutionException|InterruptedException e) {
                throw new RuntimeException("Error occurred. ", e);
            }
        }, commonExecutorService);
    }

    @Override
    public UserDto getUserByNbr(String userNbr) {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_TIME_SEC);
        }
        catch (InterruptedException e) {
            log.error("Error occurred when invoking getUserByNbr, userNbr={}. ",
                    userNbr, e);
        }
        UserDto dto = new UserDto();
        dto.setUsername(UUID.randomUUID().toString());
        dto.setUserNbr("getUserByNbr");
        dto.setBirthday(LocalDate.now());
        return dto;
    }

    @Override
    public CompletableFuture<UserDto> getUserByNbrAsync(String userNbr) {
        return null;
    }

    @Override
    public List<UserDto> listUsersByBirthday(LocalDate birthday) {
        return Collections.emptyList();
    }


    @Slf4j
    @Component
    public static class UserAdapterObserver implements Observer<UserDto> {

        @Override
        public void onSubscribe(Disposable d) {
            log.info("onSubscribe, d={}. ", d);
        }

        @Override
        public void onNext(UserDto userDto) {
            log.info("onNext, userDto={}. ", userDto);
        }

        @Override
        public void onError(Throwable e) {
            if (e == null) {
                log.error("onError withoud e. ");
            }
            else {
                log.error("onError", e);
            }
        }

        @Override
        public void onComplete() {
            log.info("onComplete. ");
        }
    }
}
