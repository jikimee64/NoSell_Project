package com.soap.moon.domains.category.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("local")
@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
class CategoryControllerTest {

    private static final String CHAMPION_API_URL = "/api/v1/champion";

}