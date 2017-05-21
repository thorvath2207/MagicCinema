package magiccinema.unideb.hu.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Movie {
    private String title;
    private BufferedImage image;
    private ArrayList<String> actors;
    private ArrayList<Genre> genres;

    public Movie(String title) {
        this.title = title;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }
}
