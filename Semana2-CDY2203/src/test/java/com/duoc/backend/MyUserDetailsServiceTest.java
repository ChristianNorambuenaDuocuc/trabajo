package com.duoc.backend;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.duoc.backend.user.MyUserDetailsService;
import com.duoc.backend.user.User;
import com.duoc.backend.user.UserRepository;


@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserDetailsService service;

    @Test
    void deberiaRetornarUsuarioSiExiste() {

        User user = new User();
        user.setUsername("christian");

        when(userRepository.findByUsername("christian")).thenReturn(user);

        var result = service.loadUserByUsername("christian");

        assertNotNull(result);
        assertEquals("christian", result.getUsername());
    }

    @Test
    void deberiaLanzarExcepcionSiNoExiste() {

        when(userRepository.findByUsername("noexiste")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername("noexiste");
        });
    }
}