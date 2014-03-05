package com.epam.study.webshop.security;

import com.epam.webshop.ejb.SecurityRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Yun
 * Date: 3/4/14
 * Time: 3:08 PM
 */
public class EjbAccessor {

    public EjbAccessor() {
    }

    private static String getLookup() {
                 /*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
        String appName = "security-ear-1.0-SNAPSHOT";
//        String appName = "";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
        String moduleName = "securityJar-1.0-SNAPSHOT";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
        String distinctName = "";

        // The EJB bean implementation class name
        String beanName = "SecurityEJB";

        // Fully qualified remote interface name
        final String interfaceName = SecurityRemote.class.getName();

        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
                + "/" + beanName + "!" + interfaceName;

        return name;
    }

    private static Context getContext() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
        Context context = new InitialContext(properties);
        return context;
    }

    public SecurityRemote acquireSecurityRemote() {
        SecurityRemote result = null;
        try {
            Context context = getContext();
            result = (SecurityRemote) context.lookup(getLookup());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
