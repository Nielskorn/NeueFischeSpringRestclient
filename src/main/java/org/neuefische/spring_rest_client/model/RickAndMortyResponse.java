package org.neuefische.spring_rest_client.model;

import java.util.List;

public record RickAndMortyResponse(RAMInfo info,List<RAMCharacter>results) {
}
