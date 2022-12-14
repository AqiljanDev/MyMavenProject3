package org.example.entity;

import java.util.Objects;

public class ActorEntity {

    private int id;
    private String fullname;
    private String birth;

    public ActorEntity(int id, String fullname, String birth) {
        this.id = id;
        this.fullname = fullname;
        this.birth = birth;
    }

    public ActorEntity() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorEntity actor = (ActorEntity) o;
        return id == actor.id && Objects.equals(fullname, actor.fullname) && Objects.equals(birth, actor.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, birth);
    }

    @Override
    public String toString() {
        return "actor{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
