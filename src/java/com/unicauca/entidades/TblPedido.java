/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_PEDIDO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPedido.findAll", query = "SELECT t FROM TblPedido t")
    , @NamedQuery(name = "TblPedido.findByIdPedido", query = "SELECT t FROM TblPedido t WHERE t.idPedido = :idPedido")
    , @NamedQuery(name = "TblPedido.findByCantidad", query = "SELECT t FROM TblPedido t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "TblPedido.findByValorTotal", query = "SELECT t FROM TblPedido t WHERE t.valorTotal = :valorTotal")
    , @NamedQuery(name = "TblPedido.findByDomicilio", query = "SELECT t FROM TblPedido t WHERE t.domicilio = :domicilio")})
public class TblPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEDIDO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD", nullable = false)
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigInteger valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOMICILIO", nullable = false)
    private BigInteger domicilio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido", fetch = FetchType.LAZY)
    private List<TblPedidoUsuario> tblPedidoUsuarioList;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblCliente idCliente;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblEstado idEstado;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblProducto idProducto;

    public TblPedido() {
    }

    public TblPedido(BigDecimal idPedido) {
        this.idPedido = idPedido;
    }

    public TblPedido(BigDecimal idPedido, BigInteger cantidad, BigInteger valorTotal, BigInteger domicilio) {
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.domicilio = domicilio;
    }

    public BigDecimal getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(BigDecimal idPedido) {
        this.idPedido = idPedido;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public BigInteger getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigInteger valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigInteger getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(BigInteger domicilio) {
        this.domicilio = domicilio;
    }

    @XmlTransient
    public List<TblPedidoUsuario> getTblPedidoUsuarioList() {
        return tblPedidoUsuarioList;
    }

    public void setTblPedidoUsuarioList(List<TblPedidoUsuario> tblPedidoUsuarioList) {
        this.tblPedidoUsuarioList = tblPedidoUsuarioList;
    }

    public TblCliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(TblCliente idCliente) {
        this.idCliente = idCliente;
    }

    public TblEstado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(TblEstado idEstado) {
        this.idEstado = idEstado;
    }

    public TblProducto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(TblProducto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPedido)) {
            return false;
        }
        TblPedido other = (TblPedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPedido + " - "+idCliente.getNombre();
    }
    
}
