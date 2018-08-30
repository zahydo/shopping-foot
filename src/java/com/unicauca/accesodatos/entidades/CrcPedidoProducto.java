/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.accesodatos.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "CRC_PEDIDO_PRODUCTO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrcPedidoProducto.findAll", query = "SELECT c FROM CrcPedidoProducto c")
    , @NamedQuery(name = "CrcPedidoProducto.findByIdPedidoProducto", query = "SELECT c FROM CrcPedidoProducto c WHERE c.idPedidoProducto = :idPedidoProducto")
    , @NamedQuery(name = "CrcPedidoProducto.findByTotalPedido", query = "SELECT c FROM CrcPedidoProducto c WHERE c.totalPedido = :totalPedido")})
public class CrcPedidoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEDIDO_PRODUCTO")
    private BigDecimal idPedidoProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_PEDIDO")
    private BigInteger totalPedido;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO")
    @ManyToOne(optional = false)
    private Pedido idPedido;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private Producto idProducto;

    public CrcPedidoProducto() {
    }

    public CrcPedidoProducto(BigDecimal idPedidoProducto) {
        this.idPedidoProducto = idPedidoProducto;
    }

    public CrcPedidoProducto(BigDecimal idPedidoProducto, BigInteger totalPedido) {
        this.idPedidoProducto = idPedidoProducto;
        this.totalPedido = totalPedido;
    }

    public BigDecimal getIdPedidoProducto() {
        return idPedidoProducto;
    }

    public void setIdPedidoProducto(BigDecimal idPedidoProducto) {
        this.idPedidoProducto = idPedidoProducto;
    }

    public BigInteger getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigInteger totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoProducto != null ? idPedidoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrcPedidoProducto)) {
            return false;
        }
        CrcPedidoProducto other = (CrcPedidoProducto) object;
        if ((this.idPedidoProducto == null && other.idPedidoProducto != null) || (this.idPedidoProducto != null && !this.idPedidoProducto.equals(other.idPedidoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.entidades.CrcPedidoProducto[ idPedidoProducto=" + idPedidoProducto + " ]";
    }
    
}
