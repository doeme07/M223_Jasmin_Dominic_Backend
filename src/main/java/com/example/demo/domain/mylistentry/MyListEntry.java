package com.example.demo.domain.mylistentry;

import com.example.demo.core.generic.AbstractEntity;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "my_list_entry")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MyListEntry extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "importance", nullable = false)
    private int importance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public MyListEntry(UUID id, String title, String text, Date creationDate, int importance, User user) {
        super(id);
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.importance = importance;
        this.user = user;
    }
}
