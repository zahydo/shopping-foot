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
@Table(name = "TBL_PRODUCTO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT prod FROM Producto prod")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT prod FROM Producto prod WHERE prod.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT prod FROM Producto prod WHERE prod.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT prod FROM Producto prod WHERE prod.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByValor", query = "SELECT prod FROM Producto prod WHERE prod.valor = :valor")
    , @NamedQuery(name = "Producto.findByDisponibles", query = "SELECT prod FROM Producto prod WHERE prod.disponibles = :disponibles")})
public class Producto implements Serializable {

    @Basic(optional = false)
    @NotNull
    private Long valor;
    @Basic(optional = false)
    @NotNull
    private Long disponibles;
    @OneToMany(mappedBy = "idProducto")
    private List<CrcPedidoProducto> crcPedidoProductoList;

    public static final String findAll="Producto.findAll";
    public static final String findByIdProducto="Producto.findByIdProducto";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_PRODUCTO", nullable = true, precision = 38, scale = 0)
    private Long idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION", nullable = false, length = 50)
    private String descripcion;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoria idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<ProductoTienda> tblProductoTiendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<IngredienteProducto> tblIngredienteProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Pedido> tblPedidoList;

    public Producto() {
    }

    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Long idProducto, String nombre, String descripcion, Long valor, Long disponibles) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.disponibles = disponibles;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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


    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public List<ProductoTienda> getProductoTiendaList() {
        return tblProductoTiendaList;
    }

    public void setProductoTiendaList(List<ProductoTienda> tblProductoTiendaList) {
        this.tblProductoTiendaList = tblProductoTiendaList;
    }

    @XmlTransient
    public List<IngredienteProducto> getTblIngredienteProductoList() {
        return tblIngredienteProductoList;
    }

    public void setTblIngredienteProductoList(List<IngredienteProducto> tblIngredienteProductoList) {
        this.tblIngredienteProductoList = tblIngredienteProductoList;
    }

    @XmlTransient
    public List<Pedido> getTblPedidoList() {
        return tblPedidoList;
    }

    public void setTblPedidoList(List<Pedido> tblPedidoList) {
        this.tblPedidoList = tblPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(Long disponibles) {
        this.disponibles = disponibles;
    }

    @XmlTransient
    public List<CrcPedidoProducto> getCrcPedidoProductoList() {
        return crcPedidoProductoList;
    }

    public void setCrcPedidoProductoList(List<CrcPedidoProducto> crcPedidoProductoList) {
        this.crcPedidoProductoList = crcPedidoProductoList;
    }
    
}
