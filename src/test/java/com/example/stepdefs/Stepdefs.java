package com.example.stepdefs;

import com.example.Datum;
import com.example.Resources;
import com.example.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.JsonObject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Stepdefs {

    String API_PATH;
    User user;
    RequestSpecification request;

    @Given("the base URI is {string}")
    public void theBaseURIIs(String baseURI) {
        request = SerenityRest.given().baseUri(baseURI).contentType("application/json")
                .header("Content-Type", "application/json");
    }

    @Given("^the api end point is \"([^\"]*)\"$")
    public void apiEndpoint(String api) {
        API_PATH = api;
    }

    @When("the request is made with post method")
    public void theRequestIsMadeWithPostMethod() {
        SerenityRest.when().post(API_PATH);
    }

    @When("the request is made with get method")
    public void theRequestIsMadeWithGetMethod() {
        SerenityRest.when().get(API_PATH);
    }

    @Given("the user credentials are")
    public void apiEndpoint(DataTable table) {
        user = new User();
        user.setEmail(table.row(0).get(0));
        user.setPassword(table.row(0).get(1));
        request.body(user);
    }


    @Then("the response code should be 200")
    public void theResponseCodeShouldBe() {
        restAssuredThat(response -> response
                .statusCode(HttpStatus.SC_OK));
    }

    @Then("response should contain id for first resource")
    public void theResponseContains() {
        Resources resources = SerenityRest.lastResponse().as(Resources.class);
        Datum data = resources.getData().get(0);
        assertThat(data.getId(), is(notNullValue()));

    }




   /* @Then("the number of should be more than {int}")
    public void theNumberOfShouldBeMoreThan(int count) {
        Networks networks = SerenityRest.lastResponse().as(Networks.class);
        Serenity.recordReportData().withTitle("Number of Networks").andContents("" + networks.getNetworks().size());
        Assert.assertTrue(networks.getNetworks().size() > count);
    }

    @Then("verify city Frankfurt is in Germany")
    public void verifyCityFrankfurtIsInGermany() {
        Networks networks = SerenityRest.lastResponse().as(Networks.class);
        network = networks.getNetworks().stream().filter(nw -> nw.getLocation().getCity().equals("Frankfurt")).findFirst().get();
        Assert.assertEquals("DE", network.getLocation().getCountry());
        Serenity.recordReportData().withTitle("Frankfurt is Germany").andContents(network.getLocation().getCountry());
    }

    @And("return their corresponded latitude and longitude")
    public void returnTheirCorrespondedLatitudeAndLongitude() {
        Double lat = network.getLocation().getLatitude();
        Double log = network.getLocation().getLongitude();
        Serenity.recordReportData().withTitle("Lat & Log").andContents("Lat:" + lat + "; Log:" + log);

    }*/
}
