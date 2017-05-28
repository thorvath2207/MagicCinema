package magiccinema.unideb.hu.utility.navigation;

import magiccinema.unideb.hu.utility.constans.AdditionalParameters;
import magiccinema.unideb.hu.utility.interfaces.IEntity;

import java.util.HashMap;
import java.util.List;

public class NavigationParameter {
    private IEntity entity;

    private HashMap<AdditionalParameters, Integer> additionalParameters;

    private List<IEntity> entities;

    public NavigationParameter(List<IEntity> entities) {
        this.additionalParameters = new HashMap<>();
        this.entities = entities;
    }

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

    public List<IEntity> getEntities() {
        return this.entities;
    }
}

