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
@Table(name = "fun", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fun.findAll", query = "SELECT f FROM Fun f"),
    @NamedQuery(name = "Fun.findById", query = "SELECT f FROM Fun f WHERE f.id = :id"),
    @NamedQuery(name = "Fun.findByFunCode", query = "SELECT f FROM Fun f WHERE f.funCode = :funCode"),
    @NamedQuery(name = "Fun.findByFunType", query = "SELECT f FROM Fun f WHERE f.funType = :funType"),
    @NamedQuery(name = "Fun.findByLayer", query = "SELECT f FROM Fun f WHERE f.layer = :layer"),
    @NamedQuery(name = "Fun.findByLayerSeq", query = "SELECT f FROM Fun f WHERE f.layerSeq = :layerSeq"),
    @NamedQuery(name = "Fun.findByFunName", query = "SELECT f FROM Fun f WHERE f.funName = :funName"),
    @NamedQuery(name = "Fun.findByFunPath", query = "SELECT f FROM Fun f WHERE f.funPath = :funPath"),
    @NamedQuery(name = "Fun.findByOperations", query = "SELECT f FROM Fun f WHERE f.operations = :operations"),
    @NamedQuery(name = "Fun.findByActiveFlag", query = "SELECT f FROM Fun f WHERE f.activeFlag = :activeFlag"),
    @NamedQuery(name = "Fun.findByBootFlag", query = "SELECT f FROM Fun f WHERE f.bootFlag = :bootFlag"),
    @NamedQuery(name = "Fun.findByOperateBy", query = "SELECT f FROM Fun f WHERE f.operateBy = :operateBy"),
    @NamedQuery(name = "Fun.findByOperateAt", query = "SELECT f FROM Fun f WHERE f.operateAt = :operateAt")})
public class Fun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 15)
    @Column(name = "fun_code", length = 15)
    private String funCode;
    @Size(max = 2)
    @Column(name = "fun_type", length = 2)
    private String funType;
    @Size(max = 2)
    @Column(name = "layer", length = 2)
    private String layer;
    @Size(max = 3)
    @Column(name = "layer_seq", length = 3)
    private String layerSeq;
    @Size(max = 60)
    @Column(name = "fun_name", length = 60)
    private String funName;
    @Size(max = 120)
    @Column(name = "fun_path", length = 120)
    private String funPath;
    @Size(max = 40)
    @Column(name = "operations", length = 40)
    private String operations;
    @Size(max = 1)
    @Column(name = "active_flag", length = 1)
    private String activeFlag;
    @Size(max = 1)
    @Column(name = "boot_flag", length = 1)
    private String bootFlag;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;
    @OneToMany(mappedBy = "funId")
    private Collection<FunAuth> funAuthCollection;
    @OneToMany(mappedBy = "parentFunId")
    private Collection<Fun> funCollection;
    @JoinColumn(name = "parent_fun_id", referencedColumnName = "id")
    @ManyToOne
    private Fun parentFunId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service serviceId;

    public Fun() {
    }

    public Fun(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getLayerSeq() {
        return layerSeq;
    }

    public void setLayerSeq(String layerSeq) {
        this.layerSeq = layerSeq;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunPath() {
        return funPath;
    }

    public void setFunPath(String funPath) {
        this.funPath = funPath;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getBootFlag() {
        return bootFlag;
    }

    public void setBootFlag(String bootFlag) {
        this.bootFlag = bootFlag;
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
    public Collection<FunAuth> getFunAuthCollection() {
        return funAuthCollection;
    }

    public void setFunAuthCollection(Collection<FunAuth> funAuthCollection) {
        this.funAuthCollection = funAuthCollection;
    }

    @XmlTransient
    public Collection<Fun> getFunCollection() {
        return funCollection;
    }

    public void setFunCollection(Collection<Fun> funCollection) {
        this.funCollection = funCollection;
    }

    public Fun getParentFunId() {
        return parentFunId;
    }

    public void setParentFunId(Fun parentFunId) {
        this.parentFunId = parentFunId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof Fun)) {
            return false;
        }
        Fun other = (Fun) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Fun[ id=" + id + " ]";
    }
    
}
