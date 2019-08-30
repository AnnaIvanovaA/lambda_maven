#!/bin/bash

set -u
set -e
set -o pipefail

# exports
export AWS_PROFILE=dev

# constants
readonly CLEAR='\033[0m'
readonly HIGHLIGHT='\033[1;33m'
readonly VERSION=0.2.1
readonly PHP_VERSION=7.1.23
readonly SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
readonly ORIGINAL_ARGUMENTS=$@

# settings
QUIET_MODE=false
VERBOSE_MODE=false
RESET_DATABASE=true
YEAH=false
NAH=false
HERO=true
PARAMS=""
COMMAND=""
IGNORE_PHP=false
DOCKER_MOUNT_SETTING=""
NO_VALIDATE=false

function git_status {
    execute "git remote update" || true

    if [[ $(git status | grep -c "branch is behind") -gt "0" ]]; then
        echo 'behind'
    elif [[ $(git status | grep -c "have diverged") -gt "0" ]]; then
        echo 'diverged'
    else
        echo 'up-to-date'
    fi
}

# just silence pushd output
function pushd {
    command pushd "$@" 1> /dev/null
}

# silence popd output too
function popd {
    command popd "$@" 1> /dev/null
}

# erase the last line
function erase {
    printf "\033[1A"  # move cursor one line up
    printf "\033[K"   # delete till end of line
}

# start everything from within the script directory
pushd "${SCRIPT_DIR}"

# color-coded echo command
function cecho {
    local reset='\033[0m'
    local color="\033[0;37m"

    case "$1" in
        black)  color="\033[0;30m";;
        red)    color="\033[1;31m";;
        green)  color="\033[1;32m";;
        yellow) color="\033[1;33m";;
        blue)   color="\033[1;34m";;
        purple) color="\033[1;35m";;
        cyan)   color="\033[1;36m";;
        gray)   color="\033[0;37m";;
        *)      color="\033[0m";;
    esac

    if [[ $QUIET_MODE == false ]]; then
        echo -e "${color}$2${reset}"
    fi
}

function confirm {
    if [[ ${NAH} == true ]]; then
        false
    elif [[ $YEAH == false ]]; then
        local color="\033[1;36m"

        if [[ $VERBOSE_MODE == false ]]; then
            read -p "$(echo -e ${color}[?]${CLEAR} $1 ${color}[Y/n]${CLEAR}) " -r
        else
            read -p "$(echo -e ${color}CONFIRM ${CLEAR}: $1 ${color}[Y/n]${CLEAR}) " -r
        fi

        if [[ $REPLY =~ ^[Nn]$ ]]; then
            false
        else
            true
        fi
    fi
}

function info {
    if [[ $VERBOSE_MODE == false ]]; then
        cecho green "[✓]${CLEAR} $1"
    else
        cecho green "INFO    ${CLEAR}: $1";
    fi
}

function warning {
    if [[ $VERBOSE_MODE == false ]]; then
        cecho yellow "[!]${CLEAR} $1"
    else
        cecho yellow "WARNING ${CLEAR}: $1";
    fi
}

function error {
    if [[ $VERBOSE_MODE == false ]]; then
        cecho red "[!]${CLEAR} $1"
    else
        cecho red "ERROR   ${CLEAR}: $1";
    fi
}

function debug {
    if [[ $VERBOSE_MODE == false ]]; then
        cecho default "[ ] $1"
    else
        cecho default "DEBUG   : $1";
    fi
}

function update {
    if [[ $VERBOSE_MODE == false ]]; then
        erase
    fi

    $1 "$2"
}

# bail out
function die {
    error "$1" 1>&2
    exit 1
}

function execute {
    if [[ $QUIET_MODE == true || $VERBOSE_MODE == false ]]; then
        $1 &>/dev/null
    else
        $1 > >(sed 's/^/OUTPUT  : /') 2> >(sed 's/^/OUTPUT  : /' >&2)
    fi
}

if [[ "$OSTYPE" == "linux-gnu" ]]; then
    readonly CONTEXT=minikube
elif [[ "$OSTYPE" == "darwin"* ]]; then
    for context in docker-for-desktop microk8s; do
	    if [[ $(kubectl config get-contexts --no-headers | grep -q "${context}") -eq "0" ]]; then
            readonly CONTEXT="${context}"
            break
        fi
    done

    if [[ -z "${CONTEXT:-}" ]]; then
        die "Failed to determine kubectl context for darwin"
    fi

else
    die "Unknown operating system $OSTYPE encountered. Exiting."
