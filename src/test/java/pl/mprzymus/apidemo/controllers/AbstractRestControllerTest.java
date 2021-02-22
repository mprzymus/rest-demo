package pl.mprzymus.apidemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestControllerTest {
    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
