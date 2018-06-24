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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sahydo
 */
@Entity
@Table(name = "TBL_PEDIDO_USUARIO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPedidoUsuario.findAll", query = "SELECT t FROM TblPedidoUsuario t")
    , @NamedQuery(name = "TblPedidoUsuario.findByIdPedidoUsuario", query = "SELECT t FROM TblPedidoUsuario t WHERE t.idPedidoUsuario = :idPedidoUsuario")})
public class TblPedidoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PEDIDO_USUARIO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idPedidoUsuario;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblPedido idPedido;
    @JoinColumn(name = "ID_TERMINAL", referencedColumnName = "ID_TERMINAL", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblTerminal idTerminal;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblUsuario idUsuario;

    public TblPedidoUsuario() {
    }

    public TblPedidoUsuario(BigDecimal idPedidoUsuario) {
        this.idPedidoUsuario = idPedidoUsuario;
    }

    public BigDecimal getIdPedidoUsuario() {
        return idPedidoUsuario;
    }

    public void setIdPedidoUsuario(BigDecimal idPedidoUsuario) {
        this.idPedidoUsuario = idPedidoUsuario;
    }

    public TblPedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(TblPedido idPedido) {
        this.idPedido = idPedido;
    }

    public TblTerminal getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(TblTerminal idTerminal) {
        this.idTerminal = idTerminal;
    }

    public TblUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(TblUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoUsuario != null ? idPedidoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPedidoUsuario)) {
            return false;
        }
        TblPedidoUsuario other = (TblPedidoUsuario) object;
        if ((this.idPedidoUsuario == null && other.idPedidoUsuario != null) || (this.idPedidoUsuario != null && !this.idPedidoUsuario.equals(other.idPedidoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPedido + " - " + idUsuario.getNombre();
    }
    
}
