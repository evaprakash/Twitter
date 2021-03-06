# Project 3 - Twitter

**Twitter** is an android app that allows a user to view their Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **15** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ]	User can **sign in to Twitter** using OAuth login
* [ ]	User can **view tweets from their home timeline**
  * [ ] User is displayed the username, name, and body for each tweet
  * [ ] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [ ] User can **compose and post a new tweet**
  * [ ] User can click a “Compose” icon in the Action Bar on the top right
  * [ ] User can then enter a new tweet and post this to Twitter
  * [ ] User is taken back to home timeline with **new tweet visible** in timeline
  * [ ] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [ ] User can **see a counter with total number of characters left for tweet** on compose tweet page
* [ ] User can **pull down to refresh tweets timeline**
* [ ] User can **see embedded image media within a tweet** on list or detail view.

The following **optional** features are implemented:

* [ ] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening (this did not end up working but I and multiple TAs spent a lot of time on it, which caused me to miss out on other stretch stories)
* [ ] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [ ] User can click on a tweet to be taken to a "detail view" of that tweet
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/evaprakash/Twitter/blob/master/Twitter.gif' title='Twitter Walkthrough' width='' alt='Twitter Walkthrough' />

## Notes

TAs and I still unable to figure out best way to show progress indicator.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2021 Eva Prakash

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
