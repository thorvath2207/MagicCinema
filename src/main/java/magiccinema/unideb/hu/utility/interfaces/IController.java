package magiccinema.unideb.hu.utility.interfaces;

import magiccinema.unideb.hu.utility.constans.AdditionalParameters;

import java.util.HashMap;
import java.util.List;

public interface IController {
    void resetData();

    void setData(IEntity entity, HashMap<AdditionalParameters, Integer> addinParams);

    void setData(List<IEntity> entities);
}
