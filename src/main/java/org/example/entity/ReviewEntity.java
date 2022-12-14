package org.example.entity;

import java.util.Objects;

public class ReviewEntity {

    private int id_movie;
    private int id_user;
    private String comment;
    private float grade;

    ReviewEntity() {};

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return id_movie == that.id_movie && id_user == that.id_user && Float.compare(that.grade, grade) == 0 && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_movie, id_user, comment, grade);
    }

    @Override
    public String toString() {
        return "ReviewEntity{" +
                "id_movie=" + id_movie +
                ", id_user=" + id_user +
                ", comment='" + comment + '\'' +
                ", grade=" + grade +
                '}';
    }
}
