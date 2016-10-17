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
@Table(name = "org", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Org.findAll", query = "SELECT o FROM Org o"),
    @NamedQuery(name = "Org.findById", query = "SELECT o FROM Org o WHERE o.id = :id"),
    @NamedQuery(name = "Org.findByOrgType", query = "SELECT o FROM Org o WHERE o.orgType = :orgType"),
    @NamedQuery(name = "Org.findByOrgProperty", query = "SELECT o FROM Org o WHERE o.orgProperty = :orgProperty"),
    @NamedQuery(name = "Org.findByUnitId", query = "SELECT o FROM Org o WHERE o.unitId = :unitId"),
    @NamedQuery(name = "Org.findByOrgName", query = "SELECT o FROM Org o WHERE o.orgName = :orgName"),
    @NamedQuery(name = "Org.findByOperateBy", query = "SELECT o FROM Org o WHERE o.operateBy = :operateBy"),
    @NamedQuery(name = "Org.findByOperateAt", query = "SELECT o FROM Org o WHERE o.operateAt = :operateAt")})
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 1)
    @Column(name = "org_type", length = 1)
    private String orgType;
    @Size(max = 1)
    @Column(name = "org_property", length = 1)
    private String orgProperty;
    @Size(max = 2147483647)
    @Column(name = "unit_id", length = 2147483647)
    private String unitId;
    @Size(max = 30)
    @Column(name = "org_name", length = 30)
    private String orgName;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @OneToMany(mappedBy = "orgId")
    private Collection<DataAuth> dataAuthCollection;
    @OneToMany(mappedBy = "parentOrgId")
    private Collection<Org> orgCollection;
    @JoinColumn(name = "parent_org_id", referencedColumnName = "id")
    @ManyToOne
    private Org parentOrgId;
    @OneToMany(mappedBy = "providerId")
    private Collection<Service> serviceCollection;
    @OneToMany(mappedBy = "dataOrgId")
    private Collection<Duty> dutyCollection;
    @OneToMany(mappedBy = "dataOrgId")
    private Collection<PriUser> priUserCollection;

    public Org() {
    }

    public Org(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgProperty() {
        return orgProperty;
    }

    public void setOrgProperty(String orgProperty) {
        this.orgProperty = orgProperty;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    @XmlTransient
    public Collection<DataAuth> getDataAuthCollection() {
        return dataAuthCollection;
    }

    public void setDataAuthCollection(Collection<DataAuth> dataAuthCollection) {
        this.dataAuthCollection = dataAuthCollection;
    }

    @XmlTransient
    public Collection<Org> getOrgCollection() {
        return orgCollection;
    }

    public void setOrgCollection(Collection<Org> orgCollection) {
        this.orgCollection = orgCollection;
    }

    public Org getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Org parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @XmlTransient
    public Collection<Duty> getDutyCollection() {
        return dutyCollection;
    }

    public void setDutyCollection(Collection<Duty> dutyCollection) {
        this.dutyCollection = dutyCollection;
    }

    @XmlTransient
    public Collection<PriUser> getPriUserCollection() {
        return priUserCollection;
    }

    public void setPriUserCollection(Collection<PriUser> priUserCollection) {
        this.priUserCollection = priUserCollection;
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
        if (!(object instanceof Org)) {
            return false;
        }
        Org other = (Org) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Org[ id=" + id + " ]";
    }
    
}
