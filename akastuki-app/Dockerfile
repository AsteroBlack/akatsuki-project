FROM node:12-alpine as angular

LABEL AUTHOR = TIGOLI FREDERIC
LABEL VERSION = 1.0


WORKDIR /app
COPY . .

RUN npm install 
RUN npm run build


FROM quay.io/centos/centos:stream9

RUN yum update -y && yum install httpd -y && yum clean all

WORKDIR /var/www/html

COPY --from=angular /app/dist ./akatsuki.com/
COPY --from=angular /app/httpd.conf /etc/httpd/conf
COPY --from=angular /app/akatsuki.conf /etc/httpd/conf.d

#VOLUME ["/var/www/html/", "/etc/httpd/conf", "/etc/httpd/conf.d"]

ENTRYPOINT ["/usr/sbin/httpd","-D","FOREGROUND"]
