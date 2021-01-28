//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2021.01.28 à 12:04:02 AM WET 
//


package com.app.cmi.soap.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour accountInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="accountInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="credit" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="strCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="accountType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountInfo", propOrder = {
    "id",
    "accountNumber",
    "amount",
    "credit",
    "strCreationDate",
    "accountType"
})
public class AccountInfo {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String accountNumber;
    protected double amount;
    protected double credit;
    @XmlElement(required = true)
    protected String strCreationDate;
    @XmlElement(required = true)
    protected String accountType;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété accountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Définit la valeur de la propriété accountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété amount.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Définit la valeur de la propriété amount.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Obtient la valeur de la propriété credit.
     * 
     */
    public double getCredit() {
        return credit;
    }

    /**
     * Définit la valeur de la propriété credit.
     * 
     */
    public void setCredit(double value) {
        this.credit = value;
    }

    /**
     * Obtient la valeur de la propriété strCreationDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCreationDate() {
        return strCreationDate;
    }

    /**
     * Définit la valeur de la propriété strCreationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCreationDate(String value) {
        this.strCreationDate = value;
    }

    /**
     * Obtient la valeur de la propriété accountType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Définit la valeur de la propriété accountType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

}
