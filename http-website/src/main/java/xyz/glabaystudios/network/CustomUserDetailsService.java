package xyz.glabaystudios.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.network.models.CustomUserDetails;
import xyz.glabaystudios.network.models.dto.ProfileDTO;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Glabay
 * @project ShitList
 * @social Discord: Glabay | Website: www.GlabayStudios.xyz
 * @since 2023-08-16
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService, GlabayStudiosNetwork {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isBlank())
            throw new UsernameNotFoundException("Username was not found");
        var response = fetchHttpGetResponse(BASE_API_ENDPOINT.concat("/api/v1/profile/fetch/").concat(username));
        var statusCode = response.getStatusLine().getStatusCode();
        if (Objects.equals(statusCode, HttpStatus.SC_OK)) {
            try {
                var dto = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity(), "UTF-8"), ProfileDTO.class);
                return new CustomUserDetails(dto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new UsernameNotFoundException("User not found");
    }
}
