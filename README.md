# Coronavirus COVID-19 API

API displays global statistics for COVID-19 (confirmed cases, deaths cases, recovered cases and existing cases), statistics for location (country, region) and searching historical data about COVID-19 cases.

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
    "active": 215654,
    "confirmed": 378287,
    "deaths": 16497,
    "recovered": 100958
  }
}
```

### Getting actual data by country and/or region

##### Requests
```http
GET /api/actual/location?country={country}
```
```http
GET /api/actual/location?region={region}
```
```http
GET /api/actual/location?country={country}&region={region}
```

##### Output
```json
{
  "last_update": "2020-03-23 23:19:34",
  "location": {
    "country": "US",
    "region": "California"
  },
  "coordinates": {
    "lat": 39.26255932,
    "lon": -121.35356440000001
  },
  "data": {
    "active": 3,
    "confirmed": 3,
    "deaths": 0,
    "recovered": 0
  }
}
```

### Getting historical data of COVID-19

##### Request
```http
GET /api/history/{MM-dd-yyyy}
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