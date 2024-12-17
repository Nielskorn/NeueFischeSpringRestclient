package org.neuefische.spring_rest_client.Service;

import org.neuefische.spring_rest_client.model.RAMCharacter;
import org.neuefische.spring_rest_client.model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RAndMortyService {
    private final RestClient restClient;
    public RAndMortyService(RestClient.Builder restBuilder) {
        this.restClient = restBuilder.baseUrl("https://rickandmortyapi.com/api").build();
    }
    public List<RAMCharacter> getAllCharacters(){
        return restClient.get().uri("/character").retrieve().body(RickAndMortyResponse.class).results();
    }
    public RAMCharacter getCharacter(int characterId){
        return restClient.get().uri("/character/"+characterId).retrieve().body(RAMCharacter.class);
    }
    public List<RAMCharacter>getAllCharactersByStatus(String status){
        return restClient.get().uri("/character/?status="+status).retrieve().body(RickAndMortyResponse.class).results();
    }
    public int getNumberOfCharactersBySpecies(String species){
        return restClient.get().uri("/character/?species="+species).retrieve().body(RickAndMortyResponse.class).info().count();
    }
}
