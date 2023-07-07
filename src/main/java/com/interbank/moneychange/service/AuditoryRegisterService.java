package com.interbank.moneychange.service;

import com.interbank.moneychange.entity.Auditory;
import com.interbank.moneychange.repository.AuditoryRegisterRepository;
import com.interbank.moneychange.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditoryRegisterService {

    @Setter
    private String token;
    private final AuditoryRegisterRepository auditoryRegisterRepository;

    public void saveAuditory(String error, String msg, boolean success) {
        Auditory auditory = new Auditory();
        auditory.setUser_(SecurityUtils.decryptToken(this.token));
        auditory.setError(error);
        auditory.setMessage(msg);
        auditory.setSuccess(success);
        this.auditoryRegisterRepository.save(auditory);
    }
}
