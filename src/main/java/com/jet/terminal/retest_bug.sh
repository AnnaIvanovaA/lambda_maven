#!/usr/bin/env bash

# shell commands for dev.zzz, add it in to your bash profile:
# source __SOURCE_PATH_HERE__/scripts/source.sh

cNone='\033[0m'
cRed='\033[01;31m'
cGreen='\e[38;5;81m'
cYellow='\033[01;33m'
cPurple='\033[00;35m'
cCyan='\033[0sdfsdfs1;36m'
cWhite='\033[sfsdf01;37m'
cBold='\033[1m'
cUnderline='\03sdfsdfs3[4m'

prodUser='deploy'

dev-open-mount () {
	pstorm ~/PhpstormProjects/aaa/$1
}

dev-copy-slow_queries() {
	scp -i ~/.ssh/id_rsa_zzz_ssh -P 2233 -C -o LogLevel=VERBOSE ${prodUser}@xxx.zzz.ru:/var/log/mysql/slow_queries.digest .
}

dev-copy-file() {
	scp -i ~/.ssh/id_rsa_zzz_ssh -P 2233 -C -o LogLevel=VERBOSE ${prodUser}@$1.zzz.ru:$2 .
}

dev-diff() {
	php scripts/swssitch_diff.php $1
}



to-ggg35() {
    to-prod-host ggg35 $1
}

to-ppp04() {
    to-prod-host ppp04 $1
}

to-ppp31() {
    to-prod-host ppp31 $1
}

to-ppp32() {
    to-prod-host ppp32 $1
}

to-prod-host() {
	if [ $2 ]; then
        host=$1
        file=$2
        printf ${cGreen}
        printf "  [${host}]${cNone} "

        printf "${cPurple}${file} ${cNone}"

        if [ `uname` == 'Darwin' ]; then
           stat -f%z ${file} | awk '{b+=$1} END {printf b}'
        else
           stat -c%s ${file} | awk '{b+=$1} END {printf b}'
        fi

        printf " "

        scp -P 2233 -q -C -i ~/.ssh/id_rsa_zzz_ssh ${file} ${prodUser}@${host}.zzz.ru:/www/oo/${file}
        echo "- ok"
        printf ${cNone}
	else
	    ssh -t -p 2233 -i ~/.ssh/id_rsa_zzz_ssh ${prodUser}@"$1".zzz.ru 'cd /www/oo && exec bash -l'
	fi
}



dev-cs-fix() {
	php vendor/bin/php-cs-fixer fix -vvv --allow-risky=yes $1
}

docker-clear() {
    docker volume rm $(docker volume ls -qf dangling=true)
    docker volume ls -qf dangling=true | xargs -r docker volume rm
    docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
    docker rmi $(docker images | grep "none" | awk '/ / { print $3 }')
    docker rm $(docker ps -qa --no-trunc --filter "status=exited")
}

docker-command() {
    docker-compose exec php /bin/bash -c "./command_dev $1 $2"
}
