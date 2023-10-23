#!/bin/bash

env=dev
shop=ro-scc-shop-b2b

if ! mvn clean verify "-Dcucumber.filter.tags=@tag1 and not @ignore and not @known-issue"; then
 # test-jar needs to be generated before report aggregation because otherwise in case of failure (e.g. uncaught exceptions) we get ClassNotFoundExceptions for our own exceptions
 mvn jar:test-jar serenity:aggregate
fi