fi

function command_exists {
    type "$1" &> /dev/null
}

function native_linux_docker_check {
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        if command_exists systemctl && systemctl is-active docker &>/dev/null; then
            DOCKER_MOUNT_SETTING="--set global.dockerMount=true"
        fi
    fi
}

function php_version_check {
    debug "Verifying PHP version"

    if [[ ${IGNORE_PHP} == false && $(php -version | grep -c "PHP 7.1") -eq "0" ]]; then
        update die "You are not running PHP version $PHP_VERSION. Please make sure your path is correct."
    fi

    update info "Finished verifying PHP version ${HIGHLIGHT}[$PHP_VERSION]"
}

function kubectl_check {
    debug "Verifying kubectl installation"

    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        command_exists kubectl || die "Couldn't find kubectl.  Is it installed? - try: 'sudo snap install kubectl --classic'"
    else
        command_exists kubectl || die "Couldn't find kubectl.  Is it installed? - try: 'brew install kubernetes-cli'"
    fi

    update info "Finished verifying kubectl installation ${HIGHLIGHT}[$(which kubectl)]"
}

function minikube_check {
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        debug "Verifying minikube installation"
        command_exists minikube || die "Couldn't find minikube.  Is it installed? - see: https://kubernetes.io/docs/tasks/tools/install-minikube/#install-minikube"
        update info "Finished verifying minikube installation ${HIGHLIGHT}[$(which minikube)]"
    fi
}

function helm_check {
    debug "Verifying helm installation"

    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        command_exists helm || die "Couldn't find helm.  Is it installed? - try: 'sudo snap install helm --classic'"
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        command_exists helm || die "Couldn't find helm.  Is it installed? - try: 'brew install kubernetes-helm'"
    fi

    update info "Finished verifying helm installation ${HIGHLIGHT}[$(which helm)]"
}

function nfs_check {
    debug "Verifying NFS configuration"

    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        EXPORT_STATUS=$(cat /etc/exports | { grep $(minikube ip) || true; } | wc -l)

        if [[ $EXPORT_STATUS == 0 && -z "$DOCKER_MOUNT_SETTING" ]]; then
            die "No nfs exports found, add:\n  $(pwd)/src $(minikube ip)(rw,anonuid=$(id -u),anongid=$(id -g),async,no_subtree_check,no_root_squash)\nto /etc/exports"
        fi
    else
        if command_exists multipass; then
            EXPORT_ENTRY="/Users -alldirs -mapall=501:20 -network 192.168.64.0 -mask 255.255.255.0"
        else
            EXPORT_ENTRY="/Users -alldirs -mapall=501:20 localhost"
        fi

        if ! grep -q "${EXPORT_ENTRY}" "/etc/exports"; then
            die "NFS exports file not configured properly. Add:\n  ${EXPORT_ENTRY}\nto /etc/exports"
        fi

        NFS_STATUS=$(cat /etc/nfs.conf | grep "nfs.server.mount.require_resv_port = 0")

        if [[ $NFS_STATUS == 0 ]]; then
            die "NFS not properly configured, add:\n  nfs.server.mount.require_resv_port = 0\nto /etc/nfs.conf"
        fi
    fi

    update info "Finished verifying NFS configuration"
}

function destroy {
    hero

    if confirm "Destroy your local development cluster?"; then
        debug "Deleting kubernetes releases"
        execute "helm delete local-dev --purge" || true
        execute "helm delete capo --purge" || true
        update info "Finished deleting kubernetes releases"

        debug "Deleting prometheus custom resource definitions"
        execute "kubectl delete crd prometheuses.monitoring.coreos.com" || true
        execute "kubectl delete crd prometheusrules.monitoring.coreos.com" || true
        execute "kubectl delete crd servicemonitors.monitoring.coreos.com" || true
        execute "kubectl delete crd alertmanagers.monitoring.coreos.com" || true
        update info "Finished deleting prometheus custom resource definitions"

        debug "Deleting kubernetes jobs"
        execute "kubectl delete job db-setup" || true
        execute "kubectl delete job dbmigrate" || true
        update info "Finished deleting kubernetes jobs"
    fi

    if [[ "$COMMAND" == 'destroy' ]]; then
        exit 0;
    fi
}

