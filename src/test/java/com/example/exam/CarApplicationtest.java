package com.example.exam;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.exam.Controller.CarControlller;
import com.example.exam.Model.Cars;
import com.example.exam.Repo.CarsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class CarApplicationtest {
    private MockMvc mvc,mvc1;
    @InjectMocks
    private CarControlller carcontroller;
    @Mock CarsRepo repo;
    // @Mock IReviewerRepository repo1;
    // private JacksonTester<Reviewer> jsonReviewer;
    private JacksonTester<Cars> jsoncars;
    // private JacksonTester<Review> jsonreview;
    @Autowired
    private WebApplicationContext context;
    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc1=MockMvcBuilders.standaloneSetup(carcontroller).build();
    }
    @Test
    public void getAllCars() throws Exception 
    {
        Cars cars1=new Cars(1L, "Alto", "very good", "not working", "wwww.image.com", 2165);
        Cars cars2=new Cars(1L, "civic", "i am working fine", "I am not working fine", "wwww.image.com", 26541);
        Cars cars3=new Cars(1L, "Mira", "Good work", "I am not working fine", "wwww.google.com", 2000);
        List<Cars> cars = new ArrayList<>();
        cars.add(cars1);
        cars.add(cars2);
        cars.add(cars3);
        when(repo.findAll()).thenReturn(cars);
        mvc.perform(get("/cars/get")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void postAllCars() throws Exception 
    {
        Cars cars1=new Cars(1L, "Alto", "very good", "not working", "wwww.image.com", 2165);
        Cars cars2=new Cars(1L, "civic", "i am working fine", "I am not working fine", "wwww.image.com", 26541);
        Cars cars3=new Cars(1L, "Mira", "Good work", "I am not working fine", "wwww.google.com", 2000);
        List<Cars> cars = new ArrayList<>();
        cars.add(cars1);
        cars.add(cars2);
        cars.add(cars3);
        when(repo.findAll()).thenReturn(cars);
        mvc.perform(MockMvcRequestBuilders.post("/cars/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsoncars.write(cars1).getJson())
                .content(jsoncars.write(cars2).getJson())
                .content(jsoncars.write(cars3).getJson()))
				.andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
       Cars cars1=new Cars(1L, "Alto", "very good", "not working", "wwww.image.com", 2165);
        when(repo.findById(1L)).thenReturn(cars1.getCars());
        mvc.perform(get("/cars/get/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());    
    }

}
