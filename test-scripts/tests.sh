#!/bin/sh

HOST="http://localhost:9000"

curl \
  --verbose \
  --request GET \
  --header 'Content-Type: application/json' \
  --data '{"from":0,"to":4102358400000}' \
  "$HOST/test/get-sales-by-period"

exit;

curl \
  --verbose \
  --request GET \
  --header 'Content-Type: application/json' \
  --data '{"from":0,"to":4102358400000,"shop":[1,2,3]}' \
  'http://localhost:9000/test/get-sales-by-shop'

curl \
  --verbose \
  --request GET \
  --header 'Content-Type: application/json' \
  --data '{"from":0,"to":4102358400000,"shop":[1,2,3],"products":[1,2]}' \
  'http://localhost:9000/test/get-sales-by-shop-product'

curl \
  --verbose \
  --request GET \
  --header 'Content-Type: application/json' \
  --data '{"from":0,"to":4102358400000,"shop":[1,2,3],"price_from":0.1,"price_to":99.9}' \
  'http://localhost:9000/test/get-sales-by-shop-price'
