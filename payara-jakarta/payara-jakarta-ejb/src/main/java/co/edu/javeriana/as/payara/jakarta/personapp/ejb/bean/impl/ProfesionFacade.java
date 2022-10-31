/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.javeriana.as.payara.jakarta.personapp.ejb.bean.impl;

import co.edu.javeriana.as.payara.jakarta.personapp.ejb.bean.AbstractFacade;
import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Profesion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author carlosloreto
 */
@Stateless
public class ProfesionFacade extends AbstractFacade<Profesion> {

    @PersistenceContext(unitName = "personapp_pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesionFacade() {
        super(Profesion.class);
    }
    
}
