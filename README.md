# crawler
This project is created as part of an interview Process.

## Building the app
```
mvn install
```

running the app
```
cd target
java -cp ".:./lib/*:./crawler-0.0.1-SNAPSHOT.jar" com.wipro.web.crawler.App http://localhost
```

The test resource has some html files which can be used as a temporary web-root
```
cd target/test-classes/
python -m SimpleHTTPServer 80
```

## Remarks
There are few improvements to be made.
* Usage of an HttpClient to follow any redirections.
* Check-points to avoid crawling into the anchor (#tags).
* Add depth option to avoid growing beyond set depth
