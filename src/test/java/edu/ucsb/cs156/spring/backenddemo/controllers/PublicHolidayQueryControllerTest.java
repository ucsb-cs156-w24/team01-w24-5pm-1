package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

@WebMvcTest(value = PublicHolidaysController.class)
public class PublicHolidayQueryControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PublicHolidayQueryService mockPublicHolidayQueryService;

    @Test
    public void test_getPublicHolidays() throws Exception {
        String fakeJsonResult = "{ \"fake\" : \"result\" }";
        String countryCode = "US";
        String year = "2024";
        when(mockPublicHolidayQueryService.getJSON(eq(year), eq(countryCode))).thenReturn(fakeJsonResult);

        String url = String.format("/api/publicholidays/get?countryCode=%s&year=%s", countryCode, year);

        MvcResult response = mockMvc
            .perform(get(url).contentType("application/json"))
            .andExpect(status().isOk()).andReturn();

        String responseString = response.getResponse().getContentAsString();

        assertEquals(fakeJsonResult, responseString);
    }
}
