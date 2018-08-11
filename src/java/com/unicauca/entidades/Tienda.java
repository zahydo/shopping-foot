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
@Table(name = "TBL_TIENDA", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT tda FROM Tienda tda")
    , @NamedQuery(name = "Tienda.findByIdTienda", query = "SELECT tda FROM Tienda tda WHERE tda.idTienda = :idTienda")
    , @NamedQuery(name = "Tienda.findByNombre", query = "SELECT tda FROM Tienda tda WHERE tda.nombre = :nombre")
    , @NamedQuery(name = "Tienda.findByDescripcion", query = "SELECT tda FROM Tienda tda WHERE tda.descripcion = :descripcion")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_TIENDA", nullable = true, precision = 38, scale = 0)
    private BigDecimal idTienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTienda", fetch = FetchType.LAZY)
    private List<ProductoTienda> tblProductoTiendaList;

    public Tienda() {
    }

    public Tienda(BigDecimal idTienda) {
        this.idTienda = idTienda;
    }

    public Tienda(BigDecimal idTienda, String nombre) {
        this.idTienda = idTienda;
        this.nombre = nombre;
    }

    public BigDecimal getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(BigDecimal idTienda) {
        this.idTienda = idTienda;
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
    public List<ProductoTienda> getTblProductoTiendaList() {
        return tblProductoTiendaList;
    }

    public void setTblProductoTiendaList(List<ProductoTienda> tblProductoTiendaList) {
        this.tblProductoTiendaList = tblProductoTiendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTienda != null ? idTienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.idTienda == null && other.idTienda != null) || (this.idTienda != null && !this.idTienda.equals(other.idTienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
