FROM mysql:8.0

COPY init.sql /docker-entrypoint-initdb.d/

CMD ["--default-authentication-plugin=mysql_native_password"]