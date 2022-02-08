package com.sakura.myblog.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Sakura
 */
@Data
public class Term implements Serializable {
    private int id;
    private String name;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return id == term.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
