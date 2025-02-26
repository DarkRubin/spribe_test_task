package com.example.spribe_test_task.controller;

import com.example.spribe_test_task.config.TestDatasourceConfig;
import com.example.spribe_test_task.dto.UnitDto;
import com.example.spribe_test_task.dto.UnitPropertiesDto;
import com.example.spribe_test_task.entity.AccommodationType;
import com.example.spribe_test_task.entity.Unit;
import com.example.spribe_test_task.entity.UnitProperties;
import com.example.spribe_test_task.repository.UnitPropertiesRepository;
import com.example.spribe_test_task.repository.UnitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TestDatasourceConfig.class)
@AutoConfigureMockMvc
class UnitControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private UnitPropertiesRepository unitPropertiesRepository;

    @BeforeEach
    void setup() {
        unitRepository.deleteAll();
    }

    @Test
    void testGetAllUnits() throws Exception {
        Unit unit1 = new Unit("Unit 1", new BigDecimal("1000"));
        Unit unit2 = new Unit("Unit 2", new BigDecimal("2000"));
        unitRepository.saveAll(List.of(unit1, unit2));
        UnitProperties properties1 = new UnitProperties(2, 3, AccommodationType.FLAT);
        UnitProperties properties2 = new UnitProperties(3, 5, AccommodationType.HOME);
        unit1.setProperties(properties1);
        unit2.setProperties(properties2);
        properties1.setUnit(unit1);
        properties2.setUnit(unit2);
        unitPropertiesRepository.saveAll(List.of(properties1, properties2));



        mockMvc.perform(get("/v1/units").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testCreateUnit() throws Exception {
        UnitPropertiesDto properties = new UnitPropertiesDto(3, 4, AccommodationType.APARTMENTS);
        UnitDto unitDto = new UnitDto("New Unit", properties, new BigDecimal("1500"));

        mockMvc.perform(post("/v1/units")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(unitDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("New Unit"));
    }

    @Test
    void testFindByPriceRange() throws Exception {
        Unit unit1 = new Unit("Unit A", new BigDecimal("800"));
        Unit unit2 = new Unit("Unit B", new BigDecimal("1200"));
        unitRepository.saveAll(List.of(unit1, unit2));
        UnitProperties properties1 = new UnitProperties(2, 3, AccommodationType.FLAT);
        UnitProperties properties2 = new UnitProperties(3, 1, AccommodationType.HOME);
        unit1.setProperties(properties1);
        unit2.setProperties(properties2);
        properties1.setUnit(unit1);
        properties2.setUnit(unit2);
        unitPropertiesRepository.saveAll(List.of(properties1, properties2));

        mockMvc.perform(get("/v1/units/price").param("minPrice", "700").param("maxPrice", "1300"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testFindByAccommodationType() throws Exception {
        Unit unit = new Unit("Apartment Unit", new BigDecimal("1600"));
        unitRepository.save(unit);
        UnitProperties unitProperties = new UnitProperties(3, 2, AccommodationType.APARTMENTS);
        unit.setProperties(unitProperties);
        unitProperties.setUnit(unit);
        unitPropertiesRepository.save(unitProperties);

        mockMvc.perform(get("/v1/units/type/{accommodationType}", "apartment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accommodationType").value("apartment"));
    }

    @Test
    void testFindByFloor() throws Exception {
        Unit unit = new Unit("Floor 5 Unit", new BigDecimal("1400"));
        unitRepository.save(unit);
        UnitProperties properties = new UnitProperties(2, 5, AccommodationType.FLAT);
        unit.setProperties(properties);
        properties.setUnit(unit);
        unitPropertiesRepository.save(properties);

        mockMvc.perform(get("/v1/units/floor/{floor}", 5))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].floor").value(5));
    }

    @Test
    void testFindByNumberOfRooms() throws Exception {
        Unit unit = new Unit("3-Room Unit", new BigDecimal("1700"));
        unitRepository.save(unit);
        UnitProperties properties = new UnitProperties(3, 1, AccommodationType.HOME);
        unit.setProperties(properties);
        properties.setUnit(unit);
        unitPropertiesRepository.save(properties);


        mockMvc.perform(get("/v1/units/rooms/{rooms}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numberOfRooms").value(3));
    }
}

