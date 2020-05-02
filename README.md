# Coronavirus COVID-19 API

API displays global statistics for COVID-19 (confirmed cases, deaths cases, recovered cases and existing cases), statistics for location (country, region, city) and searching historical data about COVID-19 cases.

## Technologies
Project is created with:
* Java 8
* Spring Boot 2.2.5
* Apache Commons CSV 1.8
* GSON 2.8.6

## Endpoints

##### Base URL
```http
https://coronavirus-covid-19-api.herokuapp.com/
```

### Getting actual data of COVID-19

##### Request
```http
GET /api/actual
```

##### Output
```json
{
  "data": {
    "active": 1820433,
    "confirmed": 2811193,
    "deaths": 197159,
    "recovered": 793601
  }
}
```

### Getting actual data by country and/or region

##### Requests
```http
GET /api/actual/location?country={country}
```
```http
GET /api/actual/location?country={country}&region={region}
```
```http
GET /api/actual/location?country={country}&region={region}&city={city}
```

##### Output
```json
{
  "last_update": "2020-04-25 06:30:53",
  "location": {
    "city": "Los Angeles",
    "country": "US",
    "region": "California"
  },
  "coordinates": {
    "lat": "34.30828379",
    "lon": "-118.2282411"
  },
  "data": {
    "active": 17695,
    "confirmed": 18545,
    "deaths": 850,
    "recovered": 0
  }
}
```

### Getting historical data of COVID-19

##### Request
```http
GET /api/history/{MM-dd-yyyy}/
```

##### Output
```json
{
  "date": "03-10-2020",
  "data": {
    "active": 49916,
    "confirmed": 118582,
    "deaths": 4262,
    "recovered": 64404
  }
}
```

## Data source
[2019 Novel Coronavirus COVID-19 (2019-nCoV) Data Repository by Johns Hopkins CSSE](https://github.com/CSSEGISandData/COVID-19)

## License
[MIT](LICENSE)