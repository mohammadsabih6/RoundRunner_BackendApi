package com.example.exam;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import com.example.exam.Controller.RentalController;
import com.example.exam.Model.Cars;
import com.example.exam.Model.RentalFrom;
import com.example.exam.Repo.CarsRepo;
import com.example.exam.Repo.RentalFormRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
public class RentalFormTest {
    private MockMvc mvc,mvc1;
    @InjectMocks
    private RentalController rentalcontroller;
    @Mock RentalFormRepo repo;
    private JacksonTester<RentalFrom> jsonrental;
    // private JacksonTester<Review> jsonreview;
    @Autowired
    private WebApplicationContext context;
    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mvc1=MockMvcBuilders.standaloneSetup(rentalcontroller).build();
    }
    @Test
    public void getAllRental() throws Exception 
    {
        RentalFrom rental1 = new RentalFrom(1L, "Sabih", "Sea View", "0306232681","4154-56",null , null, 2155.4, 45544, 2321);
        RentalFrom rental2 = new RentalFrom(2L, "Kamran", "Sea View", "0306232681","12-545-5",null, null , 14564.4,2541.,1258);
        RentalFrom rental3 = new RentalFrom(3L, "Atia", "Sea View", "0306232681","125-44-52",null, null, 2413.321,32132,35540);
        ArrayList<RentalFrom> rentals = new ArrayList<>();
        rentals.add(rental1);
        rentals.add(rental2);
        rentals.add(rental3);
        when(repo.findAll()).thenReturn(rentals);
        mvc.perform(get("/rental/get")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void postAllRental() throws Exception 
    {
        RentalFrom rental1 = new RentalFrom(1L, "Sabih", "Sea View", "0306232681","4154-56",null , null, 2155.4, 45544, 2321);
        RentalFrom rental2 = new RentalFrom(2L, "Kamran", "Sea View", "0306232681","12-545-5",null, null , 14564.4,2541.,1258);
        RentalFrom rental3 = new RentalFrom(3L, "Atia", "Sea View", "0306232681","125-44-52",null, null, 2413.321,32132,35540);
        ArrayList<RentalFrom> rentals = new ArrayList<>();
        rentals.add(rental1);
        rentals.add(rental2);
        rentals.add(rental3);
        when(repo.findAll()).thenReturn(rentals);
        mvc.perform(MockMvcRequestBuilders.post("/rental/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonrental.write(rental1).getJson())
                .content(jsonrental.write(rental2).getJson())
                .content(jsonrental.write(rental3).getJson()))
				.andExpect(status().isOk());
    }
}
