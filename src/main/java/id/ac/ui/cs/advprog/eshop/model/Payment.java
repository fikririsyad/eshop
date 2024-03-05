package id.ac.ui.cs.advprog.eshop.model;

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
        this.id = id;
        setOrder(order);
        setMethod(method);

        if (this.method.equals("voucher")) {
            setPaymentDataVoucher(paymentData);
        } else if (this.method.equals("bank")) {
            setPaymentDataBank(paymentData);
        }
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException();
        }
        this.order = order;
    }

    private void setMethod(String method) {
        if ((method == null) || ((!method.equals("voucher")) && (!method.equals("bank")))) {
            throw new IllegalArgumentException();
        }
        this.method = method;
    }

    private void setPaymentDataVoucher(Map<String, String> paymentData) {
        if ((paymentData.size() != 1) && (!paymentData.containsKey("voucherCode"))) {
            throw new IllegalArgumentException();
        }

        String code = paymentData.get("voucherCode");

        if (isVoucherCodeValid(code)) {
            this.status = "PENDING";
        } else {
            this.status = "REJECTED";
        }
        this.paymentData = paymentData;
    }

    private boolean isVoucherCodeValid(String code) {
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

    private void setPaymentDataBank(Map<String, String> paymentData) {
        if (paymentData.size() != 2) {
            throw new IllegalArgumentException();
        }

        if ((!paymentData.containsKey("bankName")) || (!paymentData.containsKey("referenceCode"))) {
            throw new IllegalArgumentException();
        }

        if ((paymentData.containsValue(null)) || (paymentData.containsValue(""))) {
            this.status = "REJECTED";
        } else {
            this.status = "PENDING";
        }
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS")) {
            this.status = status;
            order.setStatus(status);
        } else if (status.equals("REJECTED")) {
            this.status = status;
            order.setStatus("FAILED");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
