package com.nimda;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Pwn3dChecker {
    /*
    Interface to harvest data from https://haveibeenpwned.com
    it uses an api-key
     */
    String key;
    HttpClientSync pwned_client_req;


    public Pwn3dChecker(String key) {
        this.key = key;
        this.pwned_client_req =  new HttpClientSync();

    }

    public void GettingAllBreachesForAnAccount(String email) throws IOException, InterruptedException {
        /*
        Getting all breaches for an account

         */
        String url_get_breach_by_email = "https://haveibeenpwned.com/api/v3/breachedaccount/" + email;
        HttpResponse<String> response = pwned_client_req.HttpClient(url_get_breach_by_email, key);

        if (response.statusCode() == 200) {
            System.out.println("[*] Breaches found for: " + email + "\n");
            System.out.println(response.body());
        } else {
            System.out.println("[x] Status Code " + response.statusCode());
            if (response.statusCode() == 404){
                System.out.println("    [-] No Breaches were found (: for " + email + " But be Careful!!!");
               }
        }
    }

    public void GetDataClasses () throws IOException, InterruptedException {
        /*
        Getting all data classes in the system
         */
        String url_get_dataclases = "https://haveibeenpwned.com/api/v3/dataclasses";
        HttpResponse<String> response = pwned_client_req.HttpClient(url_get_dataclases, key);
        if (response.statusCode() == 200) {
            System.out.println("[*] Data Classes found:\n");
            System.out.println(response.body());
        } else {
            System.out.println("[x] Exception Occurred " + response.statusCode());

        }

    }

    public void GettingAllBreachedSitesInTheSystem() throws IOException, InterruptedException {
         /*
            Getting all breached sites in the system
         */
        String url_get_breach_by_sites = "https://haveibeenpwned.com/api/v3/breaches";
        HttpResponse<String> response = pwned_client_req.HttpClient(url_get_breach_by_sites, key);

        if (response.statusCode() == 200) {
            System.out.println("[*] Breached Sites in System: \n");
            System.out.println(response.body());
        } else {
            System.out.println("[x] Exception Occurred " + response.statusCode());

        }
    }

    public void GettingASingleBreachedSite(String site) throws IOException, InterruptedException {
        /*
            Getting a single breached site
         */

        String url_get_breach_by_single_site = "https://haveibeenpwned.com/api/v3/breach/" + site;
        HttpResponse<String> response = pwned_client_req.HttpClient(url_get_breach_by_single_site, key);

        if (response.statusCode() == 200) {
            System.out.println("[*] Breaches found for: " + site + "\n");
            System.out.println(response.body());
        } else {
            System.out.println("[x] Exception Occurred " + response.statusCode());
            if (response.statusCode() == 404){
                System.out.println("[+] No Breaches were found (: for: " + site + " But be Careful!!!");
            }
        }
    }

    public void GettingAllPastesForAnAccount(String email) throws IOException, InterruptedException {
        /*
        Getting all Pastes for an account

         */
        String url_get_pastes_by_email = "https://haveibeenpwned.com/api/v3/pasteaccount/" + email;
        HttpResponse<String> response = pwned_client_req.HttpClient(url_get_pastes_by_email, key);

        if (response.statusCode() == 200) {
            System.out.println("[*] Pastes found for: " + email + "\n");
            System.out.println(response.body());
        } else {
            System.out.println("[x] Status Code " + response.statusCode());
            if (response.statusCode() == 404){
                System.out.println("    [-] No Pastes were found (: for " + email + " But be Careful!!!");
            }
        }
    }

}









/*
    public static void main(String[] args) throws InterruptedException, IOException {
        Pwn3dCheck check_for_breach  = new Pwn3dCheck("71a0bea02e84414689c833df0220b50a");
        check_for_breach.GettingAllBreachesForAnAccount("jonathan.angeles.contractor@bbva.com");
        //check_for_breach.GetDataClasses();
        //check_for_breach.GettingASingleBreachedSite("adobe");
        //check_for_breach.GettingAllBreachedSitesInTheSystem();
    }

 */


