package marcozagaria;


import marcozagaria.Classi.Customer;
import marcozagaria.Classi.Order;
import marcozagaria.Classi.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        //creazione prodotti
        List<Product> products = new ArrayList<>();
        products.add(new Product(11561, "libro 1", "books", 120.0));
        products.add(new Product(21561, "lavatrice", "elettrodomestici", 200.0));
        products.add(new Product(34165, "libro 3", "books", 9.9));
        products.add(new Product(41165, "libro 4", "books", 170.7));
        products.add(new Product(51961, "Action Figure 1", "boys", 120.0));
        products.add(new Product(61531, "Action Figure 2", "boys", 90.0));
        products.add(new Product(81651, "Action Figure 3", "boys", 200.0));
        products.add(new Product(1L, "bambola 1", "baby", 15.0));
        products.add(new Product(2L, "bambola 2", "baby", 25.0));
        products.add(new Product(3L, "bambola 3", "boys", 120.0));

        //creazione utenti
        Customer utente1 = new Customer(1, "marco", 2);
        Customer utente2 = new Customer(2, "giuseppe", 2);
        Customer utente3 = new Customer(3, "luca ", 2);
        Customer utente4 = new Customer(4, "aldo", 2);

        //creazione ordini
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, "spedito", LocalDate.of(2024, 9, 29), LocalDate.of(2024, 10, 4), List.of(products.get(0), products.get(9)), utente1));
        orderList.add(new Order(2, "consegnato", LocalDate.of(2024, 9, 15), LocalDate.of(2024, 9, 23), List.of(products.get(2), products.get(5)), utente2));
        orderList.add(new Order(3, "in cosegna", LocalDate.of(2024, 9, 25), LocalDate.of(2024, 9, 2), List.of(products.get(8), products.get(9)), utente3));
        orderList.add(new Order(4, "consegnato", LocalDate.of(2024, 9, 17), LocalDate.of(2024, 9, 25), List.of(products.get(3), products.get(7)), utente4));


        System.out.println("---------------------esercizio 1----------------------------");
        Map<Customer, List<Order>> ordiniCliente = orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        ordiniCliente.forEach(((customer, orders) -> {
            System.out.println("ciao " + customer.getName());
            orders.forEach(order -> System.out.println("il tuo ordine id " + order.getId() + " stato di consegna: " + order.getStatus()));
        }));


        System.out.println("---------------------esercizio 2----------------------------");

        Map<Customer, Double> importoCliente = orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(order -> order.getProducts().stream()
                        .mapToDouble(Product::getPrice).sum())));

        importoCliente.forEach((customer, total) -> {
            System.out.println("ciao " + customer.getName() + " hai speso: " + total + "€");
        });

        System.out.println("---------------------esercizio 3----------------------------");

        List<Product> prodottiCostosi = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(5).toList();

        prodottiCostosi.forEach(product -> System.out.println("i progotti più costosi sono: " + product.getName() + " nella categoria " + product.getCategory() + " al prezzo di " + product.getPrice()));

        System.out.println("---------------------esercizio 4----------------------------");

        double mediaOrdini = orderList.stream()
                .collect(Collectors.averagingDouble(order -> order.getProducts()
                        .stream().mapToDouble(Product::getPrice).sum()));

        System.out.println("gli utenti in media hanno speso: " + mediaOrdini);
    }
}
