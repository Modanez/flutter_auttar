package com.modanez.pdx.pdx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import br.com.auttar.mobile.libctfclient.sdk.AuttarConfiguration;
import br.com.auttar.mobile.libctfclient.sdk.AuttarPermissionType;
import br.com.auttar.mobile.libctfclient.sdk.AuttarSDK;
import br.com.auttar.mobile.libctfclient.sdk.LibCTFClient;

public class Auttar {
    private AuttarSDK auttarSDK;
    private AuttarConfiguration configuration;
    private Context ctx;


    public void Init(Context ctx, Context appContext) {
        this.auttarSDK = new AuttarSDK(appContext);
        this.configuration = this.auttarSDK.getConfiguration();

        this.configuration.setCancellationPermissionType(AuttarPermissionType.permited);
        this.ctx = ctx;
    }

    public void Login(Activity act) {

        try {
            Intent loginIntent = this.auttarSDK.createDefaultLoginIntent();

            act.startActivity(loginIntent);
        } catch (Exception error) {
            error.printStackTrace();
        }

        //darumaMobileUtil.fnInicializarDmf(ctx, "teste" );
    }

    public void CreditPayment(Activity act, double amount, int installments) {
        int code = 113;
        LibCTFClient libCTFClient = new LibCTFClient(act);
        LibCTFClient.IntentBuilder builder = LibCTFClient.IntentBuilder.from(code);
        builder.setAmount(BigDecimal.valueOf(amount));
        builder.setInstallments(installments);
        libCTFClient.executeTransaction(builder, code);
    }

    public void CreditPayment(int code, Activity act, double amount, int installments) {

        LibCTFClient libCTFClient = new LibCTFClient(act);
        LibCTFClient.IntentBuilder builder = LibCTFClient.IntentBuilder.from(code);
        builder.setAmount(BigDecimal.valueOf(amount));
        builder.setInstallments(installments);
        libCTFClient.executeTransaction(builder, code);
    }

    public void DebitPayment(Activity act, double amount) {
        int code = 101;
        LibCTFClient libCTFClient = new LibCTFClient(act);
        LibCTFClient.IntentBuilder builder = LibCTFClient.IntentBuilder.from(code);
        builder.setAmount(BigDecimal.valueOf(amount));
        libCTFClient.executeTransaction(builder, code);
    }

    public void DebitCancel(Activity act, int nsu, Date dar_inc) {
        int code = 128;
        LibCTFClient libCTFClient = new LibCTFClient(act);
        LibCTFClient.IntentBuilder builder = LibCTFClient.IntentBuilder.from(code);
        //builder.setAmount(BigDecimal.valueOf(amount));
        builder.setNsuCTF(nsu);
        builder.setDateOfOriginalTransation(dar_inc);
        libCTFClient.executeTransaction(builder, code);
    }

    public void Configure(Activity act) {
        Intent config = this.configuration.createDefaultIntent();
        act.startActivity(config);
    }


}
