package xyz.glabaystudios.network.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.glabaystudios.network.models.dto.ProfileDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-16
 */
public class CustomUserDetails implements UserDetails {

    private final ProfileDTO profile;

    public CustomUserDetails(ProfileDTO profile) {
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return a list of authorities this user has
        return new ArrayList<SimpleGrantedAuthority>();
    }

    @Override public String getPassword() {
        return profile.getPassword();
    }

    @Override public String getUsername() {
        return profile.getUsername();
    }

    @Override public boolean isAccountNonExpired() {
        return false;
    }

    @Override public boolean isAccountNonLocked() {
        return false;
    }

    @Override public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override public boolean isEnabled() {
        return false;
    }
}
