package org.example.entity;

import java.util.Objects;

public class Movie_actorEntity {

    private int id_movie;
    private int id_actor;

    Movie_actorEntity() {};

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie_actorEntity that = (Movie_actorEntity) o;
        return id_movie == that.id_movie && id_actor == that.id_actor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, id_actor);
    }

    @Override
    public String toString() {
        return "Movie_actorEntity{" +
                "id_movie=" + id_movie +
                ", id_actor=" + id_actor +
                '}';
    }
}
