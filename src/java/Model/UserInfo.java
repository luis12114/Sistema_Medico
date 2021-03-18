package Model;

public class UserInfo {

    String name, las_tname, user_name,email;
    String password, password_validate;
    String faculty, college_career,role;

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLas_tname() {
        return las_tname;
    }

    public void setLas_tname(String las_tname) {
        this.las_tname = las_tname;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_validate() {
        return password_validate;
    }

    public void setPassword_validate(String password_validate) {
        this.password_validate = password_validate;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCollege_career() {
        return college_career;
    }

    public void setCollege_career(String college_career) {
        this.college_career = college_career;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
