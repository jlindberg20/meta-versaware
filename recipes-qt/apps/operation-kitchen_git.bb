DESCRIPTION = "VersaWware Qt Application"
LICENSE = "CLOSED"

DEPENDS = " \
    qtvirtualkeyboard \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
    qtquickcontrols2 \
    i2c-tools \
    libgpiod \
"

RDEPENDS_${PN} = " \
    qtvirtualkeyboard \
    default-qt-envs \
    dbus-session \
    libgpiod \
    i2c-tools \
"

SRCBRANCH = "test"
SRCREV = "02cce0c6c8a7903ccd9208cb551e18166a2bc51f"

# Clone private github repository using ssh
# Requires your public ssh key to be added to your github account
# QT_GIT = "git://git@github.com/jlindberg20/OperationKitchen.git;protocol=ssh"

# Use a local directory
#   $ mkdir ~/versaware
#   $ cd ~/versaware
#   $ git clone git@github.com:jlindberg20/OperationKitchen.git
QT_GIT = "git:///home/${USER}/versaware/OperationKitchen;protocol=file"

SRC_URI = " \
    ${QT_GIT};branch=${SRCBRANCH}; \
"

S = "${WORKDIR}/git/VersaWareProject"
PV = "vw-${SRCPV}"

inherit qt6-qmake

FILES_${PN} = "/opt/VersaWareProject/bin/VersaWareProject"
