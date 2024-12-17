package org.neuefische.spring_rest_client.Controller;

import org.neuefische.spring_rest_client.Service.RAndMortyService;
import org.neuefische.spring_rest_client.model.RAMCharacter;
import org.neuefische.spring_rest_client.model.RickAndMortyResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rick")

public class RAMController {
    RAndMortyService rAndMortyService;
    public RAMController(RAndMortyService rAndMortyService) {
        this.rAndMortyService = rAndMortyService;
    }
    @GetMapping("/chars")
    List<RAMCharacter> getAllChracters() {
        return rAndMortyService.getAllCharacters();
    }
    @GetMapping("/chars/{id}")
    RAMCharacter getCharacterById(@PathVariable int id) {
        return rAndMortyService.getCharacter(id);
    }
    @GetMapping("/charsByStatus/{status}")
    List<RAMCharacter> getCharactersByStatus(@PathVariable String status) {
        return  rAndMortyService.getAllCharactersByStatus(status);
    }
    @GetMapping("/species-statistic")
    int getNumberOfCharactersBySpecies(@RequestParam String species) {
        return rAndMortyService.getNumberOfCharactersBySpecies(species);
    }


}
