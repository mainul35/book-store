package com.book.entity.paymentInfo;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethodType {
    MASTER_CARD,
    VISA,
    CASH;

    public Map<PaymentMethodType, String> getContactTypes () {
        Map<PaymentMethodType, String> map = new HashMap<>();
        map.put(MASTER_CARD, "Master Card");
        map.put(VISA, "Visa");
        map.put(CASH, "Cash");
        return map;
    }
}
