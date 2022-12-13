package com.prgrms.m3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItem extends BaseEntity{

    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public OrderItem(int price, int quantity, Item item) {
        this.price = price;
        this.quantity = quantity;
        this.item = item;
    }

    public static OrderItem createOrderItem(Item item, int quantity) {
        int totalPrice = item.getPrice() * quantity;
        item.removeStock(quantity);

        return new OrderItem(totalPrice, quantity, item);
    }

    public void addOrder(Order order) {
        if (Objects.nonNull(this.order)) {
            this.order.getOrderItems().remove(this);
        }

        this.order = order;
        order.getOrderItems().add(this);
    }

    public void cancel() {
        item.addStock(quantity);
    }

    public int getTotalPrice() {
        return getQuantity() * getPrice();
    }
}
