package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name="Public Holiday info from Nager.Date API")
@RestController
@RequestMapping("/api/publicholidays")
public class PublicHolidaysController {
    
    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary = "Get public holidays for a given country and year", description = "JSON return format documented at https://date.nager.at/Api")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="countryCode", description="Country Code", example="US") @RequestParam String countryCode,
        @Parameter(name="year", description="Year", example="2024") @RequestParam String year
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={}, countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }

}
