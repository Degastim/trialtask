package com.kameleoon.trialtask.entity;

import com.kameleoon.trialtask.repository.audit.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "quotes")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "quote_id"))
@NoArgsConstructor
public class Quote extends AbstractEntity {
    @Column
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User postingUser;
    @OneToMany(mappedBy = "quote")
    private List<Vote> voteList;

    public Quote(long id) {
        super(id);
    }

    public Quote(String content) {
        this.content = content;
    }

    public Quote(long id, LocalDateTime creationDate) {
        super(id, creationDate);
    }

    public Quote(long id, List<Vote> voteList) {
        super(id);
        this.voteList = voteList;
    }

    public Quote(String content, User postingUser) {
        this.content = content;
        this.postingUser = postingUser;
    }

    public Quote(long id, String content, User postingUser, List<Vote> voteList) {
        this(id, content, postingUser);
        this.voteList = voteList;
    }

    public Quote(long id, String content, User postingUser) {
        super(id);
        this.content = content;
        this.postingUser = postingUser;
    }

    public Quote(long quoteId, String content, User postingUser, List<Vote> voteList,
                 LocalDateTime creationDate, LocalDateTime updateDate) {
        super(quoteId, creationDate, updateDate);
        this.content = content;
        this.postingUser = postingUser;
        this.voteList = voteList;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Quote quote = (Quote) o;
        if (postingUser != null ? !postingUser.equals(quote.postingUser) : quote.postingUser != null) {
            return false;
        }
        return content != null ? content.equals(quote.content) : quote.content == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result += 2 * (content != null ? content.hashCode() : 0);
        result += 5 * (postingUser != null ? postingUser.hashCode() : 0);
        return result;
    }
}
