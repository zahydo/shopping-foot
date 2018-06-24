/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_PRODUCTO_TIENDA", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProductoTienda.findAll", query = "SELECT t FROM TblProductoTienda t")
    , @NamedQuery(name = "TblProductoTienda.findByIdProductoTienda", query = "SELECT t FROM TblProductoTienda t WHERE t.idProductoTienda = :idProductoTienda")})
public class TblProductoTienda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUCTO_TIENDA", nullable = false, precision = 38, scale = 0)
    private BigDecimal idProductoTienda;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblProducto idProducto;
    @JoinColumn(name = "ID_TIENDA", referencedColumnName = "ID_TIENDA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblTienda idTienda;

    public TblProductoTienda() {
    }

    public TblProductoTienda(BigDecimal idProductoTienda) {
        this.idProductoTienda = idProductoTienda;
    }

    public BigDecimal getIdProductoTienda() {
        return idProductoTienda;
    }

    public void setIdProductoTienda(BigDecimal idProductoTienda) {
        this.idProductoTienda = idProductoTienda;
    }

    public TblProducto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(TblProducto idProducto) {
        this.idProducto = idProducto;
    }

    public TblTienda getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(TblTienda idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoTienda != null ? idProductoTienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductoTienda)) {
            return false;
        }
        TblProductoTienda other = (TblProductoTienda) object;
        if ((this.idProductoTienda == null && other.idProductoTienda != null) || (this.idProductoTienda != null && !this.idProductoTienda.equals(other.idProductoTienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idTienda + " - " + idProducto.getNombre();
    }
    
}
