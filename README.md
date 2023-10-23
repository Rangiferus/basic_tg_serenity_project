# basic_tg_serenity_project
## Basic Serenity project to show problematic cases

Run:
```
mvn clean verify "-Dcucumber.filter.tags=@tag2 and not @ignore and not @known-issue"
mvn jar:test-jar serenity:aggregate
```
