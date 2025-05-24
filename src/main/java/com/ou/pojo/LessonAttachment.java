/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "lesson_attachment")
@NamedQueries({
    @NamedQuery(name = "LessonAttachment.findAll", query = "SELECT l FROM LessonAttachment l"),
    @NamedQuery(name = "LessonAttachment.findById", query = "SELECT l FROM LessonAttachment l WHERE l.id = :id")})
public class LessonAttachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Attachment attachmentId;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lesson lessonId;



    public LessonAttachment() {
    }

    public LessonAttachment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Attachment getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Attachment attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LessonAttachment)) {
            return false;
        }
        LessonAttachment other = (LessonAttachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.LessonAttachment[ id=" + id + " ]";
    }

}
