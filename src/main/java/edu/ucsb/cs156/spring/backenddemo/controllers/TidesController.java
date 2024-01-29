package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Tides info from NOAA")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary = "Get tides for a certain station", description = "JSON return format documented here: https://api.tidesandcurrents.noaa.gov/api/prod/")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @Parameter(name="beginDate", description="begin date", example="01-03") @RequestParam String beginDate,
        @Parameter(name="endDate", description="end date", example="01-24") @RequestParam String endDate,
        @Parameter(name="station", description="station id", example="cool") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides: begin_date={} end_date={} station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }
}
