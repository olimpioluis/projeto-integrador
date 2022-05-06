package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.UserDoesNotMatchsTokenSentException;
import br.com.meli.projetointegrador.model.Customer;
import br.com.meli.projetointegrador.security.services.UserDetailsImpl;
import br.com.meli.projetointegrador.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class UserMatchsTokenSent implements Validator {

    private Long customerId;
    private CustomerService customerService;

    @Override
    public void validate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Customer customer = customerService.findByUserUsername(userDetails.getUsername());

        if (!customer.getId().equals(customerId)) throw new UserDoesNotMatchsTokenSentException("User does not match token sent!");
    }
}
