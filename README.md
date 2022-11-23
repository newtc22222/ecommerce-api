## Ecommerce Website for Laptop
This project is a `Spring boot` microservice 
<hr>

### INFORMATION
#### Connection
- DBMS: MySQL
- Connection Framework: Spring boot JDBC
- [File database](https://drive.google.com/drive/folders/1q7t7k1k7veKzN9Ptdctm9bN3y6_01Fuk?usp=share_link)
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
- [Diagrams](https://drive.google.com/file/d/1OE_KbW5tsNvdta7HfWI2ZhBS9Ljk1yFS/view?usp=share_link)
- [Document](https://drive.google.com/drive/folders/1t4cuOCmw3CqHkqSruj4DafwQF2-rfvm0?usp=share_link)