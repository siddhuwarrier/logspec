# logspec

[![Build Status](https://travis-ci.org/siddhuwarrier/logspec.svg)](https://travis-ci.org/siddhuwarrier/logspec)

`logspec` is a library that allows developers to verify their logging. It is inspired by the [glager](https://github.com/st3v/glager)
library.

## Why?

When your application goes wrong in production, the first thing we turn to is our logs. However, most of us never test
our logs, and do not pay any attention to it until we start looking at the production logs and find that it gives us very
little information of value.

As a result, it makes sense for us to treat our logs as a first class feature and test it. `logspec` helps you do just that.

## Build

`logspec` should soon be found on Maven Central. But if you want to build it yourself, clone the Git repository and 
execute:

        mvn install -Dskip.gpg=true
        
from the project's root directory.

## Supported Scala versions

The only Scala major version `logspec` supports at the time of writing is `2.11`. 

## Usage

### Logback

> Note: This example is illustrated using the fabulous `scala-logging` framework, but you don't need to use it
> if you don't want to.

Include the Logback dependency in your project by adding the following lines to your `pom.xml`
 
    <dependency>
        <groupId>info.siddhuw</groupId>
        <artifactId>logspec-logback_2.11</artifactId>
    </dependency>

If you have a class that outputs the following logs in order:

    logger.debug("start")
    logger.info("Processing file {}...", fileName)
    logger.debug("end")
    
you can verify that these statements have been logged in your test as follows:

    
    import info.siddhuw.logback.LogbackSpec
    import info.siddhuw.models.Log
    import info.siddhuw.models.LogLevel._

    class ExampleSpec extends LogbackSpec {
        ...
        expectLogs(List(Log(DEBUG, "start"), Log(INFO, "Processing file ...", Array(fileName)), Log(DEBUG, "end")) {
            //insert the code that invokes the code that performs the actual logging
        }
    }
    
    
#### Regular expressions

You can additionally use regular expressions to match a message. To illustrate,
    
    logger.debug("I am bored because 12345")

can be matched using:
       
       expectLogs(List(Log(DEBUG, ".*bored because \d+"))) {
       }
       
#### Partial Sequence matching (contains)

If you have a class that outputs the following logs in order:

    logger.debug("start")
    logger.info("Processing file {}...", fileName)
    logger.warn("OH NOES")
    logger.debug("end")
    
you can verify that any ordered sub-list of these has been logged using

        containsLogs(List(Log(INFO, "Processing file ...", Array(fileName)), Log(WARN, "OH NOES"))) {
            //insert the code that invokes the code that performs the actual logging
        }
       

    
    








