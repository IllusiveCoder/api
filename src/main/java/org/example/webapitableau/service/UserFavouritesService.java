package org.example.webapitableau.service;

import org.example.webapitableau.Mapper.RecipeMapper;
import org.example.webapitableau.models.*;
import org.example.webapitableau.repository.RecipeRepository;
//import org.example.webapitableau.repository.UserFavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class UserFavouritesService {


    public HashMap<String, List<Integer>> hashMap = new HashMap<>();
    @Autowired
    private RecipeRepository recipeRepository;

//    @Autowired
//    private UserFavouritesRepository userFavouritesRepository;

    @Autowired
    private RecipeMapper recipeMapper;
    @Autowired
    private RecipeService recipeService;

    public PaginatedFavourites deletefavourite(String uid, Integer recipeid) {
        List<Integer> favourites = hashMap.get(uid);
        favourites.remove(recipeid);
        hashMap.put(uid, favourites);

        return null;
    }

    public PaginatedFavourites getfavourites(String uid) {

        List<Integer> favourites = hashMap.get(uid);
        List<Recipe> recipeList = new ArrayList<>();
        for (Integer favourite : favourites) {
            recipeList.add(recipeService.getRecipe(favourite));
        }
        List<RecipeShort> List = RecipeMapper.mapRecipesToRecipeShorts(recipeList);
        PaginatedFavourites pf = new PaginatedFavourites();
        pf.setContent(List);
        return  pf;
    }

    public PaginatedFavourites postfavourites(String uid, Integer recipeid) {
        if (!hashMap.containsKey(uid)) {
            List<Integer> favourites = new ArrayList<>();
            favourites.add(recipeid);
            hashMap.put(uid, favourites);
        }else{
            List<Integer> favourites = hashMap.get(uid);
            favourites.add(recipeid);
            hashMap.put(uid, favourites);
        }
        return null;
    }
}
