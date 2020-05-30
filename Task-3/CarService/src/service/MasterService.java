package service;

import dao.MasterRepository;
import entities.Master;
import entities.Speciality;

public class MasterService {
    MasterRepository repository = new MasterRepository();


    public Master findByName(String name) {
        for (Master master : repository.findAll()) {
            if (master != null && master.getFullName() == name) return master;

        }

        return null;
    }


    public Master addMaster(String name, Speciality speciality) {
        Master master = new Master( speciality, name );
        for (int i = 0; i < repository.findAll().length; i++) {
            if (repository.findAll()[ i ] == null) {
                master.setId( i );
                repository.findAll()[ i ] = master;
                repository.save( master );
                return master;
            }
        }
        return null;
    }

    public void removeMaster(int id) {
        repository.findAll()[ id ] = null;
        repository.delete( id );
    }

}
