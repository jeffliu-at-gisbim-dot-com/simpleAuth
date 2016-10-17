/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.persist;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jeff
 */
@Entity
@Table(name = "duty", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Duty.findAll", query = "SELECT d FROM Duty d"),
    @NamedQuery(name = "Duty.findById", query = "SELECT d FROM Duty d WHERE d.id = :id"),
    @NamedQuery(name = "Duty.findByValidAfter", query = "SELECT d FROM Duty d WHERE d.validAfter = :validAfter"),
    @NamedQuery(name = "Duty.findByValidBefore", query = "SELECT d FROM Duty d WHERE d.validBefore = :validBefore"),
    @NamedQuery(name = "Duty.findByOperateBy", query = "SELECT d FROM Duty d WHERE d.operateBy = :operateBy"),
    @NamedQuery(name = "Duty.findByOperateAt", query = "SELECT d FROM Duty d WHERE d.operateAt = :operateAt")})
public class Duty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "valid_after")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validAfter;
    @Column(name = "valid_before")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validBefore;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @JoinColumn(name = "data_org_id", referencedColumnName = "id")
    @ManyToOne
    private Org dataOrgId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private PriUser userId;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    private Role roleId;

    public Duty() {
    }

    public Duty(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getValidAfter() {
        return validAfter;
    }

    public void setValidAfter(Date validAfter) {
        this.validAfter = validAfter;
    }

    public Date getValidBefore() {
        return validBefore;
    }

    public void setValidBefore(Date validBefore) {
        this.validBefore = validBefore;
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

    public Org getDataOrgId() {
        return dataOrgId;
    }

    public void setDataOrgId(Org dataOrgId) {
        this.dataOrgId = dataOrgId;
    }

    public PriUser getUserId() {
        return userId;
    }

    public void setUserId(PriUser userId) {
        this.userId = userId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
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
        if (!(object instanceof Duty)) {
            return false;
        }
        Duty other = (Duty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Duty[ id=" + id + " ]";
    }
    
}
