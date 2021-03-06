package io.github.danilkuznetsov.cleverlinks.web.ui;

import io.github.danilkuznetsov.cleverlinks.domain.dto.FullUrlDescription;
import io.github.danilkuznetsov.cleverlinks.domain.dto.UpdatedShortUrl;
import io.github.danilkuznetsov.cleverlinks.domain.urls.DeletedShortUrl;
import io.github.danilkuznetsov.cleverlinks.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/urls/{urlId}")
    public String displayFullUrlDetailsPage(
        @PathVariable("urlId") final Long urlId,
        final Model model
    ) {
        model.addAttribute("fullUrlDetails", this.urlService.loadDetails(urlId));
        return "dashboard/fullUrlDetails";
    }

    @PostMapping("/urls")
    public String createUrl(@ModelAttribute("url") final String url) {
        FullUrlDescription description = this.urlService.createUrl(url);
        return "redirect:/dashboard/urls/" + description.getId();
    }

    @PostMapping("/urls/{urlId}/short-urls")
    public String addCustomUrl(
        @PathVariable("urlId") final Long urlId,
        @ModelAttribute("short-url") final String newCustomShortUrl
    ) {
        FullUrlDescription description = this.urlService.addCustomShortUrl(urlId, newCustomShortUrl);
        return "redirect:/dashboard/urls/" + description.getId();
    }

    @PostMapping("/urls/{urlId}/short-urls/{shortUrlId}")
    public String updateCustomUrl(
        @PathVariable("urlId") final Long urlId,
        @PathVariable("shortUrlId") final Long shortUrlId,
        @ModelAttribute("new-short-url") final String newCustomShortUrl
    ) {
        final UpdatedShortUrl updatedShortUrl = new UpdatedShortUrl(shortUrlId, newCustomShortUrl);

        FullUrlDescription description = this.urlService.updateCustomShortUrl(urlId, updatedShortUrl);
        return "redirect:/dashboard/urls/" + description.getId();
    }

    @PostMapping("/urls/{fullUrlId}/short-urls/{shortUrlId}/delete")
    public String deleteCustomUrl(
        @PathVariable("fullUrlId") final Long fullUrlId,
        @PathVariable("shortUrlId") final Long shortUrlId
    ) {
        final DeletedShortUrl deletedShortUrl = this.urlService.deleteShortUrl(fullUrlId, shortUrlId);
        return "redirect:/dashboard/urls/" + deletedShortUrl.getFullUrlId();
    }
}
