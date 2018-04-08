package com.vm.heroes.service;

import com.vm.heroes.config.StatusCodeException;
import com.vm.heroes.model.Hero;
import com.vm.heroes.utils.CustomStatusCodes;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class HeroesService {

    private List<Hero> heroesList = new ArrayList<>();

    public HeroesService() {
        buildHeroes();
    }

    public Hero getHeroById(int id) {

        return getHeroesList().stream().filter(
                hero -> hero.getId() == id
        ).findFirst().orElse(null);
    }

    public List<Hero> getHeroesFilteredByName(String name) {
        ArrayList<Hero> heroesFilteredList = new ArrayList<>();

        for (Hero heroFiltered : getHeroesList()) {
            if (heroFiltered.getName().toLowerCase().contains(name.toLowerCase())){
                heroesFilteredList.add(heroFiltered);
            }
        }
        return heroesFilteredList;
    }

    public Hero createHero(Hero hero) {
        int id = getHeroesList().get(getHeroesList().size()-1).getId() + 1;
        hero.setId(id);

        getHeroesList().add(hero);
        return hero;
    }

    public Hero updateHero(Hero hero) {
        int index = -1;

        for (Hero heroFilter : getHeroesList()) {
            if (heroFilter.getId() == hero.getId()) {
                index = getHeroesList().indexOf(heroFilter);
                break;
            }
        }

        if (index == -1) {
            throw new StatusCodeException(HttpStatus.NOT_FOUND, CustomStatusCodes.HERO_NOT_FOUND);
        }

        getHeroesList().get(index).setName(hero.getName());

        return hero;
    }

    public Hero deleteHero(int id) {
        Hero hero = getHeroesList().stream().filter(
                heroToFind -> heroToFind.getId() == id
        ).findFirst().orElse(null);

        if (hero == null) {
            throw new StatusCodeException(HttpStatus.NOT_FOUND, CustomStatusCodes.HERO_NOT_FOUND);
        }

        getHeroesList().remove(hero);

        return hero;
    }

    private void buildHeroes() {
        heroesList.addAll(Arrays.asList(
                new Hero(1, "Victor"),
                new Hero(2, "Dubraska"),
                new Hero(3, "Luis"),
                new Hero(4, "Juan"),
                new Hero(5, "Laura"),
                new Hero(6, "Simon")
        ));
    }

}
