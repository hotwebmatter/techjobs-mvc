package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {


    @RequestMapping(value = "")
    public String search(Model model) {
 //       model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
            // model.addAttribute("title", "Search for " + searchTerm + " in All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            // model.addAttribute("title", "Search for " + searchTerm + " by " + ListController.columnChoices.get(searchType));
        }
        //       model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchTypeSelected", searchType);
        model.addAttribute("searchTermSelected", searchTerm);
        return "search";

    }

}
