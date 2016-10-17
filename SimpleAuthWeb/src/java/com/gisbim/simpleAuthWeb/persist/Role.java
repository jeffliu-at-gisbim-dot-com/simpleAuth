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
@Table(name = "role", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByRoleType", query = "SELECT r FROM Role r WHERE r.roleType = :roleType"),
    @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName"),
    @NamedQuery(name = "Role.findByRoleDescription", query = "SELECT r FROM Role r WHERE r.roleDescription = :roleDescription"),
    @NamedQuery(name = "Role.findByEditable", query = "SELECT r FROM Role r WHERE r.editable = :editable"),
    @NamedQuery(name = "Role.findByOperateBy", query = "SELECT r FROM Role r WHERE r.operateBy = :operateBy"),
    @NamedQuery(name = "Role.findByOperateAt", query = "SELECT r FROM Role r WHERE r.operateAt = :operateAt")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 1)
    @Column(name = "role_type", length = 1)
    private String roleType;
    @Size(max = 20)
    @Column(name = "role_name", length = 20)
    private String roleName;
    @Size(max = 60)
    @Column(name = "role_description", length = 60)
    private String roleDescription;
    @Size(max = 1)
    @Column(name = "editable", length = 1)
    private String editable;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @OneToMany(mappedBy = "roleId")
    private Collection<DataAuth> dataAuthCollection;
    @OneToMany(mappedBy = "roleId")
    private Collection<FunAuth> funAuthCollection;
    @OneToMany(mappedBy = "roleId")
    private Collection<Duty> dutyCollection;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
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
    public Collection<FunAuth> getFunAuthCollection() {
        return funAuthCollection;
    }

    public void setFunAuthCollection(Collection<FunAuth> funAuthCollection) {
        this.funAuthCollection = funAuthCollection;
    }

    @XmlTransient
    public Collection<Duty> getDutyCollection() {
        return dutyCollection;
    }

    public void setDutyCollection(Collection<Duty> dutyCollection) {
        this.dutyCollection = dutyCollection;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Role[ id=" + id + " ]";
    }
    
}
