package com.safecharge.response;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 4/6/2017
 */
public class VoidTransactionResponse extends SafechargeTransactionResponse {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VoidTransactionResponse{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
