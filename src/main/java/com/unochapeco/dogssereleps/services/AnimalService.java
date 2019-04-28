package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.models.User;

import java.util.List;

public interface AnimalService {
    public Animal save(Animal animal);
    public List<Animal> findByUser(User user);
}
