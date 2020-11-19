package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService implements IUserService {

    private UserDao userDao;

    private UserMapper userMapper;

    public UserService(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto( userDao.findById( id ) );
    }

    @Override
    public void remove(Long id) {
         userDao.remove( id );
    }

    @Override
    public List <UserDto> getAll() {
        return userMapper.listToDto( userDao.findAll() );
    }

    @Override
    public UserDto getByUserName(String userName) {
        return userMapper.toDto( userDao.findByUserName( userName ) );
    }

    @Override
    public void saveOrUpdate(UserDto dto) {
      User user = userMapper.fromDto( dto );
      if(userDao.findById( user.getId() )==null){
          userDao.save( user );
      }
      else{
          userDao.update( user );
      }
    }
}
