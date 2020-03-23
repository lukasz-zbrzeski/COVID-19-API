# Coronavirus COVID-19 API

API displays global statistics for COVID-19 (confirmed cases, deaths cases, recovered cases and existing cases) and statistics for location (country, region).

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
    "confirmed": 335957,
    "deaths": 14634,
    "existing": 223441,
    "recovered": 97882
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
  "last_update": "2020-03-22T22:13:28",
  "location": {
    "country": "US",
    "region": "California"
  },
  "coordinates": {
    "lat": 36.1162,
    "lon": -119.6816
  },
  "data": {
    "confirmed": 1642,
    "deaths": 30,
    "existing": 1612,
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
    "confirmed": 118582,
    "deaths": 4262,
    "existing": 49916,
    "recovered": 64404
  }
}
```

## License
[MIT](LICENSE)