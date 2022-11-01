package com.dragonlayout.khadgar.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    private Optional<Long> currentUserId = Optional.of(111L);

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<Long> getCurrentAuditor() {
        return this.currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = Optional.of(currentUserId);
    }
}
