/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankrest;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.endpoint.Server;

/**
 *
 * @author biar
 */
public class RestServer {
    public static void main(String args[]) throws Exception {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(ClientRepository.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new ClientRepository()));
        factoryBean.setAddress("http://localhost:9001/");
        Server server = factoryBean.create();

        System.out.println("Server ready...");
        Thread.sleep(600 * 10000);
        System.out.println("Server exiting");
        server.destroy();
        System.exit(0);
    }
}
