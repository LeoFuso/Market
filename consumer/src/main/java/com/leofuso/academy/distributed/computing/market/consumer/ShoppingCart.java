package com.leofuso.academy.distributed.computing.market.consumer;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.CellMatcher;
import org.springframework.shell.table.SimpleHorizontalAligner;
import org.springframework.shell.table.SimpleVerticalAligner;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import com.leofuso.academy.distributed.computing.market.consumer.cart.api.CartService;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.OfferService;

/*
listar itens disponíveis

adicionar item ao carrinho
- quantidade, item

remover item do carrinho
- quantidade, item

finalizar compra

mostrar itens do carrinho

* */

@ShellComponent
public class ShoppingCart {

    private final OfferService offerService;
    private final CartService cartService;
    private final ConversionService converter;

    public ShoppingCart(OfferService offerService,
                        CartService cartService,
                        ConversionService conversionService) {

        this.offerService = Objects.requireNonNull(offerService);
        this.cartService = Objects.requireNonNull(cartService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @ShellMethod(
            value = "Display a list of all offers available for purchasing",
            key = "list offers"
    )
    public Table listAvailableOffers() {
        Set<Offer> offers = offerService.list();
        return converter.convert(offers, Table.class);
    }

}
