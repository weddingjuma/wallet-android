package com.mycelium.wallet.glidera.api.response;

public class VerifyPictureIdResponse extends GlideraResponse {
    private State userPictureIdState;

    public State getUserPictureIdState() {
        return userPictureIdState;
    }

    public void setUserPictureIdState(State userPictureIdState) {
        this.userPictureIdState = userPictureIdState;
    }
}
