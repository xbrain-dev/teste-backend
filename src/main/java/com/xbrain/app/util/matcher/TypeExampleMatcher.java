package com.xbrain.app.util.matcher;

import org.springframework.data.domain.ExampleMatcher;

public class TypeExampleMatcher {
    /**
     * Faz a busca por matchingAny campo or campo or campo
     * <p>
     * Fusca por StringMatcher CONTAINING que seria o like
     * <p>
     * Ignora se está maiúsculo ou minúsculo
     * <p>
     * Não trata os valores nulos
     */
    public ExampleMatcher exampleMatcherMatchingAny() {
        return ExampleMatcher
                .matchingAny() // campo or campo or campo
                // .matching() // campo and campo and campo
                // .matchingAll() // campo and campo
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // faz a consulta com o like
                .withIgnoreCase() // ignora se está maiúsculo ou minúsculo
                .withIgnoreNullValues();

    }
}
