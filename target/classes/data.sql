

-- add base classes in base data source
insert into base_method (description, name) values ('class for generating random personal data (first names, last names, etc.)', 'name'); --1
insert into base_method (name, description) values ('address', 'class for generating full-fledged address data, has more than 20 possible options for composing an address'); -- 2
insert into base_method (description, name) values ('class for generating random country, has more possible options', 'country'); -- 3
insert into base_method (description, name) values ('class for generating random date time with optional patterns and durations', 'date'); -- 4
insert into base_method (description, name) values ('class for generating and Returns a Duration object representing a random duration of minutes in the given range', 'duration'); -- 5
insert into base_method (description, name) values ('class for generating random number with optionals min and max values', 'number'); --6
insert into base_method (description, name) values ('class for generating random phone number with optionals min and max values', 'phoneNumber'); --7
insert into base_method (description, name) values ('class for generating random email with optionals min and max values', 'internet'); --8
insert into base_method (description, name) values ('class for generating random boolean', 'boolean'); --9
insert into base_method (description, name) values ('class for generating random text, has more possible options', 'text'); --10


-- add base class methods in data generator
-- Class Name: Name
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) values ('Returns a random female ''given'' name. has no parameters', false, 'femaleFirstName', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random ''given'' name such as Aaliyah, Aaron, Abagail or Abbey has no parameters', false, 'firstName', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) values ('Returns the same value as name() has no parameters', false, 'fullName', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random last name such as Smith, Jones or Baldwin, has no parameters', false, 'lastName', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) values ('Returns a random male ''given'' name. has no parameters', false, 'maleFirstName', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('A multipart name composed of an optional prefix, a firstname and a lastname or other possible variances based on locale. has no parameters', false, 'name', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('A multipart name composed of an optional prefix, a given and family name, another ''firstname'' for the middle name and an optional suffix such as Jr. has no parameters', false, 'nameWithMiddle', 0, 1);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('A three part title composed of a descriptor level and job. has no parameters', false, 'title', 0, 1);

-- Class Name: Address
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random city. has no parameters', false, 'city', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random city name. has no parameters', false, 'cityName', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random city prefix. has no parameters', false, 'cityPrefix', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random city suffix. has no parameters', false, 'citySuffix', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random country name. has no parameters', false, 'country', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random country code. has no parameters', false, 'countryCode', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random building number. has no parameters', false, 'buildingNumber', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random full address. has no parameters', false, 'fullAddress', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random latitude. has no parameters', false, 'latitude', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random longitude. has no parameters', false, 'longitude', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns the lat/lon coordinates formatted as lat delimiter lon. has one String delimiter parameter', true, 'latLon', 1, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns the lat/lon coordinates formatted as lat,lon. has no parameter', false, 'latLon', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns the lat/lon coordinates formatted as lon delimiter lat. has one String delimiter parameter', true, 'lonLat', 1, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns the lat/lon coordinates formatted as lon,lat. has no parameter', false, 'lonLat', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random timeZone. has no parameters', false, 'tineZone', 0, 2);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random state. has no parameters', false, 'state', 0, 2);


-- Class Name: Country
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random country name. has no parameters', false, 'country', 0, 3);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random country code. has no parameters', false, 'countryCode2', 0, 3);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random currency name. has no parameters', false, 'currency', 0, 3);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random capital. has no parameters', false, 'capital', 0, 3);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random currency code. has no parameters', false, 'currencyCode', 0, 3);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random flag. has no parameters', false, 'flag', 0, 3);

-- Class Name: Date
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random birth date, type Timestamp. has no parameters', false, 'birthday', 0, 4);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Generates and converts to string representation a random birthday, type String. has one pattern', true, 'birthday', 1, 4);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Generates a random birthday between two ages from now, type Timestamp. has two parameters', true, 'birthday', 2, 4);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Generates and converts to string representation a random birthday between two ages from now, type String. has three parameters minAge, maxAge, pattern', true, 'birthday', 3, 4);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Generates a random Duration lower than max, type Duration. has two parameters (Long max, String unit)', true, 'duration', 2, 4);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Generates a random Duration between min and max., type Duration. has three parameters (Long min, Long max, String unit)', true, 'duration', 3, 4);

-- Class Name: Duration
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a Duration object representing a random duration of days in the given range. has one parameter (Long daysMax)', true, 'atMostDays', 1, 5);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a Duration object representing a random duration of hours in the given range. has one parameter (Long hoursMax)', true, 'atMostHours', 1, 5);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a Duration object representing a random duration of minutes in the given range. has one parameter (Long minutesMax)', true, 'atMostMinutes', 1, 5);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a Duration object representing a random duration of seconds in the given range. has one parameter (Long secondsMax)', true, 'atMostSeconds', 1, 5);

-- Class Name: Number
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random digit. has no parameters', false, 'digit', 0, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random digits, has one parameter (int count)', true, 'digits', 1, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a positive number, has no parameters', false, 'positive', 0, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a negative number, has no parameters', false, 'negative', 0, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random number in the given range, has two parameters (int min, int max)', true, 'numberBetween', 2, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random number from 0-9 (both inclusive), has no parameters', false, 'randomDigit', 0, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random number from 1-9 (both inclusive), has no parameters', false, 'randomDigitNotZero', 0, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random double, has parameters (int maxNumberOfDecimals, long min, long max)', true, 'randomDouble', 3, 6);
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random number, has no parameters', false, 'randomNumber', 0, 6);

-- Class Name: PhoneNumber
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random phone number. has no parameters', false, 'phoneNumber', 0, 7);

-- Class Name: Internet
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random email. has no parameters', false, 'emailAddress', 0, 8);

-- Class Name: Bool
insert into method_values (description, has_params, method_value, params_max_count, base_method_id) VALUES ('Returns a random boolean. has no parameters', false, 'bool', 0, 9);

