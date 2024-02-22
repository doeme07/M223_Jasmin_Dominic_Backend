package com.example.demo.domain.mylistentry.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.role.dto.RoleDTO;
import com.example.demo.domain.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MyListEntryDTO extends AbstractDTO{

    private String title;
    private String text;
    private Date creationDate;
    private int importance;
    private User user;

    public MyListEntryDTO(UUID id, String title, String text, Date creationDate, int importance, User user){
        super(id);
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.importance = importance;
        this.user = user;
    }
}
