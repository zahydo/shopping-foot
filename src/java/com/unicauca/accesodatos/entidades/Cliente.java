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
    @NamedQuery(name = "Cliente.findAll", query = "SELECT cli FROM Cliente cli")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT cli FROM Cliente cli WHERE cli.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByIdentificacion", query = "SELECT cli FROM Cliente cli WHERE cli.identificacion = :identificacion")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT cli FROM Cliente cli WHERE cli.email = :email")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT cli FROM Cliente cli WHERE cli.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT cli FROM Cliente cli WHERE cli.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT cli FROM Cliente cli WHERE cli.telefono = :telefono")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ID_CLIENTE", nullable = true, precision = 38, scale = 0)
    private Long idCliente;
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
    private List<Pedido> tblPedidoList;

    public Cliente() {
    }

    public Cliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Long idCliente, String identificacion, String email, String nombre, String direccion) {
        this.idCliente = idCliente;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
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
    public List<Pedido> getTblPedidoList() {
        return tblPedidoList;
    }

    public void setTblPedidoList(List<Pedido> tblPedidoList) {
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
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
