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
 * @author yudhna
 */
@Entity
@Table(name = "exercise_score_status")
@NamedQueries({
    @NamedQuery(name = "ExerciseScoreStatus.findAll", query = "SELECT e FROM ExerciseScoreStatus e"),
    @NamedQuery(name = "ExerciseScoreStatus.findById", query = "SELECT e FROM ExerciseScoreStatus e WHERE e.id = :id"),
    @NamedQuery(name = "ExerciseScoreStatus.findByName", query = "SELECT e FROM ExerciseScoreStatus e WHERE e.name = :name")})
public class ExerciseScoreStatus implements Serializable {

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
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<ExerciseAttempt> exerciseAttemptSet;

    public ExerciseScoreStatus() {
    }

    public ExerciseScoreStatus(Integer id) {
        this.id = id;
    }

    public ExerciseScoreStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ExerciseAttempt> getExerciseAttemptSet() {
        return exerciseAttemptSet;
    }

    public void setExerciseAttemptSet(Set<ExerciseAttempt> exerciseAttemptSet) {
        this.exerciseAttemptSet = exerciseAttemptSet;
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
        if (!(object instanceof ExerciseScoreStatus)) {
            return false;
        }
        ExerciseScoreStatus other = (ExerciseScoreStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ExerciseScoreStatus[ id=" + id + " ]";
    }

}
