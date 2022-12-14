package org.example.entity;

import java.util.Objects;

public class Movie_producerEntity {

    private int id_movie;
    private int id_producer;

    Movie_producerEntity() {};

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public int getId_producer() {
        return id_producer;
    }

    public void setId_producer(int id_producer) {
        this.id_producer = id_producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie_producerEntity that = (Movie_producerEntity) o;
        return id_movie == that.id_movie && id_producer == that.id_producer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, id_producer);
    }

    @Override
    public String toString() {
        return "Movie_producerEntity{" +
                "id_movie=" + id_movie +
                ", id_producer=" + id_producer +
                '}';
    }
}