function kubectl_setup {
    debug "Setting up kubectl"

    # make sure kubectl is working and installed
    execute "kubectl cluster-info" || update die "Couldn't access Kubernetes. Do you have ${CONTEXT} installed and running?"

    # force kubectl to use defined context
    execute "kubectl config use-context ${CONTEXT}" || update die "Couldn't switch kubernetes context to your local ${CONTEXT}. Do you have ${CONTEXT} installed?"

    update info "Finished setting up kubectl"
}

function helm_setup {
    debug "Setting up helm"

    execute "kubectl -n kube-system create sa tiller" || true
    update info "Finished Creating Tiller"
    execute "kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller" || true
    update info "Finished Creating Cluster Admin Binding for Tiller"
    execute "helm init --service-account tiller --wait"
    update info "Finished Setting up Helm Service Account"
    execute "helm repo add coreos https://s3-eu-west-1.amazonaws.com/coreos-charts/stable/" || update die "Unable to add coreos repository"

    update info "Finished setting up helm"
}

function minikube_setup {
    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        debug "Signing into minikube docker"
        if ! minikube docker-env; then
            native_linux_docker_check
        fi
        update info "Finished signing into minikube docker"
    fi
}

function microk8s_setup {

    if [[ "$OSTYPE" != "darwin"* ]]; then
        die "microk8s has only been implemented on OSX"
    fi

    debug "Setting up microk8s"

    command_exists multipass || die "Couldn't find multipass.  Is it installed? - try: 'brew cask install multipass'"

    if pgrep -q 'com.docker.hyperkit'; then
        warning "Docker for desktop is still running. You probably don't want it running alongside microk8s."
    fi

    if ! multipass info microk8s &>/dev/null; then
        debug "Deploying microk8s VM"

        # Launch VM and wait a bit for it to be ready to receive commands
        execute "multipass launch --name microk8s --cpus 3 --mem 4G --disk 40G"
        sleep 30s

        # Force configure DNS resolution to work properly. Otherwise if Host is running something like
        # resolvconfd, things don't work since it copies host /etc/resolv.conf into the VM.
        execute "multipass exec microk8s -- sudo rm /etc/resolv.conf"
        multipass exec microk8s -- sudo sh -c 'echo "nameserver 1.1.1.1" > /etc/resolv.conf'
        execute "multipass exec microk8s -- sudo snap install microk8s --classic --channel=1.13/stable"
        execute "multipass exec microk8s -- sudo apt -y -o Dpkg::Progress-Fancy=0 install nfs-common"
        execute "multipass exec microk8s -- sudo apt -y -o Dpkg::Progress-Fancy=0 autoremove"
        execute "multipass exec microk8s -- sudo iptables -P FORWARD ACCEPT"
    fi

    if ! kubectl config get-contexts --no-headers | grep -q microk8s; then
        debug "Configuring host kubectl to use microk8s"
        execute "mkdir -p ~/.kube"
        pushd ~/.kube
        if [[ -e "config" ]]; then
            mv config config.bak
        fi

        execute "multipass exec microk8s -- /snap/bin/microk8s.config > config"
        popd
    fi

    if ! multipass exec microk8s -- grep -q "tcp://" /var/snap/microk8s/current/args/dockerd; then
        debug "Exposing microk8s docker port"
        multipass exec microk8s -- sudo sh -c 'echo "-H tcp://0.0.0.0:2375" >> /var/snap/microk8s/current/args/dockerd'
        execute "multipass exec microk8s -- sudo systemctl restart snap.microk8s.daemon-docker.service"
    fi

    # Enable microk8s addons
    execute "multipass exec microk8s -- sudo /snap/bin/microk8s.enable dns ingress storage"

    if ! multipass exec microk8s -- grep -q 'allow-privileged' /var/snap/microk8s/current/args/kube-apiserver; then
        debug "Allowing privileged kube-apiserver"
        multipass exec microk8s -- sudo sh -c 'echo --allow-privileged >> /var/snap/microk8s/current/args/kube-apiserver'
        execute "multipass exec microk8s -- sudo systemctl restart snap.microk8s.daemon-apiserver.service"
    fi

    microk8s_ip=$(multipass info microk8s | awk '/IPv4:/{print $2}')
    update info "Verified microk8s running ${HIGHLIGHT}[${microk8s_ip}]"
    docker_host="tcp://${microk8s_ip}:2375"
    if [[ "${SHELL}" =~ "bash" ]]; then
        if ! grep -q "DOCKER_HOST" ~/.bashrc ; then
            debug "Adding DOCKER_HOST=${docker_host} to ~/.bashrc"
            execute "export DOCKER_HOST=${docker_host} >> ~/.bashrc"
        fi
    else
        warning "Please add 'export DOCKER_HOST=${docker_host}' to your shell config file"
    fi
}

