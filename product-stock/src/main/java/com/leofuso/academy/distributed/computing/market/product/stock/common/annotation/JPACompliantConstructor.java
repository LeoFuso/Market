package com.leofuso.academy.distributed.computing.market.product.stock.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;

/**
 * Used to point out that a specific constructor exists only to satisfy Java Persistent API constraints
 */
@Target({CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface JPACompliantConstructor {}
