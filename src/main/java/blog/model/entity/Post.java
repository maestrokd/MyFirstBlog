package blog.model.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "posts")
public class Post {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;


    @Column(name = "title")
    private String title;

    @Column(name = "body", columnDefinition="TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_users_posts"))
    private User user;

    @Column
    private Date date = new Date();


    // Constructors
    public Post() {}


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Methods



}
