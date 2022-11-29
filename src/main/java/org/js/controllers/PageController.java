package org.js.controllers;

import org.js.entities.Zadanie;
import org.js.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.js.entities.Country;
import org.js.repositories.CountryRepository;

@Controller
public class PageController {

    @Autowired
    public CountryRepository repc;
   
    
    public ZadanieRepository rep;
    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan(){
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
        rep.save(zadanie);
        for(Zadanie i: rep.findAll()){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
    
    
private String printList(List<Country> countries) {
        StringBuilder odp = new StringBuilder();
        for (Country country : countries) {
            odp.append(country).append("<br>");
        }
        return odp.toString();
    }
    
     @RequestMapping("/CountryList")
    @ResponseBody
    public String CountryList(){
        StringBuilder odp = new StringBuilder();
        Country country = new Country();
        for(Country i: repc.findAll()){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
    @RequestMapping("/country/continent/{name}")
    @ResponseBody
    public String countryByContinent(@PathVariable("name") String name) {
        var countries = repc.findCountriesByContinent(name);
        return printList(countries);
    }

    @RequestMapping("/country/population/{min}/{max}")
    @ResponseBody
    public String countryByPopulation(@PathVariable("min") int min, @PathVariable("max") int max) {
        var countries = repc.findCountriesByPopulationBetween(min, max);
        return printList(countries);
    }

    @RequestMapping("/country/surfaceArea/{name}/{min}/{max}")
    @ResponseBody
    public String countryBySurfaceAreaAndContinent(@PathVariable("min") double min, @PathVariable("max") double max, @PathVariable("name") String name) {
        var countries = repc.findCountriesBySurfaceAreaBetweenAndContinent(min, max, name);
        return printList(countries);
    }

    @RequestMapping("/dodatkoweZadania")
    @ResponseBody
    public void dodatkoweZadania(){
        Zadanie z;
        double k=1000;
        boolean wyk=false;
        for (int i=1;i<=10;i++){
            z = new Zadanie();
            z.setNazwa("zadanie "+i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu "+i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk=!wyk;
            k+=200.50;
            rep.save(z);
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteZadanie(@PathVariable Long id){
        rep.deleteById(id);
    }

    @RequestMapping("/zadaniaWykonane/{wykonane}")
    @ResponseBody
    public String zadaniaWykonane(@PathVariable Boolean wykonane) {
        StringBuilder odp = new StringBuilder();

        for(Zadanie i: rep.findByWykonane(wykonane)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/mniejniz/{koszt}")
    @ResponseBody
    public String zadaniaKosztMniejNiż(@PathVariable String koszt){
        StringBuilder odp = new StringBuilder();

        Double kosztMax = Double.parseDouble(koszt);
        for(Zadanie i: rep.findByKosztLessThan(kosztMax)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/miedzy/{kosztMin}/{kosztMax}")
    @ResponseBody
    public String zadaniaKosztMiedzy(@PathVariable String kosztMin, @PathVariable String kosztMax){
        StringBuilder odp = new StringBuilder();
        Double min = Double.parseDouble(kosztMin);
        Double max = Double.parseDouble(kosztMax);
        for(Zadanie i: rep.findByKosztBetween(min,max)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }



}
