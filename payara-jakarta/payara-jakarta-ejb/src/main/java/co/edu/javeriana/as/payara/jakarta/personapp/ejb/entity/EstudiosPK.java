/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author carlosloreto
 */
@Embeddable
public class EstudiosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prof")
    private int idProf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cc_per")
    private int ccPer;

    public EstudiosPK() {
    }

    public EstudiosPK(int idProf, int ccPer) {
        this.idProf = idProf;
        this.ccPer = ccPer;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public int getCcPer() {
        return ccPer;
    }

    public void setCcPer(int ccPer) {
        this.ccPer = ccPer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProf;
        hash += (int) ccPer;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosPK)) {
            return false;
        }
        EstudiosPK other = (EstudiosPK) object;
        if (this.idProf != other.idProf) {
            return false;
        }
        if (this.ccPer != other.ccPer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK[ idProf=" + idProf + ", ccPer=" + ccPer + " ]";
    }
    
}
