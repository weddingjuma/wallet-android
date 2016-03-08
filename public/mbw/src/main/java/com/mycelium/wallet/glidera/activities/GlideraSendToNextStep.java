package com.mycelium.wallet.glidera.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.mycelium.wallet.Utils;
import com.mycelium.wallet.glidera.api.GlideraService;
import com.mycelium.wallet.glidera.api.response.StatusResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class GlideraSendToNextStep extends Activity {
    private GlideraService glideraService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String uriString = getIntent().getStringExtra("uri");

        Uri uri = Uri.parse(uriString);

        if (uri.getQueryParameter("status").equals("SUCCESS")) {
            Log.i("Glidera", "GlideraSendToNextStep, uri = " + uri);
            glideraService = GlideraService.getInstance();
            glideraService.status()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<StatusResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            handleError();
                        }

                        @Override
                        public void onNext(StatusResponse statusResponse) {
                            if (statusResponse.isUserCanTransact()) {
                                //Send to buy
                                Log.i("Glidera", "Bitid account found, setup complete");
                                Intent intent = new Intent(GlideraSendToNextStep.this, GlideraMainActivity.class);
                                startActivity(intent);
                                finish();
                                return;
                            } else {
                                //Send to setup
                                String uri = glideraService.getSetupUrl();
                                Log.i("Glidera", "Bitid account found, setup incomplete");
                                Log.i("Glidera", "redirect to " + uri);
                                Utils.openWebsite(GlideraSendToNextStep.this, uri);
                            }
                        }
                    });
        } else {
            handleError();
        }
    }

    private void handleError() {
        //TODO go to activity where they clicked glidera and toast an error
    }
}
