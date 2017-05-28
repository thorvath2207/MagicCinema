package magiccinema.unideb.hu.utility.navigation;

import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.util.HashMap;

public class NavigationParameter {
    private IEntity entity;

    private HashMap<AdditionalParameters, Integer> additionalParameters;

    public NavigationParameter(IEntity entity) {
        this.additionalParameters = new HashMap<>();
        this.entity = entity;
    }

    public IEntity getEntity() {
        return this.entity;
    }

    public HashMap<AdditionalParameters, Integer> getAdditionalParameters() {
        return this.additionalParameters;
    }
}

