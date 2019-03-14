# Prometheus & grafana demo

## static prometheus demo

`./prometheus/prometheus-local.sh`

http://localhost:9090/graph

- select latest samples - instant vecor (iv)
`lfc_fruits_shipped_total`

- filter on label
`lfc_fruits_shipped_total{type='apple'}`

- Select samples during period - range vector selector (rv)
`lfc_fruits_shipped_total[5m]`

- Select shipped during period
`sum(increase(lfc_fruits_shipped_total[10m]))`

- Select most shipped fruit type
`topk(1, sum(increase (lfc_fruits_shipped_total[10m])) by(type))`


## grafana
`./grafana/grafana-local.sh`


Access http://localhost:3000. Default user & password is: admin/secret. Add a Prometheus datasource e.g. http://docker.for.mac.localhost:9090.

Singlestats panel:
`sum(increase(lfc_fruits_shipped_total[$__range]))`

Table panel:
sum(increase(lfc_fruits_shipped_total[$__range])) by (type)


