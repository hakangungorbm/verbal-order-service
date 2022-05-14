package tr.com.medlineadana.verbalorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceFaultException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String FAULT_STRING = "DETAY";
    private static final String UNKNOWN_ERROR_CODE = "BILINMEYEN HATA";

    private List<ServiceFault> serviceFaults = new ArrayList<>();

    public ServiceFaultException(Throwable e, String code, String description) {
//        super(FAULT_STRING + " : " + description, e);
        super(description, e);
        this.serviceFaults.add(new ServiceFault(code, description));
    }

    public ServiceFaultException(String faultString, Throwable e, ServiceFault serviceFault) {
        super(faultString, e);
        this.serviceFaults.add(serviceFault);
    }

    public ServiceFaultException(String errorMessage) {
        this((Throwable) null, UNKNOWN_ERROR_CODE, errorMessage);
    }

    public ServiceFaultException(String errorMessage, String errorCode) {
        this((Throwable) null, errorCode, errorMessage);
    }

    public ServiceFaultException(String faultString, String errorMessage, String errorCode) {
        this(faultString, null, new ServiceFault(errorCode, errorMessage));
    }

    public List<ServiceFault> getServiceFaults() {
        return serviceFaults;
    }

    public void setServiceFault(List<ServiceFault> serviceFaults) {
        this.serviceFaults = serviceFaults;
    }

    public String getServiceFaultsAsString() {
        return serviceFaults.stream()
                .map(serviceFault -> serviceFault.getCode() + " : " + serviceFault.getDescription())
                .collect(Collectors.joining(" \n"));
    }



}
