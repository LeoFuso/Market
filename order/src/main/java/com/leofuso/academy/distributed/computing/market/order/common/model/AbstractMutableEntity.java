package com.leofuso.academy.distributed.computing.market.order.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import java.time.Instant;

import org.springframework.data.annotation.LastModifiedDate;

import com.leofuso.academy.distributed.computing.market.order.common.annotation.JPACompliantConstructor;

@MappedSuperclass
public abstract class AbstractMutableEntity extends AbstractEntity implements MutableEntity {

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedDate;

    @Version
    @Column(nullable = false)
    private Long version;

    @JPACompliantConstructor
    protected AbstractMutableEntity() {}

    @Override
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public Long getVersion() {
        return version;
    }

}