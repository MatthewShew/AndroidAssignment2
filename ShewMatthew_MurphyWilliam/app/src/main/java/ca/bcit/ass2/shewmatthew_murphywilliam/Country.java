package ca.bcit.ass2.shewmatthew_murphywilliam;

/**
 * Created by Matt on 10/11/2017.
 */


import java.util.ArrayList;
import java.util.List;
public class Country
{
    private String name;

    private String capital;

    private String region;

    private int population;

    private int area;

    //private List<String> borders;
    private String borders;

    private String flag;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCapital(String capital){
        this.capital = capital;
    }
    public String getCapital(){
        return this.capital;
    }
    public void setRegion(String region){
        this.region = region;
    }
    public String getRegion(){
        return this.region;
    }
    public void setPopulation(int population){
        this.population = population;
    }
    public int getPopulation(){
        return this.population;
    }
    public void setArea(int area){
        this.area = area;
    }
    public int getArea(){
        return this.area;
    }
    public void setBorders(String borders){
        this.borders = borders;
    }
    public String getBorders(){
        return this.borders;
    }
    public void setFlag(String flag){
        this.flag = flag;
    }
    public String getFlag(){
        return this.flag;
    }
}
