package com.optum.bankingapi.subscriptions;

import com.optum.bankingapi.dtos.Currency;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@Slf4j
public class TransactionSubscription implements GraphQLSubscriptionResolver {
    public Publisher<Currency> currency(String symbol) {
       log.info("Enters subscription");
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(2))
                .map(num -> new Currency(symbol, random.nextDouble(), LocalDateTime.now().toString()));
    }
}
