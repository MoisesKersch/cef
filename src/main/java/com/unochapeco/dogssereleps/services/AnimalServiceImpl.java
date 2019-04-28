package com.unochapeco.dogssereleps.services;

import com.unochapeco.dogssereleps.models.Animal;
import com.unochapeco.dogssereleps.models.User;
import com.unochapeco.dogssereleps.repositories.AnimalRepository;
import com.unochapeco.dogssereleps.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    private UserRepository userRepository;

    private UserService userService;


    AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal save(Animal animal) {
        try {
            return animalRepository.save(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Animal> findByUser(User user) {
        try {
            return this.animalRepository.findByUser(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
