package co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity;

import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.EstudiosPK;
import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Persona;
import co.edu.javeriana.as.payara.jakarta.personapp.ejb.entity.Profesion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-10-31T00:36:09")
@StaticMetamodel(Estudios.class)
public class Estudios_ { 

    public static volatile SingularAttribute<Estudios, Date> fecha;
    public static volatile SingularAttribute<Estudios, Persona> persona;
    public static volatile SingularAttribute<Estudios, String> univer;
    public static volatile SingularAttribute<Estudios, EstudiosPK> estudiosPK;
    public static volatile SingularAttribute<Estudios, Profesion> profesion;

}