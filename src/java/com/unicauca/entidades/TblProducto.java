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
    @NamedQuery(name = "TblProducto.findAll", query = "SELECT t FROM TblProducto t")
    , @NamedQuery(name = "TblProducto.findByIdProducto", query = "SELECT t FROM TblProducto t WHERE t.idProducto = :idProducto")
    , @NamedQuery(name = "TblProducto.findByNombre", query = "SELECT t FROM TblProducto t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TblProducto.findByDescripcion", query = "SELECT t FROM TblProducto t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "TblProducto.findByValor", query = "SELECT t FROM TblProducto t WHERE t.valor = :valor")
    , @NamedQuery(name = "TblProducto.findByDisponibles", query = "SELECT t FROM TblProducto t WHERE t.disponibles = :disponibles")})
public class TblProducto implements Serializable {

    public static final String findAll="TblProducto.findAll";
    public static final String findByIdProducto="TblProducto.findByIdProducto";
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_PRODUCTO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idProducto;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR", nullable = false)
    private BigInteger valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DISPONIBLES", nullable = false)
    private BigInteger disponibles;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblCategoria idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<TblProductoTienda> tblProductoTiendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<TblIngredienteProducto> tblIngredienteProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<TblPedido> tblPedidoList;

    public TblProducto() {
    }

    public TblProducto(BigDecimal idProducto) {
        this.idProducto = idProducto;
    }

    public TblProducto(BigDecimal idProducto, String nombre, String descripcion, BigInteger valor, BigInteger disponibles) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.disponibles = disponibles;
    }

    public BigDecimal getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(BigDecimal idProducto) {
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

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public BigInteger getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(BigInteger disponibles) {
        this.disponibles = disponibles;
    }

    public TblCategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(TblCategoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public List<TblProductoTienda> getTblProductoTiendaList() {
        return tblProductoTiendaList;
    }

    public void setTblProductoTiendaList(List<TblProductoTienda> tblProductoTiendaList) {
        this.tblProductoTiendaList = tblProductoTiendaList;
    }

    @XmlTransient
    public List<TblIngredienteProducto> getTblIngredienteProductoList() {
        return tblIngredienteProductoList;
    }

    public void setTblIngredienteProductoList(List<TblIngredienteProducto> tblIngredienteProductoList) {
        this.tblIngredienteProductoList = tblIngredienteProductoList;
    }

    @XmlTransient
    public List<TblPedido> getTblPedidoList() {
        return tblPedidoList;
    }

    public void setTblPedidoList(List<TblPedido> tblPedidoList) {
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
        if (!(object instanceof TblProducto)) {
            return false;
        }
        TblProducto other = (TblProducto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
