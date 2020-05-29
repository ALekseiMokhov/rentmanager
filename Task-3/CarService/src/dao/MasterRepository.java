package dao;

import entities.Master;

public class  MasterRepository implements Dao{
    private Master[] masters = new Master[ 100 ];

    @Override
    public Object findById(int id) {
         return masters[ id ];
    }

    @Override
    public Object[] findAll() {
        return masters;
    }

    @Override
    public void delete(int id) {
      masters[ id ] = null;

    }

    @Override
    public void save(Object o) {
        if(o instanceof Master ==false)   {
            throw new IllegalArgumentException( "Wrong object type!" )  ;
        }
        if(masters[((Master) o).getId()]==null) {
            masters[((Master) o).getId()] = (Master) o;
        }
    }




}
