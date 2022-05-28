package sg.edu.np.mad.practical3cyrus;

public class User {
    public String name;
    public String description;
    public Integer id;
    public Boolean followed;

    public User(String name, String description, Integer id, Boolean followed){
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
}
