# RESTfulAPIClient
Sample application using RESTful API with open source libraries

Introduction
------------
This repository contains a Weather application illustrating how to use RESTful API on Android using the following open source libraries.

This app calls RESTful API to [OpenWeatherMap][0] and receives current weather data formatted in Json.
Please visit [https://openweathermap.org/](https://openweathermap.org/)

[0]: https://openweathermap.org/


Getting Stared
--------------
In your local.properties file, put your own key given from [OpenWeatherMap][0]:

```
openWeatherMapApiKey="yourOwnAPIKey"
```
for example, you should write it as follows
```
openWeatherMapApiKey="788a5fnd5r134id6a792ff39pp68dcs3"
```


Open source libraries Used
--------------------------
* [Retrofit][1] - Type-safe HTTP client for Android and Java which makes it easier to consume RESTful API services.
* [OkHttp Logging Intercepter][2] - Logs HTTP request and response data with different logging levels in order to debug HTTP error 
* [Gson][3] - Used to convert a JSON string to Java objects with Retrofit Gson converter
* [EventBus][4] - A publish/subscribe event bus which simplifies the communication between Activity and RestfulService
* [Picasso][5] - A powerful image downloading and caching library which needs just one line of code
* [ButterKnife][6] - Binds field and method for Android views with annotation processing and it reduces boilerplate codes

[1]: http://square.github.io/retrofit/
[2]: https://github.com/square/okhttp/wiki/Interceptors
[3]: https://github.com/google/gson
[4]: https://github.com/greenrobot/EventBus
[5]: http://square.github.io/picasso/
[6]: http://jakewharton.github.io/butterknife/

License
-------

    Copyright Jaemoon Hwang <jaemoon.hwang@gmail.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
