FROM mariadb:10.6

LABEL AUTHOR = TIGOLI FREDERIC
LABEL VERSION = 1.0

ENV MARIADB_ROOT_PASSWORD=P@ssword2024$

COPY .  /docker-entrypoint-initdb.d/

EXPOSE 3306

CMD ["mysqld"]

