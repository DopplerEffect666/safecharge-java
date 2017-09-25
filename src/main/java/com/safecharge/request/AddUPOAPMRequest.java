package com.safecharge.request;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.safecharge.model.UserDetails;
import com.safecharge.request.builder.SafechargeBuilder;
import com.safecharge.util.AddressUtils;
import com.safecharge.util.Constants;
import com.safecharge.util.ValidChecksum;
import com.safecharge.util.ValidationUtils;

/**
 * Copyright (C) 2007-2017 SafeCharge International Group Limited.
 * <p>
 * Request to add APM User Payment Option to a User
 *
 * @author <a mailto:nikolad@safecharge.com>Nikola Dichev</a>
 * @since 3/21/2017
 */
@ValidChecksum(orderMappingName = Constants.ChecksumOrderMapping.ADD_CASHIER_APM)
public class AddUPOAPMRequest extends SafechargeRequest {

    /**
     * The unique name of the payment method in Cashier system (for example apmgw_Neteller).  For a list of possible values, see APM Unique SafeCharge Cashier Names.
     */
    @NotNull(message = "paymentMethodName parameter is mandatory!")
    private String paymentMethodName;

    /**
     * A list of name-value pairs that contain the parameters of the user payment option.
     */
    @NotNull(message = "apmData parameter is mandatory!")
    @Size(min = 1, message = "apmData must have at least one entry")
    private Map<String, String> apmData;

    /**
     * This parameter is a unique identifier for each customer generated by you.
     */
    @NotNull(message = "userTokenId parameter is mandatory!")
    private String userTokenId;

    /**
     * Billing address related to a user payment option. Since order can contain only one payment option billing address is part of the order parameters.
     */
    private UserDetails billingAddress;

    public static Builder builder() {
        return new Builder();
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public Map<String, String> getApmData() {
        return apmData;
    }

    public void setApmData(Map<String, String> apmData) {
        this.apmData = apmData;
    }

    public String getUserTokenId() {
        return userTokenId;
    }

    public void setUserTokenId(String userTokenId) {
        this.userTokenId = userTokenId;
    }

    public UserDetails getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(UserDetails billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddUPOAPM{");
        sb.append("billingAddress=")
                .append(billingAddress);
        sb.append(", paymentMethodName='")
                .append(paymentMethodName)
                .append('\'');
        sb.append(", apmData=")
                .append(apmData);
        sb.append(", userTokenId='")
                .append(userTokenId)
                .append('\'');
        sb.append(", ")
                .append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public static class Builder extends SafechargeBuilder<Builder> {

        UserDetails billingAddress;
        private String paymentMethodName;
        private Map<String, String> apmData;

        @NotNull
        private String userTokenId;

        /**
         * Adds the payment method name to the request.
         *
         * @param paymentMethodName The unique name of the payment method in Cashier system.
         * @return this object
         * @see <a href="https://www.safecharge.com/docs/api/#apm-account-identifiers}">APM account identifiers</a>
         */
        public Builder addPaymentMethodName(String paymentMethodName) {
            this.paymentMethodName = paymentMethodName;
            return this;
        }

        /**
         * Adds user token id to the request.
         *
         * @param userTokenId The user token as {@link String}
         * @return this object
         */
        public Builder addUserTokenId(String userTokenId) {
            this.userTokenId = userTokenId;
            return this;
        }

        /**
         * Adds map from APM specific data to the request. E.g. username -> user1111, password -> p4ssw0rd
         *
         * @param apmData map of APM specific key-value data
         * @return this object
         */
        public Builder addApmData(Map<String, String> apmData) {
            this.apmData = apmData;
            return this;
        }

        /**
         * Adds one key-value pair of APM specific data to the request.
         *
         * @param key   {@link String} key. E.g. "username"
         * @param value {@link String} value to assign to the {@code key}. E.g. "user1111"
         * @return this object
         */
        public Builder addApmDataEntry(String key, String value) {

            if (apmData == null) {
                apmData = new HashMap<>();
            }
            apmData.put(key, value);
            return this;
        }

        /**
         * Adds billing address data to the request.
         *
         * @param billingAddress {@link UserDetails} object to get the billing details from
         * @return this object
         */
        public Builder addBillingAddress(UserDetails billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        /**
         * Adds billing address data to the request.
         *
         * @param firstName   The first name of the recipient
         * @param lastName    The last name of the recipient
         * @param address     The address of the recipient
         * @param phone       The phone number of the recipient
         * @param zip         The postal code of the recipient
         * @param city        The city of the recipient
         * @param countryCode The country of the recipient(two-letter ISO country code)
         * @param state       The state of the recipient(two-letter ISO state code)
         * @param email       The email of the recipient
         * @param locale      The recipient’s locale and default language, for example en_UK.
         * @param dateOfBirth The date of birth of the recipient
         * @return this object
         */
        public Builder addBillingAddress(String firstName, String lastName, String address, String phone, String zip, String city, String countryCode,
                                         String state, String email, String locale, String dateOfBirth) {

            UserDetails billingAddress = AddressUtils.createUserDetailsFromParams(firstName, lastName, address, phone,
                    zip, city, countryCode, state, email, locale, dateOfBirth);
            return addBillingAddress(billingAddress);
        }

        /**
         * Builds the request.
         *
         * @return {@link SafechargeRequest} object build from the params set by this builder
         * @throws ConstraintViolationException if the validation of the params fails
         */
        @Override
        public SafechargeRequest build() throws ConstraintViolationException {

            AddUPOAPMRequest addUPOAPM = new AddUPOAPMRequest();
            addUPOAPM.setPaymentMethodName(paymentMethodName);
            addUPOAPM.setUserTokenId(userTokenId);
            addUPOAPM.setApmData(apmData);
            addUPOAPM.setBillingAddress(billingAddress);

            return ValidationUtils.validate(super.build(addUPOAPM));
        }
    }
}
