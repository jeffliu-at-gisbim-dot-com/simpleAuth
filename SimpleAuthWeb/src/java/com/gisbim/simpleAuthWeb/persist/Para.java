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
@Table(name = "para", catalog = "BuildingWorld", schema = "pri")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Para.findAll", query = "SELECT p FROM Para p"),
    @NamedQuery(name = "Para.findById", query = "SELECT p FROM Para p WHERE p.id = :id"),
    @NamedQuery(name = "Para.findByParaCatalogue", query = "SELECT p FROM Para p WHERE p.paraCatalogue = :paraCatalogue"),
    @NamedQuery(name = "Para.findByParaI18n", query = "SELECT p FROM Para p WHERE p.paraI18n = :paraI18n"),
    @NamedQuery(name = "Para.findByParaName", query = "SELECT p FROM Para p WHERE p.paraName = :paraName"),
    @NamedQuery(name = "Para.findByParaValue", query = "SELECT p FROM Para p WHERE p.paraValue = :paraValue"),
    @NamedQuery(name = "Para.findByOperateBy", query = "SELECT p FROM Para p WHERE p.operateBy = :operateBy"),
    @NamedQuery(name = "Para.findByOperateAt", query = "SELECT p FROM Para p WHERE p.operateAt = :operateAt")})
public class Para implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 60)
    @Column(name = "para_catalogue", length = 60)
    private String paraCatalogue;
    @Size(max = 120)
    @Column(name = "para_i18n", length = 120)
    private String paraI18n;
    @Size(max = 60)
    @Column(name = "para_name", length = 60)
    private String paraName;
    @Size(max = 60)
    @Column(name = "para_value", length = 60)
    private String paraValue;
    @Size(max = 30)
    @Column(name = "operate_by", length = 30)
    private String operateBy;
    @Column(name = "operate_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operateAt;

    public Para() {
    }

    public Para(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParaCatalogue() {
        return paraCatalogue;
    }

    public void setParaCatalogue(String paraCatalogue) {
        this.paraCatalogue = paraCatalogue;
    }

    public String getParaI18n() {
        return paraI18n;
    }

    public void setParaI18n(String paraI18n) {
        this.paraI18n = paraI18n;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Para)) {
            return false;
        }
        Para other = (Para) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gisbim.simpleAuthWeb.persist.Para[ id=" + id + " ]";
    }
    
}
