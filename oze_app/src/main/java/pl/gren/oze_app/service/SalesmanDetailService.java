package pl.gren.oze_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gren.oze_app.model.db.entity.Salesman;

import java.util.Optional;

@Service
public class SalesmanDetailService implements UserDetailsService {

    @Autowired
    private SalesmanService salesmanService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Salesman> salesman = salesmanService.findByName(username);
        if (salesman.isPresent()) {
            Salesman salesmanObj = salesman.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(salesmanObj.getFirstName() + " " + salesmanObj.getLastName())
                    .password(salesmanObj.getPasswordHash())
                    .roles(getRoles(salesmanObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(Salesman salesman) {
        if (salesman.getRole() == null) {
            return new String[]{"HANDLOWIEC"};
        }
        return salesman.getRole().split(",");
    }
}