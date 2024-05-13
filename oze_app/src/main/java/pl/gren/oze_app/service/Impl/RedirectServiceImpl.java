package pl.gren.oze_app.service.Impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import pl.gren.oze_app.service.RedirectService;

@Service
public class RedirectServiceImpl implements RedirectService {

    @Override
    public String getDashboardRedirect() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "redirect:/home_admin";
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("SALESMAN"))) {
            return "redirect:/home_salesman";
        } else {
            return "redirect:/home";
        }
    }
}
