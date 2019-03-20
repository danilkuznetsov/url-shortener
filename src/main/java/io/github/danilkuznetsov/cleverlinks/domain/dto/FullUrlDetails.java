package io.github.danilkuznetsov.cleverlinks.domain.dto;

import io.github.danilkuznetsov.cleverlinks.domain.FullUrl;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(exclude = "id")
@ToString
public class FullUrlDetails {

    private final Long id;

    private final String url;

    public static FullUrlDetails of(final FullUrl url) {
        return new FullUrlDetails(url.getId(), url.getUrl());
    }
}