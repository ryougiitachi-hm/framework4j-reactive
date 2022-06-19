package per.itachi.framework4j.reactive.webflux.infra.restful.customersvc;

import java.util.concurrent.CompletableFuture;
import per.itachi.framework4j.reactive.webflux.infra.restful.customersvc.dto.CustomerDTO;

public interface CustomerPort {

    CustomerDTO getCustomerByIdCard(String idCard);

    CompletableFuture<CustomerDTO> getCustomerByIdCardAsync(String idCard);

    CustomerDTO getCustomerByNbr(String customerNbr);

    CompletableFuture<CustomerDTO> getCustomerByNbrAsync(String customerNbr);
}
