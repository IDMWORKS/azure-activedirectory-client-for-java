package com.identityforge.aad.adcl4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

/**
 * Created by nwoolls on 5/4/15.
 */
public interface RestClient {
    <T> T createEntry(Class<T> clazz, String path, Object entry)
            throws IOException, URISyntaxException, AuthenticationException;
    <T> Collection<T> getAllEntries(Class<T> clazz, String path)
            throws IOException, URISyntaxException, AuthenticationException;
    <T> T getEntry(Class<T> clazz, String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException;
    void updateEntry(String path, String oid, Object entry)
            throws IOException, URISyntaxException, AuthenticationException;
    void deleteEntry(String path, String oid)
            throws IOException, URISyntaxException, AuthenticationException;
}
