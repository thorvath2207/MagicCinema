package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "actor")
public class Actor implements IEntity {

    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;

    @Column(name = "actor_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "actorsCollection")
    private Collection<Movie> movieCollection;

    public Actor() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActorId() {

        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

}
