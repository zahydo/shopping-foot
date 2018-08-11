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
    @NamedQuery(name = "PedidoUsuario.findAll", query = "SELECT pdUs FROM PedidoUsuario pdUs")
    , @NamedQuery(name = "PedidoUsuario.findByIdPedidoUsuario", query = "SELECT pdUs FROM PedidoUsuario pdUs WHERE pdUs.idPedidoUsuario = :idPedidoUsuario")})
public class PedidoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_PEDIDO_USUARIO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idPedidoUsuario;
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido idPedido;
    @JoinColumn(name = "ID_TERMINAL", referencedColumnName = "ID_TERMINAL", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Terminal idTerminal;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public PedidoUsuario() {
    }

    public PedidoUsuario(BigDecimal idPedidoUsuario) {
        this.idPedidoUsuario = idPedidoUsuario;
    }

    public BigDecimal getIdPedidoUsuario() {
        return idPedidoUsuario;
    }

    public void setIdPedidoUsuario(BigDecimal idPedidoUsuario) {
        this.idPedidoUsuario = idPedidoUsuario;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Terminal getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(Terminal idTerminal) {
        this.idTerminal = idTerminal;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
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
        if (!(object instanceof PedidoUsuario)) {
            return false;
        }
        PedidoUsuario other = (PedidoUsuario) object;
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
