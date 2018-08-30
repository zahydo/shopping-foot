/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.accesodatos.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Pedido.findAll", query = "SELECT ped FROM Pedido ped")
    , @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT ped FROM Pedido ped WHERE ped.idPedido = :idPedido")
    , @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT ped FROM Pedido ped WHERE ped.cantidad = :cantidad")
    , @NamedQuery(name = "Pedido.findByValorTotal", query = "SELECT ped FROM Pedido ped WHERE ped.valorTotal = :valorTotal")
    , @NamedQuery(name = "Pedido.findByDomicilio", query = "SELECT ped FROM Pedido ped WHERE ped.domicilio = :domicilio")})
public class Pedido implements Serializable {

    @Basic(optional = false)
    @NotNull
    private Long cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_TOTAL")
    private Long valorTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido")
    private List<CrcPedidoProducto> crcPedidoProductoList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_PEDIDO", nullable = true, precision = 38, scale = 0)
    private Long idPedido;
    @Basic(optional = true)
    @Size(min = 1, max = 100)
    @Column(name = "DOMICILIO", nullable = true, length = 100)
    private String domicilio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido", fetch = FetchType.LAZY)
    private List<PedidoUsuario> tblPedidoUsuarioList;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado idEstado;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto idProducto;

    public Pedido() {
    }

    public Pedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Long idPedido, Long cantidad, Long valorTotal, String domicilio) {
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.domicilio = domicilio;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @XmlTransient
    public List<PedidoUsuario> getPedidoUsuarioList() {
        return tblPedidoUsuarioList;
    }

    public void setPedidoUsuarioList(List<PedidoUsuario> tblPedidoUsuarioList) {
        this.tblPedidoUsuarioList = tblPedidoUsuarioList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
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
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPedido + " - " + idCliente.getNombre();
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @XmlTransient
    public List<CrcPedidoProducto> getCrcPedidoProductoList() {
        return crcPedidoProductoList;
    }

    public void setCrcPedidoProductoList(List<CrcPedidoProducto> crcPedidoProductoList) {
        this.crcPedidoProductoList = crcPedidoProductoList;
    }

}
