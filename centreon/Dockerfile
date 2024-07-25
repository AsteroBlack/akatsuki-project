
FROM quay.io/centos/centos:stream9
LABEL AUTHOR = TIGOLI FREDERIC
LABEL VERSION = 1.0

WORKDIR /centreon
RUN yum update -y && yum install -y centos-release-scl && yum install -y yum-utils && yum install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm && yum install -y https://rpms.remirepo.net/enterprise/remi-release-7.rpm && yum-config-manager --enable remi-php80 && yum install -y https://yum.centreon.com/standard/21.10/el7/stable/noarch/RPMS/centreon-release-21.10-5.el7.centos.noarch.rpm

RUN yum install -y centreon centreon-database && systemctl daemon-reload && systemctl restart mariadb && mysql_secure_installation


	
