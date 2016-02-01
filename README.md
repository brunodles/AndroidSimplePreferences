# SimplePreferences

[![Release](https://jitpack.io/v/brunodles/AndroidSimplePreferences.svg)](https://jitpack.io/#brunodles/AndroidSimplePreferences)

A simple library to work with SharedPreferences.

## How add it
On your `build.gradle` add the maven repository from *jitpack.io*

        repositories {
            maven { url "https://jitpack.io" }
        }

On your project level (`app/build.gradle`) add the _androidSimplePreferences_ dependency.

        dependencies {
            compile 'com.github.brunodles:AndroidSimplePreferences:{latest version}'
        }


Now, you just need to run a _GradleSync_.

## Choosing how to work with it
The first thing you need to do is create a new class.

There is two options, use it as a `ActiveRecord` where the object can _save_ \ _load_ \ _clean_ itself. The problem is that you need to extend our class to make it work.  
The other option, you can use a DAO to save many objects, this way you don't need to extend any class from the lib.

### Using it as ActiveRecord
To use as an active record you just need to 
* Create your _data class_
* Make it _extends_ `ActivePreferences`
* *Annotate* your _persistant fields_ with `@Property`

This class is intent to be used when you have only one of this kind, like the _current user_ data, the _user preferences_ or even the app preferences.

#### Apply \ Reload \ Clean
To save the data as an activePreferences you just need to call `apply()`.  
To load the data you just need to call `load()`.  
To clean the data you just need to call `clean()`.  
That simple.

#### Fast sample

		public class CurrentUser extends ActivePreferences {
		    @Property public String name;
		    @Property public boolean isJedi = false;
		}

		CurrentUser user = new CurrentUser()
		user.name = "Anakin Skywalker";
		// to save the current object on a preferences
		user.apply();
		// Reload the object
		user.reload();
		// Clean the preferences data.
		user.clean();

### Using it as Data Access Object
To use the _DAO_ is simple too, you need to:
* Create your _data class_
* *Annotate* the _persistant fields_ with `@Property`

#### Saving \ Loading \ Cleaning
To save the data as an activePreferences you just need to call `apply`.  
To load the data you just need to call `load`.  
To clean the data you just need to call `clean`.  

#### Fast sample

		public class Person{
		    @Property public String name;
		    @Property public boolean isJedi = false;
		}

		Person person = new Person()
		person.name = "Anakin Skywalker";
		// to save the current object on a preferences
		
		// "this" is a context
		DaoPreferences dao = new DaoPreferences(this)
		// save the person with the key "anakin"
        dao.save(person, "anakin"); 
        // load the person
        person = dao.load(person, "anakin");
        // clean the data
        dao.clean("anakin");

# Sample
You can see it in "pratice", it have some explanations too.
On the sample you will see how to
* Create a data class
* Save data
* load data

# You can help this lib to grow
If you fond something wrong or if you want some feature, just create a issue or even better create
a pull request with you idea.
