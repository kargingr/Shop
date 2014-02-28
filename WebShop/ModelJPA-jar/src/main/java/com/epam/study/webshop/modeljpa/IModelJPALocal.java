package com.epam.study.webshop.modeljpa;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 * Created by Gennadiy_Kargin on 20.02.14.
 */
@Local
public interface IModelJPALocal
{
    String getName();
    EntityManager getEntityManager();


}
