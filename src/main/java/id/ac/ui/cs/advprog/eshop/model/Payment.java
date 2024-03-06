package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        setOrder(order);
        setMethod(method);

        boolean isPaymentValid;
        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
             isPaymentValid = isVoucherCodeValid(paymentData);
        } else {
            isPaymentValid = isBankValid(paymentData);
        }

        if (isPaymentValid) {
            setStatus(status);
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }
        this.paymentData = paymentData;
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException();
        }
        this.order = order;
    }

    private void setMethod(String method) {
        if ((method == null) || (!PaymentMethod.contains(method))) {
            throw new IllegalArgumentException();
        }
        this.method = method;
    }

    private boolean isVoucherCodeValid(Map<String, String> paymentData) {
        if ((paymentData.size() != 1) && (!paymentData.containsKey("voucherCode"))) {
            throw new IllegalArgumentException();
        }

        String code = paymentData.get("voucherCode");
        if (code == null) {
            return false;
        }

        if ((code.length() != 16) || (!code.startsWith("ESHOP"))) {
            return false;
        }

        int counter = 0;
        for (int i = 5; i < code.length(); i++) {
            if (Character.isDigit(code.charAt(i))) {
                counter++;
                if (counter == 8) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBankValid(Map<String, String> paymentData) {
        if (paymentData.size() != 2) {
            throw new IllegalArgumentException();
        }

        if ((!paymentData.containsKey("bankName")) || (!paymentData.containsKey("referenceCode"))) {
            throw new IllegalArgumentException();
        }

        return (!paymentData.containsValue(null)) && (!paymentData.containsValue(""));
    }

    public void setStatus(String status) {
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            this.status = status;
            order.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            this.status = status;
            order.setStatus(OrderStatus.FAILED.getValue());
        } else if (status.equals(PaymentStatus.PENDING.getValue())) {
            this.status = status;
            order.setStatus(OrderStatus.WAITING_PAYMENT.getValue());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
