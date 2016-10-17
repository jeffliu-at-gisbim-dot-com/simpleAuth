/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.persist;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jeff
 */
@Entity
@Table(name = "service", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id"),
    @NamedQuery(name = "Service.findByServiceName", query = "SELECT s FROM Service s WHERE s.serviceName = :serviceName"),
    @NamedQuery(name = "Service.findByServiceUrl", query = "SELECT s FROM Service s WHERE s.serviceUrl = :serviceUrl"),
    @NamedQuery(name = "Service.findByParameterDescription", query = "SELECT s FROM Service s WHERE s.parameterDescription = :parameterDescription"),
    @NamedQuery(name = "Service.findByServiceKey", query = "SELECT s FROM Service s WHERE s.serviceKey = :serviceKey"),
    @NamedQuery(name = "Service.findByServiceKeyPassword", query = "SELECT s FROM Service s WHERE s.serviceKeyPassword = :serviceKeyPassword"),
    @NamedQuery(name = "Service.findByConnectFrequency", query = "SELECT s FROM Service s WHERE s.connectFrequency = :connectFrequency"),
    @NamedQuery(name = "Service.findByOperateBy", query = "SELECT s FROM Service s WHERE s.operateBy = :operateBy"),
    @NamedQuery(name = "Service.findByOperateAt", query = "SELECT s FROM Service s WHERE s.operateAt = :operateAt")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 60)
    @Column(name = "service_name", length = 60)
    private String serviceName;
    @Size(max = 255)
    @Column(name = "service_url", length = 255)
    private String serviceUrl;
    @Size(max = 255)
    @Column(name = "parameter_description", length = 255)
    private String parameterDescription;
    @Size(max = 255)
    @Column(name = "service_key", length = 255)
    private String serviceKey;
    @Size(max = 20)
    @Column(name = "service_key_password", length = 20)
    private String serviceKeyPassword;
    @Size(max = 2)
    @Column(name = "connect_frequency", length = 2)
    private String connectFrequency;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @ManyToOne
    private Org providerId;
    @OneToMany(mappedBy = "serviceId")
    private Collection<Fun> funCollection;

    public Service() {
    }

    public Service(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getParameterDescription() {
        return parameterDescription;
    }

    public void setParameterDescription(String parameterDescription) {
        this.parameterDescription = parameterDescription;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getServiceKeyPassword() {
        return serviceKeyPassword;
    }

    public void setServiceKeyPassword(String serviceKeyPassword) {
        this.serviceKeyPassword = serviceKeyPassword;
    }

    public String getConnectFrequency() {
        return connectFrequency;
    }

    public void setConnectFrequency(String connectFrequency) {
        this.connectFrequency = connectFrequency;
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy;
    }

    public Date getOperateAt() {
        return operateAt;
    }

    public void setOperateAt(Date operateAt) {
        this.operateAt = operateAt;
    }

    public Org getProviderId() {
        return providerId;
    }

    public void setProviderId(Org providerId) {
        this.providerId = providerId;
    }

    @XmlTransient
    public Collection<Fun> getFunCollection() {
        return funCollection;
    }

    public void setFunCollection(Collection<Fun> funCollection) {
        this.funCollection = funCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Service[ id=" + id + " ]";
    }
    
}
