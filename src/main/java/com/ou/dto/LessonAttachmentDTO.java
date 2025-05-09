package com.ou.dto;

import com.ou.pojo.LessonAttachment;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link LessonAttachment}
 */
public class LessonAttachmentDTO implements Serializable {
    private final Integer id;

    public LessonAttachmentDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAttachmentDTO entity = (LessonAttachmentDTO) o;
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