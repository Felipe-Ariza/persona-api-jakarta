/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.javeriana.as.payara.jakarta.personapp.ejb.bean;

import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Telefono;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author carlosloreto
 */
@Local
public interface TelefonoFacadeLocal {

    void create(Telefono telefono);

    void edit(Telefono telefono);

    void remove(Telefono telefono);

    Telefono find(Object id);

    List<Telefono> findAll();

    List<Telefono> findRange(int[] range);

    int count();
    
}
