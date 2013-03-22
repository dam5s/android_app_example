package com.example.androidApp.support;

import com.example.androidApp.ApiGateway;
import com.google.inject.AbstractModule;

import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/22/13
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */

public class ApplicationModuleWithMockApiGateway extends AbstractModule {
    @Override
    public void configure() {
        bind(ApiGateway.class).toInstance(mock(ApiGateway.class));
    }
}
