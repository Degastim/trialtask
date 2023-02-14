package com.kameleoon.trialtask.entity;

import com.kameleoon.trialtask.repository.audit.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractEntity {
    @Column(name = "user_name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column
    private String password;
    @OneToMany(mappedBy = "votedUser")
    @Fetch(FetchMode.SUBSELECT)
    private List<Vote> voteList;
    @OneToMany(mappedBy = "postingUser")
    @Fetch(FetchMode.SUBSELECT)
    private List<Quote> quoteList;

    public User(long id) {
        this.id = id;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(long id, String name, String password) {
        this(id, name);
        this.password = password;
    }

    public User(long userId, String name, String email, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(userId, creationDate, updateDate);
        this.name = name;
        this.email = email;
    }

    public User(int userId, String name, String email, String password, LocalDateTime creationDate, LocalDateTime updateDate) {
        this(userId, name, email, creationDate, updateDate);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 2 * result + (name != null ? name.hashCode() : 0);
        result = 3 * result + (password != null ? password.hashCode() : 0);
        result = 5 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
