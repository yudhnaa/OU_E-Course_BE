package com.ou.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.TestQuestion}
 */
public class TestQuestionDTO implements Serializable {
    private final Integer id;

    public TestQuestionDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestionDTO entity = (TestQuestionDTO) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}