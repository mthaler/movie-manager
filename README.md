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

To create a new user, execute the following **shell command** as user postgres:

```
$ createuser -dRS moviemanager
```

This will create a new user that can create databases, but no roles and the user does not have superuser privileges. psql should now show the new user:

```
postgres=# \du
                                     List of roles
  Role name   |                         Attributes                         | Member of 
--------------+------------------------------------------------------------+-----------
 moviemanager | Create DB                                                  | {}
 postgres     | Superuser, Create role, Create DB, Replication, Bypass RLS | {}

```

#### Set password

To securely connect to the database from Java, we need to set a password for the user:

```
ALTER USER moviemanager WITH PASSWORD 'new_password';
```

#### Create database

To create a new database, execute the following shell command as user postgres:


```
$ createdb -O moviemanager moviemanager
```

This will create a new database moviemanager owned by the user moviemanager:

```
postgres=# \l
                                     List of databases
     Name     |    Owner     | Encoding |   Collate   |    Ctype    |   Access privileges   
--------------+--------------+----------+-------------+-------------+-----------------------
 moviemanager | moviemanager | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
 postgres     | postgres     | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
 template0    | postgres     | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
              |              |          |             |             | postgres=CTc/postgres
 template1    | postgres     | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
              |              |          |             |             | postgres=CTc/postgres
(4 rows)
```

To switch to the moviemanager database in psql, do:

```
postgres=# \c moviemanager
```

#### Create movies table

The CreateMoviesTable utility class will create the movies table used by moviemanager. Run it with the following arguments:

```
--username moviemanager --password secret
```

where the password needs to be replaced by the real password. In psql we should now see the movies database:

```
moviemanager=# \d
           List of relations
 Schema |  Name  | Type  |    Owner     
--------+--------+-------+--------------
 public | movies | table | moviemanager
(1 row)
```

To display the columns in psql, do:

```
moviemanager=# \d+ movies
                                    Table "public.movies"
  Column  |  Type   | Collation | Nullable | Default | Storage  | Stats target | Description 
----------+---------+-----------+----------+---------+----------+--------------+-------------
 id       | integer |           | not null |         | plain    |              | 
 title    | text    |           | not null |         | extended |              | 
 director | text    |           | not null |         | extended |              | 
 synopsis | text    |           | not null |         | extended |              | 
Indexes:
    "movies_pkey" PRIMARY KEY, btree (id)
Access method: heap
```

#### Add data to movies table

The FillMoviesTable utility class can be used to add some data to the movies table. Run it with the following arguments:

```
--username moviemanager --password secret
```

where the password needs to be replaced by the real password. To check that the data was actually added to the movies table, run the following query with psql:

```
moviemanager=# select * from movies;
 id |  title  |  director  |                                                                                                 synopsis                                                                                                 
----+---------+------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  1 | Top Gun | Tony Scott | Maverick is a hot pilot. When he encounters a pair of MiGs over the Persian Gulf, his wingman is clearly outflown and freaks. On almost no fuel, Maverick is able to talk him back down to the Carrier..
(1 row)
```
