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
    @NamedQuery(name = "TblIngredienteProducto.findAll", query = "SELECT t FROM TblIngredienteProducto t")
    , @NamedQuery(name = "TblIngredienteProducto.findByIdIngredienteProducto", query = "SELECT t FROM TblIngredienteProducto t WHERE t.idIngredienteProducto = :idIngredienteProducto")
    , @NamedQuery(name = "TblIngredienteProducto.findByGramos", query = "SELECT t FROM TblIngredienteProducto t WHERE t.gramos = :gramos")})
public class TblIngredienteProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INGREDIENTE_PRODUCTO", nullable = false, precision = 38, scale = 0)
    private BigDecimal idIngredienteProducto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRAMOS", nullable = false)
    private BigInteger gramos;
    @JoinColumn(name = "ID_INGREDIENTE", referencedColumnName = "ID_INGREDIENTE", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblIngrediente idIngrediente;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblProducto idProducto;

    public TblIngredienteProducto() {
    }

    public TblIngredienteProducto(BigDecimal idIngredienteProducto) {
        this.idIngredienteProducto = idIngredienteProducto;
    }

    public TblIngredienteProducto(BigDecimal idIngredienteProducto, BigInteger gramos) {
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

    public TblIngrediente getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(TblIngrediente idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public TblProducto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(TblProducto idProducto) {
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
        if (!(object instanceof TblIngredienteProducto)) {
            return false;
        }
        TblIngredienteProducto other = (TblIngredienteProducto) object;
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
