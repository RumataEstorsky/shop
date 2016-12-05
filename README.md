# How to run

- It requires Play 2.5 && Slick 3.1 && PostgreSQL >= 9.4
- Install PostgreSQL >= 9.4
- Run commans: 
    - `echo "CREATE ROLE rumata LOGIN PASSWORD '';" | psql`
    - `echo 'CREATE DATABASE olabsshop WITH OWNER rumata;' | psql`
- Or fix `conf/application.conf`
- Then run app from application root dir `sbt run` (default port = 9000)
- Open `http://localhost:9000/` and press `apply` in request of migration db
- CURL tests in folder `/test-scripts` 
