package org.example.webapitableau.service;

import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.models.*;
import org.example.webapitableau.repository.RecipeRepository;
//import org.example.webapitableau.repository.UserFavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFavouritesService {

    @Autowired
    private RecipeRepository recipeRepository;

//    @Autowired
//    private UserFavouritesRepository userFavouritesRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public PaginatedFavourites deletefavourite(String uid, Integer recipeid) {
        return null;
    }

    public PaginatedFavourites getfavourites(String uid) {
//        String favs = userFavouritesRepository.findByUid(uid);
//        String[] favsList = favs.split(",");
//        List<String> ids = Arrays.asList(favsList);
//        List<Integer> idsInt = ids.stream().map(Integer::parseInt).collect(Collectors.toList());
//        List<RecipeEntity> recipeEntityList = recipeRepository.findAllById(idsInt);
//        List<RecipeShort> recipeShortList = RecipeMapper.mapRecipeEntitiesToRecipeShorts(recipeEntityList);
//        PaginatedFavourites paginatedFavourites = new PaginatedFavourites();
//        paginatedFavourites.setContent(recipeShortList);

        return  null;
    }

    public PaginatedFavourites postfavourites(String uid, Integer recipeid) {
//        List<UserFavouriteEntity> userFavouriteEntities = userFavouritesRepository.findBy(uid);
//        UserFavouriteEntity userFavouriteEntity = userFavouriteEntities.get(0);
//        String newfavs = userFavouriteEntity.getUid();
//        if(newfavs.isEmpty()){
//            newfavs = recipeid.toString() + ",";
//        }
//        else{
//            newfavs = recipeid.toString() + ",";
//        }
//        userFavouriteEntity.setUid(newfavs);
//        userFavouritesRepository.save(userFavouriteEntity);
        return null;
    }
}
