this is for learning spring cloud

1. part one is configuration that including config-server and limits-service
   test api:
   http://localhost:8081/limits
   http://localhost:8888/cbx.limits.service/dev
2. part two has service-registry, api-gateway, currency-exchange and currency-convert
   test api:
   http://localhost:8001/currency-exchange/from/USD/to/INR
   http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/14
   http://localhost:8765/currency.conversion.service/currency-conversion-feign/from/USD/to/INR/quantity/15
   http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/15
   http://localhost:8765/currency-exchange/from/USD/to/INR
   http://localhost:8001/smaple-api
   http://localhost:8001/circuit-api

   watch -n 0.1 curl  http://localhost:8001/circuit-api

3. start zipkin
   docker run -d -p 9411:9411 openzipkin/zipkin
   
4. build docker images
   docker build -t cbx/currency-exchange .
   docker build -t cbx/currency-conversion .
   docker build -t cbx/registry-service .
   docker build -t cbx/gateway-service .
   
note:
1. application name should be same as service name in docker-compose. feign is using application name to communication through client side load balance 


Mar 17 2021 11:45pm
the conversion call exchange service failed, because of the url that reset by gateway route
1. look the udyme code to find the way to fix
