package com.unicorns.apis;


import io.restassured.response.Response;

import static com.unicorns.properties_reading.ReadPropertiesFile.loadProperties;

public class BaseAPI {

    public String BASE_URI;


    public BaseAPI(){
        loadProperties();
        BASE_URI = System.getProperty("BASE_URI");
    }

    protected enum Status {
        SUCCESS(200), SUCCESS_CREATE(201);

        private int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public String getResponseStatusCode(Response response){
        return Integer.toString(response.getStatusCode());
    }
}
