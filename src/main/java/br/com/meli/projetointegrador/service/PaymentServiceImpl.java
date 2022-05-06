package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentPaymentException;
import br.com.meli.projetointegrador.model.Payment;
import br.com.meli.projetointegrador.model.PaymentStatus;
import br.com.meli.projetointegrador.model.Wallet;
import br.com.meli.projetointegrador.repository.PaymentRepository;
import br.com.meli.projetointegrador.validator.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private BatchService batchService;
    private ProductService productService;
    private WalletService walletService;
    private CustomerService customerService;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new InexistentPaymentException("Payment " + id + " does not exists"));
    }

    @Override
    public Payment pay(Long id) {
        Payment payment = findById(id);
        Wallet wallet = walletService.findByUserId(payment.getCustomer().getId());

        List<Validator> validators = Arrays.asList(
                new ProductExpirationDateGreaterThan3Weeks(payment.getCart().getItems(), batchService),
                new ProductHasEnoughStock(productService, payment.getCart().getItems()),
                new BalanceHasValidAmount(wallet.getBalance().subtract(payment.getValue()), "Insufficient balance in the account to make the payment."),
                new UserMatchsTokenSent(payment.getCustomer().getId(), customerService)
        );

        validators.forEach(Validator::validate);

        Wallet customerWallet = walletService.findByUserId(payment.getCustomer().getId());

        walletService.withdraw(customerWallet.getId(), payment.getCart().getTotalCart());

        payment.setStatus(PaymentStatus.PAID);
        Payment paymentResponse = paymentRepository.save(payment);

        batchService.takeOutProducts(payment.getCart().getItems());

        return paymentResponse;
    }

    @Override
    public Payment getPayment(Long id) {
        Payment payment = findById(id);

        new UserMatchsTokenSent(payment.getCustomer().getId(), customerService).validate();

        return payment;
    }
}
