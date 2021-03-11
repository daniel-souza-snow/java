package com.crud.java.service;

import com.crud.java.exceptions.BusinessRuleException;
import com.crud.java.model.Adress;
import com.crud.java.repository.AdressRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdressService {
    @Autowired
    private AdressRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${google-api-geometry}")
    private String url_client;
    @Value("${google-api-key}")
    private String API_KEY;

    public Adress save(Adress adress) throws  JSONException {
        if(adress.getLatitude().isEmpty() || adress.getLongitude().isEmpty()){
            JSONObject data = getGeometryLocation(adress);
            String latitude = String.valueOf(data.getDouble("lat"));
            String longitude = String.valueOf(data.getDouble("lng"));
            adress.setLatitude(latitude);
            adress.setLongitude(longitude);
        }
        return repository.save(adress);
    }

    public Adress findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()-> new BusinessRuleException("CANNOT GET ADRESS THAT IS WHY DOES NOT EXIST"));
    }

    public void delete(Integer id){
        repository.findById(id)
                .map(adress -> {
                    repository.deleteById(adress.getId());
                    return adress;
                }).orElseThrow(()-> new BusinessRuleException("CANNOT DELETE ADRESS THAT IS WHY DOES NOT EXIST"));
    }

    public Adress update(Integer id, Adress newAdress) {
        return repository.findById(id)
                .map(adress -> {
                    adress.setStreetName(newAdress.getStreetName());
                    adress.setComplement(newAdress.getComplement());
                    adress.setNumber(newAdress.getNumber());
                    adress.setNeighbourhood(newAdress.getNeighbourhood());
                    adress.setCity(newAdress.getCity());
                    adress.setState(newAdress.getState());
                    adress.setCountry(newAdress.getCountry());
                    adress.setZipcode(newAdress.getZipcode());
                    adress.setLatitude(newAdress.getLatitude());
                    adress.setLongitude(newAdress.getLongitude());
                    return repository.save(adress);
                }).orElseThrow(()-> new BusinessRuleException("CANNOT UPDATE ADRESS THAT IS WHY DOES NOT EXIST"));
    }

    /**
     *
     * @param adress
     * @return JSONObject with geometry data about the adress param
     * @throws JSONException
     */
    private JSONObject getGeometryLocation(Adress adress) throws JSONException {
        String[]adressClient= {adress.getStreetName(),adress.getNumber().toString(), adress.getCity()};
        String requestBody = String.join(", ", adressClient);
        Map<String,String> uriVaribles= new HashMap<>();
        uriVaribles.put("key",API_KEY);
        uriVaribles.put("address",requestBody);
        JsonNode response= restTemplate
                .getForObject(url_client+"/maps/api/geocode/json?address={address}&key={key}",JsonNode.class, uriVaribles);
        JSONObject data = new JSONObject(response.toString()).getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");
        return data;
    }
}
