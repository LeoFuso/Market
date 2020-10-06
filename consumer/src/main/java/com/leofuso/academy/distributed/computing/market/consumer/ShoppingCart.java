package com.leofuso.academy.distributed.computing.market.consumer;

import java.util.List;
import java.util.Objects;
import org.springframework.core.convert.ConversionService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.Table;
import com.leofuso.academy.distributed.computing.market.consumer.commons.ShellHelper;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.ShoppingService;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;

import static com.leofuso.academy.distributed.computing.market.consumer.commons.TableRenderer.render;

@ShellComponent
public class ShoppingCart {

    private final ShoppingService shoppingService;
    private final ConversionService converter;
    private final ShellHelper shell;

    public ShoppingCart(final ShoppingService shoppingService,
                        final ConversionService conversionService,
                        final ShellHelper shell) {

        this.shoppingService = Objects.requireNonNull(shoppingService);
        this.converter = Objects.requireNonNull(conversionService);
        this.shell = Objects.requireNonNull(shell);
    }


    @ShellMethod(
            value = "Display a list of all offers available for purchasing",
            key = "list offers"
    )
    public void listAvailableOffers() {
        final List<Offer> offers = this.shoppingService.listOffers();
        final Table table = Objects.requireNonNull(this.converter.convert(offers, Table.class));
        this.shell.print(render(table));
    }

    @ShellMethod(
            value = "Create a new empty cart",
            key = "create cart"
    )
    public void createCart() {
        final Cart cart = this.shoppingService.createCart();
        this.shell.print(String.format("New cart created with id: %d", cart.getId()));
    }

    @ShellMethod(
            value = "Print information related to a cart",
            key = "retrieve cart"
    )
    public void retrieveCart(
            @ShellOption(
                    value = {"-c", "--cart"},
                    help = "The id of the cart to be retrieved."
            ) final Long cartId) {

        final Cart cart = this.shoppingService.retrieveCart(cartId);
        printCartInformation(cart);
    }

    @ShellMethod(
            value = "Add items to a cart",
            key = "add item"
    )
    public void addItem(
            @ShellOption(
                    value = {"-c", "--cart"},
                    help = "The id of the cart to be retrieved."
            ) final Long cartId,

            @ShellOption(
                    value = {"-i", "--item"},
                    help = "The id of the offer to be added to the cart"
            ) final Long offerId,

            @ShellOption(
                    value = {"-q", "--quantity"},
                    help = "The quantity of items to be added to the cart"
            ) final Integer quantity) {

        final Cart cart = this.shoppingService.addItem(cartId, offerId, quantity);
        printCartInformation(cart);
    }

    @ShellMethod(
            value = "Remove items from a cart",
            key = "remove item"
    )
    public void removeItem(
            @ShellOption(
                    value = {"-c", "--cart"},
                    help = "The id of the cart to be retrieved."
            ) final Long cartId,

            @ShellOption(
                    value = {"-i", "--item"},
                    help = "The id of the offer to be added to the cart"
            ) final Long offerId,

            @ShellOption(
                    value = {"-q", "--quantity"},
                    help = "The quantity of items to be added to the cart"
            ) final Integer quantity) {

        final Cart cart = this.shoppingService.removeItem(cartId, offerId, quantity);
        printCartInformation(cart);
    }

    @ShellMethod(
            value = "Finish the order for a given cart",
            key = "finish order"
    )
    public void finishOrder(
            @ShellOption(
                    value = {"-c", "--cart"},
                    help = "The id of the cart with the items to include in the order to be finished."
            ) final Long cartId) {

        final Cart cart = this.shoppingService.finishOrder(cartId);
        printCartInformation(cart);
    }

    private void printCartInformation (final Cart cart) {
        //TODO this
        throw new UnsupportedOperationException("to be implemented");
    }
}
