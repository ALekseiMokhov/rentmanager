package main.service;

import main.entities.master.Master;
import main.repository.MasterRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MasterService {
    private final MasterRepository repository = new MasterRepository();


    public Master findByNameAndSpeciality(String name, Class clazz) {
        for (Master master : this.repository.findAll()) {
            if (master.getFullName() == name && master.getClass() == clazz) return master;

        }
        throw new NoSuchElementException( "There is no Master with that name & skills!" );
    }


    public void saveMaster(Master master) {
        this.repository.save( master );

    }

    public void removeMaster(UUID id) {
        this.repository.delete( id );
    }

    public List <Master> getMastersByAlphabet() {
        Comparator <Master> comparator = Comparator.comparing( m -> m.getFullName() );
        Collections.sort( repository.findAll(), comparator );
        return this.repository.findAll();
    }

    public List <Master> getFreeMasters(LocalDate date) {
        return this.repository.findAll().stream().filter( (m) -> m.isFreeForDate( date ) ).collect( Collectors.toList() );
    }

    public List <Master> getMastersBySpeciality(Class <? extends Master> clazz) {
        return this.repository.findAll().stream().filter( ((m) -> m.getClass() == clazz) ).collect( Collectors.toList() );
    }

    public void bookMaster(Master master, LocalDate date) {
        master.bookMaster( date );
        this.repository.save( master );
    }

    public void unBookMaster(Master master, LocalDate date) {
        master.unBookMaster( date );
        this.repository.save( master );
    }


}

