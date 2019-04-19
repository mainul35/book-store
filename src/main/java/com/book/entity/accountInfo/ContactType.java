package com.book.entity.accountInfo;

import java.util.HashMap;
import java.util.Map;

public enum ContactType {
    SHIPPING_CONTACT,
    BILLING_CONTACT;

    public Map<ContactType, String> getContactTypes () {
        Map<ContactType, String> map = new HashMap<>();
        map.put(SHIPPING_CONTACT, "Shipping Contact");
        map.put(BILLING_CONTACT, "Billing Contact");
        return map;
    }
}
