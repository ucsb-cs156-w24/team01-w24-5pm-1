package edu.ucsb.cs156.spring.backenddemo.controllers;
import edu.ucsb.cs156.spring.backenddemo.controllers.UniversityController;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
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

@Tag(name="University Information")
@Slf4j
@RestController
@RequestMapping("/api/university") 
public class UniversityController {

    @Autowired
    private UniversityQueryService universityQueryService;

    @Operation(summary = "Get list of universities that match a given name", description = "Uses API documented here: http://universities.hipolabs.com/search")
    @GetMapping("/search") 
    public ResponseEntity<String> getUniversitiesByName(
        @Parameter(name="name", description="The name of the university to search for", example="Harvard") 
        @RequestParam String name
    ) throws JsonProcessingException {
        log.info("Fetching universities with name: {}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }

}
