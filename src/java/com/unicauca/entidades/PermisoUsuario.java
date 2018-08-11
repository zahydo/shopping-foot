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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_PERMISO_USUARIO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoUsuario.findAll", query = "SELECT prUsr FROM PermisoUsuario prUsr")
    , @NamedQuery(name = "PermisoUsuario.findByIdPermisoUsuario", query = "SELECT prUsr FROM PermisoUsuario prUsr WHERE prUsr.idPermisoUsuario = :idPermisoUsuario")})
public class PermisoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_PERMISO_USUARIO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idPermisoUsuario;
    @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID_PERMISO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Permiso idPermiso;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 30)
    private String nombreUsuario;

    public PermisoUsuario() {
    }

    public PermisoUsuario(BigDecimal idPermisoUsuario) {
        this.idPermisoUsuario = idPermisoUsuario;
    }

    public BigDecimal getIdPermisoUsuario() {
        return idPermisoUsuario;
    }

    public void setIdPermisoUsuario(BigDecimal idPermisoUsuario) {
        this.idPermisoUsuario = idPermisoUsuario;
    }

    public Permiso getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permiso idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisoUsuario != null ? idPermisoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoUsuario)) {
            return false;
        }
        PermisoUsuario other = (PermisoUsuario) object;
        if ((this.idPermisoUsuario == null && other.idPermisoUsuario != null) || (this.idPermisoUsuario != null && !this.idPermisoUsuario.equals(other.idPermisoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPermiso + " - " + idUsuario.getNombre();
    }

}
