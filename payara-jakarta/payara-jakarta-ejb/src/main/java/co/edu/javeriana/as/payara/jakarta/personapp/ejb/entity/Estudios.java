/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author carlosloreto
 */
@Entity
@Table(name = "estudios")
@NamedQueries({
    @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e"),
    @NamedQuery(name = "Estudios.findByIdProf", query = "SELECT e FROM Estudios e WHERE e.estudiosPK.idProf = :idProf"),
    @NamedQuery(name = "Estudios.findByCcPer", query = "SELECT e FROM Estudios e WHERE e.estudiosPK.ccPer = :ccPer"),
    @NamedQuery(name = "Estudios.findByFecha", query = "SELECT e FROM Estudios e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Estudios.findByUniver", query = "SELECT e FROM Estudios e WHERE e.univer = :univer")})
public class Estudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudiosPK estudiosPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "univer")
    private String univer;
    @JoinColumn(name = "cc_per", referencedColumnName = "cc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "id_prof", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profesion profesion;

    public Estudios() {
    }

    public Estudios(EstudiosPK estudiosPK) {
        this.estudiosPK = estudiosPK;
    }

    public Estudios(int idProf, int ccPer) {
        this.estudiosPK = new EstudiosPK(idProf, ccPer);
    }

    public EstudiosPK getEstudiosPK() {
        return estudiosPK;
    }

    public void setEstudiosPK(EstudiosPK estudiosPK) {
        this.estudiosPK = estudiosPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUniver() {
        return univer;
    }

    public void setUniver(String univer) {
        this.univer = univer;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiosPK != null ? estudiosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudios)) {
            return false;
        }
        Estudios other = (Estudios) object;
        if ((this.estudiosPK == null && other.estudiosPK != null) || (this.estudiosPK != null && !this.estudiosPK.equals(other.estudiosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Estudios[ estudiosPK=" + estudiosPK + " ]";
    }
    
}
