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
@Table(name = "TBL_PERMISO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPermiso.findAll", query = "SELECT t FROM TblPermiso t")
    , @NamedQuery(name = "TblPermiso.findByIdPermiso", query = "SELECT t FROM TblPermiso t WHERE t.idPermiso = :idPermiso")
    , @NamedQuery(name = "TblPermiso.findByNombre", query = "SELECT t FROM TblPermiso t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TblPermiso.findByDescripcion", query = "SELECT t FROM TblPermiso t WHERE t.descripcion = :descripcion")})
public class TblPermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "ID_PERMISO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "idPermiso", fetch = FetchType.LAZY)
    private List<TblPermisoUsuario> tblPermisoUsuarioList;

    public TblPermiso() {
    }

    public TblPermiso(BigDecimal idPermiso) {
        this.idPermiso = idPermiso;
    }

    public TblPermiso(BigDecimal idPermiso, String nombre) {
        this.idPermiso = idPermiso;
        this.nombre = nombre;
    }

    public BigDecimal getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(BigDecimal idPermiso) {
        this.idPermiso = idPermiso;
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
    public List<TblPermisoUsuario> getTblPermisoUsuarioList() {
        return tblPermisoUsuarioList;
    }

    public void setTblPermisoUsuarioList(List<TblPermisoUsuario> tblPermisoUsuarioList) {
        this.tblPermisoUsuarioList = tblPermisoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPermiso)) {
            return false;
        }
        TblPermiso other = (TblPermiso) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
