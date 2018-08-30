/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.accesodatos.entidades;

import java.io.Serializable;
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
@Table(name = "TBL_INGREDIENTE", catalog = "", schema = "VENDEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingrediente.findAll", query = "SELECT ing FROM Ingrediente ing")
    , @NamedQuery(name = "Ingrediente.findByIdIngrediente", query = "SELECT ing FROM Ingrediente ing WHERE ing.idIngrediente = :idIngrediente")
    , @NamedQuery(name = "Ingrediente.findByNombre", query = "SELECT ing FROM Ingrediente ing WHERE ing.nombre = :nombre")
    , @NamedQuery(name = "Ingrediente.findByDescripcion", query = "SELECT ing FROM Ingrediente ing WHERE ing.descripcion = :descripcion")})
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_INGREDIENTE", nullable = true, precision = 38, scale = 0)
    private Long idIngrediente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2030)
    @Column(name = "NOMBRE", nullable = false, length = 2030)
    private String nombre;
    @Size(max = 50)
    @Column(name = "DESCRIPCION", length = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIngrediente", fetch = FetchType.LAZY)
    private List<IngredienteProducto> tblIngredienteProductoList;

    public Ingrediente() {
    }

    public Ingrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Ingrediente(Long idIngrediente, String nombre) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
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
    public List<IngredienteProducto> getIngredienteProductoList() {
        return tblIngredienteProductoList;
    }

    public void setIngredienteProductoList(List<IngredienteProducto> tblIngredienteProductoList) {
        this.tblIngredienteProductoList = tblIngredienteProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngrediente != null ? idIngrediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.idIngrediente == null && other.idIngrediente != null) || (this.idIngrediente != null && !this.idIngrediente.equals(other.idIngrediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
