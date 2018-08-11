/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "TBL_INGREDIENTE_PRODUCTO", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngredienteProducto.findAll", query = "SELECT ingPrd FROM IngredienteProducto ingPrd")
    , @NamedQuery(name = "IngredienteProducto.findByIdIngredienteProducto", query = "SELECT ingPrd FROM IngredienteProducto ingPrd WHERE ingPrd.idIngredienteProducto = :idIngredienteProducto")
    , @NamedQuery(name = "IngredienteProducto.findByGramos", query = "SELECT ingPrd FROM IngredienteProducto ingPrd WHERE ingPrd.gramos = :gramos")})
public class IngredienteProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_INGREDIENTE_PRODUCTO", nullable = true, precision = 38, scale = 0)
    private BigDecimal idIngredienteProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRAMOS", nullable = false)
    private BigInteger gramos;
    @JoinColumn(name = "ID_INGREDIENTE", referencedColumnName = "ID_INGREDIENTE", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ingrediente idIngrediente;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto idProducto;

    public IngredienteProducto() {
    }

    public IngredienteProducto(BigDecimal idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public IngredienteProducto(BigDecimal idIngredienteProducto, BigInteger gramos) {
        this.idIngredienteProducto = idIngredienteProducto;
        this.gramos = gramos;
    }

    public BigDecimal getIdIngredienteProducto() {
        return idIngredienteProducto;
    }

    public void setIdIngredienteProducto(BigDecimal idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public BigInteger getGramos() {
        return gramos;
    }

    public void setGramos(BigInteger gramos) {
        this.gramos = gramos;
    }

    public Ingrediente getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Ingrediente idIngrediente) {
        this.idIngrediente = idIngrediente;
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
        hash += (idIngredienteProducto != null ? idIngredienteProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngredienteProducto)) {
            return false;
        }
        IngredienteProducto other = (IngredienteProducto) object;
        if ((this.idIngredienteProducto == null && other.idIngredienteProducto != null) || (this.idIngredienteProducto != null && !this.idIngredienteProducto.equals(other.idIngredienteProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProducto.getNombre() + " - " + idIngrediente.getNombre();
    }
    
}
