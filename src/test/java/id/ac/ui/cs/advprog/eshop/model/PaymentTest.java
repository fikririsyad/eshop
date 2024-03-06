package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product1);
        products.add(product2);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentNullOrder() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
           Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), null, paymentData);
        });
    }

    @Test
    void testCreatePaymentNullMethod() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id12345", null, order, paymentData);
        });
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidVoucherCode() {
        this.paymentData.put("voucherCode", null);

        Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentMismatchedMethod() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id12345", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreatePaymentIncompleteBankData() {
        this.paymentData.put("bankName", "BCA");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id12345", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidBankData() {
        this.paymentData.put("bankName", null);
        this.paymentData.put("referenceCode", null);

        Payment payment = new Payment("id12345", PaymentMethod.BANK.getValue(), order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccess() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);

        assertEquals("id12345", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertTrue(payment.getPaymentData().containsKey("voucherCode"));
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("id12345", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}
