package ru.hilariousstartups.javaskills.homework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.hilariousstartups.javaskills.homework.model.generated.GetUserRequest;
import ru.hilariousstartups.javaskills.homework.model.generated.GetUserResponse;
import ru.hilariousstartups.javaskills.homework.model.generated.User;

@Component
public class SoapClient extends WebServiceGatewaySupport {

    private final String endpoint;

    public SoapClient(@Value("${ws.endpoint}") String endpoint, Jaxb2Marshaller marshaller) {
        this.endpoint = endpoint;
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    public User getUser(Integer id) {
        GetUserRequest request = new GetUserRequest();
        request.setUserId(id);
        GetUserResponse response = (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);
        return response.getUser();
    }
}
