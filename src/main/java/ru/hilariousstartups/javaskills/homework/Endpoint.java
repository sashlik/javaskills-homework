package ru.hilariousstartups.javaskills.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hilariousstartups.javaskills.homework.model.generated.User;
import ru.hilariousstartups.javaskills.homework.rs.api.RsEndpointApi;
import ru.hilariousstartups.javaskills.homework.rs.model.PhonesResponse;

import java.util.Optional;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class Endpoint {

    private SoapClient soapClient;
    private RsEndpointApi rsEndpointApi;

    public Endpoint(SoapClient soapClient, RsEndpointApi rsEndpointApi) {
        this.soapClient = soapClient;
        this.rsEndpointApi = rsEndpointApi;
    }

    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable("id") final Integer id) {
        Response response = new Response();
        response.setCode(0);

       ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<User> userFuture = pool.submit(() -> getUserRemote(id));
        Future<PhonesResponse> phonesFuture = pool.submit(() -> getPhonesRemote(id));

        User user = null;
        try {
            user = userFuture.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.error("User timeout");
            response.setCode(1);
            return response;
        } catch (Exception e) {
            log.error("User error");
            response.setCode(2);
            return response;
        }

        PhonesResponse phones = null;
        try {
             phones = phonesFuture.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.error("Phones timeout");
        } catch (Exception e) {
            log.error("Phones error");
        }

        String phone = Optional.ofNullable(phones).map(PhonesResponse::getPhones).filter(p -> !p.isEmpty()).map(p -> p.get(0)).orElse(null);
        String name = Optional.ofNullable(user).map(u -> u.getFirstName() + " " + u.getLastName()).orElse(null);

        response.setName(name);
        response.setPhone(phone);

        return response;
    }

    private User getUserRemote(Integer id) {
        log.info("Getting user");
        User user = soapClient.getUser(id);
        log.info("Got user");
        return user;
    }

    private PhonesResponse getPhonesRemote(Integer id) {
        log.info("Getting phones");
        PhonesResponse phones = rsEndpointApi.phones(id);
        log.info("Got phones");
        return phones;
    }

}
