package per.itachi.framework4j.reactive.webflux.infra.restful.customersvc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.dto.CustomerDTO;

@Slf4j
@Component
public class CustomerAdapter implements CustomerPort{

    private static final String V1_CUSTOMERS_IDCARD = "/v1/customers/idCard/{idCard}";

    private static final String V1_CUSTOMERS_NBR = "/v1/customers/customerNbr/{customerNbr}";

    @Autowired
    private WebClient customerSvcWebClient;

    @Autowired
    private ExecutorService commonExecutorService;

    @Override
    public CustomerDTO getCustomerByIdCard(String idCard) {
        return null;
    }

    @Override
    public CompletableFuture<CustomerDTO> getCustomerByIdCardAsync(String idCard) {
        log.info("getCustomerByIdCardAsync, idCard={}. ", idCard);
        return customerSvcWebClient.get()
                .uri(V1_CUSTOMERS_IDCARD, idCard).retrieve()
                .bodyToMono(CustomerDTO.class)
                .toFuture();
    }

    @Override
    public CustomerDTO getCustomerByNbr(String customerNbr) {
        return null;
    }

    @Override
    public CompletableFuture<CustomerDTO> getCustomerByNbrAsync(String customerNbr) {
        return null;
    }
}
