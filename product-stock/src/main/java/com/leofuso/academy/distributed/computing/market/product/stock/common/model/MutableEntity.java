package com.leofuso.academy.distributed.computing.market.product.stock.common.model;

import java.time.Instant;

/**
 * <p>
 * Not all entities always remain with the same properties, some are mutable.
 * </p>
 */
public interface MutableEntity extends Entity {

    /**
     * @return the {@link Instant} when the last update was made in this {@link Entity}
     */
    Instant getLastModifiedDate();

    /**
     * Every time an update is performed in this {@link Entity} its version change.
     *
     * @return the current version of this {@link Entity}.
     */
    Long getVersion();

}