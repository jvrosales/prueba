/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.ambiente.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ishmael
 */
public class UsuarioVO implements Serializable {

    private static final long serialVersionUID = 2335071894416421264L;

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private String fechaCreacion;

    @Getter
    @Setter
    private String roles;

    @Getter
    @Setter
    private String cedula;

    public UsuarioVO() {
    }

    public UsuarioVO(String id, String nombre, String fechaCreacion, String roles) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.roles = roles;
    }

    public UsuarioVO(String id, String nombre, String fechaCreacion, String nombrePersona, String nombreOrganizacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        if (nombreOrganizacion != null && !nombreOrganizacion.isEmpty()) {
            this.roles = nombreOrganizacion;
        } else {
            this.roles = nombrePersona;
        }

    }

    public UsuarioVO(String id, String nombre, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioVO)) {
            return false;
        }
        UsuarioVO other = (UsuarioVO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
