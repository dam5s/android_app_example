package com.example.androidApp.support;

import com.example.androidApp.api.ApiGateway;
import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: pivotal
 * Date: 3/22/13
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationModuleWithFakeApiGateway extends AbstractModule {
    @Override
    public void configure() {
        bind(ApiGateway.class).toInstance(new FakeApiGateway());
    }
}
