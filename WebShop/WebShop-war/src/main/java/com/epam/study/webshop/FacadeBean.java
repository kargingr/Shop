package com.epam.study.webshop;

import com.epam.study.webshop.modeljpa.IModelJPALocal;

import javax.enterprise.context.*;
import javax.inject.*;
import java.io.Serializable;

@Named("facade")
@SessionScoped
public class FacadeBean implements Serializable
{
    @Inject
    private IModelJPALocal model;
    private String userName = "user name";

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getModelName()
    {
        return model.getName();
    }
}
