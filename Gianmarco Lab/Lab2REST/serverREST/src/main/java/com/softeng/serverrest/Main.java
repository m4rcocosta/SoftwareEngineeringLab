package com.softeng.serverrest;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Main {
    public static void main(String[] args) {
        
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(Manager.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new Manager()));
        factoryBean.setAddress("http://localhost:8080");
        
        Server server = factoryBean.create();
        System.out.println("Server run...");
    }
}
