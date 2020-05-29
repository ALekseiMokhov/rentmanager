package service;

import dao.MasterRepository;
import entities.Master;
import entities.Speciality;

public class MasterService {
    MasterRepository repository = new MasterRepository();

    Master[] masters = (Master[]) repository.findAll();


    public Master findByName(String name) {
        for (Master master : masters) {
            if (master != null && master.getFullName() == name) return master;

        }

        return null;
    }


    public Master addMaster(String name, Speciality speciality) {
        Master master = new Master( speciality, name );
        for (int i = 0; i < masters.length; i++) {
            if (masters[ i ] == null) {
                master.setId( i );
                masters[ i ] = master;
                repository.save( master );
                return master;
            }
        }
        return null;
    }

    public void removeMaster(int id) {
        masters[ id ] = null;
        repository.delete( id );
    }

}
