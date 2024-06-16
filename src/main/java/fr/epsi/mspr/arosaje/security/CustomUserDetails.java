package fr.epsi.mspr.arosaje.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    @Getter
    private int id;

    @Getter
    private String email;

    private final UserDetails delegate;

    private final boolean active; // Ajout de la propriété pour gérer l'état d'activation

    public CustomUserDetails(UserDetails userDetails, int id, String email, boolean active) {
        this.delegate = userDetails;
        this.id = id;
        this.email = email;
        this.active = active;
    }

    // Implémentez les méthodes de UserDetails en les déléguant à l'instance delegate
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return delegate.getAuthorities();
    }

    @Override
    public String getPassword() {
        return delegate.getPassword();
    }

    @Override
    public String getUsername() {
        return delegate.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Votre logique pour vérifier si le compte a expiré
        return true; // Par défaut, supposons que le compte n'expire pas
    }

    @Override
    public boolean isAccountNonLocked() {
        // Votre logique pour vérifier si le compte est verrouillé
        return active; // Le compte est verrouillé si non actif
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Votre logique pour vérifier si les informations d'identification ont expiré
        return true; // Par défaut, supposons que les informations d'identification ne sont pas expirées
    }

    @Override
    public boolean isEnabled() {
        // Votre logique pour vérifier si le compte est activé
        return true; // Le compte est activé s'il est actif
    }
}
