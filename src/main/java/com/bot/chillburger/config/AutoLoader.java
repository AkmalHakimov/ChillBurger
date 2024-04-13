package com.bot.chillburger.config;

import com.bot.chillburger.entity.Category;
import com.bot.chillburger.entity.Product;
import com.bot.chillburger.repository.CategoryRepo;
import com.bot.chillburger.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoLoader implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public void run(String... args) {
        if (categoryRepo.findAll().isEmpty()){
            categoryRepo.save(Category.builder()
                    .id(1)
                    .uzName("🍕 Pitsa")
                    .ruName("🍕 Пицца")
                    .build());
            categoryRepo.save(Category.builder()
                    .id(2)
                    .uzName("\uD83E\uDD64 Ichimliklar")
                    .ruName("\uD83E\uDD64 Напитки")
                    .build());
            categoryRepo.save(Category.builder()
                    .id(3)
                    .uzName("\uD83C\uDF70 Desertlar")
                    .ruName("\uD83C\uDF70 Десерты")
                    .build());
            categoryRepo.save(Category.builder()
                    .id(4)
                    .uzName("\uD83E\uDD57 Salatlar")
                    .ruName("\uD83E\uDD57 Салаты")
                    .build());
        }

        if(productRepo.findAll().isEmpty()){
            productRepo.save(Product.builder()
                    .uzName("Qazi pitsa")
                    .ruName("Казы пицца")
                    .price(63_000)
                    .photoId("AgACAgIAAxkBAAIC92X_p57041LDAXn2QJTH_9EK1-2tAAL-3DEbTbX5SxbzedSuLC2lAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(1).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Pishloqli pitsa")
                    .ruName("Пицца с сыром")
                    .price(39_000)
                    .photoId("AgACAgIAAxkBAAIKy2YT61grTxPma2yh2dkVBQ0blzCsAAKv2TEbXtyhSDyt5amunmS2AQADAgADcwADNAQ")
                    .category(categoryRepo.findById(1).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Tovuqli donar")
                    .ruName("Куриный дoнар")
                    .price(63_000)
                    .photoId("AgACAgIAAxkBAAIKzWYT66dsfqT8haS6aqI3K6Ug26mGAAK02TEbXtyhSAzvbiH6HCXJAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(1).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Go'shtli miks")
                    .ruName("Мясной микc")
                    .price(92_000)
                    .photoId("AgACAgIAAxkBAAIKyWYT6xw8fsaampWXNaN54dAHwO5iAAKt2TEbXtyhSFIBtfu8r0k6AQADAgADcwADNAQ")
                    .category(categoryRepo.findById(1).orElseThrow())
                    .build());


            productRepo.save(Product.builder()
                    .uzName("Coca-Cola 0,5 l")
                    .ruName("Кока-Кола 0,5 л")
                    .price(10_000)
                    .photoId("AgACAgIAAxkBAAIKx2YT6tnwAAHqD8aj-6H0XA7e1ixefAACpdkxG17coUiXfHo8I6ZhAQEAAwIAA3MAAzQE")
                    .category(categoryRepo.findById(2).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Coca-Cola 1 l")
                    .ruName("Кока-Кола 1 л")
                    .price(14_000)
                    .photoId("AgACAgIAAxkBAAIKx2YT6tnwAAHqD8aj-6H0XA7e1ixefAACpdkxG17coUiXfHo8I6ZhAQEAAwIAA3MAAzQE")
                    .category(categoryRepo.findById(2).orElseThrow())
                    .build());


            productRepo.save(Product.builder()
                    .uzName("Sinnamon Rollar 8 ta")
                    .ruName("Роллы с Синнамоном 8 шт.")
                    .price(15_000)
                    .photoId("AgACAgIAAxkBAAIKzGYT64ZwmlsgaH7e0CEtvLmsjF1lAAKx2TEbXtyhSAABm1pfkfNXUgEAAwIAA3MAAzQE")
                    .category(categoryRepo.findById(3).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Shokoladli Fondan")
                    .ruName("Шоколадный фондан")
                    .price(19_000)
                    .photoId("AgACAgIAAxkBAAIKyGYT6wagNlCPNYEtHn4hBrPJIo67AAKs2TEbXtyhSNh5FsCRU6rbAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(3).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Shokoladli chizkeyk")
                    .ruName("Шоколадный чизкейк")
                    .price(22_000)
                    .photoId("AgACAgIAAxkBAAIC92X_p57041LDAXn2QJTH_9EK1-2tAAL-3DEbTbX5SxbzedSuLC2lAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(3).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Laymli chizkeyk")
                    .ruName("Лаймовый чизкейк")
                    .price(22_000)
                    .photoId("AgACAgIAAxkBAAIKymYT60MWaCX44TwEpfZynsO2G8xpAAKu2TEbXtyhSP2J9tXGIShAAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(3).orElseThrow())
                    .build());


            productRepo.save(Product.builder()
                    .uzName("Grecheskiy salat")
                    .ruName("Салат Греческий")
                    .price(24_000)
                    .photoId("AgACAgIAAxkBAAIKzmYT6_JiFyY7PYrBMi0EbYbRRd7jAAK32TEbXtyhSO-6i4CSEv8fAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(4).orElseThrow())
                    .build());
            productRepo.save(Product.builder()
                    .uzName("Sezar salat")
                    .ruName("Салат Цезарь")
                    .price(24_000)
                    .photoId("AgACAgIAAxkBAAIKxWYT6oQ9diXRd401SaQkLWW1D92YAAKi2TEbXtyhSB1j7ZwjyLjXAQADAgADcwADNAQ")
                    .category(categoryRepo.findById(4).orElseThrow())
                    .build());
        }
    }
}
