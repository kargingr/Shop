package com.epam.study.webshop.modeljpa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Gennadiy_Kargin on 20.02.14.
 */
@Stateless
public class ModelJPA implements IModelJPALocal
{
   // @PersistenceContext(unitName = "WebShopOracleDB")
    private EntityManager em;

    @Override
    public String getName()
    {
        return "ModelJPA";
    }

    @Override
    public EntityManager getEntityManager()
    {
        return em;
    }


}
