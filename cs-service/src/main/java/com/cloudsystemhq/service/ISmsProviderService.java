package com.cloudsystemhq.service;

import com.cloudsystemhq.service.smsApi.SentSms;
import com.cloudsystemhq.service.smsApi.SmsAccount;
import com.cloudsystemhq.service.smsApi.SmsList;

public interface ISmsProviderService {

  SmsAccount getAccountInfo();

  SentSms sendSmsToCustomer(String customerNumber, String message);

  SmsList getRecentCustomersSms(String customerNumber);
}
