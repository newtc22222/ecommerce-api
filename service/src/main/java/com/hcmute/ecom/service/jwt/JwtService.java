package com.hcmute.ecom.service.jwt;

import com.hcmute.ecom.dao.UserDAO;
import com.hcmute.ecom.model.User;
import com.hcmute.ecom.service.jwt.dto.JwtRequest;
import com.hcmute.ecom.service.jwt.dto.JwtResponse;
import com.hcmute.ecom.service.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) {
        String phone = jwtRequest.getPhone();
        String password = jwtRequest.getPassword();
        authenticate(phone, password);

        final UserDetails userDetails = loadUserByUsername(phone);
        String generateNewToken = jwtUtil.generateJwtToken(userDetails);

        User user = userDAO.findUserByPhone(phone);
        return new JwtResponse(user, generateNewToken);
    }

    @Override
    public UserDetails loadUserByUsername(String userPhone) throws UsernameNotFoundException {
        User user = userDAO.findUserByPhone(userPhone);

        if(user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getPhone(),
                    user.getPassword(),
                    getRoleOfUser(user)
            );
        }
        else {
            throw new UsernameNotFoundException("User phone are not valid");
        }
    }

    private Set<GrantedAuthority> getRoleOfUser(User user) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString().toUpperCase()));
        return roles;
    }

    private void authenticate(String phone, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password));
        }
        catch (DisabledException | BadCredentialsException e) {
            throw new RuntimeException(e);
        }
    }
}
