package com.syscal.apisyscal.service;

import com.syscal.apisyscal.service.UserService;
import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.EmailCodeEntity;
import com.syscal.apisyscal.model.entity.UserEntity;
import com.syscal.apisyscal.repository.EmailCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Autowired
    private EmailCodeRepository emailCodeRepository;

    @Autowired
    private UserService userService;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public EmailCodeEntity getOne(Integer id) {
        Optional<EmailCodeEntity> EmailCode = emailCodeRepository.findByUserId(id);
        if (!EmailCode.isPresent()) {
            throw new BusinessException("not Exist an code for this user","404", HttpStatus.NOT_FOUND);
        }
        return EmailCode.get();
    }

    @Override
    public EmailCodeEntity getOneByUsername(String username) {
        UserEntity user = userService.getUserByUsernameOrEmail(username);
        EmailCodeEntity EmailCOde = getOne(user.getId());
        return EmailCOde;
    }

    @Override
    public EmailCodeEntity save(EmailCodeEntity body) {
        try {
            return emailCodeRepository.save(body);
        } catch (Exception ex) {
            throw new BusinessException(ex.getCause().getCause().getMessage(), "500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmailCodeEntity update(Integer id, EmailCodeEntity body) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
