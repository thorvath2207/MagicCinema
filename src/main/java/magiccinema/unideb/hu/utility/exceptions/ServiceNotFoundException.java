package magiccinema.unideb.hu.utility.exceptions;

public class ServiceNotFoundException extends Exception {
    private String serviceName;

    public ServiceNotFoundException(String serivceName) {
        this.serviceName = serivceName;
    }
}
