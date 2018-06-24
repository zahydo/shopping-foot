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
@Table(name = "TBL_ESTADO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEstado.findAll", query = "SELECT t FROM TblEstado t")
    , @NamedQuery(name = "TblEstado.findByIdEstado", query = "SELECT t FROM TblEstado t WHERE t.idEstado = :idEstado")
    , @NamedQuery(name = "TblEstado.findByNombre", query = "SELECT t FROM TblEstado t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TblEstado.findByDescripcion", query = "SELECT t FROM TblEstado t WHERE t.descripcion = :descripcion")})
public class TblEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<TblPedido> tblPedidoList;

    public TblEstado() {
    }

    public TblEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public TblEstado(BigDecimal idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    public BigDecimal getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
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
    public List<TblPedido> getTblPedidoList() {
        return tblPedidoList;
    }

    public void setTblPedidoList(List<TblPedido> tblPedidoList) {
        this.tblPedidoList = tblPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEstado)) {
            return false;
        }
        TblEstado other = (TblEstado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
