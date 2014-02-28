package com.epam.study.webshop;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
//import javax.persistence.*;
import com.epam.study.webshop.modeljpa.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Gennadiy_Kargin on 19.02.14.
 */
@WebServlet("/ts")
public class TS extends HttpServlet
{
    /*@PersistenceContext(unitName = "WebShopOracleDB")
    private EntityManager em;*/

    @EJB
    private IModelJPALocal  jpaStore;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().write("Servlet TS" + System.lineSeparator());
        EntityManager em = jpaStore.getEntityManager();
        response.getWriter().write(em.toString());
        response.getWriter().write(System.lineSeparator());
        response.getWriter().write(em.toString());
    }
}
