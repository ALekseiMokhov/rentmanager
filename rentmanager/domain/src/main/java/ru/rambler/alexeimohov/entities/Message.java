package ru.rambler.alexeimohov.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
 * Entity sending to User n:1. Having Id as an equals param to compare*/
@Entity
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 1024)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "date_time")
    private LocalDateTime dateTimeOfSending;

    public Message() {
        this.dateTimeOfSending = LocalDateTime.now();
    }

    /* overriding due to use id for equals*/
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Message))
            return false;

        return
                id != null &&
                        id.equals( ((Message) o).getId() );
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
