package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        String searchType="all";
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", searchType);
        return "search";
    }


    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value="results", method = RequestMethod.GET)
    public String getSearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchType.equals("all") && searchTerm.isEmpty()) {
            Iterable<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            return "search";
            }
        else if (searchType.equals("all") && (searchTerm.length() > 0)) {
            List<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            model.addAttribute(searchTerm);
            return "search";
        }
        else {
            List<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            model.addAttribute(searchTerm);
            model.addAttribute("searchType" , searchType);
        }
            return "search";
    }

}