package magiccinema.unideb.hu.utility;

import magiccinema.unideb.hu.services.interfaces.IService;

public class TestService implements IService {
    @Override
    public String getName() {
        return "TestService";
    }

    public int multiply(int x, int y) {
        return x*y;
    }
}
