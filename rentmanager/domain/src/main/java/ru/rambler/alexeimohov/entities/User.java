package ru.rambler.alexeimohov.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.rambler.alexeimohov.entities.enums.Privilege;
import ru.rambler.alexeimohov.entities.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/*Main entity class
 * Relationships:
 * 1:1 Subscription     through the joining table to avoid null values
 * 1:n Message bidirectional , methods add- and remove- for sync purpose
 * embedded enums: Role, Privilege
 * TODO: consider to make extra entity for security*/
@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User name should'not be null")
    @Length(min = 5, max = 35)
    @Column(name = "full_name", unique = true)
    private String fullName;

    @NotNull(message = "User password should'not be null")
    @Length(min = 8, max = 16)
    private String password;

    @Email(message = "Email should be in valid format!")
    private String email;

    @Column(name = "phone_number")
    @Length(min = 11, max = 11)
    private long phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinTable(name = "user_subscription",
            joinColumns = { @JoinColumn(name = "id_user", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "id_subscription", referencedColumnName = "id") })
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    private Privilege privilege;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List <Message> messages;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List <Card> creditCards;

    public User() {
        this.messages = new ArrayList <>();
    }

    public User(Long id, String fullName, String password, Long phoneNumber, String email,
                Role role, Subscription subscription, Privilege privilege) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.subscription = subscription;
        this.privilege = privilege;
        this.messages = new ArrayList <>();
        this.creditCards = new ArrayList <>();
    }

    public void addMessage(Message message) {
        messages.add( message );
        message.setUser( this );
    }

    public void removeMessage(Message message) {
        messages.remove( message );
        message.setUser( null );
    }

    public void addCreditCard(Card card) {

        creditCards.add( card );
        card.setUser( this );
    }

    public void removeCreditCard(Card card) {
        creditCards.remove( card );
        card.setUser( null );
    }
}
