/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_CATEGORIA", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategoria.findAll", query = "SELECT t FROM TblCategoria t")
    , @NamedQuery(name = "TblCategoria.findByIdCategoria", query = "SELECT t FROM TblCategoria t WHERE t.idCategoria = :idCategoria")
    , @NamedQuery(name = "TblCategoria.findByNombre", query = "SELECT t FROM TblCategoria t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TblCategoria.findByDescripcion", query = "SELECT t FROM TblCategoria t WHERE t.descripcion = :descripcion")})
public class TblCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_CATEGORIA", nullable = true, precision = 38, scale = 0)
    private BigDecimal idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private List<TblProducto> tblProductoList;

    public TblCategoria() {
    }

    public TblCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
    }

    public TblCategoria(BigDecimal idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public BigDecimal getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<TblProducto> getTblProductoList() {
        return tblProductoList;
    }

    public void setTblProductoList(List<TblProducto> tblProductoList) {
        this.tblProductoList = tblProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCategoria)) {
            return false;
        }
        TblCategoria other = (TblCategoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
