package com.podio.contact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactCreateResponse {

        private long id;

        public long getId() {
                return id;
        }

        @JsonProperty("profile_id")
        public void setId(long id) {
                this.id = id;
        }

}
