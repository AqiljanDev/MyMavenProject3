package org.example.entity;

import java.util.Objects;

public class ProducerEntity {

    private int id;
    private String fullname;
    private String birth;

    public ProducerEntity(int id, String fullname, String birth) {
        this.id = id;
        this.fullname = fullname;
        this.birth = birth;
    }

    public ProducerEntity() {};

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
        ProducerEntity that = (ProducerEntity) o;
        return id == that.id && Objects.equals(fullname, that.fullname) && Objects.equals(birth, that.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, birth);
    }

    @Override
    public String toString() {
        return "ProducerEntity{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
