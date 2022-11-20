## Ecommerce Website for Laptop
This project is a `Spring boot` microservice 
<hr>

### INFORMATION
#### Connection
- DBMS: MySQL
- Connection Framework: Spring boot JDBC
- [File database]()
#### Folder's structure
- **config**: datasource configuration and `JdbcTemplate`
- **dao**: contains query and update for database
- **domain**: include model (blueprints of object)
- **dto**: model with optional information
- **mapper**: `RowMapper` support for `JdbcTemplate` query
- **service**: handle mapping and return `ResponseEntity`
- **web-api**: __main module__ include `@SpringbootApplication`
<hr>

### Documents and Files
- [Diagrams]()
- [Document]()