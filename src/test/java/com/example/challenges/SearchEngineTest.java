package com.example.challenges;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class SearchEngineTest {

    record SearchEngineInputWrapper(List<Listing> listings, int k) {}

    // 2. OJO AQUÍ: Tenemos que decirle a Gson que la salida esperada es una Lista de Listings.
    // Como en Java los genéricos (List<T>) se borran en tiempo de ejecución, pasamos un array de la clase base
    // y lo convertimos a lista en el runner, o simplemente tipamos el runner para que espere un Array de Listings.
    // Para mantenerlo simple con tu runner actual, usaremos un Array para la salida esperada.
    
    private final ChallengeTestRunner<SearchEngineInputWrapper, Listing[]> runner = new ChallengeTestRunner<>(
            "search_engine",
            SearchEngineInputWrapper.class,
            Listing[].class,
            input -> new SearchEngine()
                    .getTopListings(input.listings(), input.k())
                    .toArray(Listing[]::new)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_happy_path_deduplication",
                "02_edge_case_tie_break_id"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}