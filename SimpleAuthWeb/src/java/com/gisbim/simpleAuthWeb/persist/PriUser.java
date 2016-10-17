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
@Table(name = "pri_user", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PriUser.findAll", query = "SELECT p FROM PriUser p"),
    @NamedQuery(name = "PriUser.findById", query = "SELECT p FROM PriUser p WHERE p.id = :id"),
    @NamedQuery(name = "PriUser.findByAccount", query = "SELECT p FROM PriUser p WHERE p.account = :account"),
    @NamedQuery(name = "PriUser.findByUserName", query = "SELECT p FROM PriUser p WHERE p.userName = :userName"),
    @NamedQuery(name = "PriUser.findByEncryptPasswd", query = "SELECT p FROM PriUser p WHERE p.encryptPasswd = :encryptPasswd"),
    @NamedQuery(name = "PriUser.findByEncryptType", query = "SELECT p FROM PriUser p WHERE p.encryptType = :encryptType"),
    @NamedQuery(name = "PriUser.findByOperateBy", query = "SELECT p FROM PriUser p WHERE p.operateBy = :operateBy"),
    @NamedQuery(name = "PriUser.findByOperateAt", query = "SELECT p FROM PriUser p WHERE p.operateAt = :operateAt")})
public class PriUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 30)
    @Column(name = "account", length = 30)
    private String account;
    @Size(max = 30)
    @Column(name = "user_name", length = 30)
    private String userName;
    @Size(max = 65)
    @Column(name = "encrypt_passwd", length = 65)
    private String encryptPasswd;
    @Column(name = "encrypt_type")
    private Character encryptType;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @OneToMany(mappedBy = "userId")
    private Collection<Duty> dutyCollection;
    @JoinColumn(name = "data_org_id", referencedColumnName = "id")
    @ManyToOne
    private Org dataOrgId;

    public PriUser() {
    }

    public PriUser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptPasswd() {
        return encryptPasswd;
    }

    public void setEncryptPasswd(String encryptPasswd) {
        this.encryptPasswd = encryptPasswd;
    }

    public Character getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(Character encryptType) {
        this.encryptType = encryptType;
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
    public Collection<Duty> getDutyCollection() {
        return dutyCollection;
    }

    public void setDutyCollection(Collection<Duty> dutyCollection) {
        this.dutyCollection = dutyCollection;
    }

    public Org getDataOrgId() {
        return dataOrgId;
    }

    public void setDataOrgId(Org dataOrgId) {
        this.dataOrgId = dataOrgId;
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
        if (!(object instanceof PriUser)) {
            return false;
        }
        PriUser other = (PriUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.PriUser[ id=" + id + " ]";
    }
    
}
