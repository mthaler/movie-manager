# movie-manager

Stores movies in Postgresql using hibernate

## PostgreSQL basics

PostgreSQL is an open source relational database management system. It can handle workloads ranging from small single-machine applications to large Internet-facing applications.
PostgreSQL is ACID-compliant and transactional. PostgreSQL has updatable views and materialized views, triggers, foreign keys; supports functions and stored procedures, and other expandability.

### Installing PostgreSQL

On Debian Linux, PostgreSQL can be installed with the following command:

```
# apt-get install postgresql postgresql-doc pgadmin3
```

Both the default database user and default database are called postgres

### Authentication

PostgreSQL database user names are logically separate from user names of the operating system in which the server runs. If all the users of a particular server also have accounts on the server's machine, it makes sense to assign database user names that match their operating system user names. However, a server that accepts remote connections might have many database users who have no local operating system account, and in such cases there need be no connection between database user names and OS user names.

Client authentication is controlled by a configuration file, which traditionally is named pg_hba.conf (HBA stands for host-based authentication). On Debian Linux, the file is stored in

```
/etc/postgresql/13/main
```


### Administration Tools

psql is a command-line interface to PostgreSQL, pgadmin3 is a GUI program that connects to the PostgreSQL database

To start psql with the postgres user, administrative rights are required. As root, do:

```
# su -s /bin/bash postgres
```

Next, run psql

```
$ psql
```

To conntect to a specific database, e.g. testdb, run

```
$ psql testdb
```

#### List users

To list all users, use the display user (du) command:

```
postgres-# \du
                                   List of roles
 Role name |                         Attributes                         | Member of 
-----------+------------------------------------------------------------+-----------
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
```

#### List databases

To list all databases, use the list (l) command:

```
postgres-# \l
                                  List of databases
   Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges   
-----------+----------+----------+-------------+-------------+-----------------------
 postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
 template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
(3 rows)  
```

#### Create user

To create a new user, execute the following shell command as user postgres:


```
$ createuser -DRS joe
```

#### Set password

To securely connect to the database from Java, we need to set a password for the user

#### Create database

To create a new database, execute the following shell command as user postgres:


```
$ createdb -O joe joework
```
