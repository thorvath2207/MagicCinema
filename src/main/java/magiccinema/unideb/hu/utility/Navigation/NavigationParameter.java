package magiccinema.unideb.hu.utility.Navigation;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

public class NavigationParameter{
    private IEntity entity;

    public NavigationParameter(IEntity entity){
        this.entity = entity;
    }

    public IEntity getEntity(){
        return this.entity;
    }
}

