Play! framework module for Apache CXF - Hello World sample
==========================================================

This sample demonstrates using development, test and production environments with different Play configurations and different endpoint URLs:
* for development: http://localhost:9000/service/hello
* for test: http://test.site:9000/service/hello
* for production: http://production.site/service/hello

Compared to the play-cxf-hello sample, the changed files are:
* `conf/applicationContext.xml` and `conf/springProfile.*.properties` describing the 3 Spring profiles
* `conf/application.conf` defining an `application.environment="production"` configuration value
* `conf/application-test.conf` and `conf/application-development.conf` overriding this configuration value for the test and development environments
* `app/Global.scala` connecting the application-*.conf values with the Spring profiles.

A real application would have real test and production machines with real host names. Since we'd like to test this sample on a single machine, we'll need to [add the test and production host names to the system hosts file](http://www.howtogeek.com/howto/27350/), with a line like:

    127.0.0.1   test.site production.site

We can then run the sample by executing the following commands in the sample's directory:
* in development mode: `play "run -Dconfig.resource=application-development.conf"`
* in test mode: `play "start -Dconfig.resource=application-test.conf"`
* in production mode: `play "start 80"`
