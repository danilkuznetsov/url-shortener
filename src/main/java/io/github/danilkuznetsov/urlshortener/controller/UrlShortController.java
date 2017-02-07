package io.github.danilkuznetsov.urlshortener.controller;

import io.github.danilkuznetsov.urlshortener.service.UrlShorterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by danil.kuznetsov on 31/01/17.
 */
@RestController
@RequestMapping("/urls")
public class UrlShortController {

    UrlShorterService urlShorterService;

    @Inject
    public UrlShortController(UrlShorterService urlShorterService) {
        this.urlShorterService = urlShorterService;
    }

    @RequestMapping("/new")
    public String createNewShortUrl(@RequestParam("url") String longUrl) {
        return urlShorterService.createNewShortUrl(longUrl);
    }
}