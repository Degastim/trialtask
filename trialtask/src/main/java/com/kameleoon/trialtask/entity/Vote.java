package com.kameleoon.trialtask.entity;

import com.kameleoon.trialtask.repository.audit.AuditListener;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "votes")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "vote_id"))
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends AbstractEntity {
    @Column
    private byte mark;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User votedUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id")
    private Quote quote;

    public Vote(long id) {
        super(id);
    }

    public Vote(long id, byte mark) {
        this(id);
        this.mark = mark;
    }

    public Vote(long id, byte mark, User votedUser) {
        this(id, mark);
        this.votedUser = votedUser;
    }

    public Vote(long id, byte mark, User votedUser, Quote quote) {
        this(id, mark, votedUser);
        this.quote = quote;
    }

    public Vote(long voteId, byte mark, User votedUser, Quote quote, LocalDateTime creationDate, LocalDateTime updateDate) {
        super(voteId, creationDate, updateDate);
        this.mark = mark;
        this.votedUser = votedUser;
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Vote vote = (Vote) o;
        if (mark != vote.mark) {
            return false;
        }
        if (votedUser != null ? !votedUser.equals(vote.votedUser) : vote.votedUser != null) {
            return false;
        }
        return quote != null ? quote.equals(vote.quote) : vote.quote == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 2 * result + mark;
        result = 3 * result + (votedUser != null ? votedUser.hashCode() : 0);
        result = 5 * result + (quote != null ? quote.hashCode() : 0);
        return result;
    }
}