package org.neuefische.spring_rest_client.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class RAMControllerTest {
@Autowired
private MockMvc mockMvc;
@Autowired
private MockRestServiceServer mockServer;
    @Test
    void getAllChracters() throws Exception {
        mockServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                        {
                          "info": {
                            "count":826,
                            "pages":42,
                            "next":"",
                            "prev":null
                          },
                          "results": [
                            {
                                "id": 1,
                                "name": "Rick Sanchez",
                                "status": "Alive",
                                "species": "Human",
                                "gender": "Male"
                            }
                          ]
                        
                        }
                        """,
                        MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/rick/chars"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                """
                        [
                             {
                                 "id": "1",
                                 "name": "Rick Sanchez",
                               //  "status": "Alive",
                                 "species": "Human"
                             }
                        ]
                        """
        ));
    }

    @Test
    void getCharacterById() throws Exception {
        mockServer.expect(requestTo("https://rickandmortyapi.com/api/character/229"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                       { "id":229,
                        "name":"Morty Mart Manager Morty",
                        "status":"Alive",
                        "species":"Human",
                        "type":"",
                        "gender":"Male"}
                        """,MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/rick/chars/229"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().json(
"""

        {
           "id":"229",
           "name":"Morty Mart Manager Morty", 
           "species":"Human"    
        }
          """
                ));
    }
}