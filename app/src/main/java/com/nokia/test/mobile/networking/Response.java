package com.nokia.test.mobile.networking;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Response(String status) {
        this.status = status;
    }
}
