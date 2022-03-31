package com.crtp.app;

import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Address;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.MalformedURLException;

/**
 *
 */
public class App  {
    public static void main( String[] args ) {
        // use test net by default
        String net = "test";
        if (args.length >= 1 && (args[0].equals("test") || args[0].equals("prod"))) {
            net = args[0];
            System.out.println("Using " + net + " network.");
        }
        // create a new EC Key ...
        ECKey key = new ECKey();
        // ... and look at the key pair
        System.out.println("We created key:\n" + key);
        // either test or production net are possible
        final NetworkParameters netParams;
        if (net.equals("prod")) {
            netParams = NetworkParameters.prodNet();
        } else {
            netParams = NetworkParameters.testNet();
        }
        // get valid Bitcoin address from public key
        Address addressFromKey = key.toAddress(netParams);
        System.out.println("On the " + net + " network, we can use this address:\n" + addressFromKey);

        System.out.println();
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            //Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            System.out.println(prop.getProperty("node.ip"));
            System.out.println(prop.getProperty("node.url"));
            System.out.println(prop.getProperty("node.usr"));
            System.out.println(prop.getProperty("node.pw"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String user = prop.getProperty("node.usr");
        String password = prop.getProperty("node.pw");
        String host = prop.getProperty("node.ip");
        String port = prop.getProperty("node.ports");

        URL DEFAULT_JSONRPC_URL;
        URL DEFAULT_JSONRPC_TESTNET_URL;
        URL DEFAULT_JSONRPC_REGTEST_URL;
      
        try {
            DEFAULT_JSONRPC_URL = new URL("http://" + user + ':' + password + "@" + host + ":" + (port == null ? "8332" : port) + "/");
            DEFAULT_JSONRPC_TESTNET_URL = new URL("http://" + user + ':' + password + "@" + host + ":" + (port == null ? "18332" : port) + "/");
            DEFAULT_JSONRPC_REGTEST_URL = new URL("http://" + user + ':' + password + "@" + host + ":" + (port == null ? "18443" : port) + "/");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            //throw new RuntimeException(ex);
        }
    }
}
