package ru.job4j.passport.management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.passport.management.model.Owner;
import ru.job4j.passport.management.repository.OwnerRepository;

import java.util.NoSuchElementException;

@Service
@Transactional
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findById(long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Owner with id: " + id + " not found.")
        );
    }

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateData(String firstName, String secondName, long id) {
        ownerRepository.updateData(firstName, secondName, id);
    }
}
