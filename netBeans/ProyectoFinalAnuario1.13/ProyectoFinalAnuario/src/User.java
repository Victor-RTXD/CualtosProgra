/**
 *
 * @author samue
 */
public class User {
    
    String user, password;
    
    public User(String user, String password){
        this.password = password;
        this.user = user;
    }
    
    public void setUser(String x){
        this.user = x;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setPassword(String y){
        this.password = y;
    }
    
    public String getPassword(){
        return password;
    }
    
}
