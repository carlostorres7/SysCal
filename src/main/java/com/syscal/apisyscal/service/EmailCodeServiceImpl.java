package com.syscal.apisyscal.service;

import com.syscal.apisyscal.exception.BusinessException;
import com.syscal.apisyscal.model.entity.EmailCodeEntity;
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

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public EmailCodeEntity getOne(Integer id) {
        List<EmailCodeEntity> EmailCode = emailCodeRepository.findByUserId(id);
        return EmailCode.get(0);
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
