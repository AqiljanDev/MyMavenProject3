package org.example.entity;

import java.util.Objects;

public class MovieEntity {

    private int id;
    private String name;
    private int id_actor;
    private int id_producer;
    private String relise_date;
    private String country;
    private String genre;

    public MovieEntity(int id, String name, int id_actor, int id_producer, String relise_date, String country, String genre) {
        this.id = id;
        this.name = name;
        this.id_actor = id_actor;
        this.id_producer = id_producer;
        this.relise_date = relise_date;
        this.country = country;
        this.genre = genre;
    }

    public MovieEntity() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    public int getId_producer() {
        return id_producer;
    }

    public void setId_producer(int id_producer) {
        this.id_producer = id_producer;
    }

    public String getRelise_date() {
        return relise_date;
    }

    public void setRelise_date(String relise_date) {
        this.relise_date = relise_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return id == that.id && id_actor == that.id_actor && id_producer == that.id_producer && Objects.equals(name, that.name) && Objects.equals(relise_date, that.relise_date) && Objects.equals(country, that.country) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, id_actor, id_producer, relise_date, country, genre);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", id_actor=" + id_actor +
                ", id_producer=" + id_producer +
                ", relise_date='" + relise_date + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
