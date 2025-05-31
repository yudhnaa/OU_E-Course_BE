package com.ou.dto;

public class StripeResponseDto {
    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;

    public StripeResponseDto() {}

    private StripeResponseDto(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.sessionId = builder.sessionId;
        this.sessionUrl = builder.sessionUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    public static class Builder {
        private String status;
        private String message;
        private String sessionId;
        private String sessionUrl;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder sessionUrl(String sessionUrl) {
            this.sessionUrl = sessionUrl;
            return this;
        }

        public StripeResponseDto build() {
            return new StripeResponseDto(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
