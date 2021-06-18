package sistema.model;


public class Category {
    private int id;
    private String name;
    private String fec;
    
 
    public Category(int id, String name, String fec) {
        super();
        this.id = id;
        this.name = name;
       
        this.fec = fec;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
        
     public String getFec() {
        return fec;
    }
 
    public void setFec(String fec) {
        this.fec = fec;
    }
}