function nfs_setup {
    debug "Identifying NFS mount address"

    if [[ "$OSTYPE" == "linux-gnu" ]]; then
        IP=$(minikube ip | grep -oE "[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.[[:digit:]]{1,3}\.")1;
    else
        if command_exists multipass; then
            IP="192.168.64.1"
        else
            IP="192.168.65.2"
        fi
    fi

    update info "Finished identifying NFS mount address ${HIGHLIGHT}[${IP}]"
}

function aws_login {
    debug "Logging into Amazon Elastic Container Registry"

    AWS_STATUS=$(eval $(aws ecr get-login --region=us-west-2 --no-include-email --profile=dev) 2>/dev/null)

    if [[ $AWS_STATUS != "Login Succeeded" ]]; then
        update die "Couldn't log into ecr (have you configured and set your AWS_PROFILE?)"
    fi

    update info "Finished logging into Amazon Elastic Container Registry"
}

function version {
    cecho default "Fastlane v$VERSION"
}

function logo {
    cecho purple "           ▄▄▄▄▄▄▄▄▄           "
    cecho purple "        ▄█████████████▄        "
    cecho purple "█████  █████████████████  █████"
    cecho purple "▐████▌ ▀███▄       ▄███▀ ▐████▌"
    cecho purple " █████▄  ▀███▄   ▄███▀  ▄█████ "
    cecho purple " ▐██▀███▄  ▀███▄███▀  ▄███▀██▌ "
    cecho purple "  ███▄▀███▄  ▀███▀  ▄███▀▄███  "
    cecho purple "  ▐█▄▀█▄▀███ ▄ ▀ ▄ ███▀▄█▀▄█▌  "
    cecho purple "   ███▄▀█▄██ ██▄██ ██▄█▀▄███   "
    cecho purple "    ▀███▄▀██ █████ ██▀▄███▀    "
    cecho purple "   █▄ ▀█████ █████ █████▀ ▄█   "
    cecho purple "   ███        ███        ███   "
    cecho purple "   ███▄    ▄█ ███ █▄    ▄███   "
    cecho purple "   █████ ▄███ ███ ███▄ █████   "
    cecho purple "   █████ ████ ███ ████ █████   "
    cecho purple "   █████ ████ ███ ████ █████   "
    cecho purple "   █████ ████ ███ ████ █████   "
    cecho purple "   █████ ████▄▄▄▄▄████ █████   "
    cecho purple "    ▀███ █████████████ ███▀    "
    cecho purple "      ▀█ ███ ▄▄▄▄▄ ███ █▀      "
    cecho purple "         ▀█▌▐█████▌▐█▀         "
    cecho purple "            ███████            "
}

function hero {
    cecho purple "  ______        _   _                   "
    cecho purple " |  ____|      | | | |                  "
    cecho purple " | |__ __ _ ___| |_| | __ _ _ __   ___  "
    cecho purple " |  __/ _\` / __| __| |/ _\` | '_ \ / _ \\ "
    cecho purple " | | | (_| \\__ \\ |_| | (_| | | | |  __/ "
    cecho purple " |_|  \\__,_|___/\\__|_|\\__,_|_| |_|\\___| "
    cecho purple "                                        "
}

function selfupdate {
    pushd "${SCRIPT_DIR}"

    if [[ $(git_status | grep -c "up-to-date") -eq "0" ]]; then
        if confirm "Fastlane is out of date, would you like to attempt to update it?"; then
            debug "Updating Fastlane"
            execute "git pull" || update die "An unexpected error was encountered while updating Fastlane"
            update info "Finished updating Fastlane"

            exec ./fastlane.sh "$ORIGINAL_ARGUMENTS" --heroless
        fi
    fi

    popd
}

function aalias {
    echo -e ""
    echo -e "# add the following to your ~/.bash_profile to execute fastlane from anywhere"
    echo -e ""
    echo -e "function fastlane() {"
    echo -e "    ${SCRIPT_DIR}/fastlane.sh \"\$@\""
    echo -e "}"
}

