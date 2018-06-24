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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_USUARIO", catalog = "", schema = "VENDEDOR", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBRE_USUARIO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUsuario.findAll", query = "SELECT t FROM TblUsuario t")
    , @NamedQuery(name = "TblUsuario.findByIdUsuario", query = "SELECT t FROM TblUsuario t WHERE t.idUsuario = :idUsuario")
    , @NamedQuery(name = "TblUsuario.findByNombreUsuario", query = "SELECT t FROM TblUsuario t WHERE t.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "TblUsuario.findByContrasena", query = "SELECT t FROM TblUsuario t WHERE t.contrasena = :contrasena")
    , @NamedQuery(name = "TblUsuario.findByNombre", query = "SELECT t FROM TblUsuario t WHERE t.nombre = :nombre")})
public class TblUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_USUARIO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 20)
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CONTRASENA", nullable = false, length = 20)
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<TblPermisoUsuario> tblPermisoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<TblPedidoUsuario> tblPedidoUsuarioList;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblRol idRol;

    public TblUsuario() {
    }

    public TblUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TblUsuario(BigDecimal idUsuario, String nombreUsuario, String contrasena, String nombre) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<TblPermisoUsuario> getTblPermisoUsuarioList() {
        return tblPermisoUsuarioList;
    }

    public void setTblPermisoUsuarioList(List<TblPermisoUsuario> tblPermisoUsuarioList) {
        this.tblPermisoUsuarioList = tblPermisoUsuarioList;
    }

    @XmlTransient
    public List<TblPedidoUsuario> getTblPedidoUsuarioList() {
        return tblPedidoUsuarioList;
    }

    public void setTblPedidoUsuarioList(List<TblPedidoUsuario> tblPedidoUsuarioList) {
        this.tblPedidoUsuarioList = tblPedidoUsuarioList;
    }

    public TblRol getIdRol() {
        return idRol;
    }

    public void setIdRol(TblRol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsuario)) {
            return false;
        }
        TblUsuario other = (TblUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }
    
}
