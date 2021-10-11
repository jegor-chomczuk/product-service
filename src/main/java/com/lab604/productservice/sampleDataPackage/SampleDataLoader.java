package com.lab604.productservice.sampleDataPackage;

import com.github.javafaker.Faker;
import com.lab604.productservice.dao.Product;
import com.lab604.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final Faker faker;

    public SampleDataLoader(ProductRepository productRepository, Faker faker) {
        this.productRepository = productRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) {

        List<Product> sampleOrders = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Product(
                        faker.funnyName().name(),
                        faker.number().randomDouble(2, 0, 1000)
                )).collect(Collectors.toList());

        productRepository.saveAll(sampleOrders);
    }
}