/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "attachment")
@NamedQueries({
        @NamedQuery(name = "Attachment.findAll", query = "SELECT a FROM Attachment a"),
        @NamedQuery(name = "Attachment.findById", query = "SELECT a FROM Attachment a WHERE a.id = :id"),
        @NamedQuery(name = "Attachment.findByName", query = "SELECT a FROM Attachment a WHERE a.name = :name"),
        @NamedQuery(name = "Attachment.findByLink", query = "SELECT a FROM Attachment a WHERE a.link = :link")})
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "link")
    private String link;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attachmentId")
    private Set<ExerciseAttachment> exerciseAttachmentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attachmentId")
    private Set<LessonAttachment> lessonAttachmentSet;

    public Attachment() {
    }

    public Attachment(Integer id) {
        this.id = id;
    }

    public Attachment(Integer id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ExerciseAttachment> getExerciseAttachmentSet() {
        return exerciseAttachmentSet;
    }

    public void setExerciseAttachmentSet(Set<ExerciseAttachment> exerciseAttachmentSet) {
        this.exerciseAttachmentSet = exerciseAttachmentSet;
    }

    public Set<LessonAttachment> getLessonAttachmentSet() {
        return lessonAttachmentSet;
    }

    public void setLessonAttachmentSet(Set<LessonAttachment> lessonAttachmentSet) {
        this.lessonAttachmentSet = lessonAttachmentSet;
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
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Attachment[ id=" + id + " ]";
    }

}