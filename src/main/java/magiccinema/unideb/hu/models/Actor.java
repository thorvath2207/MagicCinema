package magiccinema.unideb.hu.models;

import magiccinema.unideb.hu.utility.interfaces.IEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "actor")
@NamedQueries({
        @NamedQuery(name = "Actor.GET_ALL", query = "SELECT a FROM Actor a")
})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actorId != actor.actorId) return false;
        return name != null ? name.equals(actor.name) : actor.name == null;

    }

    @Override
    public int hashCode() {
        int result = actorId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