function usage {
    local command='help'

    if [[ -n ${1+x} ]]; then
        command="$1"
    fi

    hero
    version
    cecho default ""
    cecho yellow "Usage:"

    if [[ "$command" == "help" ]]; then
        cecho default "  ${command} [command] [options]"
    else
        cecho default "  ${command} [options]"
    fi

    cecho default ""
    cecho yellow "Options:"
    cecho green "  -h, --help         ${CLEAR}Display this help message"

    if [[ "$command" != "alias" ]]; then
        cecho green "  -q, --quiet        ${CLEAR}Do not output any log messages"
        cecho green "  -v, --verbose      ${CLEAR}Increase the verbosity of the log messages"
    fi

    if [[ "$command" == "install" ]]; then
        cecho green "  -p                 ${CLEAR}Preserve the database"
        cecho green "  -r, --reset        ${CLEAR}Delete everything before installing"
        cecho green "  -y, --yeah         ${CLEAR}Do not ask any interactive questions"
        cecho green "  -n, --nope         ${CLEAR}Do not ask any interactive questions, takes precedence over -y"
        cecho green "      --no-validate  ${CLEAR}Skip dependency check"
        cecho green "      --ignore-php   ${CLEAR}Skip PHP version check"
    elif [[ "$command" == "destroy" ]]; then
        cecho green "  -y, --yeah         ${CLEAR}Do not ask any interactive questions"
        cecho green "  -n, --nope         ${CLEAR}Do not ask any interactive questions, takes precedence over -y"
    elif [[ "$command" != "alias" ]]; then
        cecho green "      --version      ${CLEAR}Display this application version"
    fi

    if [[ "$command" == 'help' ]]; then
        cecho default ""
        cecho yellow "Available commands:"
        cecho green "  alias            ${CLEAR}Output shell script suitable for eval"
        cecho green "  destroy          ${CLEAR}Delete everything"
        cecho green "  help             ${CLEAR}Displays help for a command"
        cecho green "  install          ${CLEAR}Install the local development environment ${HIGHLIGHT}(default)"
        cecho green "  microk8s         ${CLEAR}Install and configure microk8s for OSX"
    fi
}

if [[ -n ${1+x} ]]; then
    case "$1" in
        help)
            COMMAND='help'

            if [[ -n ${2+x} ]]; then
                if [[ "$2" != "install" && "$2" != "destroy" && "$2" != "help" && "$2" != "alias" ]]; then
                    usage "help"
                else
                    usage "$2"
                fi
            else
                usage "help"
            fi

            exit 0
            ;;
        install)
            COMMAND='install'
            shift
            ;;
        destroy)
            COMMAND='destroy'
            destroy
            shift
            ;;
        alias)
            COMMAND='alias'
            aalias
            shift
            ;;
        transform)
            logo
            exit 0
            ;;
        microk8s)
            COMMAND='microk8s'
            shift
            ;;
    esac
fi

# parse options
while (( "$#" )); do
    case "$1" in
        -h|--help)          usage; exit 0;;
        -v|--verbose)       VERBOSE_MODE=true; shift;;
        --version)          version; exit 0;;
        -q|--quiet)         QUIET_MODE=true; shift;;
        -p)
            if [[ "$CONTEXT" == 'install' ]]; then
                RESET_DATABASE=false
            fi

            shift
            ;;
        -r|--reset)
            if [[ "$CONTEXT" == 'install' ]]; then
                destroy
            fi

            shift
            ;;
        -y|--yeah|--yes)    YEAH=true; shift;;
        -n|--nope|--no)     NAH=true; shift;;
        --no-validate)      NO_VALIDATE=true; shift;;
        --heroless)         HERO=false; shift;;
        --ignore-php)       IGNORE_PHP=true; shift;;
        --)                 shift; break;;
        -*|--*=)            cecho red "Invalid Option: $1\n" >&2; usage; exit 1;;
        *)                  PARAMS="$PARAMS $1"; shift;;
    esac
done

# do not pass go, do not collect $200
if [[ "$COMMAND" == 'alias' ]]; then
    exit 0
fi

# LOGOS
if [[ $HERO == true ]]; then
    hero
fi

selfupdate

if [[ "${COMMAND}" == "microk8s" ]]; then
    microk8s_setup
    exit $?
fi

# deps check
native_linux_docker_check
if [[ $NO_VALIDATE == false ]]; then
    php_version_check
    kubectl_check
    minikube_check
    helm_check
    nfs_check
fi

# basic setups
kubectl_setup
helm_setup
minikube_setup
nfs_setup

aws_login

# cecho green "Setting kubectl bash completion docker for desktop"
# source <(kubectl completion bash)

# clone repos
pushd ".."

info "Starting deployment of Automox local development environment"

