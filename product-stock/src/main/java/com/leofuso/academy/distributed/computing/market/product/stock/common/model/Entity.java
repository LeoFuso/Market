package com.leofuso.academy.distributed.computing.market.product.stock.common.model;

import java.io.Serializable;
import java.time.Instant;

/**
 * Primarily used for database mapping, although an Id is extremely useful in many cases, the other
 * attributes are not as widely used, and are for identification purposes only.
 */
public interface Entity extends Serializable {

    /**
     * @return the unique identifier of this {@link Entity}
     */
    Long getId();

    /**
     * @return the {@link Instant} referencing the insertion date of this {@link Entity}
     */
    Instant getCreatedDate();

}