package com.nimda;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) throws Exception {


        Options options = new Options();

        Option k = new Option("k", "api-key", true, "Authorization APv3 Key for RESTFull Service (haveibeenpwned)");
        k.setRequired(true);
        options.addOption(k);

        Option ba = new Option("ba", "breach_by_account", false, "Getting all breaches for an account - Require an email");
        ba.setRequired(false);
        options.addOption(ba);

        Option b = new Option("b", "sites_breach", false, "Getting all breached sites in the system");
        b.setRequired(false);
        options.addOption(b);

        Option bs = new Option("bs", "breach_by_site", false, "Getting a single breached site - Required a Site Name");
        bs.setRequired(false);
        options.addOption(bs);

        Option dc = new Option("dc", "data_classes", false, "Getting all data classes in the system");
        dc.setRequired(false);
        options.addOption(dc);

        Option pa = new Option("pa", "pastes_by_account", false, "Getting all pastes for an account - Require an email");
        pa.setRequired(false);
        options.addOption(pa);

        Option pp = new Option("pp", "Passwords-Pwned", false, "Passwords Pwned - Required a Hash Suffix");
        pp.setRequired(false);
        options.addOption(pp);
        // additional args
        Option account = new Option("acc", "email-account", true, "Email Account");
        account.setRequired(false);
        options.addOption(account);

        Option site_name = new Option("n", "name", true, "Site Name");
        site_name.setRequired(false);
        options.addOption(site_name);

        Option hp = new Option("hp", "hash-prefix", true, "Hash Prefix");
        hp.setRequired(false);
        options.addOption(hp);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(" -:== Pwn3dChecker V1.0 ==:- \n" +
                    "   ';--have you been pwned?\n" +
                    "   Author: Jonathan Angeles\n" +
                    "   Email: jangelesg@gangsecurity.com\n" +
                    "   GitHub: https://github.com/jangelesg/Pwn3dChecker/\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -acc user@domain.com -ba\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -bs\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -b\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -bs -name adobe\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -pa -acc user@domain.com\n" +
                    "java -jar pwn3dCheck.jar -k xxxxxx-api-key -pp -hp 21BD2\n" +
                    " ", options);
            System.exit(1);
        }



        // logic to create functionality
        String key = cmd.getOptionValue("k");
        String banner = " -:== Pwn3dChecker V1.0 ==:- \n";
        Pwn3dChecker check_for_breach  = new Pwn3dChecker(key);

        if(cmd.hasOption("ba")) {
            /*
            The most common use of the API is to return a list of all breaches a particular account has been involved in.
            The API takes a single parameter which is the account to be searched for. The account is not case sensitive
             and will be trimmed of leading or trailing white spaces. The account should always be URL encoded.
             This is an authenticated API and an HIBP API key must be passed with the request.
             */
            if (cmd.hasOption("acc")){
                String email = cmd.getOptionValue("acc");
                System.out.println(banner);
                check_for_breach.GettingAllBreachesForAnAccount(email);
            }
            else {
                //
                System.out.println("[X] C1 Error found with the arguments ");
                System.exit(1);
            }
        }

        if(cmd.hasOption("b")) {
            /*
            A "breach" is an instance of a system having been compromised by an attacker and the data disclosed.
            For example, Adobe was a breach, Gawker was a breach etc. It is possible to return the details of each of
            breach in the system which currently stands at 456 breaches.
             */
            System.out.println(banner);
            check_for_breach.GettingAllBreachedSitesInTheSystem();
            }


        if(cmd.hasOption("bs")) {
            /*
            Sometimes just a single breach is required and this can be retrieved by the breach "name".
            This is the stable value which may or may not be the same as the breach "title" (which can change).
            See the breach model below for more info.
             */
            if (cmd.hasOption("n")){
                String name = cmd.getOptionValue("n");
                System.out.println(banner);
                check_for_breach.GettingAllBreachesForAnAccount(name);
            }
            else {
                //
                System.out.println("[X] C2 Error found with the arguments ");
                System.exit(1);
            }
        }

        if(cmd.hasOption("pa")) {
            /*
            The API takes a single parameter which is the email address to be searched for.
            The email is not case sensitive and will be trimmed of leading or trailing white spaces.
            The email should always be URL encoded.
            This is an authenticated API and an HIBP API key must be passed with the request.
             */
            if (cmd.hasOption("acc")){
                String email = cmd.getOptionValue("acc");
                System.out.println(banner);
                check_for_breach.GettingAllPastesForAnAccount(email);
            }
            else {
                //
                System.out.println("[X] C3 Error found with the arguments ");
                System.exit(1);
            }
        }

        if(cmd.hasOption("dc")) {
            /*
            A "breach" is an instance of a system having been compromised by an attacker and the data disclosed.
            For example, Adobe was a breach, Gawker was a breach etc. It is possible to return the details of each of
            breach in the system which currently stands at 456 breaches.
             */
            System.out.println(banner);
            check_for_breach.GetDataClasses();
        }

        if(cmd.hasOption("pp")) {
            /*
            Pwned Passwords are more than half a billion passwords which have previously been exposed in data breaches.
            The service is detailed in the launch blog post then further expanded on with the release of version 2.
            The entire data set is both downloadable and searchable online via the Pwned Passwords page.
            Each password is stored as a SHA-1 hash of a UTF-8 encoded password. The downloadable source data delimits
            the full SHA-1 hash and the password count with a colon (:) and each line with a CRLF.
            Searching by range In order to protect the value of the source password being searched for, Pwned Passwords
            also implements a k-Anonymity model that allows a password to be searched for by partial hash.
            This allows the first 5 characters of a SHA-1 password hash (not case-sensitive) to be passed to the API
             */
            if (cmd.hasOption("hp")){
                String hash_suffix = cmd.getOptionValue("hp");
                System.out.println(banner);
                check_for_breach.GettingAllPwnedPasswordsbyHash(hash_suffix);
            }
            else {
                //
                System.out.println("[X] C3 Error found with the arguments - hash prefix missing");
                System.exit(1);
            }
        }
    }

}



