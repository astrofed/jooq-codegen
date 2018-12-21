Local deploy OPSO Services 
==========================

### Preparations

Set actual environment variables in `.env`, and copy ip addresses from hosts to your `/etc/hosts` 

### Run postgres server and pgadmin

```
$ docker-compose -f postgres.yml up
```

pgadmin is available by url `http://pgadmin.local:8080`