# rabbitmq
if [[ ! -d "rabbitmq" ]]; then
    debug "Cloning down rabbitmq repository"
    execute "git clone git@github.com:PatchSimple/rabbitmq.git" || update die "There was an unexpected error cloning down the rabbitmq repository"
    update info "Finished cloning down rabbitmq repository ${HIGHLIGHT}[git@github.com:PatchSimple/rabbitmq.git]"
fi

pushd "rabbitmq"

RABBIT_DOCKER=$(docker images automox/local/*mq -q | wc -l)

REBUILD_RABBIT=false

if [[ $(git_status | grep -c "up-to-date") -eq "0" ]]; then
    if confirm "Your rabbitmq repository is out of date, would you like to pull down any changes?"; then
        debug "Pulling down changes for the rabbitmq repository"
        execute "git pull" || update die "There was an unexpected error pulling down changes for the rabbitmq repository"
        update info "Finished pulling down changes for the rabbitmq repository"
    fi
fi

if [[ $RABBIT_DOCKER -ge "2" ]]; then
    if confirm "Agentmq and Appmq images already found, would you like to rebuild them?"; then
        REBUILD_RABBIT=true
    fi
else
    REBUILD_RABBIT=true
fi

if [[ $REBUILD_RABBIT == true ]]; then
    debug "Building agentmq and appmq images"
    execute "docker pull 402475032688.dkr.ecr.us-west-2.amazonaws.com/automox/rabbitmq-exporter:latest"
    execute "docker pull 402475032688.dkr.ecr.us-west-2.amazonaws.com/automox/fluentd:latest"
    execute "make agentmq-docker" || update die "There was an unexpected error building the agentmq image"
    execute "make appmq-docker" || update die "There was an unexpected error building the appmq image"
    update info "Finished building agentmq and appmq images"
fi

popd

# php-fpm exporter
debug "Pulling php-fpm exporter image"
execute "docker pull 402475032688.dkr.ecr.us-west-2.amazonaws.com/automox/php-fpm-exporter:latest" || update die "There was an unexpected pulling the php-fpm exporter image"
update info "Finished pulling php-fpm exporter image"

# landmine
if [[ ! -d "landmine" ]]; then
    debug "Cloning down landmine repository"
    execute "git clone git@github.com:PatchSimple/landmine.git" || update die "There was an unexpected error cloning down the landmine repository"
    update info "Finished cloning down landmine repository ${HIGHLIGHT}[git@github.com:PatchSimple/landmine.git]"
fi

# webserver
if [[ ! -d "webserver" ]]; then
    debug "Cloning down webserver repository"
    execute "git clone git@github.com:PatchSimple/webserver.git" || update die "There was an unexpected error cloning down the webserver repository"
    update info "Finished cloning down webserver repository ${HIGHLIGHT}[git@github.com:PatchSimple/webserver.git]"
fi

pushd "webserver"

REBUILD_WEBSERVER=false

if [[ $(git_status | grep -c "up-to-date") -eq "0" ]]; then
    if confirm "Your webserver repository is out of date, would you like to pull down any changes?"; then
        debug "Pulling down changes for the webserver repository"
        execute "git pull" || update die "There was an unexpected error pulling down changes for the webserver repository"
        update info "Finished pulling down changes for the webserver repository"
    fi
fi

# capture src dir for later on NFS
debug "Identifying webserver directory"
readonly WEBSERVER_DIR=$(pwd)
update info "Finished identifying webserver directory ${HIGHLIGHT}[$WEBSERVER_DIR]"

WS_DOCKER=$(docker images automox/local/ws -q | wc -l)

if [[ $WS_DOCKER -gt "0" ]]; then
    if confirm "Webserver image already found, would you like to rebuild it?"; then
        REBUILD_WEBSERVER=true
    fi
else
    REBUILD_WEBSERVER=true
fi

if [[ $REBUILD_WEBSERVER == true ]]; then
    debug "Building webserver image"
    REGHOST=automox/local execute "make docker-k8s-ws -e" || update die "There was an unexpected error building the webserver image"
    update info "Finished building webserver image"
fi

debug "Building website assets"
execute "make web" || update die "There was an unexpected error building website assets"
update info "Finished building website assets"

popd

# api
if [[ ! -d "api" ]]; then
    debug "Cloning down api repository"
    execute "git clone git@github.com:PatchSimple/api.git" || update die "There was an unexpected error cloning down the api repository"
    update info "Finished cloning down api repository ${HIGHLIGHT}[git@github.com:PatchSimple/api.git]"
fi

pushd "api"

REBUILD_API=false

if [[ $(git_status | grep -c "up-to-date") -eq "0" ]]; then
    if confirm "Your api repository is out of date, would you like to pull down any changes?"; then
        debug "Pulling down changes for the api repository"
        execute "git pull" || update die "There was an unexpected error pulling down changes for the api repository"
        update info "Finished pulling down changes for the api repository"
    fi
fi

# capture src dir for later on NFS
debug "Identifying api directory"
readonly API_DIR=$(pwd)
update info "Finished identifying api directory ${HIGHLIGHT}[$API_DIR]"

API_DOCKER=$(docker images automox/local/agent-api -q | wc -l)

if [[ $API_DOCKER -gt "0" ]]; then
    if confirm "API image already found, would you like to rebuild it?"; then
        REBUILD_API=true
    fi
else
    REBUILD_API=true
fi

if [[ $REBUILD_API == true ]]; then
    debug "Building api image"
    REGHOST=automox/local execute "make docker-k8s-api -e" || update die "There was an unexpected error building the api image"
    update info "Finished building api image"
fi

debug "Building api assets"
execute "make web" || update die "There was an unexpected error building api assets"
update info "Finished building api assets"

popd

# migrations
if [[ ! -d "migrations" ]]; then
    debug "Cloning down migrations repository"
    execute "git clone git@github.com:PatchSimple/migrations.git" || update die "There was an unexpected error cloning down the migrations repository"
    update info "Finished cloning down migrations repository ${HIGHLIGHT}[git@github.com:PatchSimple/migrations.git]"
fi

pushd "migrations"

debug "Building dbmigrate image"
REGHOST=automox/local DBHOST=127.0.0.1 DBPORT=5432 execute "make docker-dbmigrate -e" || update die "There was an unexpected error building the dbmigrate image"
update info "Finished building dbmigrate image"

if [[ $RESET_DATABASE == true ]];then
    if confirm "Destroy and re-migrate your local database?"; then
        RESET_DATABASE=true
    else
        RESET_DATABASE=false
    fi
fi

popd

# frontend
if [[ ! -d "frontend" ]]; then
    debug "Cloning down frontend repository"
    execute "git clone git@github.com:PatchSimple/frontend.git" || update die "There was an unexpected error cloning down the frontend repository"
    update info "Finished cloning down frontend repository ${HIGHLIGHT}[git@github.com:PatchSimple/frontend.git]"
fi

pushd "frontend"

debug "Identifying frontend directory"
readonly FRONTEND_DIR=$(pwd)
update info "Finished identifying frontend directory ${HIGHLIGHT}[$FRONTEND_DIR]"

debug "Building frontend image"
REGHOST=automox/local execute "make docker-frontend -e"  || update die "There was an unexpected error building the frontend image"
update info "Finished building frontend image"

debug "Building frontend assets"
execute "make web" || update die "There was an unexpected error building frontend assets"
update info "Finished building frontend assets"

popd

# axpatchservice
if [[ ! -d "axpatchservice" ]]; then
    debug "Cloning down axpatchservice repository"
    execute "git clone git@github.com:PatchSimple/axpatchservice.git" || update die "There was an unexpected error cloning down the axpatchservice repository"
    update info "Finished cloning down axpatchservice repository ${HIGHLIGHT}[git@github.com:PatchSimple/axpatchservice.git]"
fi

pushd axpatchservice

debug "Building axpatchservice assets"
execute "make linux" || update die "There was an unexpected error building axpatchservice assets"
update info "Finished building axpatchservice assets"

AXPATCHSERVICE_DOCKER=$(docker images automox/local/axpatchservice -q | wc -l)

if [[ $AXPATCHSERVICE_DOCKER -gt "0" ]]; then
    if confirm "Axpatchservice image already found, would you like to rebuild it?"; then
        REBUILD_AXPATCHSERVICE=true
    fi
else
    REBUILD_AXPATCHSERVICE=true
fi

if [[ $REBUILD_AXPATCHSERVICE == true ]]; then
    debug "Building axpatchservice image"
    REGHOST=automox/local execute "make docker docker-packager -e" || update die "There was an unexpected error building the axpatchservice image"
    update info "Finished building axpatchservice image"
fi

popd

# charts
if [[ ! -d "charts" ]]; then
    info "Cloning down charts repository from git@github.com:PatchSimple/charts.git"
    git clone git@github.com:PatchSimple/charts.git || update die "There was an unexpected error cloning down the charts repository"
fi

pushd "charts"

if [[ $(git_status | grep -c "up-to-date") -eq "0" ]]; then
    if confirm "Your charts repository is out of date, would you like to pull down any changes?"; then
        debug "Pulling down changes for the charts repository"
        execute "git pull" || update die "There was an unexpected error pulling down changes for the charts repository"
        update info "Finished pulling down changes for the charts repository"
    fi
fi

# helm install
debug "Deploying local-dev chart"

GLOBAL_OS_TYPE="linux"
if [[ "$OSTYPE" == "darwin"* ]]; then
    GLOBAL_OS_TYPE="mac"
fi

execute "helm dep update local-dev"

execute "helm upgrade -f local-dev/values.yaml --install local-dev local-dev/ --set api.enabled=true --set global.os=${GLOBAL_OS_TYPE} ${DOCKER_MOUNT_SETTING} --set global.sourceDir=\"$WEBSERVER_DIR/src\" --set global.frontendSourceDir=\"$FRONTEND_DIR/src/public/console\" --set global.apiSourceDir=\"$API_DIR/src\" --set global.minikubeIp=\"$IP\" --set db-setup.dbReset=$RESET_DATABASE" || update die "Couldn't install local-dev chart.  Please check the outpout. For further info type 'helm ls'"

execute "helm upgrade -f s3/values.yaml --install s3 stable/minio --version 2.4.16"

update debug "Waiting for pods to come up"

# wait for pods and k8s to be ready
sleep 5

NOT_RUNNING=$(kubectl get pods --no-headers=true --field-selector=status.phase!=Running,status.phase=Completed 2>/dev/null | wc -l)

COUNT=0
while [[ $NOT_RUNNING -gt "1" ]]; do
    sleep 1
    NOT_RUNNING=$(kubectl get pods --no-headers=true --field-selector=status.phase!=Running,status.phase=Completed 2>/dev/null | wc -l)
    ((COUNT++))

    if [ $COUNT -eq 60 ];then
        update die "It took too long for the pods to come up. Please check 'kubectl get pods' to see why."
    fi
done

update info "Finished deploying local-dev chart"

if [ $(helm ls | grep capo | wc -l) -lt 1 ]; then
    debug "Deploying minimal monitoring"
    execute "helm dep update capo/" || update die "There was an unexpected error deploying minimal monitoring"

    if [[ "$OSTYPE" == "darwin"* ]]; then
        execute "helm upgrade --install capo capo/ -f capo/values.yaml --namespace=monitoring" || update die "There was an unexpected error deploying minimal monitoring"
    else
        execute "helm upgrade --install capo capo/ -f capo/values.yaml --namespace=monitoring --recreate-pods --wait" || update die "There was an unexpected error deploying minimal monitoring"
    fi

    update info "Finished deploying minimal monitoring"
fi

# run additional setup scripts
popd

pushd "webserver"

debug "Building dynamodb"

if command_exists multipass || [[ -n "$DOCKER_MOUNT_SETTING" ]]; then

    # microk8s has no load balancer. Without a load balancer it is impossible to route to the pods from our hose OS.
    # This prevents the below `make dynamodb-setup` from working from our host OS as it cannot reach the dynamodb
    # instance running inside microk8s. To get around that we temporarily enable port forwarding so that we can
    # route to it properly. We'll also give a hint to the user below that they may want to install and use kubefwd
    # to be able to seamlessly route to microk8s services from their host OS.
    DYNAMODB_POD=$(kubectl get pods | grep ^dynamodb- | awk '{print $1}')
    kubectl port-forward "${DYNAMODB_POD}" 8000:8000 &>/dev/null &
    DYNAMODB_PORTFWD_PID=$!
    trap "kill ${DYNAMODB_PORTFWD_PID}" EXIT
fi

ENV=local DYNAMODB_HOST=127.0.0.1 DYNAMODB_PORT=8000 execute "make dynamodb-setup -e" || update die "There was an unexpected error building dynamodb"
update info "Finished building dynamodb"

info "Automox deployment complete"

if command_exists multipass; then
    warning "microk8s has no load balancer! So you need to manually port-forward to access services from your host."
    warning "Alternatively, consider the fantastic kubefwd: https://github.com/txn2/kubefwd"
fi

cecho default ""
cecho yellow "+----------+-------------------------+"
cecho yellow "| Address  | https://127.0.0.1:4443  |"
cecho yellow "| Username | mark@patchsimple.com    |"
cecho yellow "| Password | markmark                |"
cecho yellow "+----------+-------------------------+"
cecho default ""

exit 0
