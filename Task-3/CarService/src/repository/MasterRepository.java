package repository

import entities.Master;

public class  MasterRepository implements Repository<Master>{
    private Master[] masters = new Master[ 100 ];

    @Override
    public Master findById(int id) {
         return masters[ id ];
    }

    @Override
    public Master[] findAll() {
        return masters;
    }

    @Override
    public void delete(int id) {
      masters[ id ] = null;

    }

    @Override
    public void save(Master master) {

        if(masters[master.getId()]==null) {
            masters[master.getId()] = master;
        }
    }




}
