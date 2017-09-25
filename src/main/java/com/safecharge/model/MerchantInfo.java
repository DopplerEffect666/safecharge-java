package com.safecharge.model;

import javax.validation.constraints.NotNull;

import com.safecharge.util.Constants;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 * <p>
 * Holder for the required for a payment Cashier's Merchant's data such as
 * secret key, the merchant id, the merchant site id, etc.
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 2/17/2017
 */
public class MerchantInfo {

    @NotNull(message = "merchantKey may not be null!")
    private String merchantKey;

    @NotNull(message = "merchantId may not be null!")
    private String merchantId;

    @NotNull(message = "merchantSiteId may not be null!")
    private String merchantSiteId;

    @NotNull(message = "serverHost may not be null")
    private String serverHost;

    private Constants.HashAlgorithm hashAlgorithm;

    /**
     * Constructs a {@link MerchantInfo} object with data from the params.
     *
     * @param merchantKey    The secret merchant key obtained by the Merchant during integration process with Safecharge
     * @param merchantId     Merchant id in the Safecharge's system
     * @param merchantSiteId Merchant site id in the Safecharge's system
     * @param serverHost     The Safecharge's server address to send the request to
     * @param hashAlgorithm  The hashing algorithm used to generate the checksum
     */
    public MerchantInfo(String merchantKey, String merchantId, String merchantSiteId, String serverHost, Constants.HashAlgorithm hashAlgorithm) {
        this.merchantKey = merchantKey;
        this.merchantId = merchantId;
        this.merchantSiteId = merchantSiteId;
        this.serverHost = serverHost;
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantSiteId() {
        return merchantSiteId;
    }

    public String getServerHost() {
        return serverHost;
    }

    public Constants.HashAlgorithm getHashAlgorithm() {
        return hashAlgorithm;
    }
}
