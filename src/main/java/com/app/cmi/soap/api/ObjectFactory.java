//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2021.01.28 à 12:04:02 AM WET 
//


package com.app.cmi.soap.api;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.app.cmi.soap.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.app.cmi.soap.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClientCreationRequest }
     * 
     */
    public ClientCreationRequest createClientCreationRequest() {
        return new ClientCreationRequest();
    }

    /**
     * Create an instance of {@link ClientInfo }
     * 
     */
    public ClientInfo createClientInfo() {
        return new ClientInfo();
    }

    /**
     * Create an instance of {@link ClientCreationResponse }
     * 
     */
    public ClientCreationResponse createClientCreationResponse() {
        return new ClientCreationResponse();
    }

    /**
     * Create an instance of {@link AccountInfo }
     * 
     */
    public AccountInfo createAccountInfo() {
        return new AccountInfo();
    }

    /**
     * Create an instance of {@link AgencyInfo }
     * 
     */
    public AgencyInfo createAgencyInfo() {
        return new AgencyInfo();
    }

    /**
     * Create an instance of {@link AgentInfo }
     * 
     */
    public AgentInfo createAgentInfo() {
        return new AgentInfo();
    }

}
