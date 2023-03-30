package com.eafc.springbootbackend.services.customer;

import com.eafc.springbootbackend.entities.customer.AccountInfo;
import com.eafc.springbootbackend.repositories.customer.AccountInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountInfoRepository accountInfoRepository;

    public UserDetailsServiceImpl(AccountInfoRepository accountInfoRepository) {
        this.accountInfoRepository = accountInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    public boolean existsByUsername(String username) {
        return accountInfoRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return accountInfoRepository.existsByEmail(email);
    }

    public void saveAccount(AccountInfo user) {
        accountInfoRepository.save(user);
    }
}
