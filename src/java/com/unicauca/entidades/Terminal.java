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
@Table(name = "TBL_TERMINAL", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminal.findAll", query = "SELECT ter FROM Terminal ter")
    , @NamedQuery(name = "Terminal.findByIdTerminal", query = "SELECT ter FROM Terminal ter WHERE ter.idTerminal = :idTerminal")
    , @NamedQuery(name = "Terminal.findByNombre", query = "SELECT ter FROM Terminal ter WHERE ter.nombre = :nombre")
    , @NamedQuery(name = "Terminal.findByDescripcion", query = "SELECT ter FROM Terminal ter WHERE ter.descripcion = :descripcion")})
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_TERMINAL", nullable = true, precision = 38, scale = 0)
    private BigDecimal idTerminal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTerminal", fetch = FetchType.LAZY)
    private List<PedidoUsuario> tblPedidoUsuarioList;

    public Terminal() {
    }

    public Terminal(BigDecimal idTerminal) {
        this.idTerminal = idTerminal;
    }

    public Terminal(BigDecimal idTerminal, String nombre) {
        this.idTerminal = idTerminal;
        this.nombre = nombre;
    }

    public BigDecimal getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(BigDecimal idTerminal) {
        this.idTerminal = idTerminal;
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
    public List<PedidoUsuario> getTblPedidoUsuarioList() {
        return tblPedidoUsuarioList;
    }

    public void setTblPedidoUsuarioList(List<PedidoUsuario> tblPedidoUsuarioList) {
        this.tblPedidoUsuarioList = tblPedidoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTerminal != null ? idTerminal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminal)) {
            return false;
        }
        Terminal other = (Terminal) object;
        if ((this.idTerminal == null && other.idTerminal != null) || (this.idTerminal != null && !this.idTerminal.equals(other.idTerminal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
