package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    @GetMapping("/city")
    public ModelAndView showListCity() {
        Iterable<City> cities = cityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateCity() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }


    @PostMapping("/create-city")
    public ModelAndView saveCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "New city created successfully");
        return modelAndView;
    }

    @GetMapping("delete-city/{id}")
    public ModelAndView deleteCity(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }  ModelAndView modelAndView = new ModelAndView("/city/delete");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }

    @PostMapping("/delete-city")
    public String deleteCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return "redirect:city";
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;

        } ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", cityOptional.get());
        return modelAndView;
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCity(@ModelAttribute("city") City city) {
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "City updated successfully");
        return modelAndView;
    }

   @GetMapping ("/detail-city/{id}")
    public ModelAndView detailCity(@PathVariable long id){
        Optional<City> city = cityService.findById(id);
        if(!city.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;

        }
       ModelAndView modelAndView = new ModelAndView("/city/detail");
       modelAndView.addObject("city",city.get());
       return modelAndView;
   }

}
