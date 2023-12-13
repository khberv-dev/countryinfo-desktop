package uz.khberv.countryinfodesktop.model;

import org.json.JSONObject;

public class Country {
    public String name;
    public String officialName;
    public String nativeName;
    public String currencyCode;
    public String currencySymbol;
    public String currencyName;
    public String capital;
    public String flag;
    public int population;
    public int area;
    public String continent;

    public static Country parseFromJSON(JSONObject object) {
        Country country = new Country();

        try {
            String nativeLangCode = object.getJSONObject("name")
                    .getJSONObject("nativeName")
                    .keySet()
                    .toArray()[0]
                    .toString();

            String currencyCode = object.getJSONObject("currencies")
                    .keySet()
                    .toArray()[0]
                    .toString();

            country.name = object.getJSONObject("name").getString("common");
            country.officialName = object.getJSONObject("name").getString("official");
            country.nativeName = object.getJSONObject("name")
                    .getJSONObject("nativeName")
                    .getJSONObject(nativeLangCode)
                    .getString("official");
            country.flag = object.getJSONObject("flags").getString("png");
            country.capital = object.getJSONArray("capital").getString(0);
            country.population = object.getInt("population");
            country.area = object.getInt("area");
            country.continent = object.getJSONArray("continents").getString(0);
            country.currencyCode = currencyCode;
            country.currencyName = object.getJSONObject("currencies")
                    .getJSONObject(currencyCode)
                    .getString("name");
            country.currencySymbol = object.getJSONObject("currencies")
                    .getJSONObject(currencyCode)
                    .getString("symbol");
        } catch (Exception e) {
            return null;
        }

        return country;
    }
}