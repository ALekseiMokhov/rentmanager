package ru.rambler.alexeimohov.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.mail.SimpleMailMessage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
 * Entity sending to User n:1. Having @field Id as an equals @param to compare.
 * Manipulated by IUserService.*/
@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
public class Message extends SimpleMailMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 1024)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private User user;

    @Column(name = "date_time")
    private LocalDateTime dateTimeOfSending;

    public Message() {
        this.dateTimeOfSending = LocalDateTime.now();
    }

    /* overriding due to use of id for equals*/
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Message))
            return false;

        return
                id != null &&
                        id.equals(((Message) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
