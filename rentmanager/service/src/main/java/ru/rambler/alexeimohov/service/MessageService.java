package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class MessageService implements IMessageService {

    private MessageDao messageDao;
    private MessageMapper messageMapper;

    public MessageService(MessageDao messageDao, MessageMapper messageMapper) {
        this.messageDao = messageDao;
        this.messageMapper = messageMapper;
    }

    @Override
    public void saveOrUpdate(MessageDto dto) {
        Message message = messageMapper.fromDto( dto );
        if(messageDao.findById( message.getId() )==null){
            messageDao.save( message );
        }
        else messageDao.update( message );
    }

    @Override
    public MessageDto getById(Long id) {
        return messageMapper.toDto( messageDao.findById( id ) );
    }

    @Override
    public List <MessageDto> getAll() {
        return messageMapper.listToDto( messageDao.findAll() );
    }

    @Override
    public void remove(Long id) {
          messageDao.remove( id );
    }
}
