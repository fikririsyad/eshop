package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository ;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");
        Payment voucherPayment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, voucherData);
        payments.add(voucherPayment);

        Map<String, String> bankData = new HashMap<>();
        bankData.put("bankName", "BCA");
        bankData.put("referenceCode", "12345678");
        Payment bankPayment = new Payment("id67890", PaymentMethod.BANK.getValue(), order, bankData);
        payments.add(bankPayment);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getOrder(),  findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                payment.getOrder(), payment.getPaymentData(), PaymentStatus.SUCCESS.getValue());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getOrder(), findResult.getOrder());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        assertEquals(payments, paymentRepository.getAllPayments());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        assertTrue(paymentRepository.getAllPayments().isEmpty());
    }
}
