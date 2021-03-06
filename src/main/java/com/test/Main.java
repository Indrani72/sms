package com.test;

import static spark.Spark.*;
import static spark.Spark.get;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Conference;
import com.twilio.sdk.resource.instance.Participant;
import com.twilio.sdk.resource.list.AccountList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.ParticipantList;
import com.twilio.sdk.verbs.TwiMLException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * The Constant ACCOUNT_SID. Find it at twilio.com/user/account
     */
    public static final String ACCOUNT_SID = "AC0b2856d397dad6c82776bc11f57ddaf9";

    /**
     * The Constant AUTH_TOKEN. Find it at twilio.com/user/account
     */
    public static final String AUTH_TOKEN = "16fe24b95d28673bb10ca0f144a9c837";

    
    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));

        get("/", (request, response) -> {

            // Create a rest client
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Get the main account (The one we used to authenticate the client)
            Account mainAccount = client.getAccount();

            // Get all accounts including sub accounts
            AccountList accountList = client.getAccounts();

            // All lists implement an iterable interface, you can use the foreach
            // syntax on them
            for (Account a : accountList) {
                System.out.println(a.getFriendlyName());
            }

            // Send an sms (using the new messages endpoint)
            MessageFactory messageFactory = mainAccount.getMessageFactory();
            List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
            messageParams.add(new BasicNameValuePair("To", "+17188665655")); // Replace with a valid phone number
            messageParams.add(new BasicNameValuePair("From", "+12039893442")); // Replace with a valid phone
            // number in your account
            messageParams.add(new BasicNameValuePair("Body", "Hello from CS6432015 from Indrani Mohankumar!"));
            messageFactory.create(messageParams);

            return "Hello From Aish!";
        });
    }
}
