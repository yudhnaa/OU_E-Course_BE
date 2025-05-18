/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "exercise_attachment")
@NamedQueries({
    @NamedQuery(name = "ExerciseAttachment.findAll", query = "SELECT e FROM ExerciseAttachment e"),
    @NamedQuery(name = "ExerciseAttachment.findById", query = "SELECT e FROM ExerciseAttachment e WHERE e.id = :id")})
public class ExerciseAttachment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Attachment attachmentId;
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exercise exerciseId;

    public ExerciseAttachment() {
    }

    public ExerciseAttachment(Integer id) {
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

    public Exercise getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Exercise exerciseId) {
        this.exerciseId = exerciseId;
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
        if (!(object instanceof ExerciseAttachment)) {
            return false;
        }
        ExerciseAttachment other = (ExerciseAttachment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ExerciseAttachment[ id=" + id + " ]";
    }
    
}
