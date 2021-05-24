
package sistema.model;

public class Roles {
  int id_rol,id_permisos;
  String name_role,permissions,url;

    public Roles() {
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_permisos() {
        return id_permisos;
    }

    public void setId_permisos(int id_permisos) {
        this.id_permisos = id_permisos;
    }

    public String getName_role() {
        return name_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
  
}
