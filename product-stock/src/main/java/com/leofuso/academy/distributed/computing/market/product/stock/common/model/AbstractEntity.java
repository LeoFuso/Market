package com.leofuso.academy.distributed.computing.market.product.stock.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.time.Instant;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;

import com.leofuso.academy.distributed.computing.market.product.stock.common.annotation.JPACompliantConstructor;

@MappedSuperclass
public class AbstractEntity implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private Instant createdDate;

    @JPACompliantConstructor
    protected AbstractEntity() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractEntity that = (AbstractEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}