package com.vector.extranet.selenium.base;

import javax.sql.DataSource;

import com.google.inject.Inject;
import com.vector.extranet.selenium.framework.ActivationEmailLink;
import com.vector.extranet.selenium.framework.Configuration;
import com.vector.extranet.selenium.framework.Pages;
import com.vector.extranet.selenium.framework.WebDriverProvider;
import com.vector.extranet.selenium.util.TestUtil;
import com.vector.extranet.selenium.util.UserGenerator;
import com.vector.extranet.selenium.util.WaitUtils;

public abstract class BaseService {

    @Inject
    protected Configuration config;

    @Inject
    protected DataSource dataSource;

    @Inject
    protected UserGenerator userGenerator;

    @Inject
    protected Pages pages;

    @Inject
    protected TestUtil testUtils;

    @Inject
    protected WebDriverProvider webDriverProvider;

    @Inject
    protected ActivationEmailLink activationEmailLink;
    
    @Inject
    protected WaitUtils waitUtils;
    
}
