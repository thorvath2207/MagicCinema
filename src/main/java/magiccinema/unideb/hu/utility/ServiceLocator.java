package magiccinema.unideb.hu.utility;

import magiccinema.unideb.hu.services.interfaces.IService;
import magiccinema.unideb.hu.utility.exceptions.ServiceNotFoundException;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ServiceLocator {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

    private static List<IService> services;

    static {
        logger.trace("Service locator created.");
        services = new ArrayList<>();
    }

    /**
     * Get serivce from service locator
     *
     * @param serviceName
     * @return
     */
    public static IService getService(String serviceName) throws ServiceNotFoundException {
        for (IService service : services) {
            if (service.getName().equalsIgnoreCase(serviceName)) {
                logger.debug(String.format("Return service from service locator: %s", serviceName));
                return service;
            }
        }

        logger.error(String.format("Service not found in service locator! %s", serviceName));
        throw new ServiceNotFoundException(serviceName);
    }

    /**
     * Add a new service to service locator if not exists.
     *
     * @param newService;
     */
    public static void registerService(IService newService) {
        boolean exists = false;

        for (IService service : services) {
            if (service.getName().equalsIgnoreCase(newService.getName())) {
                exists = true;
            }
        }

        if (!exists) {
            logger.debug(String.format("Add service: %s", newService.getName()));
            services.add(newService);
        }
    }
}