package com.example.ensayopruebabg2.data.source.local.temp;

import com.example.ensayopruebabg2.data.entity.PokemonResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImgService {

    @GET("pokemon/{id}")
    Call<PokemonResponse> getPosts(
            @Path(value = "id", encoded = true) String id
    );

}
