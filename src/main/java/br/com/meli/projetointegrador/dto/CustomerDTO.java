package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerDTO {
    private String name;
    private String email;

    public static CustomerDTO map(Customer customer) {
        return new CustomerDTO(customer.getUser().getName(), customer.getUser().getEmail());
    }
}
