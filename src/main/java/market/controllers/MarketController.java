package market.controllers;

import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.services.ItemService;
import com.geekbrains.marketdemoa.utils.ItemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MarketController {
    private ItemService itemService;

    @Autowired
    public MarketController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        Sort.Direction sort = Sort.Direction.ASC;
        String sortType = "price";
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        if(params.containsKey("sorties")){
            if(params.get("sorties").equals("TITLEDOWN") || params.get("sorties").equals("PRICEDOWN")){
                sort = Sort.Direction.DESC;
            }
            if (params.get("sorties").equals("TITLEUP") || params.get("sorties").equals("TITLEDOWN")){
                sortType = "title";
            }
            if (params.get("sorties").equals("PRICEUP") || params.get("sorties").equals("PRICEDOWN")){
                sortType = "price";
            }
        }

        Pageable pageRequest = PageRequest.of(pageIndex, 5, sort, sortType);
        ItemFilter itemFilter = new ItemFilter(params);
        Page<Item> page = itemService.findAll(itemFilter.getSpec(), pageRequest);

        List<String> categories = Arrays.stream(Item.Category.values()).map(Item.Category::name).collect(Collectors.toList());
        List<String> sorties = Arrays.stream(Item.Sorties.values()).map(Item.Sorties::name).collect(Collectors.toList());

        model.addAttribute("filtersDef", itemFilter.getFilterDefinition());
        model.addAttribute("categories", categories);
        model.addAttribute("sorties", sorties);
        model.addAttribute("page", page);
        return "index";
    }
}
