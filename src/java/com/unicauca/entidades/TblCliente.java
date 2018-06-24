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
@Table(name = "TBL_CLIENTE", catalog = "", schema = "VENDEDOR", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDENTIFICACION"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCliente.findAll", query = "SELECT t FROM TblCliente t")
    , @NamedQuery(name = "TblCliente.findByIdCliente", query = "SELECT t FROM TblCliente t WHERE t.idCliente = :idCliente")
    , @NamedQuery(name = "TblCliente.findByIdentificacion", query = "SELECT t FROM TblCliente t WHERE t.identificacion = :identificacion")
    , @NamedQuery(name = "TblCliente.findByEmail", query = "SELECT t FROM TblCliente t WHERE t.email = :email")
    , @NamedQuery(name = "TblCliente.findByNombre", query = "SELECT t FROM TblCliente t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TblCliente.findByDireccion", query = "SELECT t FROM TblCliente t WHERE t.direccion = :direccion")
    , @NamedQuery(name = "TblCliente.findByTelefono", query = "SELECT t FROM TblCliente t WHERE t.telefono = :telefono")})
public class TblCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_CLIENTE", nullable = true, precision = 38, scale = 0)
    private BigDecimal idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDENTIFICACION", nullable = false, length = 20)
    private String identificacion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMAIL", nullable = false, length = 30)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DIRECCION", nullable = false, length = 20)
    private String direccion;
    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<TblPedido> tblPedidoList;

    public TblCliente() {
    }

    public TblCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public TblCliente(BigDecimal idCliente, String identificacion, String email, String nombre, String direccion) {
        this.idCliente = idCliente;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public BigDecimal getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(BigDecimal idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCliente)) {
            return false;
        }
        TblCliente other = (TblCliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
