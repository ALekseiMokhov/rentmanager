package ru.rambler.alexeimohov.entities;


import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rambler.alexeimohov.entities.enums.Privilege;
import ru.rambler.alexeimohov.entities.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*Main entity class
 * Relationships:
 * 1:1 Subscription     through the joining table to avoid null values
 * 1:n Message bidirectional , methods add- and remove- for sync purpose
 * embedded enums: Role, Privilege
 * */
@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(exclude ={ "subscription","messages","creditCards","privilege"})
@Builder
@ToString
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User name should'not be null")
    @Length(min = 5, max = 35)
    @Column(name = "full_name", unique = true)
    @NaturalId
    private String username;

    @NotNull(message = "User password should'not be null")
    @Length(min = 8, max = 16)
    private String password;

    @Email(message = "Email should be in valid format!")
    private String email;

    @Column(name = "phone_number",unique = true)
    private long phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
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
        this.creditCards = new ArrayList <>();
        this.messages = new ArrayList <>();
    }

    public User(Long id, String username, String password, Long phoneNumber, String email,
                Role role, Subscription subscription, Privilege privilege) {
        this.id = id;
        this.username = username;
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

    public void setSubscription(Subscription subscription){
           if(subscription==null){
               if(this.subscription!=null){
                   this.subscription.setUser( null );
               }
           }
           else {
               subscription.setUser( this );
           }
           this.subscription=subscription;
    }
      /*UserDetails inherited methods*/
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(String.valueOf(role)));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
