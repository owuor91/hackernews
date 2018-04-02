# HackerNews

This is an android app that allows users to read HackerNews articles. It consumes the hackernews v0 API. The application's architecture follows the Model-View-Presenter design pattern and is divided into 4 modules:

 - app - Contains the UI components of the app and the root of the dependency injection.
 - presentation - Contains presenter classes that obtain data from repositories, process and pass it to the views
                  as well as handling and processing view events and logic.
 - data - Contains the implementation of the data sources (repositories) used in the app, both api and local db.
 - domain - Contains interfaces of the repositories and models used throughout the app.

### HackerNews is available on the Playstore
<img src="https://raw.githubusercontent.com/owuor91/hackernews/master/images/story.png" width="260"> <img src="https://raw.githubusercontent.com/owuor91/hackernews/master/images/comment.png" width="260"> <img src="https://raw.githubusercontent.com/owuor91/hackernews/master/images/user.png" width="260">

<a href='https://play.google.com/store/apps/details?id=io.github.owuor91.hackernews&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1' target='_blank'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width='360'/></a>

### Pull the project
    git clone https://github.com/owuor91/hackernews.git
    cd hackernews

## Libraries Used:
### General
 - [Dagger2](https://google.github.io/dagger/) - Dependency Injection framework
 - [RxJava](https://github.com/ReactiveX/RxJava) - Asynchronous event-based reactive streams
 - [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Android bindings for RxJava
 - [Joda-Time](http://www.joda.org/joda-time/) - DateTime utilities
 - [Crashlytics](https://fabric.io/kits/android/crashlytics) - Crash reporting

### View
 - [Butter Knife](http://jakewharton.github.io/butterknife/) - View binding
 - [Calligraphy](https://github.com/chrisjenx/Calligraphy) - Custom fonts

### Network
 - [Retrofit](http://square.github.io/retrofit/) -HTTP requests
 - [Gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Json serialization and deserialization

### Database
 - [Stetho](http://facebook.github.io/stetho/) - Local database debug bridge
 - [Room](https://developer.android.com/topic/libraries/architecture/room.html) - Local SQLite ORM

### Testing
 - [Mockito](http://site.mockito.org/) - Unit testing framework
